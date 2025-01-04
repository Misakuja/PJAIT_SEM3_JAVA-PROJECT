package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindByAccuracyFormPage {
    private final WebDriver driver;

    @FindBy(id = "moveAccuracy")
    private WebElement accuracyInput;

    @FindBy(id = "submit")
    private WebElement submitInput;

    public FindByAccuracyFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FindByAccuracyFormPage open() {
        this.driver.get("http://localhost:8083/client/move/find/accuracy");
        return this;
    }

    public FindByAccuracyFormPage fillInAccuracyInput(String text) {
        this.accuracyInput.clear();
        this.accuracyInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
