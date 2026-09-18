package tests;


import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import constants.APIConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.User;
import routes.Routes;
import specifications.RequestSpecs;
import specifications.ResponseSpec;
import utilities.DateUtil;
import utilities.JsonFileReader;
import utilities.RandomDateGenerator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UtilityTest extends BaseTest {
    String name = RandomDateGenerator.randomName();
    String username = RandomDateGenerator.randomUsername();
    String email = RandomDateGenerator.randomEmail();
    User requestUser = new User(name, username, email);

    @Test(priority = 1)
    public void createUser() {
        given()
                .spec(RequestSpecs.jsonRequestSpec())
                .body(requestUser)
                .log().all()
                .when()
                .post(Routes.USERS)
                .then()
                .log().all()
                .spec(ResponseSpec.createdResponse())
                .body("name", equalTo(name))
                .body("username", equalTo(username))
                .body("email", equalTo(email))
                .body("id", notNullValue());
    }

    @Test(priority = 2)
    public void createUserUsingJsonFile() {
        String requestBody = JsonFileReader.readJsonAsString("testdata/createUser.json");

        given()
                .spec(RequestSpecs.jsonRequestSpec())
                .body(requestBody)
                .log().all()
                .when()
                .post(Routes.USERS)
                .then()
                .log().all()
                .statusCode(APIConstants.STATUS_CREATED)
                .body("name", equalTo("QA student"))
                .body("username", equalTo("student"))
                .body("email", equalTo("student@practice.test"))
                .body("id", notNullValue());
    }

    @Test(priority = 3)
    public void readJsonFileAsNode() {
        JsonNode userNode = JsonFileReader.readJsonAsNode("testdata/createUser.json");

        Assert.assertEquals(userNode.get("name").asText(), "QA student");
        Assert.assertEquals(userNode.get("username").asText(), "student");
        Assert.assertEquals(userNode.get("email").asText(), "student@practice.test");
    }



    @Test(priority = 4)
    public void readJsonAsPojo() {
        User user = JsonFileReader.readJsonAsObject(
                "testdata/createUser.json",
                User.class
        );
        Assert.assertEquals(user.getName(), "QA student");
        Assert.assertEquals(user.getUsername(), "student");
        Assert.assertEquals(user.getEmail(), "student@practice.test");
    }


    @Test(priority = 5)
    public void genarateRandomData() {
        String currentDate = DateUtil.getCurrentDate();
        System.out.println("Current date: " + currentDate);

        String currentTime = DateUtil.getCurrentTime();
        System.out.println("Current time: " + currentTime);

        String timeStamp = DateUtil.getTimeStamp();
        System.out.println("Timestamp: " + timeStamp);

        Assert.assertFalse(currentDate.isBlank());// check whether the date,time,time stamp are blank
        Assert.assertFalse(currentTime.isBlank());
        Assert.assertFalse(timeStamp.isBlank());
    }
}