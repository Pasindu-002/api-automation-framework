package base;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = BASE_URL;

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterClass
    public void tearDown(){
        RestAssured.reset();
    }

}
