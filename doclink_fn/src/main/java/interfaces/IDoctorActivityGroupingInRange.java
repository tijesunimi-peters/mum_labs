package interfaces;

import models.Comment;
import models.Doctor;
import models.Post;

import java.util.Date;
import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface IDoctorActivityGroupingInRange {
    Map<Doctor, Long> apply(List<Doctor> doctors, List<Comment> comments, List<Post> posts, Date startDate, Date endDate);
}
