package pjatk.edu.pl.pokemon_client.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AddFormTest {
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
    public void addFormTest() {
        String nameInputText = "Test";
        String apiInputText = "10000";
        AddFormPage addFormPage = new AddFormPage(driver)
                .open()
                .fillInNameInput(nameInputText)
                .fillInApiIdInput(apiInputText);
        DisplayListPage displayListPage = addFormPage.submitForm();

        assertTrue(displayListPage.isBackButtonVisible());
        assertTrue(displayListPage.isTableVisible());
    }

}
