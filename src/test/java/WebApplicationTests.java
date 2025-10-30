import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebApplicationTests {

    @Test
    public void testOpenWebAppChrome(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");

        String actual = driver.getTitle();
        String expected = "Track & Field Calculator";

        driver.quit();
        assertEquals(expected, actual);
    }
    @Test
    public void testOpenWebAppFireFox(){
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");

        String actual = driver.getTitle();
        String expected = "Track & Field Calculator";

        driver.quit();
        assertEquals(expected, actual);
    }
    @Test
    public void addCompetitorDecathlonChrome(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");

        WebElement nameBox = driver.findElement(By.id("name"));
        nameBox.sendKeys("Ben");

        WebElement addButton = driver.findElement(By.id("add"));
        addButton.click();

        WebElement competitor = driver.findElement(By.xpath("/html/body/div[4]/table/tbody/tr[1]/td[1]"));
        String actual = competitor.getText();

        driver.quit();
        assertEquals("Ben",actual);
    }
    @Test
    public void addCompetitorDecathlonFirefox(){
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");

        WebElement nameBox = driver.findElement(By.id("name"));
        nameBox.sendKeys("Ben");

        WebElement addButton = driver.findElement(By.id("add"));
        addButton.click();

        WebElement competitor = driver.findElement(By.xpath("/html/body/div[4]/table/tbody/tr[1]/td[1]"));
        String actual = competitor.getText();

        driver.quit();

        assertEquals("Ben",actual);
    }
    @Test
    public void changeCompetitionToHeptathlonChrome(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");

        WebElement dropMenu = driver.findElement(By.id("mode"));
        Select select = new Select(dropMenu);

        select.selectByVisibleText("Heptathlon");
        WebElement selectedOption = select.getFirstSelectedOption();

        String actual = selectedOption.getText();
        driver.quit();

        assertEquals("Heptathlon", actual);
    }
    @Test
    public void changeCompetitionToHeptathlonFirefox(){
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");

        WebElement dropMenu = driver.findElement(By.id("mode"));
        Select select = new Select(dropMenu);

        select.selectByVisibleText("Heptathlon");
        WebElement selectedOption = select.getFirstSelectedOption();

        String actual = selectedOption.getText();
        driver.quit();

        assertEquals("Heptathlon", actual);
    }
}
