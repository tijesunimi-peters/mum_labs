import data.DataBuilder;
import functions.DocLinkFunctions;
import models.Comment;
import models.Doctor;
import models.Post;
import models.User;

import java.util.*;

public class CommandLineApp {
    private static Boolean quit = false;

    private static HashMap<Integer, String> queries = new HashMap();
    private static HashMap<Character, String> preOptions = new HashMap() {{
        put('a', "Run a query");
        put('q', "Quit");
    }};

    public static void init() {
        buildQueries();
        while(!quit) {
            run();
        }
    }

    private static void run() {
        showPreOptions();
    }

    private static void showPreOptions() {
        optionsHeader("Available Options");
        showOptions(preOptions);

        System.out.println("Enter choice: ");
        char answer = scanner().nextLine().charAt(0);
        System.out.println();

        switch (answer) {
            case 'a':
                optionsHeader("Available Queries");
                showOptions(queries);
                System.out.println("Choose query: ");
                Long ans = scanner().nextLong();

                getQueryChoice(ans);
                break;
            case 'q':
                quit=true;
                break;
        }
    }

    private static Scanner scanner() {
        return new Scanner(System.in);
    }

    private static void getQueryChoice(Long answer) {
        if (answer == 1) {
            System.out.println("Getting all comments for user");
            System.out.println("Choose user id between (1 - 300): ");
            Integer userId = scanner().nextInt();
            User user = DataBuilder.getUsers().stream().filter(u -> u.getId().equals(userId)).findFirst().get();
            System.out.printf("%n Getting all comments for %s %s %n", user.getFirstName(), user.getLastName());
            System.out.println("Processing...");
            List<Comment> comments = DocLinkFunctions.getAllComments.apply(user, DataBuilder.getComments());
            System.out.printf("%nFound %d comments%nResult%n======%n%s%n", comments.size(), comments);
        } else if (answer == 2) {
            System.out.println("\nChoose doctor id between (1 - 75): ");
            Integer doctorId = scanner().nextInt();
            Doctor doctor = DataBuilder.getDoctors().stream().filter(d -> d.getId().equals(doctorId)).findFirst().get();
            System.out.printf("%n Getting all announcements for %s %s %n", doctor.getUser().getFirstName(), doctor.getUser().getLastName());
            System.out.println("Processing...");
            List<Post> announcements = DocLinkFunctions.getAllAnnouncements.apply(doctor.getUser(), DataBuilder.getPosts());
            System.out.printf("%nFound %d announcements%nResult%n======%n%s%n", announcements.size(), announcements);
        } else if (answer == 3) {
            System.out.println("\nChoose user id between (1 - 300): ");
            Integer userId = scanner().nextInt();
            User user = DataBuilder.getUsers().stream().filter(d -> d.getId().equals(userId)).findFirst().get();
            System.out.printf("%n Getting all health issues for %s %s %n", user.getFirstName(), user.getLastName());
            System.out.println("Processing...");
            List<Post> healthIssues = DocLinkFunctions.getAllHealthIssues.apply(user, DataBuilder.getPosts());
            System.out.printf("%nFound %d health issues%nResult%n======%n%s%n", healthIssues.size(), healthIssues);
        } else if (answer == 4) {
            System.out.println("\nChoose user id between (1 - 300): ");
            Integer userId = scanner().nextInt();
            System.out.println("\nChoose doctor id between (1 - 75): ");
            Integer doctorId = scanner().nextInt();
            User user = DataBuilder.getUsers().stream().filter(d -> d.getId().equals(userId)).findFirst().get();
            Doctor doctor = DataBuilder.getDoctors().stream().filter(d -> d.getId().equals(doctorId)).findFirst().get();
            System.out.printf("%n Getting all health issues with doctors comment for %s %s %n", user.getFirstName(), user.getLastName());
            System.out.println("Processing...");
            List<Post> healthIssues = DocLinkFunctions.userPostsWithDoctorComment.apply(user, doctor, DataBuilder.getComments());
            System.out.printf("%nFound %d health issues%nResult%n======%n%s%n", healthIssues.size(), healthIssues);
        } else if (answer == 5) {
            System.out.println("\nChoose user id between (1 - 300): ");
            Integer userId = scanner().nextInt();
            System.out.println("\nChoose doctor id between (1 - 75): ");
            Integer doctorId = scanner().nextInt();
            User user = DataBuilder.getUsers().stream().filter(d -> d.getId().equals(userId)).findFirst().get();
            Doctor doctor = DataBuilder.getDoctors().stream().filter(d -> d.getId().equals(doctorId)).findFirst().get();
            System.out.printf("" +
                    "%nGetting all health issues without Dr. %s %s comment for %s %s %n",
                    doctor.getUser().getFirstName(),
                    doctor.getUser().getLastName(),
                    user.getFirstName(),
                    user.getLastName()
            );
            System.out.println("Processing...");
            List<Post> healthIssuesWithComments = DocLinkFunctions.userPostsWithDoctorComment.apply(user, doctor, DataBuilder.getComments());
            List<Integer> healthIssuesWithCommentsIds = DocLinkFunctions.getPostIds.apply(healthIssuesWithComments);
            List<Post> postsWithoutComments = DocLinkFunctions.userPostsWithNoDoctorsComment.apply(DocLinkFunctions.getAllHealthIssues.apply(user, DataBuilder.getPosts()), healthIssuesWithCommentsIds);
            System.out.printf("%nFound %d health issues without comments%nResult%n======%n%s%n", postsWithoutComments.size(), postsWithoutComments);
        } else if (answer == 6) {
            System.out.println("\nChoose user id between (1 - 300): ");
            Integer userId = scanner().nextInt();
            System.out.println("\nEnter threshhold ");
            Integer threshhold = scanner().nextInt();
            User user = DataBuilder.getUsers().stream().filter(d -> d.getId().equals(userId)).findFirst().get();
            System.out.printf("%nRecommending doctors for %s %s %n",
                    user.getFirstName(),
                    user.getLastName()
            );
            System.out.println("Processing...");
            List<Doctor> doctors = DocLinkFunctions.recommendDoctorsToUser.apply(
                    user,
                    DocLinkFunctions.getAllHealthIssues.apply(user, DataBuilder.getPosts()),
                    DataBuilder.getDoctors(),
                    DataBuilder.getComments(),
                    threshhold);
            System.out.printf("%nFound %d doctor recommendations %nResult%n======%n%s%n", doctors.size(), doctors);
        } else if (answer == 7) {
        } else if (answer == 8) {
        } else if (answer == 9) {
        } else if (answer == 10) {
        } else if (answer == 11) {
        } else if (answer == 12) {
        } else if (answer == 13) {
        } else if (answer == 14) {
        } else if (answer == 15) {
        }


    }

    private static void optionsHeader(String text) {
        System.out.println("\n"+ text +"\n==================");
    }

    private static void showOptions(HashMap queries) {
        Iterator it = queries.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.printf("%s. %s%n", e.getKey(), e.getValue());
        }
        System.out.println();
    }

    private static void buildQueries() {
        queries.put(1, "Get all comments for user");
        queries.put(2, "Get all announcement for doctor");
        queries.put(3, "Get all health issues for user");
        queries.put(4, "Get all health issues with doctors comment");
        queries.put(5, "Get all posts without doctors comment");
        queries.put(6, "Recommend doctors to user");
        queries.put(7, "Recommend posts to doctor");
        queries.put(8, "Group health issues by location");
        queries.put(9, "Get all non-active doctors");
        queries.put(10, "Get all non-active users");
        queries.put(11, "Get all issues never resolved");
        queries.put(12, "Group health issues by age-group");
        queries.put(13, "Get highest rated doctors per specialization");
        queries.put(14, "Get active users");
        queries.put(15, "Get active doctors");
    }
}
