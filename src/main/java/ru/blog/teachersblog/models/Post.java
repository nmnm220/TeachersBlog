package ru.blog.teachersblog.models;

public class Post {
    private int id;
    private String text;
    private String title;
    public Post() {
    }

    public Post(int id, String text, String title) {
        this.id = id;
        this.text = text;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
