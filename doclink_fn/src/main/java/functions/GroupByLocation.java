package functions;

import models.Country;
import models.Post;
import models.PostType;
import models.User;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupByLocation {
    public static final BiFunction<List<Post>, PostType, Map<Country, List<String>>> groupByLocation = (
            ((posts, postType) -> posts.stream()
                    .filter(s -> s.getPostType().equals(postType))
                    .collect(Collectors.groupingBy(post -> post.getUser().getCountry()))
                    .entrySet()
                    .stream()
                    .collect(
                            Collectors.toMap(entry -> entry.getKey(),
                                    entry -> entry
                                            .getValue()
                                            .stream()
                                            .map(post -> post.getTitle())
                                            .collect(Collectors.toList())
                            ))));

}
