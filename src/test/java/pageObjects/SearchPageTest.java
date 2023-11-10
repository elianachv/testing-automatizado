package pageObjects;

import pageObjects.SearchPage;
import reports.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPageTest {

    SearchPage searchPage;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reports/SearchPageTest.html");
    static ExtentSparkReporter parcial = new ExtentSparkReporter("target/reports/Parcial1.html");

    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
        extent.attachReporter(parcial);
    }

    @BeforeEach
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        searchPage = new SearchPage(driver, wait);
        searchPage.setUp();
        searchPage.getUrl("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @Test
    @Tag("Search")
    @Tag("Parcial1")
    @Tag("ALL")
    public void searchIphoneSuccesfully() {
        ExtentTest test = extent.createTest("Test search iphone succesfully");
        test.log(Status.INFO, "Start test");

        try {

            searchPage.searchProduct("iphone");
            test.log(Status.PASS, "Search Iphone");

            searchPage.addFirstResultToCart();
            test.log(Status.PASS, "Add first result to car");

            Assertions.assertTrue(searchPage.getSuccesfullAlertMessage().contains("You have added iPhone to your shopping cart!"));
            test.log(Status.PASS, "Verify iphone was added successfully to the cart");


        } catch (Exception error) {
            test.log(Status.FAIL, "Report error in test " + error.getMessage());
        }
    }

    @AfterEach
    public void close() {
        searchPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
