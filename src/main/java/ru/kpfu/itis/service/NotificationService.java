package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Notification;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.dto.NotificationDto;

import java.util.List;
import java.util.Optional;

/**
 * Created by Safin Ramil on 08.05.17
 * Safin.Ramil@ordotrans.ru
 */

@Service
public interface NotificationService {

    Notification save(NotificationDto dto);

    Optional<Notification> findById(Long id);

    Optional<List<Notification>> findToUser(Long userId);

    Optional<List<Notification>> findOwnedByAuthor(Long authorId);

    int getNotificationsCount(User user);

    void confirm(long notificationId, long userId);
}
