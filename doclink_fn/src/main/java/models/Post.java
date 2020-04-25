package models;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public abstract class Post {
    protected Integer id;
    protected String title;
    protected String description;
    protected User user;
    protected PostType postType;
    protected Date createdAt;
    protected Date updatedAt;
    protected List<Category> categories;

    public PostType getPostType() {
        return postType;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Post(Integer id, String title, String description, User user, List<Category> categories) {
        Faker faker = new Faker();
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.createdAt = faker.date().between(user.getCreatedAt(), new Date());
        this.updatedAt = new Date();
        this.categories = categories;
    }

    public Post(Integer id, String title, String description, User user, Date createdAt, Date updatedAt, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categories = categories;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "\nPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", postType=" + postType +
                ", categories=" + categories +
                '}' + "\n";
    }

    public String categoriesToCsvFormat() {
        StringBuffer buffer = new StringBuffer();
        for(Category cat: categories) {
            buffer.append(cat + "<>");
        }

        return "\"" + buffer.toString().substring(0, buffer.length() - 2) + "\"";
    }


}
