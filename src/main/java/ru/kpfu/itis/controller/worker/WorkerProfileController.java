package ru.kpfu.itis.controller.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.EducationType;
import ru.kpfu.itis.model.enums.Sex;
import ru.kpfu.itis.model.enums.SocialStatus;
import ru.kpfu.itis.model.enums.UserRole;
import ru.kpfu.itis.model.forms.WorkerProfileForm;
import ru.kpfu.itis.service.*;
import ru.kpfu.itis.service.impl.StGroupServiceImpl;
import ru.kpfu.itis.service.impl.UserServiceImpl;
import ru.kpfu.itis.utils.UploadUtils;

import java.security.Principal;
import java.util.Objects;

import static ru.kpfu.itis.service.impl.CertificateOrderServiceImpl.DEFAULT_WORKER;

/**
 * Created by vladislav on 11.05.17.
 */
@Controller
@RequestMapping("/worker/profile")
public class WorkerProfileController {

    private static final Logger logger = LoggerFactory.getLogger(WorkerProfileController.class);


    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private StorageService imagesStorageService;

    @Autowired
    private UserSearchService userSearchService;

    @Autowired
    private StGroupServiceImpl studentGroupServiceImpl;

    @Autowired
    private StudentGroupService studentGroupService;

    @Autowired
    private CertificateOrderService certificateOrderService;

    @RequestMapping("")
    @PreAuthorize(("hasRole('WORKER')"))
    public String loadProfile(ModelMap model, Principal principal) {

        populate(model, principal);

        return "profile/worker_lk";
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public String showEditProfile(Model model, Principal principal) {
        userService.findByEmail(principal.getName()).ifPresent(u -> {
            model.addAttribute("user", u);

        });
        return "worker/editProfile";
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public String editProfileSubmit(
            @ModelAttribute("editForm") WorkerProfileForm form,
            BindingResult bindingResult, // form binding errors
            RedirectAttributes redirectAttributes,
            Principal principal, @RequestParam("position") String position
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("edit_status", "Ошибка обновления профиля");
            return "redirect:/worker/profile"; // return the same page
        }
        userService.findByEmail(principal.getName()).ifPresent(user -> {
            user.setAboutMe(position);
            if (!form.getAvatar().isEmpty()) {

                String filename = imagesStorageService.store(UploadUtils.USERS_PATH, form.getAvatar()); // store

                user.setAvatarUrl(filename);
            }
            userService.updateProfile(user, form);
            redirectAttributes.addFlashAttribute("edit_status", "Profile has been successfully edited");
        });
        return "redirect:/worker/profile";
    }

    @RequestMapping(value = "/create_user", method = RequestMethod.POST)
    public String saveUser(
            @ModelAttribute("userForm") User user,
            @RequestParam(required = false, name = "course") int course,
            @RequestParam(name = "group", required = false) int group,
            @RequestParam(name = "pay", required = false) String pay,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "trainingForm", required = false) String trainingForm,
            RedirectAttributes redirectAttributes
    ) {

        if (user.getUserRole() == UserRole.ROLE_STUDENT) {
            StudentGroup studentGroup = studentGroupServiceImpl.findByCourseAndGroup(course, group);
            user.setGroup(studentGroup);
            user.setSocialStatus(SocialStatus.valueOf(status));
            user.setEducation(EducationType.valueOf(trainingForm));
        }
        if (userService.getByEmail(user.getEmail()) == null) {
            user.setSex(Sex.valueOf(gender));
            user.setUserRole(UserRole.valueOf(pay));
            userService.add(user);
        } else {
            redirectAttributes.addFlashAttribute("userAlreadyExist", "Пользователь с данной почтой уже существует");
        }
        return "redirect:/worker/profile";
    }

    @PostMapping("/edit_student")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editStudentProfile(
            @RequestParam long userId,
            @RequestParam String education,
            @RequestParam String socialStatus,
            @RequestParam int course,
            @RequestParam int group
    ) {
        logger.warn("Updating user...");
        ModelAndView mav = new ModelAndView("profile/modals/search_edit_student_item");
        User user = userService.updateProfileByWorker(userId, education, socialStatus, course, group);
        mav.addObject("u", user);
        mav.addObject("courses", userSearchService.findAllCoursesValue());
        mav.addObject("groups", studentGroupServiceImpl.getAll());
        mav.addObject("message", "Успешно!");
        return mav;
    }

    @ResponseBody
    @PostMapping("/certificate_done")
    public int certificateDone(@RequestParam long userId) {
        logger.warn("Certificate is done...creating notification");
        //Пока за справки отвечает worker@mail.ru
        certificateOrderService.done(userId);

        return 200;
    }

    private void populate(ModelMap model, Principal principal) {
        userService.findByEmail(principal.getName()).ifPresent(user -> { // find user
//            model.addAttribute("user", user);

            if (Objects.equals(user.getEmail(), DEFAULT_WORKER)) {
                model.addAttribute("certificates", certificateOrderService.getAll());
            }
            model.addAttribute("positionToShow", user.getAboutMe());
            model.addAttribute("mailToShow", user.getEmail());
            model.addAttribute("telNumber", user.getPhone());
            model.addAttribute("notifications", user.getNotifications());
            model.addAttribute("projects", projectService.findByUser(user));
            model.addAttribute("communities", communityService.findByUser(user));
            model.addAttribute("courses", userSearchService.findAllCoursesValue());
            model.addAttribute("editForm", new WorkerProfileForm(user.getPhone(), user.getName(), user.getSurname()));

            User user1 = new User();
            model.addAttribute("userForm", user1);
        });
    }
}
