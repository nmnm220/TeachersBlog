package ru.blog.teachersblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.blog.teachersblog.dao.VKPostDAO;

@Controller
@RequestMapping("refresh")
public class RefreshController {

    private final VKPostDAO vkPostDAO;

    public RefreshController(VKPostDAO vkPostDAO) {
        this.vkPostDAO = vkPostDAO;
    }

    @GetMapping()
    public String refresh() {
        vkPostDAO.refresh();
        return "redirect:/vkposts";
    }
}
