package ru.kpfu.itis.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.EducationType;
import ru.kpfu.itis.model.enums.Sex;
import ru.kpfu.itis.model.enums.SocialStatus;
import ru.kpfu.itis.model.enums.UserRole;
import ru.kpfu.itis.service.NewsService;
import ru.kpfu.itis.service.exception.UserNotFoundException;
import ru.kpfu.itis.service.impl.StGroupServiceImpl;
import ru.kpfu.itis.service.impl.UserServiceImpl;
import ru.kpfu.itis.service.mail.MailService;
import ru.kpfu.itis.service.security.MyUserDetailService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by vladislav on 18.04.17.
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {

    private int currentPage = 1;

    private final NewsService newsService;
    private final MailService mailService;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private StGroupServiceImpl stGroupService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public MainController(NewsService newsService, MailService mailService) {
        this.newsService = newsService;
        this.mailService = mailService;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loadMainPage(ModelMap modelMap, Principal principal) {
        if(principal != null){
            return "redirect:/news";
        }
        currentPage = 1;
        modelMap.addAttribute("news", newsService.loadTen(currentPage - 1));
        return "main";
    }


    @ResponseBody
    @RequestMapping(value = "/load_more", method = RequestMethod.GET)
    public ModelAndView loadMoreNews() {
        currentPage++;
        ModelAndView modelAndView = new ModelAndView("main/newsItem");
        modelAndView.addObject("news", newsService.loadTen(currentPage - 1));
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
    public String recoverPassword(@RequestParam(value = "email") String email) {
        try {
            mailService.recoverPassword(email);
        } catch (MessagingException e) {
            return "Возникла ошибка отправки, попробуйте позже";
        } catch (UserNotFoundException e) {
            return "Пользователя с такой почтой не существует";
        }
        return "Пароль отправлен, проверьте почту";
    }


    @RequestMapping(value = "/generate_user", method = RequestMethod.POST)
    public String generateUser(@RequestParam("surname") String surname,
                               @RequestParam("name") String name,
                               @RequestParam(value = "course", required = false) Integer course,
                               @RequestParam(value = "group", required = false) Integer group,
                               @RequestParam("userRole") String userRole, RedirectAttributes redirectAttributes, HttpServletRequest request) {


        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || userRole == null || userRole.isEmpty()) {
            redirectAttributes.addFlashAttribute("ErrorMessageForReg", "Пожалуста заполните все доступные поля");
            return "redirect:/main";
        }

        if (name.length() > 200 || surname.length() > 200) {
            redirectAttributes.addFlashAttribute("ErrorMessageForReg", "Слишком длинное имя или фамилия");
            return "redirect:/main";
        }

        try {
            UserRole.valueOf(userRole);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("ErrorMessageForReg", "Пожалуста заполните все доступные поля");
            return "redirect:/main";
        }
        User user = new User();

        if (UserRole.valueOf(userRole) == UserRole.ROLE_STUDENT) {
            if (course == null || group == null || course == 0 || group == 0) {
                redirectAttributes.addFlashAttribute("ErrorMessageForReg", "Пожалуста заполните все доступные поля");
                return "redirect:/main";
            }
            StudentGroup groupThis = stGroupService.findByCourseAndGroup(course, group);
            if (groupThis == null) {
                redirectAttributes.addFlashAttribute("ErrorMessageForReg", "Такой группы нет");
                return "redirect:/main";
            }
            user.setGroup(groupThis);
        } else if (UserRole.valueOf(userRole) == UserRole.ROLE_TEACHER) {
            user.setAboutMe("Физика");
        } else if (UserRole.valueOf(userRole) == UserRole.ROLE_WORKER) {
            user.setAboutMe("Секретарь");
        }


        user.setName(name);
        user.setSurname(surname);
        user.setUserRole(UserRole.valueOf(userRole));

        user.setPassword("$2a$12$7DsfOtowFQwC2p7LESJ5HeXOnlDPJquES82vW8/hm3a20S1mrGgve");

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setEmail(RandomStringUtils.random(8, characters) + "@mail.ru");
        user.setEducation(EducationType.BUDGET);
        user.setSex(Sex.NONE);
        user.setPatronymic("Отчество");
        user.setPhone("+7-917-999-99-99");
        user.setSocialStatus(SocialStatus.NONE);
        user.setAvatarUrl("default.jpg");

        userService.add(user);
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), myUserDetailService.loadUserByUsername(user.getEmail()).getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/news";
    }

}
