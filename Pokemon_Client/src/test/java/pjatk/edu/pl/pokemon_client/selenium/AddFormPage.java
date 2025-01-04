package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFormPage {
    private final WebDriver driver;

    @FindBy(id = "itemApiId")
    private WebElement apiIdInput;

    @FindBy(id = "itemName")
    private WebElement nameInput;

    @FindBy(id = "itemSubmit")
    private WebElement submitInput;

    public AddFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AddFormPage open() {
        this.driver.get("http://localhost:8083/client/item/add");
        return this;
    }

    public AddFormPage fillInNameInput(String text) {
        this.nameInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInApiIdInput(String text) {
        this.apiIdInput.clear();
        this.apiIdInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
