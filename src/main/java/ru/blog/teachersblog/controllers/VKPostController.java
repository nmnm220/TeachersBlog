package ru.blog.teachersblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.blog.teachersblog.dao.PostDAO;
import ru.blog.teachersblog.dao.VKPostDAO;
import ru.blog.teachersblog.models.Post;

@Controller
@RequestMapping("vkposts")
public class VKPostController {

    private final VKPostDAO vkPostDAO;

    public VKPostController(VKPostDAO vkPostDAO) {
        this.vkPostDAO = vkPostDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("posts", vkPostDAO.index());
        return "posts/index";
    }
    @GetMapping("refresh")
    public void refresh() {
        vkPostDAO.refresh();
    }
}
