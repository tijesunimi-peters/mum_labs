package interfaces;

import models.Comment;
import models.Doctor;
import models.Post;

import java.util.Date;
import java.util.List;

@FunctionalInterface
public interface INonActiveDoctorsInTimeRange {
    List<Doctor> apply(List<Doctor> doctors, List<Comment> comments, List<Post> posts, Date startDate, Date endDate);
}
