package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.Project;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.forms.ProjectForm;
import ru.kpfu.itis.service.ProjectService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.service.ProjectService;
import ru.kpfu.itis.service.UserService;


/**
 * Created by Daniel Shchepetov on 13.05.2017.
 */
@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;

    private static final int DEFAULT_NUM = 1;

    private int currentPage;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loadMainPage(ModelMap modelMap) {

        currentPage = 0;
        modelMap.addAttribute("projectForm", new ProjectForm());
        modelMap.addAttribute("projects", projectService.getPosts(currentPage, DEFAULT_NUM));

        currentPage++;

        return "projects/projects";
    }

    @ResponseBody
    @RequestMapping(value = "/load_more", method = RequestMethod.GET)
    public ModelAndView loadMoreProject() {

        ModelAndView modelAndView = new ModelAndView("projectsItem");
        modelAndView.addObject("projects", projectService.getPosts(currentPage, DEFAULT_NUM));

        currentPage++;

        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createProject(RedirectAttributes redirectAttributes, @Valid @ModelAttribute ProjectForm projectForm, BindingResult bindingResult, Principal principal, ModelAndView modelAndView,ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            //TODO: пофиксить баг с созданием пустого проекта
            return "redirect:/projects";
        }
        if (projectService.findByName(projectForm.getName()) != null) {
            redirectAttributes.addFlashAttribute("message", "Проект с таким названием уже существует");
            return "redirect:/projects";
        }
        User user = userService.findByEmail(principal.getName()).get();
        projectService.createProject(projectForm, user);
//TODO: в одном из следующих спринтов сделать редирект на страницу проекта
        return "redirect:/projects";
    }

}
