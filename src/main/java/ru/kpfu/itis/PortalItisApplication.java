package ru.kpfu.itis;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class PortalItisApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PortalItisApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
