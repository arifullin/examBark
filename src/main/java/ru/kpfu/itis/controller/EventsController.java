package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.dto.EventDto;
import ru.kpfu.itis.service.EventService;
import ru.kpfu.itis.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/events")
public class EventsController {

    private EventService eventService;

    private UserService userService;

    private static final int DEFAULT_NUM = 10;

    private int currentPage;

    @Autowired
    public EventsController(EventService eventService, UserService userService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String events(ModelMap map) {

        currentPage = 0;

        map.addAttribute("events", eventService.loadEvents(currentPage, DEFAULT_NUM));

        currentPage++;

        return "events/events";
    }


    @ResponseBody
    @RequestMapping(value = "/load_more", method = RequestMethod.GET)
    public ModelAndView loadMoreEvents() {

        ModelAndView modelAndView = new ModelAndView("eventItem");
        modelAndView.addObject("events", eventService.loadEvents(currentPage, DEFAULT_NUM));

        currentPage++;

        return modelAndView;
    }


    @PostMapping("/create")
    public String post(
            @RequestParam("header") String title,
            @RequestParam("description") String description,
            @RequestParam("address") String address,
            @RequestParam("date") String date,
            @RequestParam("time1") String hours,
            @RequestParam("time2") String minutes,
            @RequestParam("files") MultipartFile[] files,
            Model model,
            RedirectAttributes redirectAttributes,
            Principal principal
    ) {

        if(title.isEmpty() || description.isEmpty()){
            model.addAttribute("error", "Пожалуйста, заполните заголовок и текст события");
            currentPage = 0;
            userService.findByEmail(principal.getName())
                    .ifPresent(user -> model.addAttribute("user", user));
            model.addAttribute("title", title);
            model.addAttribute("body", description);
            model.addAttribute("address", address);
            model.addAttribute("date", date);
            model.addAttribute("time1", hours);
            model.addAttribute("time2", minutes);
            model.addAttribute("events", eventService.loadEvents(currentPage, DEFAULT_NUM));
            return "/events/events";
        }

        if (title.length() >= 256  || address.length() >= 256 || description.length() >= 256) {
            model.addAttribute("error", "Слишком много букав");
            currentPage = 0;
            userService.findByEmail(principal.getName())
                    .ifPresent(user -> model.addAttribute("user", user));
            model.addAttribute("title", title);
            model.addAttribute("body", description);
            model.addAttribute("address", address);
            model.addAttribute("date", date);
            model.addAttribute("time1", hours);
            model.addAttribute("time2", minutes);
            model.addAttribute("events", eventService.loadEvents(currentPage, DEFAULT_NUM));
            return "/events/events";
        }

        if (files.length > 3) {
            model.addAttribute("error", "Необходимо не более 3 фотографий");
            currentPage = 0;
            userService.findByEmail(principal.getName())
                    .ifPresent(user -> model.addAttribute("user", user));
            model.addAttribute("title", title);
            model.addAttribute("body", description);
            model.addAttribute("address", address);
            model.addAttribute("date", date);
            model.addAttribute("time1", hours);
            model.addAttribute("time2", minutes);
            model.addAttribute("events", eventService.loadEvents(currentPage, DEFAULT_NUM));
            return "/events/events";
        }

        if (hours.isEmpty() || minutes.isEmpty() || date.isEmpty()) {
            model.addAttribute("error", "Пожалуйста, отметьте дату события");
            currentPage = 0;
            userService.findByEmail(principal.getName())
                    .ifPresent(user -> model.addAttribute("user", user));
            model.addAttribute("title", title);
            model.addAttribute("body", description);
            model.addAttribute("address", address);
            model.addAttribute("date", date);
            model.addAttribute("time1", hours);
            model.addAttribute("time2", minutes);
            model.addAttribute("events", eventService.loadEvents(currentPage, DEFAULT_NUM));
            return "/events/events";
        }

        EventDto eventDto = new EventDto(title, "", description, address, date, hours, minutes, files);

        userService.findByEmail(principal.getName()).ifPresent(eventDto::setUser);

        eventService.save(eventDto);

        redirectAttributes.addFlashAttribute("msg", "Событие успешно добавлено!");

        return "redirect:/events";
    }

    private LocalDateTime getDateStarted(String date, String hours, String minutes) {
        String[] partsOfDate = date.split("-");
        return LocalDateTime.of(Integer.parseInt(partsOfDate[0]),
                Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]),
                Integer.parseInt(hours), Integer.parseInt(minutes));
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/subscribe", method = RequestMethod.GET)
    public void subscribe(@PathVariable("id") Long id){
        eventService.subscribe(id);
    }
}