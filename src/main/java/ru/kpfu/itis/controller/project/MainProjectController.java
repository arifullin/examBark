package ru.kpfu.itis.controller.project;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.model.Event;
import ru.kpfu.itis.model.News;
import ru.kpfu.itis.model.Project;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.CommunityType;
import ru.kpfu.itis.model.enums.Privileges;
import ru.kpfu.itis.model.forms.EditProjectForm;
import ru.kpfu.itis.model.forms.NewsForm;
import ru.kpfu.itis.model.forms.ProjectEventForm;
import ru.kpfu.itis.service.NewsService;
import ru.kpfu.itis.service.ProjectService;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.exception.ProjectAccessDeniedException;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Class to work with one project (its page)
 */
@Controller
@RequestMapping("/project")
public class MainProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String loadProjectPage(@PathVariable("id") Long id, ModelMap modelMap) {
        Project project = projectService.getById(id);
        modelMap.addAttribute("project", project);
        modelMap.addAttribute("postForm", new News());
        EditProjectForm editProjectForm = new EditProjectForm();
        editProjectForm.setName(project.getProjectName());
        editProjectForm.setDescription(project.getDescription());
        modelMap.addAttribute("projectForm", editProjectForm);
        modelMap.addAttribute("user_counter", project.getTotalUserCount());
        User currentUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        modelMap.addAttribute("isSubscribed", projectService.isSubscribed(id, currentUser));
        try {
            Privileges privileges = projectService.checkPrivileges(project);
            modelMap.addAttribute("privilege", privileges);
        } catch (ProjectAccessDeniedException e) {
            if (project.getCommunityType().equals(CommunityType.PUBLIC)) {
                modelMap.addAttribute("privilege", Privileges.USER);
                return "projects/mainPage";
            } else {
                LoggerFactory.getLogger(MainProjectController.class).error("ACCESS DENIED" + 1);
                return "projects/mainPage";
            }
        }
        return "projects/mainPage";
    }

    @RequestMapping(value = "/{id}/subscribe")
    @PreAuthorize("isAuthenticated()")
    public String subscribe(@PathVariable("id") Long id) {
        userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).ifPresent(u -> {
            projectService.subscribeUser(id, u);
        });
        return "redirect:/project/" + id;
    }

    @RequestMapping(value = "/{id}/unsubscribe")
    @PreAuthorize("isAuthenticated()")
    public String unsubscribe(@PathVariable("id") Long id) {
        User currentUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        projectService.deleteUser(id, currentUser);
        return "redirect:/project/" + id;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/new_news", method = RequestMethod.POST)
    public ModelAndView createNews(@PathVariable("id") Long id,
                                   @ModelAttribute @Valid NewsForm post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ModelAndView();
        try {
            ArrayList<News> news = new ArrayList<>();
            News news1 = newsService.saveForm(post);
            projectService.createPost(id, news1);
            news.add(news1);
            ModelAndView modelAndView = new ModelAndView("news/newsItem");
            modelAndView.addObject("news", news);
            return modelAndView;
        } catch (ProjectAccessDeniedException e) {
            LoggerFactory.getLogger(MainProjectController.class).error("ACCESS DENIED" + 2);
            return new ModelAndView();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public String updateProject(@PathVariable("id") Long id,
                                @ModelAttribute @Autowired EditProjectForm projectForm,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "projects/mainPage";
        try {
            projectService.update(id, projectForm);
        } catch (ProjectAccessDeniedException e) {
            LoggerFactory.getLogger(MainProjectController.class).error("ACCESS DENIED" + 3);
            return "my403";
        }
        return "redirect:/project/" + id;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/new_event", method = RequestMethod.POST)
    public ModelAndView createEvent(@PathVariable Long id, @ModelAttribute @Valid ProjectEventForm event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ModelAndView();
        try {
            Event createdEvent = projectService.createEvent(id, event);
            ModelAndView modelAndView = new ModelAndView("projects/eventItem");
            modelAndView.addObject("event", createdEvent);
            return modelAndView;
        } catch (ProjectAccessDeniedException e) {
            LoggerFactory.getLogger(MainProjectController.class).error("ACCESS DENIED" + 4);
            return new ModelAndView();
        }

    }
}
