package ru.kpfu.itis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.model.Notification;
import ru.kpfu.itis.model.dto.NotificationDto;
import ru.kpfu.itis.service.NotificationService;

import javax.validation.Valid;

/**
 * Created by Safin Ramil on 08.05.17
 */

@RestController
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping("/notifications/publish")
    public ResponseEntity<String> publishNotification(@Valid @RequestBody NotificationDto dto, BindingResult bindingResult) {

        logger.warn(dto.toString());

        try {
            if (bindingResult.hasErrors()) {
                if (dto.getUsers().isEmpty())
                    return ResponseEntity.status(400).body("Выберите пользователей для рассылки!");

                if (bindingResult.getFieldError("title") != null) {
                    return ResponseEntity.status(400).body("Неправильно введена тема!");
                }

                if (bindingResult.getFieldError("body") != null) {
                    return ResponseEntity.status(400).body("Сообщение не может быть пустым!");
                }

                if (bindingResult.getFieldError("users") != null) {
                    return ResponseEntity.status(400).body("Не выбраны пользователи для уведомления!");
                }

                if (dto.getDeadline()) {
                    if (bindingResult.getFieldError("time1") != null || bindingResult.getFieldError("time2") != null) {
                        return ResponseEntity.status(400).body("Не корректно указано время!");
                    }
                }
            }

            Notification notification = notificationService.save(dto);

            if (notification == null) return ResponseEntity.status(400).body("Can't save");

            return ResponseEntity.ok("Success!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
