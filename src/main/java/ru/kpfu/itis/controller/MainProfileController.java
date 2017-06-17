package ru.kpfu.itis.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.utils.UploadUtils;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by vladislav on 12.05.17.
 */
@Controller
public class MainProfileController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/profile")
    public String loadProfile() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) return "redirect:/student/profile";
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) return "redirect:/teacher/profile";
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_WORKER"))) return "redirect:/worker/profile";
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/uploads/user/avatar/{filename:.+}") // NOTE: use regexp, 'cause spring truncates all after '.'
    public byte[] getImage(@PathVariable("filename") String filename) {

        Resource resource = storageService.loadAsResource(UploadUtils.USERS_PATH, filename);

        try {
            return IOUtils.toByteArray(resource.getInputStream());
        } catch (IOException | RuntimeException e) {
            return new byte[0];
        }
    }
}
