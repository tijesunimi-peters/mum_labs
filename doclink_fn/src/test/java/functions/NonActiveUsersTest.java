package functions;

import models.*;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class NonActiveUsersTest {

    @Test
    public void nonActiveUsers() {

        List<Category> categories = new ArrayList<Category>() {
            {
                add(Category.HEART);
                add(Category.CHEST);
            }
        };
        List<User> expected = new ArrayList();

        User user = new User(
                1,
                "User1",
                "Last1",
                new Date(),
                Country.USA,
                "email@gmail.com", new Date(), new Date(), new ArrayList() {{
            add(Role.PATIENT);
        }});

        List<Post> posts = new ArrayList<>();

        User user2 = new User(
                2,
                "User1",
                "Last1",
                new Date(),
                Country.USA,
                "email@gmail.com",
                new Date(),
                new Date(),
                new ArrayList() {{
                    add(Role.DOCTOR);
                }});

        User user3 = new User(
                3,
                "User1",
                "Last1",
                new Date(),
                Country.USA,
                "email@gmail.com",
                new Date(),
                new Date(),
                new ArrayList() {{
                    add(Role.PATIENT);
                }});

        List<User> users = new ArrayList() {{
            add(user);
            add(user2);
            add(user3);
        }};


        posts.add(new HealthIssue(1, "Title1", "Health 1", user2, categories, Status.PENDING));
        posts.add(new HealthIssue(2, "Title1", "Health 1", user2, categories, Status.PENDING));

        Comment comment1 = new Comment(1, user2, posts.get(0), 0, "Dummy1");
        Comment comment2 = new Comment(2, user2, posts.get(1), 0, "Dummy2");
        Comment comment3 = new Comment(3, user2, posts.get(0), 0, "Dummy3");

        List<Comment> comments = new ArrayList() {{
            add(comment1);
            add(comment2);
            add(comment3);
        }};

        Map<User, Long> resultGrouping = NonActiveUsers.userActivityGrouping.apply(users, comments, posts);

        Map<User, Long> expectedGrouping = new HashMap();
        expectedGrouping.put(user, Long.valueOf(0));
        expectedGrouping.put(user2, Long.valueOf(5));
        expectedGrouping.put(user3, Long.valueOf(0));

        expected.add(user);
        expected.add(user3);

        assertEquals(expected, NonActiveUsers.nonActiveUsers.apply(users, comments, posts));
        assertEquals(expectedGrouping, resultGrouping);


        Calendar cal = Calendar.getInstance();

        cal.set(2015, 10, 12);
        Date startDate = cal.getTime();

        cal.set(2015, 11, 12);
        Date startDate1 = cal.getTime();

        cal.set(2015, 11, 20);
        Date startDate2 = cal.getTime();

        cal.set(2015, 12, 20);
        Date startDate3 = cal.getTime();

        cal.set(2018, 11, 12);
        Date endDate = cal.getTime();

        posts.add(new HealthIssue(3, "title", "description", user2, startDate1, startDate1, categories));
        posts.add(new HealthIssue(4, "title", "description", user2, startDate1, startDate1, categories));

        Comment comment4 = new Comment(1, user2, posts.get(0), 0, "Dummy1", startDate1);
        Comment comment5 = new Comment(1, user2, posts.get(0), 0, "Dummy1", startDate2);

        resultGrouping = NonActiveUsers.usersActivityGroupingInTimeRange.apply(users, comments, posts, startDate, endDate);

        comments.add(comment4);
        comments.add(comment5);

        expectedGrouping.put(user2, Long.valueOf(2));

        assertEquals(expectedGrouping, resultGrouping);

        assertThat(NonActiveUsers.nonActiveUsersInTimeRange.apply(users, comments, posts, startDate, endDate), hasItem(user));
        assertThat(NonActiveUsers.nonActiveUsersInTimeRange.apply(users, comments, posts, startDate, endDate), hasItem(user3));
    }
}