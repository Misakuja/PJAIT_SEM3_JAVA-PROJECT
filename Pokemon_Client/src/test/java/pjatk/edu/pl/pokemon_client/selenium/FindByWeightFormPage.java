package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindByWeightFormPage {
    private final WebDriver driver;

    @FindBy(id = "pokemonWeight")
    private WebElement weightInput;

    @FindBy(id = "submit")
    private WebElement submitInput;

    public FindByWeightFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FindByWeightFormPage open() {
        this.driver.get("http://localhost:8083/client/pokemon/find/weight");
        return this;
    }

    public FindByWeightFormPage fillInWeightInput(String text) {
        this.weightInput.clear();
        this.weightInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
