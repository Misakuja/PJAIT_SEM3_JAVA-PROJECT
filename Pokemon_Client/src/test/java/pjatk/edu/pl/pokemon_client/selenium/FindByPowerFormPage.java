package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindByPowerFormPage {
    private final WebDriver driver;

    @FindBy(id = "movePower")
    private WebElement powerInput;

    @FindBy(id = "submit")
    private WebElement submitInput;

    public FindByPowerFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FindByPowerFormPage open() {
        this.driver.get("http://localhost:8083/client/move/find/power");
        return this;
    }

    public FindByPowerFormPage fillInPowerInput(String text) {
        this.powerInput.clear();
        this.powerInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
