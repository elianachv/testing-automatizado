package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RegisterBackTest {


    @Test
    @Tag("Back")
    @Tag("Parcial2")
    @Tag("ALL")
    @Tag("Register")
    public void registerUserSuccesfully() {

        given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("customer.firstName", "Pepito")
                .formParam("customer.lastName", "Perez")
                .formParam("customer.address.street", "Av Boyaca")
                .formParam("customer.address.city", "Cali")
                .formParam("customer.address.state", "Valle")
                .formParam("customer.address.zipCode", "11829")
                .formParam("customer.phoneNumber", "12345678")
                .formParam("customer.ssn", "12345678")
                .formParam("customer.username", "cali89")
                .formParam("customer.password", "1234")
                .formParam("repeatedPassword","1234")
                .formParam("submit","")
                .when()
                .post("https://parabank.parasoft.com/parabank/register.htm")
                .then().statusCode(200)
                .log().status()
                .log().body();

    }


}