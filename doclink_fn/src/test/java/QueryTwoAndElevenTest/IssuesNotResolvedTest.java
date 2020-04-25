package QueryTwoAndElevenTest;

import functions.IssuesNotResolved;
import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IssuesNotResolvedTest {
    private List<Post> cj;
    private User user = new User(1,"User1", "Last1",new Date(), "USA", "email@gmail.com");
    private List<Post> posts;
    private List<Category> categories = new ArrayList<Category>(){ { add(Category.HEART); add(Category.CHEST); } };
    HealthIssue post;
    HealthIssue post1;
    HealthIssue post2;
    HealthIssue post3;
    HealthIssue post4;


        @Before
        public void setUp() {
            cj=new ArrayList<>();
            posts = new ArrayList<>();
             post = new HealthIssue(1,"Title1","description1",user,categories, Status.PENDING);
             post1 = new HealthIssue(2,"Title2","description2",user,categories, Status.RESOLVED);
            post2 = new HealthIssue(3,"Title3","description3",user,categories, Status.IN_CONSULTATION);
            post3 = new HealthIssue(4,"Title4","description4",user,categories, Status.PENDING);
            post4 = new HealthIssue(5,"Title5","description5",user,categories, Status.RESOLVED);
            posts.add(post);
            posts.add(post1);
            cj.add(post);
            posts.add(post2);
            posts.add(post3);
            posts.add(post4);
        }

    @Test
    public void notResolved(){
            List<Post> expectedResult = new ArrayList<>();
            new IssuesNotResolvedTest().setUp();
            expectedResult.add(post);
            expectedResult.add(post2);
            expectedResult.add(post3);
            //List<Post> functionResult = IssuesNotResolved.issuesNotResolved.apply(posts);
        Assert.assertEquals(expectedResult,IssuesNotResolved.issuesNotResolved.apply(posts));
    }


}
