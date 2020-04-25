package interfaces;

import models.Comment;
import models.Doctor;
import models.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface IMostComment {
    User apply(List<Comment> comments, Date startDate, Date endDate);
}
