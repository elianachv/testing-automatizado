package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    private final By name = By.id("customer.firstName");
    private final By lastName = By.id("customer.lastName");
    private final By address = By.id("customer.address.street");
    private final By city = By.id("customer.address.city");
    private final By state = By.id("customer.address.state");
    private final By zip = By.id("customer.address.zipCode");
    private final By phone = By.id("customer.phoneNumber");
    private final By ssn = By.id("customer.ssn");
    private final By username = By.id("customer.username");
    private final By password = By.id("customer.password");
    private final By confirmPassword = By.id("repeatedPassword");
    private final By continueButton = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");

    private final By welcomeRegisterTitle = By.xpath("//*[@id=\"rightPanel\"]/h1");
    private final By welcomeRegisterMessage = By.xpath("//*[@id=\"rightPanel\"]/p");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void fulfillForm(String name, String lastname, String address, String city, String state, String zip, String phone, String ssn, String username, String password, String confirmPassword) throws InterruptedException {
        sendText(name, this.name);
        sendText(lastname, this.lastName);
        sendText(address, this.address);
        sendText(city, this.city);
        sendText(state, this.state);
        sendText(zip, this.zip);
        sendText(phone, this.phone);
        sendText(ssn, this.ssn);
        sendText(username, this.username);
        sendText(password, this.password);
        sendText(confirmPassword, this.confirmPassword);
    }

    public void continueRegisterProcess() {
        click(continueButton);
    }

    public String getWelcomeRegisterMessageText() {
        return getText(welcomeRegisterMessage);
    }

    public String getWelcomeRegisterTitleText() {
        return getText(welcomeRegisterTitle);
    }

}
