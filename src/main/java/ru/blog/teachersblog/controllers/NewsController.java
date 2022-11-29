package ru.blog.teachersblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    @GetMapping("/news")
    public void news(Model model) {
        model.addAttribute("title", "Новости");
        model.addAttribute("page", "index");
        //return "main_template";
    }

}
