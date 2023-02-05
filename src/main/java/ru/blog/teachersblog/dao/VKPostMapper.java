package ru.blog.teachersblog.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.blog.teachersblog.models.VKPost;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VKPostMapper implements RowMapper<VKPost> {
    @Override
    public VKPost mapRow(ResultSet rs, int rowNum) throws SQLException {
        VKPost vkPost = new VKPost();
        vkPost.setId(rs.getInt("id"));
        vkPost.setTitle(rs.getString("date"));
        vkPost.setText(rs.getString("text"));
        vkPost.setHash(rs.getString("hash"));
        return vkPost;
    }
}
