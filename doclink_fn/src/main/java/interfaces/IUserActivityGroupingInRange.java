package interfaces;

import models.Comment;
import models.User;
import models.Post;

import java.util.Date;
import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface IUserActivityGroupingInRange {
    Map<User, Long> apply(List<User> doctors, List<Comment> comments, List<Post> posts, Date startDate, Date endDate);

}
