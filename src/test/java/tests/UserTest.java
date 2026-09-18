package tests;

import base.BaseTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.User;
import routes.Routes;
import specifications.RequestSpecs;
import specifications.ResponseSpec;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest extends BaseTest {

//    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
@Test(priority = 1)
public void getSingleUser() {
    given()
            .spec(RequestSpecs.readRequestSpec())
            .pathParam("userId", 1)
            .log().all()
            .when()
            .get(Routes.USER_BY_ID)
            .then()
            .log().all()
            .spec(ResponseSpec.successResponse())
            .body("id", equalTo(1))
            .body("username", equalTo("Bret"))
            .body("email", notNullValue());
}

@Test(priority = 2)
public void getUserUsingPathParameter() {
    given()
            .spec(RequestSpecs.readRequestSpec())
            .pathParam("userId", 2)
            .when()
            .get(Routes.USER_BY_ID)
            .then()
            .log().ifValidationFails()
            .spec(ResponseSpec.successResponse())
            .body("id", equalTo(2))
            .body("username", equalTo("Antonette"));
}

@Test(priority = 3)
public void getPostsUsingQueryParameter() {
    given()
            .spec(RequestSpecs.readRequestSpec())
            .queryParam("userId", 1)
            .when()
            .get(Routes.POSTS)
            .then()
            .log().ifValidationFails()
            .spec(ResponseSpec.successResponse())
            .body("", not(empty()))
            .body("userId", everyItem(equalTo(1)));
}

@Test(priority = 4)
public void createUserUsingSerialization() {
    User requestUser = new User(
            "name",
            "username",
            "new@example.com"
    );

    given()
            .spec(RequestSpecs.jsonRequestSpec())
            .body(requestUser)
            .log().all()
            .when()
            .post(Routes.USERS)
            .then()
            .log().all()
            .spec(ResponseSpec.createdResponse())
            .body("name", equalTo("name"))
            .body("username", equalTo("username"))
            .body("email", equalTo("new@example.com"))
            .body("id", notNullValue());
}
    @Test(priority = 4)
    public void updateUserUsingInput(){

        User requestUser = new User(
                "name u",
                "username u",
                "new@example.com"
        );
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestUser)
                .log().all()
                .when()
                .put(Routes.USER_BY_ID_1)
                .then()
                .log().all()
                .statusCode(200)
                .body("name",equalTo("name u"))
                .body("username",equalTo("username u"))
                .body("email",equalTo("new@example.com"));
    }
@Test(priority = 5)
public void updateUserUsingPut() {
    User updatedUser = new User(
            "name Updated",
            "username Updated",
            "updated@example.com"
    );
    updatedUser.setId(1);

    given()
            .spec(RequestSpecs.jsonRequestSpec())
            .pathParam("userId", 1)
            .body(updatedUser)
            .log().all()
            .when()
            .put(Routes.USER_BY_ID)
            .then()
            .log().all()
            .spec(ResponseSpec.successResponse())
            .body("id", equalTo(1))
            .body("name", equalTo("name Updated"))
            .body("username", equalTo("username Updated"));
}

@Test(priority = 6)
public void partiallyUpdateUserUsingPatch() {
    String patchBody = """
            {
              "email": "patch@example.com"
            }
            """;

    given()
            .spec(RequestSpecs.jsonRequestSpec())
            .pathParam("userId", 1)
            .body(patchBody)
            .when()
            .patch(Routes.USER_BY_ID)
            .then()
            .log().all()
            .spec(ResponseSpec.successResponse())
            .body("id", equalTo(1))
            .body("email", equalTo("patch@example.com"));
}
@Test(priority = 7)
public void extractResponseValues() {
    Response response = given()
            .spec(RequestSpecs.readRequestSpec())
            .pathParam("userId", 1)
            .when()
            .get(Routes.USER_BY_ID);

    int statusCode = response.getStatusCode();
    int userId = response.jsonPath().getInt("id");
    String username = response.jsonPath().getString("username");
    String city = response.jsonPath().getString("address.city");
    long responseTime = response.getTime();

    System.out.println("Status Code  : " + statusCode);
    System.out.println("User ID      : " + userId);
    System.out.println("Username     : " + username);
    System.out.println("City         : " + city);
    System.out.println("Response Time: " + responseTime + " ms");

    Assert.assertEquals(statusCode, 200);
    Assert.assertEquals(userId, 1);
    Assert.assertEquals(username, "Bret");
    Assert.assertNotNull(city);
}

@Test(priority = 9)
public void extractListFromJsonArray() {
    Response response = given()
            .spec(RequestSpecs.readRequestSpec())
            .when()
            .get(Routes.USERS);

    List<String> usernames = response.jsonPath().getList("username");

    System.out.println("Usernames: " + usernames);

    Assert.assertEquals(response.getStatusCode(), 200);
    Assert.assertEquals(usernames.size(), 10);
    Assert.assertTrue(usernames.contains("Bret"));
}

@Test(priority = 10)
public void deleteUser() {
    given()
            .spec(RequestSpecs.readRequestSpec())
            .pathParam("userId", 1)
            .when()
            .delete(Routes.USER_BY_ID)
            .then()
            .log().all()
            .spec(ResponseSpec.statusOkResponse());
}

}
