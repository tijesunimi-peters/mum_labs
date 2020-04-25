package functions;

import models.HealthIssue;
import models.Post;
import models.PostType;
import models.Status;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IssuesNotResolved {
    public static Function<List<Post>,List<Post>> issuesNotResolved =
            (posts) -> Optional.ofNullable(posts).orElse(posts).stream().filter(x->x.getPostType().equals(PostType.HEALTH_ISSUE)).
//                    .filter(x->x instanceof HealthIssue)
                    filter(x->((HealthIssue) x).getStatus().equals(Status.PENDING) || (((HealthIssue) x).getStatus().equals(Status.IN_CONSULTATION)))
                    .collect(Collectors.toList());

}
