package interfaces;

import models.Comment;
import models.User;

import java.util.Date;
import java.util.List;

@FunctionalInterface
public interface ICommentsInRange {
    Long apply(User user, List<Comment> comments, Date startDate, Date endDate);
}
