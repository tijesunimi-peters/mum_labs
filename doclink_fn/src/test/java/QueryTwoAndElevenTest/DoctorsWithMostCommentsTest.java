package QueryTwoAndElevenTest;

import functions.DoctorsWithMostComments;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DoctorsWithMostCommentsTest {
    Calendar cal = Calendar.getInstance();

    Calendar cal3 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();

    HashMap hashMap2 = new HashMap<Integer, Integer>();
    HashMap hashMap = new HashMap<Integer, Integer>();
    // List<Map.Entry<Category,Integer>> cat = new HashMap<>();

    Date d3 = new Date(22 / 34 / 34);

    List<Post> posts;
    List<Comment> comments;
    List<Doctor> doctors;
    List<User> users;
    List<Role> roles = new ArrayList<Role>() {
        {
            add(Role.DOCTOR);
        }
    };

    User user = new User(1, "User2", "Last2", d3, Country.Nepal, "email@gmail.com", d3, d3, roles);
    User user2 = new User(2, "User1", "Last1", d3, Country.Ethiopia, "email@gmail.com", d3, d3, roles);
    User user3 = new User(3, "User3", "Last2", d3, Country.Nepal, "email@gmail.com", d3, d3, roles);
    Doctor doctor1 = new Doctor(1, "Cardiology", "lol", user, d3, d3);
    Doctor doctor2 = new Doctor(2, "Dermatology", "lol", user2, d3, d3);

    List<Category> categories = new ArrayList<Category>() {
        {
            add(Category.HEART);
            add(Category.CHEST);
            add(Category.DENTAL);
        }
    };


    @Before
    public void setUp() {

        posts = new ArrayList<>();
        comments = new ArrayList<>();
        doctors = new ArrayList<>();
        users = new ArrayList<>();

        Announcement announcement1 = new Announcement(1, "test1", "descriptionTest", doctor1, categories);
        Announcement announcement2 = new Announcement(2, "test2", "descriptionTest2", doctor2, categories);
        Announcement announcement3 = new Announcement(3, "test3", "descriptionTest3", doctor2, categories);
        Announcement announcement4 = new Announcement(4, "test4", "descriptionTest4", doctor2, categories);
        Announcement announcement5 = new Announcement(5, "test5", "descriptionTest5", doctor2, categories);
        Announcement announcement6 = new Announcement(6, "test6", "descriptionTest6", doctor2, categories);

        posts.add(announcement1);
        posts.add(announcement2);
        posts.add(announcement3);
        posts.add(announcement4);
        posts.add(announcement5);
        posts.add(announcement6);
        HealthIssue post = new HealthIssue(1, "Title1", "description1", user, categories, Status.PENDING);
        HealthIssue post1 = new HealthIssue(2, "Title2", "description2", user2, categories, Status.PENDING);
        HealthIssue post2 = new HealthIssue(3, "Title3", "description3", user2, categories, Status.PENDING);
        HealthIssue post3 = new HealthIssue(4, "Title4", "description4", user2, categories, Status.PENDING);
        HealthIssue post4 = new HealthIssue(5, "Title5", "description5", user2, categories, Status.PENDING);
        HealthIssue post5 = new HealthIssue(6, "Title2", "description6", user3, categories, Status.PENDING);
        HealthIssue post6 = new HealthIssue(7, "Title3", "description7", user3, categories, Status.PENDING);
        HealthIssue post7 = new HealthIssue(8, "Title4", "description8", user3, categories, Status.PENDING);
        HealthIssue post8 = new HealthIssue(9, "Title5", "description9", user3, categories, Status.PENDING);
        posts.add(post);
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
        posts.add(post5);
        posts.add(post6);
        posts.add(post7);
        posts.add(post8);
        cal.set(2020, 3, 1);
        cal2.set(2020, 4, 1);
        cal3.set(2020, 3, 16);
        Comment comment1 = new Comment(1, user, post, 1, "this is a test", cal.getTime());
        Comment comment2 = new Comment(2, user2, post, 1, "this is a test1", cal.getTime());
        Comment comment3 = new Comment(3, user2, post3, 1, "this is a test2", cal.getTime());
        Comment comment4 = new Comment(4, user2, post2, 1, "this is a test3", cal.getTime());
        Comment comment5 = new Comment(5, user2, post4, 1, "this is a test4", cal.getTime());
        Comment comment6 = new Comment(6, user2, post, 1, "this is a test5", cal3.getTime());
        Comment comment7 = new Comment(7, user, post3, 1, "this is a test6", cal3.getTime());
        Comment comment8 = new Comment(8, user2, post, 1, "this is a test7", cal2.getTime());
        Comment comment9 = new Comment(9, user2, post3, 1, "this is a test8", cal2.getTime());
        Comment comment10 = new Comment(10, user2, post2, 1, "this is a test9", cal2.getTime());
        Comment comment11 = new Comment(11, user2, post4, 1, "this is a test10", cal2.getTime());
        Comment comment12 = new Comment(12, user2, post, 1, "this is a test11", cal2.getTime());
        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
        comments.add(comment4);
        comments.add(comment5);
        comments.add(comment6);
        comments.add(comment7);
        comments.add(comment8);
        comments.add(comment9);
        comments.add(comment10);
        comments.add(comment11);
        comments.add(comment12);
        doctors.add(doctor2);
        doctors.add(doctor1);
        users.add(user3);
    }


    @Test
    public void test() {
        User user3 = DoctorsWithMostComments.getDoctor.apply(comments, cal.getTime(), cal2.getTime());

        System.out.printf("============%n%s%n============%n", user3);
        assertEquals(user, DoctorsWithMostComments.getDoctor.apply(comments, cal.getTime(), cal2.getTime()));
    }

    public void test2() {


    }


}
