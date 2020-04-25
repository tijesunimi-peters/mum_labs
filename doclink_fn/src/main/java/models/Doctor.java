package models;

import data.DataBuilder;
import interfaces.IPostsWithComment;

import java.util.Date;

public class Doctor implements IPostsWithComment {
    private Integer id;
    private Specialization specialization;
    private String experience;
    private User user;
    private Date createdAt;
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Doctor(Integer id, String specialization, String experience, User user) {
        this.id = id;
        this.specialization = Specialization.valueOf(specialization);
        this.experience = experience;
        this.user = user;
        this.createdAt = user.getCreatedAt();
        this.updatedAt = new Date();
    }

    public Doctor(Integer id, String specialization, String experience, User user, Date createdAt, Date updatedAt) {
        this.id = id;
        this.specialization = Specialization.valueOf(specialization);
        this.experience = experience;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public String getExperience() {
        return experience;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "\nDoctor{" +
                "id=" + id +
                ", user=" + user +
                '}' + "\n";
    }

    @Override
    public String writeToCsv() {

        return String.format(
                "%d,%s,\"%s\",%d,%s,%s",
                id,
                specialization,
                experience,
                user.getId(),
                DataBuilder.getSimpleDateFormat().format(createdAt),
                DataBuilder.getSimpleDateFormat().format(updatedAt)
        );
    }
}
