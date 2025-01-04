package pjatk.edu.pl.pokemon_client.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FindByIdFormTest {
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
    public void findByIdFormTest() {
        String idInput = "5";
        FindByIdFormPage findByIdFormPage = new FindByIdFormPage(driver)
                .open()
                .fillInIdInput(idInput);
        DisplayListPage displayListPage = findByIdFormPage.submitForm();

        assertTrue(displayListPage.isBackButtonVisible());
        assertTrue(displayListPage.isTableVisible());
    }
}
