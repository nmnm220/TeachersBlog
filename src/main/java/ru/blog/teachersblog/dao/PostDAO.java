package ru.blog.teachersblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.blog.teachersblog.models.Post;

import java.util.List;

@Component
public class PostDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PostDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> index() {
        return jdbcTemplate.query("SELECT * from posts", new BeanPropertyRowMapper<>(Post.class));
    }
    public Post show (int id) {
        return jdbcTemplate.queryForObject("SELECT * from posts where id=?", new BeanPropertyRowMapper<>(Post.class), id);
    }
    public void save(Post post) {
        jdbcTemplate.update("insert into posts (title, text) values (?, ?)", post.getTitle(), post.getText());
    }
    public void update(int id, Post updatedPost) {
        jdbcTemplate.update("update posts set title=?, text=? where id=?", updatedPost.getTitle(), updatedPost.getText(), id);
    }
    public void delete (int id) {
        jdbcTemplate.update("delete from posts where id=?", id);
    }
}
