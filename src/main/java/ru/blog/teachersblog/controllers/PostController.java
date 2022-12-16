package ru.blog.teachersblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.blog.teachersblog.dao.PostDAO;
import ru.blog.teachersblog.models.Post;

@Controller
@RequestMapping("posts")
public class PostController {

    private final PostDAO postDAO;

    public PostController(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("posts", postDAO.index());
        return "posts/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", postDAO.show(id));
        return "posts/show";
    }
    @GetMapping("/new")
    public String newPost(@ModelAttribute ("post") Post post) {
        return "posts/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("post") Post post) {
        postDAO.save(post);
        return "redirect:/posts";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("post", postDAO.show(id));
        return "posts/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("post") Post post, @PathVariable("id") int id) {
        postDAO.update(id, post);
        return "redirect:/posts";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        postDAO.delete(id);
        return "redirect:/posts";
    }
}
