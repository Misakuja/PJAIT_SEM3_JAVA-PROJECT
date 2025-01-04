package pjatk.edu.pl.pokemon_client.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UpdateFormTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        this.driver.close();
    }

    @Test
    public void updateFormTest() {
        String idInputText = "6";
        String nameInputText = "TestUpdate";
        String apiInputText = "123321";

        UpdateFormPage updateFormPage = new UpdateFormPage(driver)
                .open()
                .fillInIdInput(idInputText)
                .fillInNameInput(nameInputText)
                .fillInApiIdInput(apiInputText);
        DisplayListPage displayListPage = updateFormPage.submitForm();

        assertTrue(displayListPage.isBackButtonVisible());
        assertTrue(displayListPage.isTableVisible());
    }
}
