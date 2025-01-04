package pjatk.edu.pl.pokemon_client.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindByApiIdFormPage {
    private final WebDriver driver;

    @FindBy(id = "itemApiId")
    private WebElement apiIdInput;

    @FindBy(id = "itemSubmit")
    private WebElement submitInput;

    public FindByApiIdFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FindByApiIdFormPage open() {
        this.driver.get("http://localhost:8083/client/item/find/apiId");
        return this;
    }

    public FindByApiIdFormPage fillInApiIdInput(String text) {
        this.apiIdInput.clear();
        this.apiIdInput.sendKeys(text);
        return this;
    }

    public DisplayListPage submitForm() {
        this.submitInput.click();
        return new DisplayListPage(driver);
    }
}
