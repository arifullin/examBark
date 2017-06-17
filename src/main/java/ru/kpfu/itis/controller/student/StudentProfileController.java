package ru.kpfu.itis.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.Document;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.EducationType;
import ru.kpfu.itis.model.enums.SocialStatus;
import ru.kpfu.itis.model.forms.StudentProfileForm;
import ru.kpfu.itis.repository.DocumentRepository;
import ru.kpfu.itis.service.*;
import ru.kpfu.itis.service.impl.StGroupServiceImpl;
import ru.kpfu.itis.utils.UploadUtils;

import java.security.Principal;

/**
 * Created by vladislav on 11.05.17.
 */
@Controller

public class StudentProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private CertificateOrderService certificateOrderService;

    @Autowired
    private StGroupServiceImpl stGroupService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private EventService eventService;

    @RequestMapping("/student/profile")
    @PreAuthorize("hasRole('STUDENT')")
    public String loadProfile(ModelMap model, Principal principal) {
        populate(model, principal);
        return "profile/student_lk";
    }

    @PostMapping("/student/profile/confirm")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public int confirmNotification(@RequestParam long notificationId, @RequestParam long userId) {
        notificationService.confirm(notificationId, userId);
        return 200;
    }

    @PostMapping("/student/profile")
    @PreAuthorize("isAuthenticated()")
    public String editProfileSubmit(
            @ModelAttribute("editForm") StudentProfileForm form,
            BindingResult bindingResult, // form binding errors
            RedirectAttributes redirectAttributes, ModelMap model,
            Principal principal,
            @RequestParam("course") int course,
            @RequestParam("group") int group,
            @RequestParam("pay") String pay,
            @RequestParam("status") String status
    ) {
        populate(model, principal);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("edit_status", "Ошибка обновления профиля");
            return "redirect:/student/profile";// return the same page
        }
        userService.findByEmail(principal.getName()).ifPresent(user -> {
            if (!form.getAvatar().isEmpty()) {
                String filename = storageService.store(UploadUtils.USERS_PATH, form.getAvatar()); // store
                user.setAvatarUrl(filename);
            }

            if (!form.getDocument().isEmpty()) {
                String filename = storageService.store(UploadUtils.DOCUMENTS_PATH, form.getDocument()); // store
                Document document = new Document();
                document.setName(form.getDocument().getOriginalFilename());
                document.setUrl(filename);
                document.setUser(user);
                documentRepository.save(document);
            }

            user.setSocialStatus(SocialStatus.valueOf(status));
            user.setEducation(EducationType.valueOf(pay));
            StudentGroup studentGroup = stGroupService.findByCourseAndGroup(course, group);
            user.setGroup(studentGroup);
            userService.updateProfile(user, form);
        });
        redirectAttributes.addFlashAttribute("edit_status", "Profile has been successfully edited");
        return "redirect:/student/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/student/profile/order", method = RequestMethod.POST)
    public String orderCertificate(@RequestParam(name = "count") int count, Principal principal) {

        User user = userService.findByEmail(principal.getName()).get();

        certificateOrderService.orderCertificate(count, user.getId());

        return "redirect:/student/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/student/delete_document/{id}", method = RequestMethod.GET)
    public String deleteDocument(@PathVariable Long id, Principal principal) {

        System.out.println("delete : " + id);

        documentRepository.delete(id);

        return "redirect:/student/profile";
    }


    private void populate(ModelMap model, Principal principal) {
        userService.findByEmail(principal.getName()).ifPresent(user -> { // find user
//            model.addAttribute("user", user);
            model.addAttribute("events", eventService.findAllByUserId(user.getId()));
            model.addAttribute("userId", user.getId());
            model.addAttribute("events", user.getSubscribedEvents());
            model.addAttribute("groupToShowEdit", user.getGroup().getNum());
            model.addAttribute("courseToShowEdit", user.getGroup().getCourse());
            model.addAttribute("telNumberToShowEdit", user.getPhone());
            model.addAttribute("payToShowEdit", user.getEducation().toString());
            model.addAttribute("statusToShowEdit", user.getSocialStatus().toString());
            model.addAttribute("projects", projectService.findByUser(user));
            model.addAttribute("communities", communityService.findByUser(user));
            model.addAttribute("notifications", user.getNotifications());
            model.addAttribute("editForm", new StudentProfileForm(user.getPhone()));
        });
    }
}