package reqres;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reqres.dto.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetUserListTest {

    public static String BASE_URI = "https://reqres.in";

    @Test
    public void getList(){
        given().baseUri("https://reqres.in/api")
                .when().log().all()
                .get("/users?page=2")
                .then().log().all();
    }

    @Test
    public void getListWithResponse(){
       Response response =  given().baseUri("https://reqres.in/api")
                .when()
                .get("/users?page=2");

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertFalse(response.asString().isEmpty());
    }

    @Test
    public void getListWithAsserInside(){
        given().baseUri("https://reqres.in/api")
                .when().log().all()
                .get("/users?page=2")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("page", Matchers.is(2));
    }

    @Test
    public void getListToClass(){
        List<UserData> users =  given()
                .baseUri(BASE_URI)
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        System.out.println(users.get(0).getId());
        //users has 6 elements
        Assertions.assertEquals(6,users.size());
        //id всех элементов списка users положительные числа (>0)
        for (UserData element:users) {
            Assertions.assertTrue(element.getId()>0);
            Assertions.assertTrue(element.getEmail().endsWith("@reqres.in"));
        }
        users.forEach(x-> Assertions.assertTrue(x.getId()>0));
        //each users' email ends with @reqres.in
        users.forEach(x-> Assertions.assertTrue(x.getEmail().endsWith("@reqres.in")));
        Assertions.assertTrue(users.stream().allMatch(x->x.getId()>0 && x.getEmail().endsWith("@reqres.in")));


        //avatar of each user has id of this user
        for(UserData element:users){
            Assertions.assertTrue(element.getAvatar().contains(element.getId().toString()));
        }
        users.forEach(element -> Assertions.assertTrue
                (element.getAvatar().contains(element.getId().toString())));


    }


}
