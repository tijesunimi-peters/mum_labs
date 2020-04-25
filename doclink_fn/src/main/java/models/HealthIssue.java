package models;

import data.DataBuilder;
import interfaces.IPostsWithComment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HealthIssue extends Post implements IPostsWithComment {
    private Status status;
    private List<Comment> comments;

    public HealthIssue(Integer id, String title, String description, User user, List<Category> categoryList, Status status)  {
        super(id, title, description, user, categoryList);
        this.postType = PostType.HEALTH_ISSUE;
        this.status = status;
        this.comments = new ArrayList<>();
    }

    public HealthIssue(Integer id, String title, String description, User user, Date createdAt, Date updatedAt, List<Category> categories) {
        super(id, title, description, user, createdAt, updatedAt, categories);
        this.postType = PostType.HEALTH_ISSUE;

    }

    public HealthIssue(Integer id, String title, String description, User user, Date createdAt, Date updatedAt, List<Category> categories, Status status) {
        this(id, title, description, user, createdAt, updatedAt, categories);
        this.status = status;
        this.postType = PostType.HEALTH_ISSUE;
    }

    public List<Comment> getComments() {
        return comments;
    }
    public Status getStatus() {
        return status;
    }

    @Override
    public String writeToCsv() {

        return String.format(
                "%d,%s,\"%s\",%d,%s,%s,%s,%s",
                id,
                "\"" + title + "\"",
                description,
                user.getId(),
                DataBuilder.getSimpleDateFormat().format(createdAt),
                DataBuilder.getSimpleDateFormat().format(updatedAt),
                categoriesToCsvFormat(),
                status
        );
    }
}
