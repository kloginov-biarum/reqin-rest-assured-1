package reqres;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reqres.dto.UserData;

import static io.restassured.RestAssured.given;

public class GetSingleUser {

    public static String BASE_URI = "https://reqres.in";
    public static Integer id = 3;

    @Test
    public void getUser(){
       UserData user =  given()
                .baseUri(BASE_URI)
                .when()
                .get("/api/users/"+id)
                .then().log().all()
               .extract().body().jsonPath().getObject("data", UserData.class);
    Assertions.assertEquals(id, user.getId());
    //не пустые поля
        Assertions.assertFalse(user.getEmail().isEmpty());
        Assertions.assertNotNull(user.getEmail());
    //в avatar есть "image"
        Assertions.assertTrue(user.getAvatar().contains("image"));
    //Что email заканчивается на @reqres.in
        Assertions.assertTrue(user.getEmail().endsWith("reqres"));
    }



}
