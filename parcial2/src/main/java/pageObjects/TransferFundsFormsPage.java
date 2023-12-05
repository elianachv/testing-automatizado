package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsFormsPage extends BasePage {

    private final By amountInput = By.id("amount");

    private final By selectFromAccount = By.id("fromAccountId");
    private final By selectToAccount = By.id("toAccountId");
    private final By toAccountIdOption = By.xpath("//*[@id=\"toAccountId\"]/option[2]");

    private final By transferButton = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div[2]/input");

    private final By resultTransferMessage = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1");

    public String getTransferResult() {
        return getText(resultTransferMessage);
    }

    public void setAmountToTransfer(String amount) {
        sendText(amount, amountInput);
    }

    public void setToAccount() {
        click(selectToAccount);
        click(toAccountIdOption);
    }

    public void transfer() {
        click(transferButton);
    }

    public TransferFundsFormsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
