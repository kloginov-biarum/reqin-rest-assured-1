import dto.User;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;


public class UserTest {

    public static String BASE_URI = "https://dummyapi.io/data/v1";

    @Test
    public void testGetList(){
        List<User> users  = given()
                .baseUri(BASE_URI)
                .header("app-id", "6380c63b2e6f5682c64dd368")
                .when().log().all()
                .contentType(ContentType.JSON)
                .get("/user")
                .then().log().all()
                .extract().body().jsonPath().getList("data", User.class);
        Assertions.assertEquals(20, users.size());
    }


    public void testCreateUser(){

        //id of response is not null

    }

}
