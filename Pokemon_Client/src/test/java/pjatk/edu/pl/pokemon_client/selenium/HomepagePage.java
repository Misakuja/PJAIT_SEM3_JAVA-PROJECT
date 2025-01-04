package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomepagePage {
    private final WebDriver driver;

    @FindBy(id = "buttonContainer")
    private WebElement buttonContainer;

    public HomepagePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomepagePage open() {
        this.driver.get("http://localhost:8083/");
        return this;
    }

    protected boolean areButtonsVisible() {
        return buttonContainer.isDisplayed();
    }


}
