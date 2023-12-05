package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {
    private final By searchBar1 = By.name("search");
    private final By categorySelector = By.name("category_id");
    private final By checkDescription = By.id("description");
    private final By searchButton1 = By.id("button-search");
    private final By sortSelector = By.id("input-sort");
    private final By showLimitSelector = By.id("input-limit");
    private final By listViewButton = By.id("list-view");
    private final By gridViewButton = By.id("grid-view");
    private final By firstRowResults = By.xpath("/html/body/div[2]/div/div/div[3]");
    private final By firstResult = By.className("product-thumb");
    private final By succesfullAlert = By.className("alert-success");

    private final By firstResultCart = By.xpath("/html/body/div[2]/div/div/div[3]/div/div/div[2]/div[2]/button[1]");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void addFirstResultToCart() {
        click(firstResultCart);
    }

    public String getSuccesfullAlertMessage() {
        return getText(succesfullAlert);
    }

}
