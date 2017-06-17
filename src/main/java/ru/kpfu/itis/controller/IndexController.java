package ru.kpfu.itis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Safin Ramil on 29.05.17
 * Safin.Ramil@ordotrans.ru
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String showIndex() {
        return "redirect:/main";
    }

}
