package ru.kpfu.itis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.model.dto.UserSearchDto;
import ru.kpfu.itis.service.StudentGroupService;
import ru.kpfu.itis.service.UserSearchService;
import ru.kpfu.itis.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Safin Ramil on 13.05.17
 */
@RestController
public class UserSearchController {

    private static final Logger logger = LoggerFactory.getLogger(UserSearchController.class);

    private final UserSearchService userSearchService;
    private final UserService userService;
    private final StudentGroupService studentGroupService;

    @Autowired
    public UserSearchController(UserSearchService userSearchService, UserService userService, StudentGroupService studentGroupService) {
        this.userSearchService = userSearchService;
        this.userService = userService;
        this.studentGroupService = studentGroupService;
    }

    @GetMapping("/search/groups")
    public List<Integer> findGroupsByCourse(Integer course) {

        List<StudentGroup> groups = userSearchService.findGroupByCourse(course);

        if (groups == null) {
            return Collections.emptyList();
        }

        return new ArrayList<>(groups.stream()
                .map(StudentGroup::getNum)
                .collect(Collectors.toSet()));
    }


    @PostMapping("/search/users/template")
    public ModelAndView searchUsers(@RequestBody UserSearchDto dto) {

        logger.warn(dto.toString());
        ModelAndView mav = new ModelAndView("search/user_search_tmpl");

        mav.addObject("users", userSearchService.searchUsers(dto));
        mav.addObject("userRole", userService.findById(dto.getUserId()).get().getUserRole());
        mav.addObject("courses", userSearchService.findAllCoursesValue());
        mav.addObject("groups", studentGroupService.getAll());
        return mav;
    }

}
