package interfaces;

import models.Comment;
import models.Doctor;
import models.Post;
import models.User;

import java.util.List;

@FunctionalInterface
public interface IRecommendation {
    List<Doctor> apply(User user, List<Post> posts, List<Doctor> doctors, List<Comment> comments, Integer threshHold);
}