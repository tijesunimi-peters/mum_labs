package interfaces;


import models.Comment;
import models.Doctor;
import models.Post;
import models.User;

import java.util.List;

@FunctionalInterface
public interface INonActiveUsers {
    List<User> apply(List<User> users, List<Comment> comments, List<Post> posts);
}
