package api;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.NodeChildren;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.testng.annotations.Ignore;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginBackTest {

    private Cookies cookies;
    private Cookie cookie;
    private List<String> accountId;
    private String user;
    private String password;
    private String userId;

    @BeforeAll
    private void setUp() {

        user = "dh-cali98";
        password = "1234";
        accountId = new ArrayList<>();

        login();
        getAccount();
    }

    public void login() {

        Response r = given()
                .when()
                .get("https://parabank.parasoft.com/parabank/services/bank/login/" + user + "/" + password);

        r.then().log().status();
        r.then().log().body();

        String stringResponse = r.asString();
        XmlPath xmlPath = new XmlPath(stringResponse);
        userId = xmlPath.get("customer.id");

        System.out.println(userId);
        System.out.println("Login succesfully");
    }

    public void getAccount() {
        NodeChildren accounts = given()
                .when()
                .get("https://parabank.parasoft.com/parabank/services/bank/customers/" + userId + "/accounts/")
                .then().extract().path("accounts.account.id");

        accounts.list().forEach(a -> accountId.add(a.toString()));

        System.out.println(accountId);
    }

    @AfterAll
    public void logout() {

        given()
                .when()
                .get("https://parabank.parasoft.com/parabank/logout.htm");

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
                .post("https://parabank.parasoft.com/parabank/services/bank/createAccount")
                .then().statusCode(200)
                .log().status()
                .log().body();

    }

    @Ignore
    @Tag("Back")
    @Tag("Parcial2")
    @Tag("ALL")
    @Tag("LoginRequired")
    public void getAccountsOverview() {

        given()
                .cookie(cookie)
                .when()
                .get("https://parabank.parasoft.com/parabank/overview.htm")
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
                .get("https://parabank.parasoft.com/parabank/services/bank/customers/" + userId + "/accounts/")
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
                .get("https://parabank.parasoft.com/parabank/services/bank/accounts/" + accountId.get(0) + "/transactions/month/All/type/All")
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
                .post("https://parabank.parasoft.com/parabank/services/bank/transfer")
                .then().statusCode(200)
                .log().status()
                .log().body();

    }

}