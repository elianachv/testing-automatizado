package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    private final By name = By.id("input-firstname");
    private final By lastName = By.id("input-lastname");
    private final By email = By.id("input-email");
    private final By telephone = By.id("input-telephone");
    private final By password = By.id("input-password");
    private final By confirmPassword = By.id("input-confirm");
    private final By newsletterOptionYes = By.xpath("/html/body/div[2]/div/div/form/fieldset[3]/div/div/label[1]");
    private final By newsletterOptionNo = By.xpath("/html/body/div[2]/div/div/form/fieldset[3]/div/div/label[2]");
    private final By checkPrivatePolicy = By.name("agree");
    private final By continueButton = By.xpath("/html/body/div[2]/div/div/form/div/div/input[2]");

    private final By welcomeRegisterMessage = By.id("content");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void fulfillForm(String name, String lastname, String email, String telephone, String password, String confirmPassword) throws InterruptedException {
        sendText(name, this.name);
        sendText(lastname, this.lastName);
        sendText(email, this.email);
        sendText(telephone, this.telephone);
        sendText(password, this.password);
        sendText(confirmPassword, this.confirmPassword);
    }

    public void suscribeToNewsletter(boolean value) {

        if (value)
            click(newsletterOptionYes);
        else
            click(newsletterOptionNo);
    }

    public void acceptPrivacyPolice() {
        click(checkPrivatePolicy);
    }

    public void continueRegisterProcess() {
        click(continueButton);
    }

    public String getWelcomeRegisterMessageText(){
        return getText(welcomeRegisterMessage);
    }

}
