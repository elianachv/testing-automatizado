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

public class NewAccountFormPageTest {

    NewAccountFormPage newAccountFormPage;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reports/NewAccountFormPageTest.html");
    static JsonFormatter json = new JsonFormatter("target/json/newAccount.json");

    static ExtentReports extent;

    @BeforeAll
    public static void createReport() throws IOException {
        extent = ExtentFactory.getInstance();
        extent.createDomainFromJsonArchive("target/json/newAccount.json");
        extent.attachReporter(json, info);
    }

    @BeforeEach
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        newAccountFormPage = new NewAccountFormPage(driver, wait);
        newAccountFormPage.setUp();
        newAccountFormPage.getUrl(Constants.FRONT_BASE_URL + "/index.htm");
        newAccountFormPage.login(Constants.USER, Constants.PASSWORD);
    }

    @Test
    @Tag("Parcial2")
    @Tag("Front")
    @Tag("ALL")
    public void createNewAccount() {
        ExtentTest test = extent.createTest("Test create new account succesfully");
        test.log(Status.INFO, "Start test");

        try {

            newAccountFormPage.routeOpenNewAccount();
            Thread.sleep(2000);

            test.log(Status.PASS, "Select saving account");
            newAccountFormPage.selectAccountType("SAVINGS");

            newAccountFormPage.createAccount();
            test.log(Status.PASS, "Create account ");

            Thread.sleep(3000);

            Assertions.assertTrue(newAccountFormPage.getResultTitle().contains("Account Opened"));
            Assertions.assertTrue(newAccountFormPage.getResultMessage().contains("Congratulations, your account is now open."));
            test.log(Status.PASS, "Validate succesfull account creation process");


        } catch (Exception error) {
            test.log(Status.FAIL, "Report error in test " + error.getMessage());
            error.printStackTrace();
        }
    }

    @AfterEach
    public void close() {
        newAccountFormPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
