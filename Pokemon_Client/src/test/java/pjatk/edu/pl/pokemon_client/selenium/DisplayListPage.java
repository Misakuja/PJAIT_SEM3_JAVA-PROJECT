package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DisplayListPage {
    private final WebDriver driver;

    @FindBy(id = "table")
    private WebElement table;

    @FindBy(id = "buttonBox")
    private WebElement buttonContainer;

    @FindBy(css = "#table tbody tr")
    private List<WebElement> tableRows;

    public DisplayListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DisplayListPage open() {
        this.driver.get("http://localhost:8083/client/item");
        return this;
    }

    protected boolean isTableVisible() {
        return table.isDisplayed();
    }

    protected boolean isBackButtonVisible() {
        return buttonContainer.isDisplayed();
    }
}
