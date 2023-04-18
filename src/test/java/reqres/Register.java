package reqres;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reqres.dto.SuccessfulRegReq;
import reqres.dto.SuccessfulRegResp;
import reqres.dto.UnsuccessfulRegResp;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Register {

    public static String BASE_URI = "https://reqres.in";

    @Test
    public void successfulRegistration(){
        Specifications.installSpecification(Specifications.requestSpecification(BASE_URI),
                Specifications.resp200Ok());
        SuccessfulRegReq request = new SuccessfulRegReq("eve.holt@reqres.in", "pistol");
        SuccessfulRegResp response = given()
                .body(request)
                .when().log().all()
                .post("/api/register")
                .then().log().all()
                .extract().body().as(SuccessfulRegResp.class);
        Assertions.assertEquals(4, response.getId());
        Assertions.assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

    @Test
    public void unSuccessfulRegistrationWithoutPassword(){
        //without password
        Specifications.installReqSpec(Specifications.requestSpecification(BASE_URI));
        SuccessfulRegReq request = new SuccessfulRegReq("eve.holt@reqres.in","" );
        UnsuccessfulRegResp response = given()
                .body(request)
                .when()
                .post("/api/register")
                .then()
                .extract().body().as(UnsuccessfulRegResp.class);
        Assertions.assertEquals("Missing password", response.getError());
    }

    @Test
    public void unSuccessfulRegistrationWithoutEmail(){
        //without password
        Specifications.installReqSpec(Specifications.requestSpecification(BASE_URI));
        SuccessfulRegReq request = new SuccessfulRegReq("","pistol" );
        UnsuccessfulRegResp response = given()
                .body(request)
                .when()
                .post("/api/register")
                .then()
                .extract().body().as(UnsuccessfulRegResp.class);
        Assertions.assertEquals("Missing email or username", response.getError());
    }




}
