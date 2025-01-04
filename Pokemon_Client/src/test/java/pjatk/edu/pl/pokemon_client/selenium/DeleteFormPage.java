package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteFormPage {
    private final WebDriver driver;

    @FindBy(id = "itemId")
    private WebElement idInput;

    @FindBy(id = "itemSubmit")
    private WebElement submitInput;

    public DeleteFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DeleteFormPage open() {
        this.driver.get("http://localhost:8083/client/item/delete");
        return this;
    }

    public DeleteFormPage fillInIdInput(String text) {
        this.idInput.clear();
        this.idInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
