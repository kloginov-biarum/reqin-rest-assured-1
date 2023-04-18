package steps;

import dto.User;
import dto.UserListSuccess;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetUsersListSteps {
    Response response;
    UserListSuccess userListSuccess;

    public static String BASE_URI = "https://dummyapi.io/data/v1";
    @Given("I as authorized user get list of users")
    public void iAsAuthorizedUserGetListOfUsers() {
        response  = given()
                .baseUri(BASE_URI)
                .header("app-id", "6380c63b2e6f5682c64dd368")
                .when().log().all()
                .contentType(ContentType.JSON)
                .get("/user")
                .then().log().all()
                .extract().response();
       response.getStatusCode();
       userListSuccess = response.getBody().as(UserListSuccess.class);
       System.out.println(userListSuccess.getData().get(0).getFirstName());
    }

}
