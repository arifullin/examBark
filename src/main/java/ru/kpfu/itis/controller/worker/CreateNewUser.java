package ru.kpfu.itis.controller.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.UserRole;
import ru.kpfu.itis.service.impl.StGroupServiceImpl;
import ru.kpfu.itis.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "/worker")
public class CreateNewUser {

    @Autowired
    StGroupServiceImpl studentGroupService;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/create_user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("userForm") User user, @RequestParam("course") int course, @RequestParam("group") int group) {

        if (user.getUserRole() == UserRole.ROLE_STUDENT) {
            StudentGroup studentGroup = studentGroupService.findByCourseAndGroup(course, group);
            user.setGroup(studentGroup);
        }
        if (userService.getByEmail(user.getEmail()) == null) {
            userService.add(user);
        }
        return "redirect:/worker/create_user";
    }
}
