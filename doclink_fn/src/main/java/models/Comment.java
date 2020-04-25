package models;

import data.DataBuilder;
import interfaces.IPostsWithComment;

import java.util.Date;

public class Comment implements IPostsWithComment {
    private Integer id;
    private User user;
    private Post post;
    private Date createdAt;
    private Integer checked;
    private String text;

    public Comment(Integer id, User user, Post post, Integer checked, String text) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.checked = checked;
        this.text = text;
        this.createdAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Comment(Integer id, User user, Post post, Integer checked,Date date, String text) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.createdAt = date;
        this.checked = checked;
        this.text = text;
    }



    public Comment(Integer id, User user, Post post, Integer checked, String text, Date createdAt) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.createdAt = createdAt;
        this.checked = checked;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    public Integer getChecked() {
        return checked;
    }

    @Override
    public String toString() {
        String base = "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", post=" + post +
                ", checked=" + checked +
                ", createdAt=" + DataBuilder.getSimpleDateFormat().format(createdAt) +
                '}';

        return String.format("%n%s%n", base);
    }

    @Override
    public String writeToCsv() {
        return String.format(
                "%d,%d,%d,%d,%s,\"%s\"",
                id,
                user.getId(),
                post.getId(),
                checked,
                DataBuilder.getSimpleDateFormat().format(createdAt),
                text);
    }
}
