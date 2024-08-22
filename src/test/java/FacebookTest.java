import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.jupiter.api.Order;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class FacebookTest {
    private WebDriver driver;
    private Facebook fb;
    private WebDriverWait wait;

    // Sets up the WebDriver, opens the Facebook login page, logs in, and navigates to the Marketplace.
    @Before
    public void makeObject(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://web.facebook.com/login.php?_rdc=1&_rdr");
        driver.manage().window().maximize();

        // Enter login credentials and click login button.
        FacebookCredentials fbCred = new FacebookCredentials();
        String email = fbCred.getUsername();
        String password = fbCred.getPassword();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("login"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Marketplace')]"))).click();

        fb = new Facebook();
    }

    // Test case 1: Valid search for the item "Laptop"
    @Test
    @Order(1)
    public void validTest1(){
        fb.searchItems("Laptop", driver);
    }

    // Test case 2: Valid search for the item "Honda CBR"
    @Test
    @Order(2)
    public void validTest2(){
        fb.searchItems("Honda CBR", driver);
    }

    // Test case 3: Invalid search using special characters.
    @Test
    @Order(3)
    public void invalidTest1(){
        fb.searchItems("@#$%^&*", driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement actualElement = driver.findElement(By.xpath("//span[contains(text(),'Browse Marketplace')]"));
        boolean isElementPresent = actualElement.isDisplayed();
        Assert.assertTrue(isElementPresent);
    }

    // Test case 4: SQL query like input.
    @Test
    @Order(4)
    public void invalidTest2(){
        fb.searchItems("\"'; DROP DATABASE users;--\"", driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement actualElement = driver.findElement(By.xpath("//span[contains(text(),'Browse Marketplace')]"));
        boolean isElementPresent = actualElement.isDisplayed();
        Assert.assertTrue(isElementPresent);
    }

    // Test case 5: Edge case search with nonsensical input.
    @Test
    @Order(5)
    public void edgeTest1(){
        fb.searchItems("Universe black dog hole", driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement actualElement = driver.findElement(By.xpath("//span[contains(text(),'Browse Marketplace')]"));
        boolean isElementPresent = actualElement.isDisplayed();
        Assert.assertTrue(isElementPresent);
    }

    // Test case 6: Edge case search with mixed-case input.
    @Test
    @Order(6)
    public void edgeTest2(){
        fb.searchItems("IpHoNe", driver);
    }

    // Logs out of Facebook and closes the browser
    @After
    public void finalMethod(){
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(5) > div:nth-child(1) > span:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > svg:nth-child(1) > g:nth-child(2) > image:nth-child(1)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-nocookies='true'] div[class='x6s0dn4 x1q0q8m5 x1qhh985 xu3j5b3 xcfux6l x26u7qi xm0m39n x13fuv20 x972fbf x9f619 x78zum5 x1q0g3np x1iyjqo2 xs83m0k x1qughib xat24cr x11i5rnm x1mh8g0r xdj266r xeuugli x18d9i69 x1sxyh0 xurb0ha xexx8yu x1n2onr6 x1ja2u2z x1gg8mnh']"))).click();
        driver.quit();
    }
}
