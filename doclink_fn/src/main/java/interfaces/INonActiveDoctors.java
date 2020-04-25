package interfaces;

import models.Comment;
import models.Doctor;
import models.Post;

import java.util.List;

@FunctionalInterface
public interface INonActiveDoctors {
    List<Doctor> apply(List<Doctor> doctors, List<Comment> comments, List<Post> posts);
}
