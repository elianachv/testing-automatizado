package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsOverviewPage extends BasePage {

    private final By accountNote = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");
    private final By firstAccountRow = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");
    private final By accountsDetailTitle = By.xpath("//*[@id=\"rightPanel\"]/div/div[1]/h1");
    private final By goButton = By.xpath("//*[@id=\"rightPanel\"]/div/div[2]/form/table/tbody/tr[3]/td[2]/input");


    public String getAccountNote() {
        return getText(accountNote);
    }

    public String getAccountDetailTitle() {
        return getText(accountsDetailTitle);
    }

    public void selectAccount() {
        click(firstAccountRow);
    }

    public void searchAccountTransactions() {
        click(goButton);
    }

    public AccountsOverviewPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
