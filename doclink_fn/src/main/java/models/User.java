package models;

import data.DataBuilder;
import functions.DocLinkFunctions;
import interfaces.IPostsWithComment;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User implements IPostsWithComment {
    private Integer id;

    public Integer getId() {
        return id;
    }

    private String firstName;
    private String lastName;
    private Date birthday;
    private Country country;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private List<Role> roles;

    public User(Integer id, String firstName, String lastName, Date birthday, String country, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.country = Country.valueOf(country);
        this.email = email;
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;

    }

    public User(Integer id, String firstName, String lastName, Date birthday, Country country, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.country = country;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(Integer id, String firstName, String lastName, Date birthday, Country country, String email, Date createdAt, Date updatedAt, List<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.country = country;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return LocalDate.now().getYear() - Instant.ofEpochMilli(birthday.getTime()).atZone(ZoneId.systemDefault()).getYear();
    }

    public Country getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    private String formatRoles() {
        return "\"" + roles.stream().map(x -> x.name()).reduce((a, b) -> a + "<>" + b).get() + "\"";
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "User{" +
                "id=" + id +
                ", roles=" + roles +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", createdAt='" + simpleDateFormat.format(createdAt) + '\'' +
                ", updatedAt='" + simpleDateFormat.format(updatedAt) + '\'' +
                '}';
    }

    public Date getBirthday() {
        return birthday;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public String writeToCsv() {

        return String.format(
                "%d,%s,%s,%s,%s,%s,%s,%s,%s",
                id,
                firstName,
                lastName,
                DataBuilder.getSimpleDateFormat().format(birthday),
                country,
                email,
                DataBuilder.getSimpleDateFormat().format(createdAt),
                DataBuilder.getSimpleDateFormat().format(updatedAt),
                formatRoles()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName()) &&
                getBirthday().equals(user.getBirthday()) &&
                getCountry() == user.getCountry() &&
                getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getBirthday(), getCountry(), getEmail(), getCreatedAt(), getUpdatedAt(), getRoles());
    }

    public List<Comment> getComments() {
        return DocLinkFunctions.getAllComments.apply(this, DataBuilder.getComments());
    }

    public List<Post> getHealthIssues() {
        return DocLinkFunctions.getAllHealthIssues.apply(this, DataBuilder.getPosts());
    }
}
