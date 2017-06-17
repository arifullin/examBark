package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Notification;
import ru.kpfu.itis.model.User;

import java.time.LocalDate;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    int countByUsersContainingAndDeadlineAfter(User user, LocalDate localDateTime);
}
