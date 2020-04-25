package functions;

import models.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CommonFunctionsTest {
    User user;
    User userForDoctor;
    List<Post> posts;

    private List<Category> categories = new ArrayList<Category>() {
        {
            add(Category.HEART);
            add(Category.CHEST);
        }
    };

    @Before
    public void setUp() {
        user = new User(
                1,
                "User1",
                "Last1",
                new Date(),
                Country.USA,
                "email@gmail.com", new Date(), new Date(), new ArrayList(){{ add(Role.PATIENT); }});

        posts = new ArrayList<>();

        userForDoctor = new User(
                2,
                "User1",
                "Last1",
                new Date(),
                Country.USA,
                "email@gmail.com",
                new Date(),
                new Date(),
                new ArrayList(){{ add(Role.DOCTOR); }});

        Doctor doctor = new Doctor(1,Specialization.getRandom().name(), "Experience", userForDoctor);
        posts.add(new Announcement(1, "Title1", "Announcement 1", doctor, categories));
        posts.add(new Announcement(2, "Title2", "Announcement 1", doctor, categories));
        posts.add(new HealthIssue(1, "Title1", "Announcement 1", user, categories, Status.PENDING));
        posts.add(new HealthIssue(2, "Title2", "Announcement 1", user, categories, Status.PENDING));
    }

    @Test
    public void getAllCommentsCount() {
        Comment comment1 = new Comment(1, userForDoctor, posts.get(0), 0, "Dummy1");
        Comment comment2 = new Comment(2, userForDoctor, posts.get(1), 0, "Dummy2");
        Comment comment3 = new Comment(3, user, posts.get(0), 0, "Dummy3");

        List<Comment> comments = new ArrayList() {{ add(comment1); add(comment2); add(comment3); }};

        Long count = 2l;
        assertEquals(count, CommonFunctions.getAllCommentsCount.apply(userForDoctor, comments));

    }

    @Test
    public void getAllAnnouncementsCount() {
        Long count = 2l;
        assertEquals(count, CommonFunctions.getAllAnnouncementsCount.apply(userForDoctor, posts));
    }

    @Test
    public void getAllHealthIssuesCount() {
        Long count = 2l;
        assertEquals(count, CommonFunctions.getAllHealthIssuesCount.apply(user, posts));
    }


    @Test
    public void getAllCommentsCountInRange() {
        Calendar cal = Calendar.getInstance();

        cal.set(2015, 10, 12);
        Date startDate = cal.getTime();

        cal.set(2015, 11,12 );
        Date startDate1 = cal.getTime();

        cal.set(2015, 11,20 );
        Date startDate2 = cal.getTime();

        cal.set(2015, 12,20 );
        Date startDate3 = cal.getTime();

        cal.set(2018, 11, 12);
        Date endDate = cal.getTime();

        Comment comment1 = new Comment(1, user, posts.get(0), 0, "Dummy1", startDate1);
        Comment comment2 = new Comment(1, user, posts.get(0), 0, "Dummy1", startDate2);
        Comment comment3 = new Comment(1, user, posts.get(0), 0, "Dummy1", startDate3);

        List<Comment> comments = new ArrayList() {{ add(comment1); add(comment2); add(comment3); }};

        Long count = 3l;
        assertEquals(count, CommonFunctions.getAllCommentsCountInRange.apply(user, comments,  startDate, endDate));

    }



    @Test
    public void getAllAnnouncementsCountInRange() {

        User userForDoctor2 = new User(
                3,
                "User1",
                "Last1",
                new Date(),
                Country.USA,
                "email@gmail.com",
                new Date(),
                new Date(),
                new ArrayList(){{ add(Role.DOCTOR); }});

        Calendar cal = Calendar.getInstance();

        cal.set(2015, 10, 12);
        Date startDate = cal.getTime();

        cal.set(2015, 11,12 );
        Date startDate1 = cal.getTime();

        cal.set(2015, 11,20 );
        Date startDate2 = cal.getTime();

        cal.set(2015, 12,20 );
        Date startDate3 = cal.getTime();

        cal.set(2018, 11, 12);
        Date endDate = cal.getTime();

        Doctor doctor = new Doctor(1,Specialization.getRandom().name(), "Experience", userForDoctor2);

        posts.add(new Announcement(1, "title", "description", doctor, startDate1, startDate1, categories));
        posts.add(new Announcement(2, "title", "description", doctor, startDate1, startDate1, categories));
        posts.add(new HealthIssue(3, "title", "description", user, startDate1, startDate1, categories));
        posts.add(new HealthIssue(4, "title", "description", user, startDate1, startDate1, categories));


        Long count = 2l;
        assertEquals(count, CommonFunctions.getAllAnnouncementsCountInRange.apply(userForDoctor2, posts,  startDate, endDate));
        assertEquals(count, CommonFunctions.getAllHealthIssuesCountInRange.apply(user, posts,  startDate, endDate));



    }


}
