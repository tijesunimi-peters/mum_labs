package interfaces;


import models.Comment;
import models.User;
import models.Post;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface IUserActivityGrouping {
    Map<User, Long> apply(List<User> users, List<Comment> comments, List<Post> posts);
}
