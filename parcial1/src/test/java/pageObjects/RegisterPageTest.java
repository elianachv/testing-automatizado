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

    Parcial parcial;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reports/Parcial.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() throws IOException {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        parcial = new Parcial(driver, wait);
        parcial.setUp();
        parcial.getUrl("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @Test
    @Tag("Registro")
    @Tag("ALL")
    public void registroExitoso() {
        ExtentTest test = extent.createTest("Registro Ok");

        try {
            test.log(Status.INFO, "Inicia test");

            test.log(Status.PASS, "Acceder a registro");
            parcial.getUrl("https://opencart.abstracta.us/index.php?route=account/register");

            test.log(Status.PASS, "Llenar formulario");
            parcial.sendText("test", parcial.nombreRegistro);
            parcial.sendText("test", parcial.apellidoRegistro);
            parcial.sendText("test@parcial1.com", parcial.emailRegistro);
            parcial.sendText("123456789", parcial.telefonoRegistro);
            parcial.sendText("1234", parcial.claveRegistro);
            parcial.sendText("1234", parcial.claveRegistro2);

            test.log(Status.PASS, "Aceptar politica privacidad");
            parcial.click(parcial.privacidadRegistro);

            test.log(Status.PASS, "Registrar");
            parcial.click(parcial.continuarRegistro);

            Assertions.assertTrue(parcial.getText(parcial.mensajeOkRegistro).contains("Congratulations!"));
            test.log(Status.PASS, "Validar registro");

        } catch (Exception error) {
            test.log(Status.FAIL, "Error " + error.getMessage());
        }
    }

    @Test
    @Tag("Buscar")
    @Tag("ALL")
    public void agregarIphone() {
        ExtentTest test = extent.createTest("Agergar Iphone al carrito");

        try {
            test.log(Status.INFO, "Inicia test");

            parcial.sendText("iphone",parcial.searchBar);
            test.log(Status.PASS, "Buscar iphone");

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
        parcial.close();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
