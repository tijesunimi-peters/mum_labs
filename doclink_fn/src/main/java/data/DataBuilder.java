package data;

import models.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DataBuilder {
    static List<Post> posts = new ArrayList<Post>();
    static List<User> users = new ArrayList<User>();
    static List<Doctor> doctors = new ArrayList<Doctor>();
    static List<Comment> comments = new ArrayList<Comment>();
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static List<Post> getPosts() {
        return posts;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Doctor> getDoctors() {
        return doctors;
    }

    public static List<Comment> getComments() {
        return comments;
    }

    public static void setUp() throws Exception {
        String filename = User.class.getName().toLowerCase() + ".csv";
        readCsv(filename, "user", users);

        filename = Doctor.class.getName().toLowerCase() + ".csv";
        readCsv(filename, "doctor", doctors);

        filename = Announcement.class.getName().toLowerCase() + ".csv";
        readCsv(filename, "announcement", posts);

        filename = HealthIssue.class.getName().toLowerCase() + ".csv";
        readCsv(filename, "healthIssue", posts);

        filename = Comment.class.getName().toLowerCase() + ".csv";
        readCsv(filename, "comment", comments);
    }

    public static void readCsv(String filename, String className, List list) throws Exception {
        File file = new File(filename);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                switch (className) {
                    case "user":
                        list.add(buildUser(scanner.nextLine()));
                        break;
                    case "healthIssue":
                        list.add(buildHealthIssue(scanner.nextLine()));
                        break;
                    case "announcement":
                        list.add(buildAnnouncement(scanner.nextLine()));
                        break;
                    case "comment":
                        list.add(buildComment(scanner.nextLine()));
                        break;
                    case "doctor":
                        list.add(buildDoctor(scanner.nextLine()));
                        break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Doctor buildDoctor(String line) throws Exception {
        String[] parts = getParts(line);

        Doctor doctor = new Doctor(
                Integer.valueOf(parts[0]),
                Specialization.valueOf(parts[1]).name(),
                parts[2],
                users.get(Integer.valueOf(parts[3]) - 1),
                simpleDateFormat.parse(parts[4]),
                simpleDateFormat.parse(parts[5])
        );

        return doctor;
    }

    public static String[] getParts(String line) {
        return line.split(",");
    }

    public static User buildUser(String line) throws Exception {
        String[] parts = getParts(line);

        List<Role> roles = Arrays.stream(parts[8].split("<>"))
                .map(x -> x.replaceAll("\"", ""))
                .filter(x -> x.length() != 0)
                .map(x -> Role.valueOf(x))
                .collect(Collectors.toList());

        User user = new User(
                Integer.valueOf(parts[0]),
                parts[1],
                parts[2],
                simpleDateFormat.parse(parts[3]),
                Country.valueOf(parts[4]),
                parts[5],
                simpleDateFormat.parse(parts[6]),
                simpleDateFormat.parse(parts[7]),
                roles
        );
        return user;
    }

    public static List<Category> buildCategories(String str) {
        String[] cats = str.replaceAll("<>", ",").split(",");

        List<Category> categories = Arrays.stream(cats)
                .map(x -> x.replace("\"", ""))
                .filter(x -> x.length() != 0)
                .map(x -> Category.valueOf(x))
                .collect(Collectors.toList());

        return categories;
    }

    public static HealthIssue buildHealthIssue(String line) throws Exception {
        String[] parts = getParts(line);

        HealthIssue healthIssue = new HealthIssue(
                Integer.valueOf(parts[0]),
                parts[1],
                parts[2],
                users.get(Integer.valueOf(parts[3]) - 1),
                simpleDateFormat.parse(parts[4]),
                simpleDateFormat.parse(parts[5]),
                buildCategories(parts[6]),
                Status.valueOf(parts[7])
        );

        return healthIssue;
    }

    public static Announcement buildAnnouncement(String line) throws Exception {
        String[] parts = getParts(line);

        Announcement announcement = new Announcement(
                Integer.valueOf(parts[0]),
                parts[1],
                parts[2],
                users.get(Integer.valueOf(parts[3]) - 1),
                simpleDateFormat.parse(parts[4]),
                simpleDateFormat.parse(parts[5]),
                buildCategories(parts[6])
        );

        return announcement;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public static Comment buildComment(String line) throws Exception {
        String[] parts = getParts(line);

        Comment comment = new Comment(
                Integer.valueOf(parts[0]),
                users.get(Integer.valueOf(parts[1]) - 1),
                posts.get(Integer.valueOf(parts[2]) - 1),
                Integer.valueOf(parts[3]),
                parts[4],
                simpleDateFormat.parse(parts[4])
        );

        return comment;
    }
}
