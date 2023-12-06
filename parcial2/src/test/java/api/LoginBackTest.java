package api;

import constants.Constants;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.NodeChildren;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pageObjects.BasePage;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginBackTest {

    private Cookie cookie;
    private List<String> accountId;

    private String user;
    private String password;
    private String userId;

    @BeforeAll
    private void setUp() {

        user = Constants.USER;
        password = Constants.PASSWORD;
        accountId = new ArrayList<>();

        login();
        loginCookie();
        getAccount();
    }


    public void loginCookie() {

        Response r = given()
                .contentType(ContentType.URLENC)
                .formParam("username", user)
                .formParam("password", password)
                .urlEncodingEnabled(false)
                .when()
                .post(Constants.FRONT_BASE_URL + "/login.htm");


        cookie = r.getDetailedCookie("JSESSIONID");
        System.out.println("Get cookie successfully " + cookie.getValue());

    }

    public void login() {

        Response r = given()
                .when()
                .get(Constants.BACK_BASE_URL + "/login/" + user + "/" + password);

        r.then().log().status();
        r.then().log().body();

        String stringResponse = r.asString();
        XmlPath xmlPath = new XmlPath(stringResponse);
        userId = xmlPath.get("customer.id");

        System.out.println(userId);
        System.out.println("Login succesfully");
    }

    public void getAccount() {

        try {

            NodeChildren accounts = given()
                    .when()
                    .get(Constants.BACK_BASE_URL + "/customers/" + userId + "/accounts/")
                    .then().extract().path("accounts.account.id");

            if (accounts.size() > 1)
                accounts.list().forEach(a -> accountId.add(a.toString()));
            else
                accountId.add(accounts.get(0).toString());

        } catch (ClassCastException e) {

            String accounts = given()
                    .when()
                    .get(Constants.BACK_BASE_URL + "/customers/" + userId + "/accounts/")
                    .then().extract().path("accounts.account.id");

            accountId.add(accounts);
        }


        System.out.println("Get accounts succesfully " + accountId);

    }

    @AfterAll
    public void logout() {

        given()
                .when()
                .get(Constants.FRONT_BASE_URL + "/logout.htm");

        System.out.println("Log out succesfully");

    }

    @Test
    @Tag("Back")
    @Tag("Parcial2")
    @Tag("ALL")
    @Tag("LoginRequired")
    public void createAccountSuccesfully() {

        given()
                .queryParam("customerId", userId)
                .queryParam("newAccountType", "1")
                .queryParam("fromAccountId", accountId.get(0))
                .when()
                .post(Constants.BACK_BASE_URL + "/createAccount")
                .then().statusCode(200)
                .log().status()
                .log().body();

    }

    @Test
    @Tag("Back")
    @Tag("Parcial2")
    @Tag("ALL")
    @Tag("LoginRequired")
    public void getAccountsOverview() {

        given()
                .cookie(cookie)
                .when()
                .get(Constants.FRONT_BASE_URL + "/overview.htm")
                .then().statusCode(200)
                .log().status()
                .log().body();

    }

    @Test
    @Tag("Back")
    @Tag("Parcial2")
    @Tag("ALL")
    @Tag("LoginRequired")
    public void getAccountsOverview2() {

        given()
                .when()
                .get(Constants.BACK_BASE_URL + "/customers/" + userId + "/accounts/")
                .then().statusCode(200)
                .log().status()
                .log().body();

    }

    @Test
    @Tag("Back")
    @Tag("Parcial2")
    @Tag("ALL")
    @Tag("LoginRequired")
    public void getAccountActivity() {

        given()
                .when()
                .get(Constants.BACK_BASE_URL + "/accounts/" + accountId.get(0) + "/transactions/month/All/type/All")
                .then().statusCode(200)
                .log().status()
                .log().body();

    }

    @Test
    @Tag("Back")
    @Tag("Parcial2")
    @Tag("ALL")
    @Tag("LoginRequired")
    public void transferMoney() {

        given()
                .queryParam("fromAccountId", accountId.get(0))
                .queryParam("toAccountId", accountId.size() > 1 ? accountId.get(1) : accountId.get(0))
                .queryParam("amount", "100")
                .when()
                .post(Constants.BACK_BASE_URL + "/transfer")
                .then().statusCode(200)
                .log().status()
                .log().body();

    }

}