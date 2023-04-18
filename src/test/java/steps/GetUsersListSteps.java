package steps;

import dto.User;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetUsersListSteps {
    public static String BASE_URI = "https://dummyapi.io/data/v1";
    @Given("I as authorized user get list of users")
    public void iAsAuthorizedUserGetListOfUsers() {
        List<User> users  = given()
                .baseUri(BASE_URI)
                .header("app-id", "6380c63b2e6f5682c64dd368")
                .when().log().all()
                .contentType(ContentType.JSON)
                .get("/user")
                .then().log().all()
                .extract().body().jsonPath().getList("data", User.class);
        System.out.println(users.get(0).getId());
    }

}
