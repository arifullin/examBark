package ru.kpfu.itis.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.service.ProjectService;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.exception.ProjectAccessDeniedException;

/**
 * Created by Vlad on 22.05.2017.
 */
@Controller
@RequestMapping("/project")
public class EditProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deleteCommunity(@PathVariable Long id) {
        try {
            projectService.delete(id);
        } catch (ProjectAccessDeniedException e) {
            return "redirect:/project/" + id;
        }
        return "redirect:/projects";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/delete_user", method = RequestMethod.POST)
    public void deleteUserFromProject(@PathVariable Long id, @RequestParam("user_id") Long userId) {

        projectService.deleteUser(id, userService.getById(userId));

    }

    @RequestMapping("/test")
    @PreAuthorize("isAuthenticated()")
    public String test() {
        return "groups/group_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/delete_event", method = RequestMethod.POST)
    public void deleteEvent(@PathVariable("id") Long id, @RequestParam("eventId") Long eventId) {
        projectService.deleteEvent(id, eventId);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/delete_news", method = RequestMethod.POST)
    public void deleteNews(@PathVariable("id") Long id, @RequestParam("newsId") Long newsId) {
        projectService.deleteNews(id, newsId);
    }

}
