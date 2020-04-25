package functions;

import interfaces.ICommentsInRange;
import interfaces.IPostsInRange;
import models.Comment;
import models.Post;
import models.PostType;
import models.User;

import java.util.List;
import java.util.function.BiFunction;


public class CommonFunctions {
    public static final BiFunction<User, List<Comment>, Long> getAllCommentsCount = (
            (user, comments) -> comments.stream()
                    .filter(comment -> Integer.valueOf(comment.getUser().getId()).equals(user.getId()))
                    .count()
    );


    public static final BiFunction<User, List<Post>, Long> getAllAnnouncementsCount = (
            (user, posts) -> posts.stream()
                    .filter(post -> post.getPostType().equals(PostType.ANNOUNCEMENT) && Integer.valueOf(post.getUser().getId()).equals(user.getId()))
                    .count()
    );

    public static final BiFunction<User, List<Post>, Long> getAllHealthIssuesCount = (
            (user, posts) -> posts.stream()
                    .filter(post -> post.getPostType().equals(PostType.HEALTH_ISSUE) && post.getUser().getId().equals(user.getId()))
                    .count()
    );


    public static final ICommentsInRange getAllCommentsCountInRange = (
            (user, comments, startDate, endDate) -> DocLinkFunctions
                    .getAllComments
                    .apply(user, comments)
                    .stream()
                    .filter(comment -> comment.getCreatedAt().after(startDate) && comment.getCreatedAt().before(endDate))
                    .count()
    );
    public static final IPostsInRange getAllAnnouncementsCountInRange = (
            (user, posts, startDate, endDate) -> posts
                    .stream()
                    .filter(post -> post.getPostType().equals(PostType.ANNOUNCEMENT) && post.getUser().getId().equals(user.getId()))
                    .filter(post -> post.getCreatedAt().after(startDate) && post.getCreatedAt().before(endDate))
                    .count()

    );

    public static final IPostsInRange getAllHealthIssuesCountInRange = (
            (user, posts, startDate, endDate) -> posts
                    .stream()
                    .filter(post -> post.getPostType().equals(PostType.HEALTH_ISSUE) && Integer.valueOf(post.getUser().getId()).equals(user.getId()))
                    .filter(post -> post.getCreatedAt().after(startDate) && post.getCreatedAt().before(endDate))
                    .count()

    );


}

