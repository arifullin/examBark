package ru.kpfu.itis.service.mail;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.exception.UserNotFoundException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by vladislav on 05.05.17.
 */
@Component
@PropertySource("classpath:additional.properties")
public class MailServiceImpl implements MailService {

    private final Environment env;
    private final UserRepository userRepository;

    private String FROM;
    private String PASSWORD;
    private final String HOST = "localhost";

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @Autowired
    public MailServiceImpl(Environment env, UserRepository userRepository) {
        this.env = env;
        FROM = env.getRequiredProperty("email.author");
        PASSWORD = env.getRequiredProperty("email.password");
        this.userRepository = userRepository;
    }

    @Override
    public void recoverPassword(String email) throws MessagingException, UserNotFoundException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM, PASSWORD);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM));
        System.out.println(email);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Восстановление пароля");
        String password = generatePassword();
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        message.setText("Новый пароль для входа в систему: " + password);
        Transport.send(message);
    }

    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return RandomStringUtils.random(7, characters);
    }
}
