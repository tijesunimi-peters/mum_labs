package functions;

import models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class DocLinkFunctionsTest {
    private User user = new User(1,"User1", "Last1",new Date(), "USA", "email@gmail.com");
    private List<Post> posts;
    private List<Category> categories = new ArrayList<Category>(){ { add(Category.HEART); add(Category.CHEST); } };

    @Before
    public void setUp() {
        posts = new ArrayList<>();
        HealthIssue post = new HealthIssue(1,"Title1","description1",user,categories, Status.PENDING);
        HealthIssue post1 = new HealthIssue(2,"Title2","description2",user,categories, Status.PENDING);
        HealthIssue post2 = new HealthIssue(3,"Title3","description3",user,categories, Status.PENDING);
        HealthIssue post3 = new HealthIssue(4,"Title4","description4",user,categories, Status.PENDING);
        HealthIssue post4 = new HealthIssue(5,"Title5","description5",user,categories, Status.PENDING);
        posts.add(post);
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
    }

    @Test
    public void reducesPosts() {
        System.out.println(DocLinkFunctions.reducePosts.apply(posts));
        assertEquals("description1 description2 description3 description4 description5", DocLinkFunctions.reducePosts.apply(posts));
    }

    @Test
    public void tokenize() {
        assertEquals(Arrays.asList("description1"), DocLinkFunctions.tokenize.apply(posts.get(0).getDescription()));

        String reducedPost = DocLinkFunctions.reducePosts.apply(posts);

        assertEquals(Arrays.asList("description1","description2","description3","description4","description5"), DocLinkFunctions.tokenize.apply(reducedPost));
    }


    @Test
    public void tokenizeNullable() {
        assertEquals(DocLinkFunctions.tokenize.apply(null), DocLinkFunctions.tokenize.apply(""));
    }

    @Test
    public void getIntersection() {
        User user2 = new User(2,"User2", "Last2",new Date(), "Ethiopia", "email@gmail.com");
        Post post6 = new HealthIssue(6, "Title6","description6 description4 description1",user2,categories,Status.IN_CONSULTATION);
        List<Post> newPosts = new ArrayList<Post>() {
            {
                add(post6);
                add(new HealthIssue(7, "Title7","description7 description2 description3",user2,categories,Status.IN_CONSULTATION));
                add(new HealthIssue(8, "Title8","description8 description3",user2,categories,Status.IN_CONSULTATION));
                add(new HealthIssue(9, "Title9","description9 description5",user2,categories,Status.IN_CONSULTATION));
                add(new HealthIssue(10, "Title10","description10 description1",user2,categories,Status.IN_CONSULTATION));
            }
        };

        List<String> words1 = Arrays.asList("game", "sanders", "lorem");
        List<String> words2 = Arrays.asList("lorem", "goal");
        HashSet<String> intersection = new HashSet();
        intersection.add("lorem");
        assertEquals(intersection, DocLinkFunctions.getIntersection.apply(words1, words2));

        String reducedPost1 = DocLinkFunctions.reducePosts.apply(posts);
        String reducedPost2 = DocLinkFunctions.reducePosts.apply(newPosts);

        Set result = DocLinkFunctions.getIntersection.apply(DocLinkFunctions.tokenize.apply(reducedPost1), DocLinkFunctions.tokenize.apply(reducedPost2));
        List<String> resultList = new ArrayList<>();
        resultList.addAll(result);

        assertThat(resultList, not(hasItem("description7")));
        assertThat(resultList, hasItem("description2"));
    }

    @Test
    public void getIntersectionOfNullables() {
        List<String> words2 = Arrays.asList("lorem", "goal");

        assertEquals(new HashSet(), DocLinkFunctions.getIntersection.apply(null, words2));
        assertEquals(new HashSet(), DocLinkFunctions.getIntersection.apply(words2, null));
        assertEquals(new HashSet(), DocLinkFunctions.getIntersection.apply(null, null));
    }

    @Test
    public void tokenizePostsThroughComments() {
        User newUser = new User(3,"user2", "last2",new Date(),"Nigeria","a@gmail.com");
        Comment comment1 = new Comment(1,user,posts.get(0),0,"Dummy1");
        Comment comment2 = new Comment(2,user,posts.get(1),0,"Dummy2");
        Comment comment3 = new Comment(3,newUser,posts.get(2),0,"Dummy3");

        List<Comment> comments = new ArrayList<Comment>() {
            { add(comment1); add(comment2); add(comment3); }
        };

        List<String> result = DocLinkFunctions.tokenizePostsThroughComments.apply(
                DocLinkFunctions.getAllComments.apply(user, comments)
                );
        assertEquals(Arrays.asList("description1","description2"), result);

        User newUser2 = new User(4,"user4", "last4",new Date(),"Nigeria","a@gmail.com");

        List<String> failedResult = DocLinkFunctions.tokenizePostsThroughComments.apply(new ArrayList<>());

        assertEquals(new ArrayList<String>(), failedResult);
        assertTrue(DocLinkFunctions.getAllComments.apply(newUser2, comments).isEmpty());
        assertFalse(DocLinkFunctions.getAllComments.apply(newUser, comments).isEmpty());
    }

    @Test
    public void doctorsMapAndJaccardIndex() {
        User user1 = new User(1,"name1", "nam2", new Date(),Country.USA, "email", new Date(), new Date(), Arrays.asList(Role.DOCTOR));
        User user2 = new User(2,"name2", "nam3", new Date(),Country.USA, "email", new Date(), new Date(), Arrays.asList(Role.DOCTOR));

        List<Post> posts1 = new ArrayList<Post>() {
            {
                addAll(posts);
                add(new HealthIssue(posts.size(),"Title21","description23",user,categories, Status.PENDING));
                add(new HealthIssue(posts.size() + 1,"Title22","description23 description1 description2 description3",user,categories, Status.PENDING));
                add(new HealthIssue(posts.size() + 2,"Title23","description3 description4 description3",user,categories, Status.PENDING));
            }
        };

        List<Doctor> doctors = new ArrayList<Doctor>(){
            {
                add(new Doctor(1,Specialization.getRandom().name(), "experience",user1));
                add(new Doctor(2,Specialization.getRandom().name(), "experience",user2));
            }
        };

        List<Comment> comments = new ArrayList<Comment>() {
            {
                add(new Comment(1, user1, posts.get(0), 0, "Dummy1"));
                add(new Comment(1, user1, posts.get(1), 0, "Dummy1"));
                add(new Comment(1, user1, posts1.get(posts1.size() - 1), 0, "Dummy1"));
                add(new Comment(1, user2, posts.get(0), 0, "Dummy1"));
            }
        };

        List<String> userTokens = DocLinkFunctions.tokenize.apply(
                DocLinkFunctions.reducePosts.apply(DocLinkFunctions.getAllHealthIssues.apply(user, posts1))
        );

        List<String> doctorsTokens = DocLinkFunctions.tokenizePostsThroughComments.apply(
                DocLinkFunctions.getAllComments.apply(doctors.get(0).getUser(), comments)
        );

        Set<String> intersection = DocLinkFunctions.getIntersection.apply(userTokens, doctorsTokens);

        double calcIndex = ((double) intersection.size() / ((userTokens.size() + doctorsTokens.size()) - intersection.size())) * 100;

        List<Doctor> recommendedDoctors = DocLinkFunctions.recommendDoctorsToUser.apply(user, posts1, doctors, comments, 30);

        assertEquals(recommendedDoctors.size(), 1);
        assertThat(recommendedDoctors, hasItem(doctors.get(0)));
        assertEquals(Double.valueOf(calcIndex), DocLinkFunctions.jaccardIndex.apply(userTokens, doctorsTokens));

        assertEquals(new ArrayList<>(), DocLinkFunctions.recommendDoctorsToUser.apply(null,null, null, null, null));
    }

    @Test
    public void recommendPostToDoctors() {
        User user1 = new User(1,"name1", "nam2", new Date(),Country.USA, "email", new Date(), new Date(), Arrays.asList(Role.DOCTOR));
        User user2 = new User(2,"name2", "nam3", new Date(),Country.USA, "email", new Date(), new Date(), Arrays.asList(Role.DOCTOR));

        List<Post> posts1 = new ArrayList<Post>() {
            {
                addAll(posts);
                add(new HealthIssue(posts.size(),"Title21","description23",user,categories, Status.PENDING));
                add(new HealthIssue(posts.size() + 1,"Title24","description23 description2 description1 description4 description3 description of mine",user,categories, Status.PENDING));
            }
        };

        List<Doctor> doctors = new ArrayList<Doctor>(){
            {
                add(new Doctor(1,Specialization.getRandom().name(), "experience",user1));
                add(new Doctor(2,Specialization.getRandom().name(), "experience",user2));
            }
        };

        List<Comment> comments = new ArrayList<Comment>() {
            {
                add(new Comment(1, user1, posts1.get(0), 0, "Dummy1"));
                add(new Comment(2, user1, posts1.get(1), 0, "Dummy1"));
                add(new Comment(3, user1, posts1.get(posts1.size() - 2), 0, "Dummy1"));
                add(new Comment(4, user2, posts1.get(2), 0, "Dummy1"));
                add(new Comment(5, user2, posts1.get(posts1.size() - 1), 0, "Dummy1"));
//                add(new Comment(6, user1, posts1.get(posts1.size() - 1), 0, "Dummy1"));
            }
        };


        List<Post> doctor1ExpectedPostsWithComments = new ArrayList() { { add(posts1.get(0)); add(posts1.get(1)); add(posts1.get(posts1.size() - 2)); } };
        List<Post> doctor1ExpectedPostsWithNoComments = new ArrayList() {{ add(posts1.get(2)); add(posts1.get(3)); add(posts1.get(posts1.size() - 1)); }};

        List<Post> postsThroughComments = DocLinkFunctions.getPostsThroughComments.apply(comments);

        assertThat(postsThroughComments, hasItem(posts1.get(0)));

        List<Post> postsWithComment = DocLinkFunctions.userPostsWithDoctorComment.apply(user, doctors.get(0), comments);

        assertEquals(doctor1ExpectedPostsWithComments, postsWithComment);

        List<Integer> postIdsWithComment = DocLinkFunctions.getPostIds.apply(postsWithComment);
        List<Post> postsWithoutDoctorsComments = DocLinkFunctions.userPostsWithNoDoctorsComment.apply(posts1, postIdsWithComment);

        assertEquals(doctor1ExpectedPostsWithNoComments, postsWithoutDoctorsComments);

        List<String> doctorsToken = DocLinkFunctions.tokenizePostsThroughComments.apply(DocLinkFunctions.getAllComments.apply(user1, comments));

        List<String> usersToken = DocLinkFunctions.tokenize.apply(DocLinkFunctions.reducePosts.apply(postsWithoutDoctorsComments));


        Set<String> intersection = DocLinkFunctions.getIntersection.apply(doctorsToken, usersToken);

        Set<String> expectedIntersection = new HashSet<String>() { { add("description23"); add("description2"); add("description1"); }};

        assertEquals(expectedIntersection, intersection);

//        Double calcIndex = DocLinkFunctions.jaccardIndex.apply(doctorsToken, usersToken);


        List<Post> recommendedPostForDoc1 = Arrays.asList(posts1.get(posts1.size() - 1));

        assertEquals(recommendedPostForDoc1, DocLinkFunctions.recommendPostsToDoctors.apply(doctors.get(0), user, posts1,comments,30));


        List<String> doctor2Token = DocLinkFunctions.tokenizePostsThroughComments.apply(DocLinkFunctions.getAllComments.apply(user2, comments));

        expectedIntersection = new HashSet<String>() { {
            add("description4");
            add("description23");
            add("description2");
            add("description1");
        }};

        postsWithComment = DocLinkFunctions.userPostsWithDoctorComment.apply(user, doctors.get(1), comments);
        postIdsWithComment = DocLinkFunctions.getPostIds.apply(postsWithComment);
        postsWithoutDoctorsComments = DocLinkFunctions.userPostsWithNoDoctorsComment.apply(posts1, postIdsWithComment);

        usersToken = DocLinkFunctions.tokenize.apply(DocLinkFunctions.reducePosts.apply(postsWithoutDoctorsComments));

        intersection = DocLinkFunctions.getIntersection.apply(doctor2Token, usersToken);

        assertEquals(expectedIntersection, intersection);

        List<Post> recommendedPost = DocLinkFunctions.recommendPostsToDoctors.apply(doctors.get(1), user ,posts1,comments,30);

        assertEquals(new ArrayList(), recommendedPost);

        assertTrue(DocLinkFunctions.jaccardIndex.apply(doctor2Token, usersToken) > 30);

    }
}