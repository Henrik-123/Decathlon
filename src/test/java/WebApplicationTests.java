import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebApplicationTests {

    @Test
    public void testOpenWebApplication(){

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");

        String actual = driver.getTitle();
        String expected = "Track & Field Calculator";

        assertEquals(expected, actual);
    }
}
