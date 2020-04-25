package functions;

import models.*;

import java.util.*;

public class QueryOneAndThreeTestData {

    User    user1, user2, user3, user4, user5,
            user6, user7, user8, user9, user10;
    Doctor doc1, doc2, doc3, doc4, doc5, doc6;

    Post healthIssue1, healthIssue2, healthIssue3, healthIssue4;
    Comment comtbyDoc1Hi1, comtbyDoc2Hi1, comtbyDoc3Hi1, comtbyDoc4Hi1, comtbyDoc5Hi1, comtbyDoc6Hi1,
            comtbyDoc1Hi2, comtbyDoc2Hi2, comtbyDoc3Hi2, comtbyDoc4Hi2, comtbyDoc5Hi2, comtbyDoc6Hi2,
            comtbyDoc1Hi3, comtbyDoc2Hi3, comtbyDoc3Hi3, comtbyDoc4Hi3, comtbyDoc5Hi3, comtbyDoc6Hi3,
            comtbyDoc1Hi4, comtbyDoc2Hi4, comtbyDoc3Hi4, comtbyDoc4Hi4, comtbyDoc5Hi4, comtbyDoc6Hi4;

    List<Comment> commentsForTest;
    List<Doctor> doctorsForTest;
    List<Role> rolesForTestD;
    List<Role> rolesForTestP;


    // Populating the data
    public void generate() {
        // Roles for testing
        rolesForTestD = new ArrayList<Role>(){
            {
                add(Role.DOCTOR);
            }
        };
        rolesForTestP = new ArrayList<Role>(){
            {
                add(Role.PATIENT);
            }
        };

        // Catagory lists
        List<Category> categoryListHeart = new ArrayList<Category>(){
            {
                add(Category.HEART);
            }
        };
        List<Category> categoryListTeeth = new ArrayList<Category>(){
            {
                add(Category.DENTAL);
            }
        };

        //List of Users
        user1 = new User(4,"Oswaldo","Goldner",new Date("December 25, 1992 23:15:20"), Country.Nigeria,"marion.feeney@gmail.com",new Date(2020-06-13),new Date(2020-29-14),rolesForTestD);
        user2 = new User(10,"Janean","Pfannerstill",new Date("December 25, 1986 23:15:20"),Country.Nigeria,"keith.bradtke@yahoo.com",new Date(2017-56-22),new Date(2019-51-14), rolesForTestD);
        user3 = new User(24,"Darci","Gutmann",new Date("December 25, 1995 23:15:20"),Country.Ethiopia,"tyrone.rutherford@gmail.com",new Date(2015-35-23), new Date(2017-38-11),rolesForTestD);
        user4 = new User(26,"Paris","Hintz",new Date("December 25, 1977 23:15:20"),Country.Nepal,"isaias.howell@hotmail.com",new Date(2020-56-17),new Date(2020-12-17), rolesForTestD);
        user5 = new User(30,"Joaquina","Prosacco",new Date("December 25, 1975 23:15:20") ,Country.USA,"jonna.gorczany@yahoo.com",new Date(2019-07-27), new Date(2019-07-06), rolesForTestD);
        user6 = new User(39,"Lucilla","Kreiger",new Date("December 25, 1982 23:15:20") ,Country.USA,"tuan.baumbach@hotmail.com",new Date(2015-26-03) ,new Date(2019-42-30), rolesForTestD);
        user7 = new User(49,"Jonah","Lesch",new Date("December 25, 1966 23:15:20"),Country.Ethiopia,"joe.stoltenberg@gmail.com",new Date(2018-59-05),new Date(2019-45-12), rolesForTestP);
        user8 = new User(54,"Aleida","Padberg",new Date("December 25, 1972 23:15:20") ,Country.Ethiopia,"rocco.simonis@gmail.com",new Date(2018-46-04), new Date(2019-27-11), rolesForTestP);
        user9 = new User(56,"Dorian","Lang",new Date("December 25, 1968 23:15:20"),Country.Nepal,"darcy.monahan@hotmail.com",new Date(2015-57-18),new Date(2019-01-12),rolesForTestP);
        user10 = new User(75,"Leroy","Strosin",new Date("December 25, 1965 23:15:20"),Country.Nepal,"andy.larkin@gmail.com",new Date(2016-38-14),new Date(2019-04-13),rolesForTestP);


        // List of Doctors
        doc1 = new Doctor(1, "Cardiology" , "seven", user1);
        doc2 = new Doctor(2, "Cardiology" , "seven", user2);
        doc3 = new Doctor(3, "Cardiology" , "seven", user3);
        doc4 = new Doctor(4, "OralSurgery" , "seven", user4);
        doc5 = new Doctor(5, "OralSurgery" , "seven", user5);
        doc6 = new Doctor(6, "OralSurgery" , "seven", user6);


        // Health Issues
        healthIssue1 = new HealthIssue(1, "Chest Pain", "I have a sever and continuous pain on my heart", user7, categoryListHeart, Status.RESOLVED);
        healthIssue2 = new HealthIssue(2, "Racing Heart", "My heart beats so fast during execise. Even walking causes shortening of breath", user8, categoryListHeart, Status.RESOLVED);
        healthIssue3 = new HealthIssue(3, "Painful Gum", "Frequent gum bleeding and severe pain during seeping cold drinks.", user9, categoryListTeeth, Status.RESOLVED);
        healthIssue4 = new HealthIssue(4, "Painful Gum", "Frequent gum bleeding and severe pain during seeping cold drinks.", user10, categoryListTeeth, Status.RESOLVED);

        // Comments made by doctors
        comtbyDoc1Hi1 = new Comment(1, user1, healthIssue1, 0, "See a cardiologist ASAP");
        comtbyDoc2Hi1 = new Comment(2, user2, healthIssue1, 0, "We can check up your heat here.");
        comtbyDoc3Hi1 = new Comment(3, user3, healthIssue1, 1, "you have see a doctor in a day");
        comtbyDoc4Hi1 = new Comment(4, user4, healthIssue1, 0, "Come and do a check up");
        comtbyDoc5Hi1 = new Comment(5, user5, healthIssue1, 0, "It has to be done soon");
        comtbyDoc6Hi1 = new Comment(6, user6, healthIssue1, 0, "Let us see the progress you made");
        comtbyDoc1Hi2 = new Comment(7, user1, healthIssue2, 0, "See a cardiologist ASAP");
        comtbyDoc2Hi2 = new Comment(8, user2, healthIssue2, 0, "We can check up your heat here.");
        comtbyDoc3Hi2 = new Comment(9, user3, healthIssue2, 1, "Try to take your heart rate measurement so often and let us know about it");
        comtbyDoc4Hi2 = new Comment(10, user4, healthIssue2, 0, "you can book an appointment here");
        comtbyDoc5Hi2 = new Comment(11, user5, healthIssue2, 0, "See a cardiologist ASAP");
        comtbyDoc6Hi2 = new Comment(12, user6, healthIssue2, 0, "Can you come and see a cardiologist in person");
        comtbyDoc1Hi3 = new Comment(13, user1, healthIssue3, 0, "See a cardiologist ASAP");
        comtbyDoc2Hi3 = new Comment(14, user2, healthIssue3, 0, "Let us check you up here in person.");
        comtbyDoc3Hi3 = new Comment(15, user3, healthIssue3, 0, "Use home remedies first");
        comtbyDoc4Hi3 = new Comment(16, user4, healthIssue3, 1, "Try mouth washes and if it is not gone book appointment ASAP");
        comtbyDoc5Hi3 = new Comment(17, user5, healthIssue3, 0, "Do flossing most often");
        comtbyDoc6Hi3 = new Comment(18, user6, healthIssue3, 0, "Change your brush more often");
        comtbyDoc1Hi4 = new Comment(19, user1, healthIssue4, 0, "See a dentist ASAP");
        comtbyDoc2Hi4 = new Comment(20, user2, healthIssue4, 0, "We can check up your heat here.");
        comtbyDoc3Hi4 = new Comment(21, user3, healthIssue4, 0, "We have to see you");
        comtbyDoc4Hi4 = new Comment(22, user4, healthIssue4, 1, "Treat it with salt and lemon and let us see the effect");
        comtbyDoc5Hi4 = new Comment(23, user5, healthIssue4, 0, "Book a schedule.");
        comtbyDoc6Hi4 = new Comment(24, user6, healthIssue4, 0, "Lets do a checkup.");
        // Array Of Comments to pass to function
        commentsForTest = new ArrayList<Comment>(){
            {
                add(comtbyDoc1Hi1); add(comtbyDoc2Hi1); add(comtbyDoc3Hi1); add(comtbyDoc4Hi1); add(comtbyDoc5Hi1); add(comtbyDoc6Hi1);
                add(comtbyDoc1Hi2); add(comtbyDoc2Hi2); add(comtbyDoc3Hi2); add(comtbyDoc4Hi2); add(comtbyDoc5Hi2); add(comtbyDoc6Hi2);
                add(comtbyDoc1Hi3); add(comtbyDoc2Hi3); add(comtbyDoc3Hi3); add(comtbyDoc4Hi3); add(comtbyDoc5Hi3); add(comtbyDoc6Hi3);
                add(comtbyDoc1Hi4); add(comtbyDoc2Hi4); add(comtbyDoc3Hi4); add(comtbyDoc4Hi4); add(comtbyDoc5Hi4); add(comtbyDoc6Hi4);
            }

        };

        // Doctor list
        doctorsForTest = new ArrayList<Doctor>() {
            {
                add(doc1); add(doc2); add(doc3); add(doc4); add(doc5); add(doc6);
            }
        };

    }


}
