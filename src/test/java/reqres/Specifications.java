package reqres;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public static RequestSpecification requestSpecification(String URL){
        return new RequestSpecBuilder().setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification resp200Ok(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }



    public static void installSpecification(RequestSpecification req, ResponseSpecification resp){
        RestAssured.requestSpecification = req;
        RestAssured.responseSpecification = resp;
    }

    public static void installReqSpec(RequestSpecification requestSpecification){
        RestAssured.requestSpecification = requestSpecification;
    }

}
