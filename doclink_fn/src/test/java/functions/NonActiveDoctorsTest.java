package functions;

import models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class NonActiveDoctorsTest {

    @Before
    public void setUp() {

    }

    @Test
    public void nonActiveDocs() {
        List<Category> categories = new ArrayList<Category>() {
            {
                add(Category.HEART);
                add(Category.CHEST);
            }
        };
        List<Doctor> expected = new ArrayList();

        User user = new User(
                1,
                "User1",
                "Last1",
                new Date(),
                Country.USA,
                "email@gmail.com", new Date(), new Date(), new ArrayList(){{ add(Role.PATIENT); }});

        List<Post> posts = new ArrayList<>();

        User userForDoctor = new User(
                2,
                "User1",
                "Last1",
                new Date(),
                Country.USA,
                "email@gmail.com",
                new Date(),
                new Date(),
                new ArrayList(){{ add(Role.DOCTOR); }});

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

        Doctor doctor = new Doctor(1, Specialization.getRandom().name(), "Experience", userForDoctor);
        Doctor doctor2 = new Doctor(3, Specialization.getRandom().name(), "Experience", userForDoctor2);


        List<Doctor> doctors = new ArrayList() {{ add(doctor); add(doctor2); }};


        posts.add(new Announcement(1, "Title1", "Announcement 1", doctor2, categories));
        posts.add(new Announcement(2, "Title1", "Announcement 1", doctor2, categories));

        Comment comment1 = new Comment(1, userForDoctor2, posts.get(0), 0, "Dummy1");
        Comment comment2 = new Comment(2, userForDoctor2, posts.get(1), 0, "Dummy2");
        Comment comment3 = new Comment(3, user, posts.get(0), 0, "Dummy3");

        List<Comment> comments = new ArrayList() {{ add(comment1); add(comment2); add(comment3); }};

        Map<Doctor, Long> resultGrouping = NonActiveDoctors.doctorsActivityGrouping.apply(doctors,comments, posts);

        Map<Doctor, Long> expectedGrouping = new HashMap();
        expectedGrouping.put(doctor, Long.valueOf(0));
        expectedGrouping.put(doctor2, Long.valueOf(4));

        expected.add(doctor);

        assertEquals(expected, NonActiveDoctors.nonActiveDocs.apply(doctors, comments, posts));
        assertEquals(expectedGrouping, resultGrouping);


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

        posts.add(new Announcement(3, "title", "description", doctor2, startDate1, startDate1, categories));
        posts.add(new Announcement(4, "title", "description", doctor2, startDate1, startDate1, categories));

        Comment comment4 = new Comment(1, userForDoctor2, posts.get(0), 0, "Dummy1", startDate1);
        Comment comment5 = new Comment(1, userForDoctor2, posts.get(0), 0, "Dummy1", startDate2);

        resultGrouping = NonActiveDoctors.doctorsActivityGrouping.apply(doctors,comments, posts);

        comments.add(comment4);
        comments.add(comment5);

        expectedGrouping.put(doctor2, Long.valueOf(6));
        assertEquals(expectedGrouping, resultGrouping);
        assertEquals(expected, NonActiveDoctors.nonActiveDocsInTimeRange.apply(doctors, comments, posts, startDate, endDate));
    }
}