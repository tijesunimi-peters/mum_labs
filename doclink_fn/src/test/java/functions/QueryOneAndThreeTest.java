package functions;

import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class QueryOneAndThreeTest {
    QueryOneAndThreeTestData queryOneAndThreeTestData = new QueryOneAndThreeTestData();
    Map<Specialization, Doctor> queryOneExpectedResult = new HashMap<>();
    Map<String, Category> queryThreeExpectedResult = new HashMap<>();
    Map<Specialization, Doctor> queryOneFunctionReturn = new HashMap<>();
    List<Post> listOfHealthIssues = new ArrayList<>();

   @Before // For testing query one
   public void setUp() {
       queryOneAndThreeTestData.generate();
       queryOneExpectedResult.put(Specialization.valueOf("Cardiology"), queryOneAndThreeTestData.doc3);
       queryOneExpectedResult.put(Specialization.valueOf("OralSurgery"), queryOneAndThreeTestData.doc4);

       queryOneFunctionReturn = DocLinkFunctionsWonde.getRatedDoctorsInSpecialization
               .apply(queryOneAndThreeTestData.commentsForTest, queryOneAndThreeTestData.doctorsForTest);

       listOfHealthIssues.add(queryOneAndThreeTestData.healthIssue1);
       listOfHealthIssues.add(queryOneAndThreeTestData.healthIssue2);
       listOfHealthIssues.add(queryOneAndThreeTestData.healthIssue3);
       listOfHealthIssues.add(queryOneAndThreeTestData.healthIssue4);


       queryThreeExpectedResult.put("[40-49]",Category.HEART);
       queryThreeExpectedResult.put("[50-59]",Category.HEART);

   }

    @Test
    public void getRatedDoctorsInSpecialization() {
        Assert.assertTrue(queryOneExpectedResult.equals(queryOneFunctionReturn));
    }

    @Test
    public void groupingHealthIssubeByAgeGroupTest() {

        Map<String, Category> actualResult = DocLinkFunctionsWonde.groupingHealthIssubeByAgeGroup.apply(listOfHealthIssues);
//        System.out.println(actualResult);
//        System.out.println(queryThreeExpectedResult);
        Assert.assertTrue(queryThreeExpectedResult.equals(actualResult));
    }


}
