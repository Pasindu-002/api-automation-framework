package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {

    private  ResponseSpec(){

    }

    public static ResponseSpecification successResponse(){

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification createdResponse(){

        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }
    public static ResponseSpecification statusOkResponse(){

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }


}
