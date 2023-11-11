package pageObjects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
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
        registerPage.getUrl("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @Test
    @Tag("Register")
    @Tag("Parcial1")
    @Tag("ALL")
    public void userRegisterSuccessfully() {
        ExtentTest test = extent.createTest("Test Register Process Succesfull");
        test.log(Status.INFO, "Start test");

        Random random = new Random();

        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Elizabeth");
        data.put("lastname", "Georges");
        data.put("telephone", "12345678");
        data.put("email", "elizabeth" + random.nextLong() + "@test.com");
        data.put("password", "1234");
        data.put("confirm", "1234");

        try {

            registerPage.routeRegister();
            test.log(Status.PASS, "Access register page");

            registerPage.fulfillForm(data.get("name"), data.get("lastname"), data.get("email"), data.get("telephone"), data.get("password"), data.get("confirm"));
            test.log(Status.PASS, "Fulfill form");

            registerPage.suscribeToNewsletter(false);
            test.log(Status.PASS, "Do not suscribe newsletter");

            registerPage.acceptPrivacyPolice();
            test.log(Status.PASS, "Accept Privacy Police");

            registerPage.continueRegisterProcess();
            test.log(Status.PASS, "Try to continue register process");

            Assertions.assertTrue(registerPage.getWelcomeRegisterMessageText().contains("Congratulations! Your new account has been successfully created!"));
            test.log(Status.PASS, "Validate succesfull register process");

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
