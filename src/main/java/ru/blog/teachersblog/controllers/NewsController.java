package ru.blog.teachersblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.blog.teachersblog.dao.PostDAO;

@Controller
public class NewsController {
    private final PostDAO postDAO;
    public NewsController(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("title", "Новости");
        model.addAttribute("posts", postDAO.index());
        return "news";
    }

}
