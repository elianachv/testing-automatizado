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

public class TransferFundsFormsPageTest {

    TransferFundsFormsPage transferFundsFormPage;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reports/TransferFundsFormsPageTest.html");
    static JsonFormatter json = new JsonFormatter("target/json/transferFunds.json");

    static ExtentReports extent;

    @BeforeAll
    public static void createReport() throws IOException {
        extent = ExtentFactory.getInstance();
        extent.createDomainFromJsonArchive("target/json/transferFunds.json");
        extent.attachReporter(json, info);
    }

    @BeforeEach
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        transferFundsFormPage = new TransferFundsFormsPage(driver, wait);
        transferFundsFormPage.setUp();
        transferFundsFormPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        transferFundsFormPage.login("dh-cali98", "1234");
    }

    @Test
    @Tag("Parcial2")
    @Tag("ALL")
    public void transferFunds() {
        ExtentTest test = extent.createTest("Test transfer funds");
        test.log(Status.INFO, "Start test");

        try {

            transferFundsFormPage.routeTransferFunds();
            Thread.sleep(2000);
            test.log(Status.PASS, "Access transfer form");

            transferFundsFormPage.setAmountToTransfer("100");
            test.log(Status.PASS, "Set transfer amount");

            transferFundsFormPage.setToAccount();
            test.log(Status.PASS, "Set to account");

            transferFundsFormPage.transfer();
            test.log(Status.PASS, "Transfer");

            Assertions.assertTrue(transferFundsFormPage.getTransferResult().contains("Transfer Complete"));
            test.log(Status.PASS, "Validate succesfull account overview");


        } catch (Exception error) {
            test.log(Status.FAIL, "Report error in test " + error.getMessage());
            error.printStackTrace();
        }
    }

    @AfterEach
    public void close() {
        transferFundsFormPage.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
