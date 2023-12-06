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

public class AccountOverviewPageTest {

    AccountsOverviewPage accountOverviewPage;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reports/AccountOverviewPageTest.html");
    static JsonFormatter json = new JsonFormatter("target/json/accountOverview.json");

    static ExtentReports extent;

    @BeforeAll
    public static void createReport() throws IOException {
        extent = ExtentFactory.getInstance();
        extent.createDomainFromJsonArchive("target/json/accountOverview.json");
        extent.attachReporter(json, info);
    }

    @BeforeEach
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        accountOverviewPage = new AccountsOverviewPage(driver, wait);
        accountOverviewPage.setUp();
        accountOverviewPage.getUrl(Constants.FRONT_BASE_URL + "/index.htm");
        accountOverviewPage.login(Constants.USER, Constants.PASSWORD);
    }

    @Test
    @Tag("Parcial2")
    @Tag("Front")
    @Tag("ALL")
    public void checkAccountsSummary() {
        ExtentTest test = extent.createTest("Test account overview");
        test.log(Status.INFO, "Start test");

        try {

            accountOverviewPage.routeAccountOverview();
            Thread.sleep(2000);
            test.log(Status.PASS, "See accounts summary");


            Assertions.assertTrue(accountOverviewPage.getAccountNote().contains("Balance includes deposits that may be subject to holds"));
            test.log(Status.PASS, "Validate succesfull account overview");


        } catch (Exception error) {
            test.log(Status.FAIL, "Report error in test " + error.getMessage());
            error.printStackTrace();
        }
    }

    @Test
    @Tag("Parcial2")
    @Tag("Front")
    @Tag("ALL")
    public void checkAccountDetails() {
        ExtentTest test = extent.createTest("Test account details");
        test.log(Status.INFO, "Start test");

        try {

            accountOverviewPage.routeAccountOverview();
            Thread.sleep(2000);
            test.log(Status.PASS, "See accounts summary");

            Assertions.assertTrue(accountOverviewPage.getAccountNote().contains("Balance includes deposits that may be subject to holds"));
            test.log(Status.PASS, "Validate succesfull account overview");

            Thread.sleep(2000);
            accountOverviewPage.selectAccount();
            test.log(Status.PASS, "Select account");

            Thread.sleep(2000);
            System.out.println(accountOverviewPage.getAccountDetailTitle());
            Assertions.assertTrue(accountOverviewPage.getAccountDetailTitle().contains("Account Details"));
            test.log(Status.PASS, "Check details title");

            accountOverviewPage.searchAccountTransactions();
            test.log(Status.PASS, "Search account activity");

        } catch (Exception error) {
            test.log(Status.FAIL, "Report error in test " + error.getMessage());
            error.printStackTrace();
        }
    }

    @AfterEach
    public void close() {
        accountOverviewPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
