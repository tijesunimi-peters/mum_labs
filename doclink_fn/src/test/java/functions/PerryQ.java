package functions;

import models.*;
import org.junit.Before;
import org.junit.Test;


import java.util.*;

import static org.junit.Assert.assertEquals;

public class PerryQ {
    Map<Integer, Integer> hashMap2 = new HashMap();
    Map<Integer, Integer> hashMap = new HashMap();


    Date d3 = new Date(22 / 34 / 34);
    List<Post> testPosts;
    List<Post> posts;
    List<Comment> comments;
    List<Doctor> doctors;
    List<User> users;
    List<Role> roles = new ArrayList<Role>() {
        {
            add(Role.DOCTOR);
            add(Role.PATIENT);
        }
    };
    User user2 = new User(1, "User1", "Last1", d3, Country.Ethiopia, "email@gmail.com", d3, d3, roles);
    User user = new User(2, "User2", "Last2", d3, Country.Nepal, "email@gmail.com", d3, d3, roles);
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
        testPosts = new ArrayList<>();
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
        testPosts.add(post);
        posts.add(post);
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
        posts.add(post5);
        posts.add(post6);
        posts.add(post7);
        posts.add(post8);

        Comment comment1 = new Comment(1, user, post, 1, "this is a test");
        Comment comment2 = new Comment(2, user2, post, 1, "this is a test1");
        Comment comment3 = new Comment(3, user2, post3, 1, "this is a test2");
        Comment comment4 = new Comment(4, user2, post2, 1, "this is a test3");
        Comment comment5 = new Comment(5, user2, post4, 1, "this is a test4");
        Comment comment6 = new Comment(6, user2, post, 1, "this is a test5");
        Comment comment7 = new Comment(7, user, post3, 1, "this is a test6");
        Comment comment8 = new Comment(8, user2, post, 1, "this is a test7");
        Comment comment9 = new Comment(9, user2, post3, 1, "this is a test8");
        Comment comment10 = new Comment(10, user2, post2, 1, "this is a test9");
        Comment comment11 = new Comment(11, user2, post4, 1, "this is a test10");
        Comment comment12 = new Comment(12, user2, post, 1, "this is a test11");
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
    public void get_Doctor_Form_Post() {//good
        hashMap2.put(1, 5);
        hashMap2.put(2, 1);
        assertEquals(hashMap2, FunctionForQ.get_doctor_from_post.apply(posts));
    }

    @Test
    public void getGet_doctor_from_commnts() {//good
        hashMap.put(1, 10);
        hashMap.put(2, 2);
        assertEquals(hashMap, FunctionForQ.getGet_doctor_from_commnts.apply(comments));

    }

    @Test
    public void Kth_Doctors_That_Active() {//good
        ArrayList n = new ArrayList<String>();

        n.add(doctor2);

        assertEquals(n, FunctionForQ.Kth_Doctors_That_Active.apply(FunctionForQ.get_doctor_from_post.apply(posts), FunctionForQ.getGet_doctor_from_commnts.apply(comments), 1, doctors));
    }

    @Test
    public void Kth_Users_That_Active() {
        ArrayList n = new ArrayList<String>();
        n.add(user3);
        assertEquals(n, FunctionForQ.Kth_Users_That_Active.apply(posts, 1, users));

    }

    @Test
    public void categories() {
        Map<Category, List<Post>> expected = new HashMap<>();
        expected.put(Category.SKIN, new ArrayList());
        expected.put(Category.EAR_NOSE_THROAT, new ArrayList());
        expected.put(Category.EYES, new ArrayList());
        expected.put(Category.DENTAL, testPosts);
        expected.put(Category.HEART, testPosts);
        expected.put(Category.CHEST, testPosts);

//        assertEquals(expected, FunctionForQ.categories.apply(testPosts));

    }

    @Test
    public void common_health_issue() {
        Map<Category, Integer> str = new HashMap();
        str.put(Category.CHEST, 15);

//        assertEquals(str, FunctionForQ.common_health_issue.apply(posts, 1));
    }
}