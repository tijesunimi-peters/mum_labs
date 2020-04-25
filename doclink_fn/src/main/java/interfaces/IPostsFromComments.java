package interfaces;


import models.Comment;
import models.Doctor;
import models.Post;
import models.User;

import java.util.List;

@FunctionalInterface
public interface IPostsFromComments {
    List<Post> apply(User user, Doctor doctor, List<Comment> doctorsComments);
}
