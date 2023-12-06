package pageObjects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import constants.Constants;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Random;

public class RegisterPageTest {

    RegisterPage registerPage;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reports/RegisterPageTest.html");
    static JsonFormatter json = new JsonFormatter("target/json/register.json");

    static ExtentReports extent;

    @BeforeAll
    public static void createReport() throws IOException {
        extent = ExtentFactory.getInstance();
        extent.createDomainFromJsonArchive("target/json/register.json");
        extent.attachReporter(json, info);
    }

    @BeforeEach
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        registerPage = new RegisterPage(driver, wait);
        registerPage.setUp();
        registerPage.getUrl(Constants.FRONT_BASE_URL + "/index.htm");
    }

    @Test
    @Tag("Register")
    @Tag("Parcial2")
    @Tag("Front")
    @Tag("ALL")
    public void userRegisterSuccessfully() {
        ExtentTest test = extent.createTest("Test Register Process Succesfull");
        test.log(Status.INFO, "Start test");

        Random random = new Random();

        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Octavia");
        data.put("lastname", "Palafox");
        data.put("address", "Av Boyaca");
        data.put("city", "Bogota");
        data.put("state", "Cundinamarca");
        data.put("zip", "11001");
        data.put("phone", "12345678");
        data.put("ssn", "987654321");
        data.put("username", "dh" + random.nextInt());
        data.put("password", "1234");
        data.put("confirm", "1234");

        try {

            registerPage.routeRegister();
            test.log(Status.PASS, "Access register page");

            registerPage.fulfillForm(data.get("name"), data.get("lastname"), data.get("address"), data.get("city"), data.get("state"), data.get("zip"), data.get("phone"), data.get("ssn"), data.get("username"), data.get("password"), data.get("confirm"));
            test.log(Status.PASS, "Fulfill form");

            registerPage.continueRegisterProcess();
            test.log(Status.PASS, "Try to continue register process");

            Thread.sleep(3000);

            Assertions.assertTrue(registerPage.getWelcomeRegisterTitleText().contains("Welcome " + data.get("username")));
            Assertions.assertTrue(registerPage.getWelcomeRegisterMessageText().contains("Your account was created successfully. You are now logged in."));
            test.log(Status.PASS, "Validate succesfull register process");

            Constants.USER = data.get("username");
            Constants.PASSWORD = data.get("password");

        } catch (Exception error) {
            test.log(Status.FAIL, "Report error in test " + error.getMessage());
        }
    }

    @AfterEach
    public void close() {
        registerPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
