import com.github.javafaker.Faker;
import interfaces.IPostsWithComment;
import models.*;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Generator implements IPostsWithComment {
    private List<User> users;
    private List<Doctor> doctors;
    private List<Post> posts;
    private List<HealthIssue> healthIssues;
    private List<Announcement> announcements;
    private int[] commentCount = {5, 6, 7, 8, 9, 10};
    private List<Comment> comments;

    public Generator() {
        this.users = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.healthIssues = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void run() {
        generateUsers();
        generateDoctors();
        generateAnnouncements();
        generateHealthIssues();
        posts.addAll(healthIssues);
        posts.addAll(announcements);
        generateComments();
        writeToCsv();
    }

    private void generateUsers() {
        Faker faker = new Faker();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -5);
        List<Role> roles = new ArrayList<Role>();
        roles.add(Role.PATIENT);


        int count = 1;
        while (count <= 200) {
            Date createdAt = faker.date().future(1825, TimeUnit.DAYS, calendar.getTime());
            Date updatedAt = faker.date().between(createdAt, new Date());

            User user = new User(
                    count,
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.date().birthday(13, 50),
                    Country.getRandom(),
                    faker.internet().emailAddress(faker.name().username()),
                    createdAt,
                    updatedAt,
                    roles
            );
            count++;
            users.add(user);
        }


    }

    private void generateDoctors() {
        Faker faker = new Faker();
        Random random = new Random();
        int count = 1;
        List<Role> roles = new ArrayList<Role>();
        roles.add(Role.DOCTOR);


        while (count <= 75) {
            int index = random.nextInt(users.size());
            User user = users.get(index);
            users.remove(index);

            user = new User(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getBirthday(),
                    user.getCountry(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt(),
                    roles
            );

            users.add(index, user);

            Doctor doctor = new Doctor(count, Specialization.getRandom().name(), faker.lorem().paragraph(10), user);
            doctors.add(doctor);
            count++;
        }
    }

    private void generateAnnouncements() {
        Faker faker = new Faker();
        Random random = new Random();
        int doctorsSize = doctors.size();
        int count = 1;

        while (count <= 75) {
            Doctor doctor = doctors.get(random.nextInt(doctorsSize));
            List<Category> categoryList = buildCategories(random.nextInt(6));
            Announcement announcement = new Announcement(
                    count,
                    faker.lorem().sentence(),
                    String.join(" ", faker.lorem().paragraphs(5)),
                    doctor,
                    categoryList);

            announcements.add(announcement);
            count++;
        }
    }

    private void generateHealthIssues() {
        Faker faker = new Faker();
        Random random = new Random();

        int count = 76;
        while (count <= 375) {
            User user = users.get(random.nextInt(users.size()));
            List<Category> categoryList = buildCategories(random.nextInt(6));
            HealthIssue healthIssue = new HealthIssue(
                    count, faker.lorem().sentence(),
                    String.join(" ", faker.lorem().paragraphs(8)),
                    user,
                    categoryList,
                    Status.getRandom()
            );

            healthIssues.add(healthIssue);
            count++;
        }

    }

    private List<Category> buildCategories(int limit) {
        HashSet<Category> categoriesSet = new HashSet<Category>();
        if (limit == 0) limit = 1;
        int innerCount = 1;
        while (innerCount <= limit) {
            categoriesSet.add(Category.getRandom());
            innerCount++;
        }

        return new ArrayList<Category>(categoriesSet);
    }

    private void generateComments() {
        Random random = new Random();

        int count = 1;

        while (count <= 1000) {
            int numOfComments = commentCount[random.nextInt(commentCount.length)];
            Post post = healthIssues.get(random.nextInt(healthIssues.size()));
            List<Comment> createdComments = createComments(numOfComments, count, post);
            count += numOfComments;
            comments.addAll(createdComments);
        }

    }

    private List<Comment> createComments(int limit, int id, Post post) {
        Faker faker = new Faker();
        int innerCount = id;
        Random random = new Random();
        List<Comment> commentList = new ArrayList<Comment>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -5);
        Date createdAt = faker.date().future(1025, TimeUnit.DAYS, calendar.getTime());

        while (innerCount <= (id + limit)) {
            Doctor doctor = doctors.get(random.nextInt(doctors.size()));
            Comment comment = new Comment(
                    innerCount,
                    doctor.getUser(),
                    post,
                    0,
                    createdAt,
                    String.join(" ", faker.lorem().sentences(3))
            );

            commentList.add(comment);

            innerCount++;
        }

        int checkedIndex = random.nextInt(limit);

        Comment comment = commentList.remove(checkedIndex);

        commentList.add(checkedIndex, new Comment(
                comment.getId(),
                comment.getUser(),
                comment.getPost(),
                1,
                createdAt,
                comment.getText()
        ));

        return commentList;
    }

    @Override
    public String writeToCsv() {
        return writeToCsv(users) + writeToCsv(doctors) + writeToCsv(healthIssues) + writeToCsv(announcements) + writeToCsv(comments);
    }

    public String writeToCsv(List list) {
        StringBuffer buffer = new StringBuffer();

        String filename = list.get(0).getClass().getName().toLowerCase();

        List<IPostsWithComment> transformed = convertToICsv(list);

        for (IPostsWithComment obj : transformed) {
            buffer.append(obj.writeToCsv() + "\n");
        }

        writeToCsv(filename, buffer);

        return buffer.toString();
    }

    private void writeToCsv(String filename, StringBuffer buffer) {
        try {
            File file = new File(filename + ".csv");
            if (file.exists()) {
                System.out.println(filename + ".csv exists");
            } else {
                file.createNewFile();

                FileWriter fileWriter = new FileWriter(filename + ".csv");
                fileWriter.write(buffer.toString());
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private List<IPostsWithComment> convertToICsv(List list) {
        List<IPostsWithComment> output = new ArrayList<IPostsWithComment>();

        for (Object item : list) {
            output.add((IPostsWithComment) item);
        }

        return output;
    }
}
