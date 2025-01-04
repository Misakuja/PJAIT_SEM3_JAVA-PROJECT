package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindByIdFormPage {
    private final WebDriver driver;

    @FindBy(id = "itemId")
    private WebElement idInput;

    @FindBy(id = "itemSubmit")
    private WebElement submitInput;

    public FindByIdFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FindByIdFormPage open() {
        this.driver.get("http://localhost:8083/client/item/find/id");
        return this;
    }

    public FindByIdFormPage fillInIdInput(String text) {
        this.idInput.clear();
        this.idInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
