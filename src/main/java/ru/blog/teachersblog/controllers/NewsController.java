package ru.blog.teachersblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.blog.teachersblog.dao.PostDAO;
import ru.blog.teachersblog.dao.VKPostDAO;
import ru.blog.teachersblog.vk.VKInitializer;

@Controller
public class NewsController {
    /*
    private final PostDAO postDAO;
    public NewsController(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

     */
    private final VKPostDAO vkPostDAO;
    public NewsController(VKPostDAO vkPostDAO) {
        this.vkPostDAO = vkPostDAO;
    }



    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("title", "Новости");
        model.addAttribute("posts", vkPostDAO.index());
        return "news";
    }

}
