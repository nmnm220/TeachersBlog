package ru.blog.teachersblog.models;

public class VKPost {
    private String hash;
    private int id;
    private String text;
    private String title;
    public VKPost() {
    }

    public VKPost(int id, String title, String text, String hash) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.hash = hash;
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
