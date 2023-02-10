package ru.blog.teachersblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ColleaguesController {

    @GetMapping("/colleagues")
    public String contacts(Model model) {
        model.addAttribute("title", "Для Вас, коллеги");
        return "colleagues";
    }

}
