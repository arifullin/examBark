package ru.kpfu.itis.controller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.forms.CreateCommunityForm;
import ru.kpfu.itis.service.CommunityService;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.impl.CommunityServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping(value = "/groups")
public class GroupsController {

    private CommunityService communityService;

    private static final int DEFAULT_NUM = 1;

    private int currentPage;

    @Autowired
    CommunityServiceImpl communityServiceImpl;

    @Autowired
    private UserService userService;


    @Autowired
    public GroupsController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loadMainPage(ModelMap modelMap) {

        currentPage = 0;

        modelMap.addAttribute("communities", communityService.getGroups(currentPage, DEFAULT_NUM));
        CreateCommunityForm communityForm = new CreateCommunityForm();
        modelMap.addAttribute("communityForm",communityForm);


        currentPage++;

        return "groups/groups";
    }

    @RequestMapping(value = "/create_community", method = RequestMethod.POST)
    public String createCommunity(@ModelAttribute("communityForm") CreateCommunityForm communityForm, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "groups/groups";
        }
        if(communityForm.getName() == null || communityForm.getName().isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Имя не может быть пустым");
            return "redirect:/groups";
        }
        if(communityServiceImpl.findByName(communityForm.getName())!=null){
            redirectAttributes.addFlashAttribute("message", "Группа с таким названием уже существует");
            return "redirect:/groups";
        }
        User user = userService.findByEmail(principal.getName()).get();
        communityServiceImpl.createCommunity(communityForm, user);

        // TODO: 14.05.2017 редирект на группу
        return "redirect:/groups";
    }

    @ResponseBody
    @RequestMapping(value = "/load_more", method = RequestMethod.GET)
    public ModelAndView loadMoreProject() {

        ModelAndView modelAndView = new ModelAndView("groups/groupsItem");
        modelAndView.addObject("communities", communityService.getGroups(currentPage, DEFAULT_NUM));

        currentPage++;

        return modelAndView;
    }
}
