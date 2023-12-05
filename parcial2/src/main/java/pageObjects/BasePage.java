package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    // Header
    protected By logo = By.xpath("//*[@id=\"topPanel\"]/a[2]/img");

    // Aside - Pre Login
    protected By registerButton = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");
    protected By userNameInput = By.name("username");
    protected By passwordInput = By.name("password");
    protected By loginButton = By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");


    // Aside Post Login
    protected By createAccountOption = By.partialLinkText("Open New Account");
    protected By accountOverviewOption = By.partialLinkText("Accounts Overview");
    protected By transferFundsOption = By.partialLinkText("Transfer Funds");
    protected By billPayOption = By.partialLinkText("Bill Pay");
    protected By findTransactionsOption = By.partialLinkText("Find Transactions");
    protected By updateContactInfo = By.partialLinkText("Update Contact Info");
    protected By requestLoanOption = By.partialLinkText("Request Loan");
    protected By logOutOption =By.partialLinkText("Log Out");


    public static WebDriver driver;
    public static WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        BasePage.driver = driver;
        BasePage.wait = wait;
    }

    public void setUp() {
        driver.manage().window().maximize();
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }


    protected WebElement findElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    protected void sendText(String inputText, By locator) {
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(inputText);
    }

    protected void sendKey(CharSequence key, By locator) {
        this.findElement(locator).sendKeys(key);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    protected void clickLogo() {
        click(logo);
    }

    protected void routeHome() {
        clickLogo();
    }

    protected void routeRegister() {
        click(registerButton);
    }

    protected void routeOpenNewAccount() {
        click(createAccountOption);
    }

    protected void routeAccountOverview() {
        click(accountOverviewOption);
    }

    protected void routeTransferFunds() {
        click(transferFundsOption);
    }

    protected void routeBillPay() {
        click(billPayOption);
    }

    protected void routeFindTransaction() {
        click(findTransactionsOption);
    }

    protected void routeUpdateContactInfo() {
        click(updateContactInfo);
    }

    protected void routeRequestLoan() {
        click(requestLoanOption);
    }

    protected void login(String username, String password) {
        sendText(username, userNameInput);
        sendText(password, passwordInput);
        click(loginButton);
    }

    protected void logout() {
        click(logOutOption);
    }

    protected String getText(By locator) {
        return this.findElement(locator).getText();
    }

}