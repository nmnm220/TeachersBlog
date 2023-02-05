package ru.blog.teachersblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.blog.teachersblog.models.VKPost;
import ru.blog.teachersblog.vk.VKInitializer;

import java.util.ArrayList;
import java.util.List;

@Component
public class VKPostDAO {
    private final VKInitializer vkInitializer;

    public VKPostDAO(VKInitializer vkInitializer) {
        this.vkInitializer = vkInitializer;
    }

    public List<VKPost> index() {
        return vkInitializer.getPostsFromDB();
    }
    public void refresh() {
        vkInitializer.getPosts();
    }
}
