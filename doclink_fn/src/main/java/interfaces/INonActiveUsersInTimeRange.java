package interfaces;

import models.Comment;

import models.Post;
import models.User;

import java.util.Date;
import java.util.List;

@FunctionalInterface
public interface INonActiveUsersInTimeRange {
    List<User> apply(List<User> users, List<Comment> comments, List<Post> posts, Date startDate, Date endDate);
}
