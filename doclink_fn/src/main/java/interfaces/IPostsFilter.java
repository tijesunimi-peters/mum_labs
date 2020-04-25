package interfaces;

import models.Comment;
import models.Post;

import java.util.List;

@FunctionalInterface
public interface IPostsFilter {
    List<Post> apply(List<String> words, List<Post> posts, Integer threshHold);
}
