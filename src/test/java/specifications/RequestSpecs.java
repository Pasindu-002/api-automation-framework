package specifications;

import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    private RequestSpecs(){
        //prevent instantiation of this utility class
    }

    private static RestAssuredConfig timeoutConfig(){
        int connectionTimeout = ConfigReader.getIntProperty("content.timeout");
        int responseTimeout = ConfigReader.getIntProperty("response.timeout");

        return RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", connectionTimeout)
                        .setParam("http.socket.timeout",responseTimeout));
    }


    //GET and Delete requests

    public static RequestSpecification readRequestSpec(){

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUrl())
                .setAccept(ContentType.JSON)
                .setConfig(timeoutConfig())
                .build();
    }

    //POST,PUT and PATCH request

    public static RequestSpecification jsonRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setConfig(timeoutConfig())
                .build();
    }
}
