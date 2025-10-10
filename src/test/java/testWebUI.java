import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class testWebUI {

    private WebDriver driver;
    private String resulttt;
    private String event;

    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Given("User navigates to the registration page")
    public void userNavigatesToTheRegistrationPage() {
        String browser = System.getenv("BROWSER") != null ? System.getenv("BROWSER") : "chrome";
        String url = System.getenv("APP_URL") != null ? System.getenv("APP_URL") : "http://localhost:8080/";

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(url);
    }

    @Given("Add competitor {string}")
    public void enteredFirstName(String name) {
        By nameFieldLocator = By.id("name");
        WebElement nameField = waitForElement(nameFieldLocator);
        nameField.sendKeys(name);
    }

    @And("Add copetion")
    public void addCopetion() {
        driver.findElement(By.xpath("//*[@id=\"add\"]")).click();
    }

    @And("Enter result name {string}")
    public void enterResultName(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"name2\"]")).sendKeys(arg0);
    }

    @And("Select Event {string}")
    public void selectEvent(String eventName) {
        this.event = eventName;
        WebElement eventDropdown = driver.findElement(By.id("event"));
        Select select = new Select(eventDropdown);
        select.selectByVisibleText(eventName); // يختار الحدث حسب النص الظاهر
    }

    // -----------------------------------
    @And("Enter raw score {string}")
    public void enterRawScore(String arg1) {
        resulttt = arg1;
        driver.findElement(By.xpath("//*[@id=\"raw\"]")).sendKeys(arg1);
    }

    @Then("Save Score")
    public void saveScore() {
        driver.findElement(By.xpath("//*[@id=\"save\"]")).click();
    }

    @Then("Score rank")
    public void scoreRank() {
        System.out.println("Recorded score for event " + event + " = " + resulttt);
    }

    @Then("Use Score rank in calculation")
    public void useScoreRankInCalculation() {
        double P = Double.parseDouble(resulttt);

        Map<String, double[]> eventParams = new HashMap<>();
        Map<String, String> eventType = new HashMap<>();

        // Decathlon events
        eventParams.put("100m (s)", new double[]{25.4347, 18, 1.81});
        eventType.put("100m (s)", "track");

        eventParams.put("Long Jump (cm)", new double[]{0.14354, 220, 1.4});
        eventType.put("Long Jump (cm)", "field");

        eventParams.put("Shot Put (m)", new double[]{51.39, 1.5, 1.05});
        eventType.put("Shot Put (m)", "field");

        eventParams.put("400m (s)", new double[]{1.53775, 82, 1.81});
        eventType.put("400m (s)", "track");

        eventParams.put("1500m (s)", new double[]{0.03768, 480, 1.85});
        eventType.put("1500m (s)", "track");

        eventParams.put("110m Hurdles (s)", new double[]{5.74352, 28.5, 1.92});
        eventType.put("110m Hurdles (s)", "track");

        eventParams.put("High Jump (cm)", new double[]{0.8465, 75, 1.42});
        eventType.put("High Jump (cm)", "field");

        eventParams.put("Pole Vault (cm)", new double[]{0.2797, 100, 1.35});
        eventType.put("Pole Vault (cm)", "field");

        eventParams.put("Discus Throw (m)", new double[]{12.91, 4, 1.1});
        eventType.put("Discus Throw (m)", "field");

        eventParams.put("Javelin Throw (m)", new double[]{10.14, 7, 1.08});
        eventType.put("Javelin Throw (m)", "field");

        // Heptathlon events
        eventParams.put("hep100mHurdles (s)", new double[]{9.23076, 26.7, 1.835});
        eventType.put("hep100mHurdles (s)", "track");

        eventParams.put("200m (s)", new double[]{4.99087, 42.5, 1.81});
        eventType.put("200m (s)", "track");

        eventParams.put("800m (s)", new double[]{0.11193, 254, 1.88});
        eventType.put("800m (s)", "track");

        eventParams.put("hepHighJump (cm)", new double[]{1.84523, 75, 1.348});
        eventType.put("hepHighJump (cm)", "field");

        eventParams.put("hepJavelinThrow (m)", new double[]{15.9803, 3.8, 1.04});
        eventType.put("hepJavelinThrow (m)", "field");

        eventParams.put("hepLongJump (cm)", new double[]{0.188807, 210, 1.41});
        eventType.put("hepLongJump (cm)", "field");

        eventParams.put("hepShotPut (m)", new double[]{56.0211, 1.5, 1.05});
        eventType.put("hepShotPut (m)", "field");

        // ----------------
        double[] params = eventParams.get(event);
        if (params == null) {
            System.out.println("Event not found: " + event);
            return;
        }

        double A = params[0];
        double B = params[1];
        double C = params[2];

        double points;
        if (eventType.get(event).equals("track")) {
            points = A * Math.pow(B - P, C);
        } else { // field
            points = A * Math.pow(P - B, C);
        }

        int finalPoints = (int) points;
        System.out.println("Event: " + event + " | Performance: " + P + " | Points: " + finalPoints);
    }
}

