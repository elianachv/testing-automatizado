package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountFormPage extends BasePage {

    private final By accountTypeSelector = By.xpath("//*[@id=\"type\"]");
    private final By accountTypeSavings = By.xpath("//*[@id=\"type\"]/option[2]");
    private final By accountTypeCheckings = By.xpath("//*[@id=\"type\"]/option[1]");
    private final By createButton = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input");

    private final By creationResultTitle = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1");
    private final By creationResultMessage = By.xpath("//*[@id=\"rightPanel\"]/div/div/p[1]");

    public void selectAccountType(String type) {

        click(accountTypeSelector);

        if (type.equals("SAVINGS")) {
            click(accountTypeSavings);
        } else if (type.equals("CHEKING")) {
            click(accountTypeCheckings);
        } else {
            System.out.println("Error");
        }
    }

    public void createAccount() {

        click(createButton);
    }

    public String getResultTitle() {
        return getText(creationResultTitle);
    }
    public String getResultMessage() {
        return getText(creationResultMessage);
    }

    public NewAccountFormPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
