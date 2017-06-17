package ru.kpfu.itis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.service.NotificationService;
import ru.kpfu.itis.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vladislav on 14.05.17.
 */
@Aspect
@Component
public class UserNotificationCounter {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest servletRequest;

    @Pointcut("execution(* ru.kpfu.itis.controller..*(..))")
    public void thing() {
    }

    @Before("thing()")
    public void doStuffBeforeThing(JoinPoint joinPoint) {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)
            return;
        userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).ifPresent(u -> {
            servletRequest.getSession().setAttribute("notifyCount", notificationService.getNotificationsCount(u));
            servletRequest.getSession().setAttribute("user", u);
        });
    }

}
