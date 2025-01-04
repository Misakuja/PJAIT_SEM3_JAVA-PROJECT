package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindByBaseExperienceFormPage {
    private final WebDriver driver;

    @FindBy(id = "pokemonBaseExperience")
    private WebElement baseExperienceInput;

    @FindBy(id = "submit")
    private WebElement submitInput;

    public FindByBaseExperienceFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FindByBaseExperienceFormPage open() {
        this.driver.get("http://localhost:8083/client/pokemon/find/baseExperience");
        return this;
    }

    public FindByBaseExperienceFormPage fillInBaseExperienceInput(String text) {
        this.baseExperienceInput.clear();
        this.baseExperienceInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
