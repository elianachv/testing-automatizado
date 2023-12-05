package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {


    protected By logo = By.id("logo");
    protected By searchBar = By.name("search");
    protected By searchButton = By.xpath("/html/body/header/div/div/div[2]/div/span/button");
    protected By myAccountButton = By.xpath("/html/body/nav/div/div[2]/ul/li[2]/a");
    protected By registerButton = By.xpath("/html/body/nav/div/div[2]/ul/li[2]/ul/li[1]/a");
    protected By loginButton = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a");

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

    public void searchProduct(String querie) throws InterruptedException {
        sendText(querie, searchBar);
        click(searchButton);
    }

    protected WebElement findElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    protected void sendText(String inputText, By locator) throws InterruptedException {
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(inputText);
    }

    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        this.findElement(locator).sendKeys(key);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    protected void clickLogo() {
        click(logo);
    }

    protected void routeRegister() {
        click(myAccountButton);
        click(registerButton);
    }

    protected void routeLogin() {
        click(myAccountButton);
        click(loginButton);
    }

    protected String getText(By locator) {
        return this.findElement(locator).getText();
    }

}