package ru.blog.teachersblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhotoController {

    @GetMapping("/photo")
    public String contacts(Model model) {
        model.addAttribute("title", "Фото");
        return "photo";
    }

}
