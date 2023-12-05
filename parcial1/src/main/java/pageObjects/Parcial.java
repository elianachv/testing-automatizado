package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Parcial extends BasePage {
    protected final By nombreRegistro = By.id("input-firstname");
    protected final By apellidoRegistro = By.id("input-lastname");
    protected final By emailRegistro = By.id("input-email");

    protected final By telefonoRegistro = By.id("input-telephone");
    protected final By claveRegistro = By.id("input-password");
    protected final By continuarRegistro = By.xpath("/html/body/div[2]/div/div/form/div/div/input[2]");
    protected final By mensajeOkRegistro = By.id("content");
    protected final By claveRegistro2 = By.id("input-confirm");

    protected final By sucribirseRegistro = By.xpath("/html/body/div[2]/div/div/form/fieldset[3]/div/div/label[1]");
    protected final By privacidadRegistro = By.name("agree");

    public Parcial(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }



}
