package interfaces;

import models.Post;
import models.User;

import java.util.Date;
import java.util.List;

@FunctionalInterface
public interface IPostsInRange {
    Long apply(User user, List<Post> posts, Date startDate, Date endDate);
}
