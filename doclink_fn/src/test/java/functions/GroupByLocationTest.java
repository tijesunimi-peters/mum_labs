package functions;

import models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GroupByLocationTest {


    @Test
    public void groupByLocation() {
        User user = new User(
                1,
                "User1",
                "Last1",
                new Date(),
                Country.USA,
                "email@gmail.com", new Date(), new Date(), new ArrayList(){{ add(Role.PATIENT); }});
        User user2 = new User(
                2,
                "User2",
                "Last2",
                new Date(),
                Country.Nepal,
                "email@gmail.com", new Date(), new Date(), new ArrayList(){{ add(Role.PATIENT); }});;

        List<Post> posts = new ArrayList();

        List<Category> categories = new ArrayList<Category>() {
            {
                add(Category.HEART);
                add(Category.CHEST);
            }
        };


        posts.add(new HealthIssue(1, "Title1", "Announcement 1", user, categories, Status.PENDING));
        posts.add(new HealthIssue(2, "Title2", "Announcement 1", user, categories, Status.PENDING));
        posts.add(new HealthIssue(3, "Title3", "Announcement 1", user2, categories, Status.PENDING));
        posts.add(new HealthIssue(4, "Title4", "Announcement 1", user2, categories, Status.PENDING));

        Map<Country, List<String>> expected = new HashMap();
        expected.put(Country.USA, new ArrayList() {{ add("Title1"); add("Title2"); }});
        expected.put(Country.Nepal, new ArrayList() {{ add("Title3"); add("Title4"); }});

        assertEquals(expected, GroupByLocation.groupByLocation.apply(posts, PostType.HEALTH_ISSUE));

    }
}
