package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindByHeightFormPage {
    private final WebDriver driver;

    @FindBy(id = "pokemonHeight")
    private WebElement heightInput;

    @FindBy(id = "submit")
    private WebElement submitInput;

    public FindByHeightFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FindByHeightFormPage open() {
        this.driver.get("http://localhost:8083/client/pokemon/find/height");
        return this;
    }

    public FindByHeightFormPage fillInHeightInput(String text) {
        this.heightInput.clear();
        this.heightInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
