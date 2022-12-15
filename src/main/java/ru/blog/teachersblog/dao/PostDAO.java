package ru.blog.teachersblog.dao;

import org.springframework.stereotype.Component;
import ru.blog.teachersblog.models.Post;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostDAO {
    private static int POST_COUNT;
    private List<Post> posts;
    {
        posts = new ArrayList<>();
        posts.add(new Post(++POST_COUNT, "123 askdjfaesp[aow509a58e6bmu9aw846bmu98mu 98wa6mb9w86m98wbay", "Первый пост!"));
        posts.add(new Post(++POST_COUNT, "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "ssssssssssssssssssssssssssssssssssssssssss ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss", "Второй пост!"));
    }

    public List<Post> index() {
        return posts;
    }
    public Post show (int id) {
        return posts.stream().filter(post -> post.getId() == id).findAny().orElse(null);
    }
    public void save(Post post) {
        post.setId(++POST_COUNT);
        posts.add(post);
    }
}
