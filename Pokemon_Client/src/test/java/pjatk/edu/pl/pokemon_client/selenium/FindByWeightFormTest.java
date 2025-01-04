package pjatk.edu.pl.pokemon_client.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FindByWeightFormTest {
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
    public void findByWeightFormTest() {
        String heightInput = "69";
        FindByWeightFormPage findByWeightFormPage = new FindByWeightFormPage(driver)
                .open()
                .fillInWeightInput(heightInput);
        DisplayListPage displayListPage = findByWeightFormPage.submitForm();

        assertTrue(displayListPage.isBackButtonVisible());
        assertTrue(displayListPage.isTableVisible());
    }
}
