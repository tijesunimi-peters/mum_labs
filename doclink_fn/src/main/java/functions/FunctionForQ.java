package functions;

import data.DataBuilder;
import interfaces.CustomFunction;
import interfaces.TriFunction;
import models.*;

import java.sql.Array;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionForQ {


    public static final Function<List<Post>, Map<Integer, Integer>> get_doctor_from_post = posts -> {
        return Optional
                .ofNullable(posts)
                .orElse(posts).stream()
                .filter(s -> s.getPostType().equals(PostType.ANNOUNCEMENT))
                .map(x -> (Announcement) x)
                .collect(Collectors.groupingBy(Announcement::getUser))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(x -> x.getKey().getId(), x -> x.getValue().size()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    };

    public static final Function<List<Comment>, Map<Integer, Integer>> getGet_doctor_from_commnts = comment -> {
        return Optional
                .ofNullable(comment)
                .orElse(comment)
                .stream()
                .filter(c -> c.getUser().getRoles().contains(Role.DOCTOR))
                .filter(c -> c.getPost().getPostType().equals(PostType.HEALTH_ISSUE))
                .collect(Collectors.groupingBy(Comment::getUser))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(x -> x.getKey().getId(), x -> x.getValue().size()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    };


    public static final CustomFunction<Map<Integer, Integer>, Map<Integer, Integer>, Integer, List<Doctor>, List<Doctor>> Kth_Doctors_That_Active = (mappedPost, mappedComments, integer, doctors)
            -> {
        assert Optional.ofNullable(mappedPost).orElse(mappedPost) != null;
        return Optional.ofNullable(mappedPost).orElse(mappedPost)
                .entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                post -> Optional.ofNullable(mappedComments.get(post.getKey()))
                                        .orElse(0) + post.getValue()
                        ))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(integer)
                .map(integerIntegerEntry -> doctors.stream()
                        .filter(doctor -> doctor.getUser().getId().equals(integerIntegerEntry.getKey()))
                        .findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    };


    public static final TriFunction<List<Post>, Integer, List<User>, List<User>> Kth_Users_That_Active = (posts, integer, users) -> {
        return Optional.ofNullable(posts).orElse(posts).stream().filter(post -> post.getPostType().equals(PostType.HEALTH_ISSUE))
                .filter(post -> post.getUser().getRoles().contains(Role.PATIENT))
                .collect(Collectors.groupingBy(Post::getUser))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(x -> x.getKey().getId(), x -> x.getValue().size()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(integerIntegerEntry -> users
                        .stream()
                        .filter(user -> user.getId().equals(integerIntegerEntry.getKey())).findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get).limit(integer)
                .collect(Collectors.toList());
    };
    public static final Function<List<Post>, Map<Category, List<Post>>> categories = (posts) -> {
        return Arrays.stream(Category.values())
                .collect(Collectors.toMap(category -> category,
                        category -> posts.stream()
                                .filter(x -> x.getCategories().contains(category))
                                .collect(Collectors.toList())))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    };
    public static final BiFunction<List<Post>, Integer, Map<Category, Long>> common_health_issue = (posts, integer) -> {

        return Arrays.stream(Category.values())
                .collect(Collectors.toMap(category -> category,
                        category -> posts.stream()
                                .filter(x -> x.getCategories().contains(category))
                                .count()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(categoryLongEntry -> categoryLongEntry.getValue()))
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue(),
                        (entry1,entry2) -> entry1,
                        LinkedHashMap::new));
    };
}

