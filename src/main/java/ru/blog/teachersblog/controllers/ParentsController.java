package ru.blog.teachersblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParentsController {

    @GetMapping("/parents")
    public String contacts(Model model) {
        model.addAttribute("title", "Для Вас, коллеги");
        return "parents";
    }

}
