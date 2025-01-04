package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateFormPage {
    private final WebDriver driver;

    @FindBy(id = "itemId")
    private WebElement IdInput;

    @FindBy(id = "itemApiId")
    private WebElement apiIdInput;

    @FindBy(id = "itemName")
    private WebElement nameInput;

    @FindBy(id = "itemSubmit")
    private WebElement submitInput;

    public UpdateFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UpdateFormPage open() {
        this.driver.get("http://localhost:8083/client/item/update");
        return this;
    }

    public UpdateFormPage fillInIdInput(String text) {
        this.apiIdInput.clear();
        this.nameInput.sendKeys(text);
        return this;
    }

    public UpdateFormPage fillInNameInput(String text) {
        this.nameInput.sendKeys(text);
        return this;
    }

    public UpdateFormPage fillInApiIdInput(String text) {
        this.apiIdInput.clear();
        this.apiIdInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
