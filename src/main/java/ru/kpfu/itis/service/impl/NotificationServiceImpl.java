package ru.kpfu.itis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Notification;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.dto.NotificationDto;
import ru.kpfu.itis.repository.NotificationRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.NotificationService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Notification save(NotificationDto dto) {

        User user = userRepository.findOne(dto.getUserId());

        Notification notification = new Notification(dto.getTitle(), dto.getBody(), null, user);

        if (dto.getDeadline()) {
            try {

                notification.setDeadline(LocalDate.from(LocalDate.parse(dto.getDatetime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

                notification.setTime(LocalTime.from(LocalTime.parse(dto.getTime1() + ":" + dto.getTime2(),
                        DateTimeFormatter.ofPattern("HH:mm"))));

            } catch (DateTimeParseException ignored) {
                logger.error(ignored.getMessage(), ignored);
            }
        }

        notification.setCreated(LocalDateTime.now(ZoneId.of("UTC+3")));

        Set<User> users = new HashSet<>(userRepository.findAllByIdsIn(dto.getUsers()));

        users.forEach(u -> {
            u.getNotifications().add(notification);
            notification.addUser(u);
        });

        return notificationRepository.save(notification);
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return null;
    }

    @Override
    public Optional<List<Notification>> findToUser(Long userId) {
        return null;
    }

    @Override
    public Optional<List<Notification>> findOwnedByAuthor(Long authorId) {
        return null;
    }

    @Override
    public int getNotificationsCount(User user) {
        return notificationRepository.countByUsersContainingAndDeadlineAfter(user, LocalDate.now());
    }

    @Override
    public void confirm(long notificationId, long userId) {
        Notification notification = notificationRepository.findOne(notificationId);
        User user = userRepository.findOne(userId);
        Set<Notification> notifications = user.getNotifications();
        System.out.println(notifications.size());
        notifications.remove(notification);
        user.setNotifications(notifications);
        System.out.println(notifications.size());
        userRepository.save(user);
    }
}
