package ru.kpfu.itis.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.forms.TeacherProfileForm;
import ru.kpfu.itis.service.*;
import ru.kpfu.itis.utils.UploadUtils;

import java.security.Principal;

/**
 * Created by vladislav on 11.05.17.
 */
@Controller
@RequestMapping("/teacher/profile")
public class TeacherProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private StorageService imagesStorageService;

    @Autowired
    private UserSearchService userSearchService;

    @RequestMapping("")
    @PreAuthorize("hasRole('TEACHER')")
    public String loadProfile(ModelMap model, Principal principal) {
        populate(model,principal);
        return "profile/teacher_lk";
    }

    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    public String editProfileSubmit(
            @ModelAttribute("editForm") TeacherProfileForm form,
            BindingResult bindingResult, // form binding errors
            RedirectAttributes redirectAttributes,
            Principal principal,@RequestParam("position") String position,ModelMap model
    ) {
        populate(model,principal);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("edit_status", "Ошибка");
            return "redirect:/teacher/profile";// return the same page
        }
        userService.findByEmail(principal.getName()).ifPresent(user -> {
            if (!form.getAvatar().isEmpty()) {

                String filename = imagesStorageService.store(UploadUtils.USERS_PATH, form.getAvatar()); // store

                user.setAvatarUrl(filename);
            }
            user.setAboutMe(position);
            userService.updateProfile(user, form);
        });
        redirectAttributes.addFlashAttribute("edit_status", "Profile has been successfully edited");
        return "redirect:/teacher/profile";
    }

    private void populate(ModelMap model,Principal principal){
        userService.findByEmail(principal.getName()).ifPresent(user -> { // find user
//            model.addAttribute("user", user);
            User user1 = new User();
            model.addAttribute("userForm",user1);
            model.addAttribute("lesson",user.getAboutMe());
            model.addAttribute("mailToShow",user.getEmail());
            model.addAttribute("telNumber",user.getPhone());
            model.addAttribute("notifications", user.getNotifications());
            model.addAttribute("projects", projectService.findByUser(user));
            model.addAttribute("communities", communityService.findByUser(user));
            model.addAttribute("editForm", new TeacherProfileForm(user.getPhone(), user.getName(), user.getSurname()));
            model.addAttribute("courses", userSearchService.findAllCoursesValue());
        });
    }
}
