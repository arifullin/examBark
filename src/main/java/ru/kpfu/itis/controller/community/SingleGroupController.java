package ru.kpfu.itis.controller.community;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.controller.project.MainProjectController;
import ru.kpfu.itis.model.Community;
import ru.kpfu.itis.model.Event;
import ru.kpfu.itis.model.News;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.CommunityType;
import ru.kpfu.itis.model.enums.Privileges;
import ru.kpfu.itis.model.forms.EditProjectForm;
import ru.kpfu.itis.model.forms.NewsForm;
import ru.kpfu.itis.model.forms.ProjectEventForm;
import ru.kpfu.itis.service.CommunityService;
import ru.kpfu.itis.service.NewsService;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.exception.ProjectAccessDeniedException;

import javax.validation.Valid;
import java.util.ArrayList;

//import ru.kpfu.itis.model.Post;

/**
 * Class to work with one project (its page)
 */
@Controller
@RequestMapping("/group")
public class SingleGroupController {

    @Autowired
    private CommunityService communityService;
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public String loadCommunityPage(@PathVariable("id") Long id, ModelMap modelMap) {
        Community community = communityService.getById(id);
        modelMap.addAttribute("community", community);
        modelMap.addAttribute("postForm", new News());
        EditProjectForm editProjectForm = new EditProjectForm();
        editProjectForm.setName(community.getName());
        editProjectForm.setDescription(community.getDescription());
        modelMap.addAttribute("projectForm", editProjectForm);
        modelMap.addAttribute("user_counter", community.getUsersCount());
        User currentUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        modelMap.addAttribute("isSubscribed", communityService.isSubscribed(id, currentUser));
        try {
            Privileges privileges = communityService.checkPrivileges(community);
            modelMap.addAttribute("privilege", privileges);
        } catch (ProjectAccessDeniedException e) {
            if (community.getCommunityType().equals(CommunityType.PUBLIC)) {
                modelMap.addAttribute("privilege", Privileges.USER);
                return "groups/group_edit";
            } else {
                LoggerFactory.getLogger(MainProjectController.class).error("ACCESS DENIED" + 1);
//                return "redirect:/main/403";}
                return "groups/group_edit";
            }
        }
        return "groups/group_edit";
    }

    @RequestMapping(value = "/{id}/subscribe")
    @PreAuthorize("isAuthenticated()")
    public String subscribe(@PathVariable("id") Long id) {
        userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).ifPresent(u -> {
            communityService.subscribeUser(id, u);
        });
        return "redirect:/group/" + id;
    }

    @RequestMapping(value = "/{id}/unsubscribe")
    @PreAuthorize("isAuthenticated()")
    public String unSubscribe(@PathVariable("id") Long id) {
        userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).ifPresent(u -> {
            communityService.unSubscribeUser(id, u);
        });
        return "redirect:/group/" + id;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/new_news", method = RequestMethod.POST)
    public ModelAndView createNews(@PathVariable("id") Long id,
                                   @ModelAttribute @Valid NewsForm post,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ModelAndView();
        try {
            ArrayList<News> news = new ArrayList<>();
            News news1 = newsService.saveForm(post);
//            communityService.createPost(id, news1);
            news.add(news1);
            ModelAndView modelAndView = new ModelAndView("news/newsItem");
            modelAndView.addObject("news", news);
            return modelAndView;
        } catch (ProjectAccessDeniedException e) {
            LoggerFactory.getLogger(MainProjectController.class).error("ACCESS DENIED" + 2);
            return new ModelAndView();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/new_event", method = RequestMethod.POST)
    public ModelAndView createEvent(@PathVariable Long id, @ModelAttribute @Valid ProjectEventForm event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ModelAndView();
        try {
            Event createdEvent = communityService.createEvent(id, event);
            ModelAndView modelAndView = new ModelAndView("projects/eventItem");
            modelAndView.addObject("event", createdEvent);
            return modelAndView;
        } catch (ProjectAccessDeniedException e) {
            LoggerFactory.getLogger(MainProjectController.class).error("ACCESS DENIED" + 4);
            return new ModelAndView();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateProject(@PathVariable("id") Long id,
                                @ModelAttribute @Autowired EditProjectForm projectForm,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "projects/mainPage";
        try {
            communityService.update(id, projectForm);
        } catch (ProjectAccessDeniedException e) {
            LoggerFactory.getLogger(MainProjectController.class).error("ACCESS DENIED" + 3);
            return "my403";
        }
        return "redirect:/group/" + id;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}/delete")
    public String deleteCommunity(@PathVariable Long id) {
        try {
            communityService.delete(id);
        } catch (ProjectAccessDeniedException e) {
            return "redirect:/group/" + id;
        }
        return "redirect:/groups";
    }

}
