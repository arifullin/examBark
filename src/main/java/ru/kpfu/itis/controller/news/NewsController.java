package ru.kpfu.itis.controller.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.Tag;
import ru.kpfu.itis.model.dto.NewsDto;
import ru.kpfu.itis.service.NewsService;
import ru.kpfu.itis.service.TagService;
import ru.kpfu.itis.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail on 13.05.2017.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    private final Logger logger = LoggerFactory.getLogger(NewsController.class);

    private int tagPage = 0;
    private int currentPage = 0;
    private List<Tag> tagList = new ArrayList<>();

    private final NewsService newsService;
    private final TagService tagService;
    private final UserService userService;

    @Autowired
    public NewsController(NewsService newsService, TagService tagService, UserService userService) {
        this.newsService = newsService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @RequestMapping("")
    @PreAuthorize("isAuthenticated()")
    public String getNews(ModelMap modelMap, Principal principal) {

        currentPage = 0;
        userService.findByEmail(principal.getName())
                .ifPresent(user -> modelMap.addAttribute("user", user));
        modelMap.put("tags", tagService.getAllTagsByNews());
        modelMap.put("news", newsService.loadTen(currentPage));
        return "news/news";
    }

    @ResponseBody
    @RequestMapping(value = "/load_more", method = RequestMethod.GET)
    public ModelAndView loadMoreNews() {
        ModelAndView modelAndView = new ModelAndView("news/newsItem");

        if (tagList.isEmpty()) {
            currentPage++;
            modelAndView.addObject("news", newsService.loadTen(currentPage));
        } else {
            tagPage++;
            modelAndView.addObject("news", newsService.loadFilteredNews(tagPage, tagList));
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/load_news", method = RequestMethod.POST)
    public ModelAndView loadNews(@RequestParam(value = "tagIds[]", required = false) Long[] tagIds) {
        currentPage = 0;
        tagPage = 0;
        tagList.clear();
        ModelAndView modelAndView = new ModelAndView("news/newsItem");
        if (tagIds != null) {
            tagList = tagsFrom(tagIds);
            modelAndView.addObject("news", newsService.loadFilteredNews(tagPage, tagList));
        } else {
            modelAndView.addObject("news", newsService.loadTen(currentPage));
        }
        return modelAndView;

    }

    private List<Tag> tagsFrom(Long[] ids) {
        ArrayList<Tag> tags = new ArrayList<>();
        for (Long l : ids) {
            Tag tag = tagService.findById(l);
            tags.add(tag);

        }
        return tags;
    }


    @PostMapping("/create")
    public String post(
            @RequestParam("title") String title,
            @RequestParam("body") String body,
            @RequestParam("tagsForm") String tagsForm,
            @RequestParam("files") MultipartFile[] files,
            RedirectAttributes redirectAttributes,
            Model model,
            Principal principal
    ) {

        if (title.isEmpty() || body.isEmpty()) {
            model.addAttribute("error", "Пожалуйста, заполните заголовок и текст новости");
            currentPage = 0;
            userService.findByEmail(principal.getName())
                    .ifPresent(user -> model.addAttribute("user", user));
            model.addAttribute("tags", tagService.getAllTagsByNews());
            model.addAttribute("title", title);
            model.addAttribute("body", body);
            model.addAttribute("tagsForm", tagsForm);
            model.addAttribute("news", newsService.loadTen(currentPage));
            return "/news/news";
        }

        if (tagsForm.isEmpty()) {
            model.addAttribute("error", "Пожалуйста, введите хотя бы один тег");
            currentPage = 0;
            userService.findByEmail(principal.getName())
                    .ifPresent(user -> model.addAttribute("user", user));
            model.addAttribute("tags", tagService.getAllTagsByNews());
            model.addAttribute("title", title);
            model.addAttribute("body", body);
            model.addAttribute("news", newsService.loadTen(currentPage));
            return "/news/news";
        }
        if (files.length > 3) {
            model.addAttribute("error", "Необходимо не более 3 фотографий");
            currentPage = 0;
            userService.findByEmail(principal.getName())
                    .ifPresent(user -> model.addAttribute("user", user));
            model.addAttribute("tags", tagService.getAllTagsByNews());
            model.addAttribute("title", title);
            model.addAttribute("body", body);
            model.addAttribute("tagsForm", tagsForm);
            model.addAttribute("news", newsService.loadTen(currentPage));
            return "/news/news";
        }

        NewsDto dto = new NewsDto(title, body, files, tagsForm);

        logger.warn(dto.toString());

        userService.findByEmail(principal.getName())
                .ifPresent(dto::setUser);

        newsService.save(dto);

        redirectAttributes.addFlashAttribute("msg", "Новость успешно размещена");

        return "redirect:/news/";
    }
}
