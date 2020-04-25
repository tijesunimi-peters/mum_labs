package functions;

import interfaces.*;

import java.util.Map;
import java.util.stream.Collectors;

public class NonActiveUsers{

    public static final IUserActivityGrouping userActivityGrouping =
            ((users, comments, posts) -> users.stream()
                    .collect(
                            Collectors.toMap(
                                    user -> user,
                                    user -> CommonFunctions.getAllCommentsCount.apply(user, comments) +
                                            CommonFunctions.getAllHealthIssuesCount.apply(user, posts)
                            )
                    )
            );

    public static final INonActiveUsers nonActiveUsers =
            ((doctors, comments, posts) -> userActivityGrouping.apply(doctors, comments, posts)
                    .entrySet()
                    .stream()
                    .filter(entry -> Long.valueOf(entry.getValue()).equals(Long.valueOf(0)))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList())
            );

    public static final IUserActivityGroupingInRange usersActivityGroupingInTimeRange = (
            (users, comments, posts, startDate, endDate) -> users.stream().collect(
                    Collectors.toMap(
                            user -> user,
                            user -> CommonFunctions.getAllCommentsCountInRange.apply(
                                 user, comments, startDate, endDate
                            ) + CommonFunctions.getAllHealthIssuesCountInRange.apply(
                                            user, posts, startDate, endDate
                                    )

                    )
            )
    );

    public static final INonActiveUsersInTimeRange nonActiveUsersInTimeRange = (
            (users, comments, posts, startDate, endDate) -> usersActivityGroupingInTimeRange
                    .apply(users, comments, posts, startDate, endDate)
                    .entrySet()
                    .stream()
                    .filter(entry -> Long.valueOf(entry.getValue()).equals(Long.valueOf(0)))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList())
    );



}
