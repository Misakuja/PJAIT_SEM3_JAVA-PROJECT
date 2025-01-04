package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindByPpFormPage {
    private final WebDriver driver;

    @FindBy(id = "movePp")
    private WebElement ppInput;

    @FindBy(id = "submit")
    private WebElement submitInput;

    public FindByPpFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FindByPpFormPage open() {
        this.driver.get("http://localhost:8083/client/move/find/pp");
        return this;
    }

    public FindByPpFormPage fillInPpInput(String text) {
        this.ppInput.clear();
        this.ppInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
