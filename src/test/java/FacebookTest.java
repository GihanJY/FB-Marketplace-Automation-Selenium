import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FacebookTest {
    private WebDriver driver;
    private Facebook fb;
    private WebDriverWait wait;

    @Before
    public void makeObject(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://web.facebook.com/login.php?_rdc=1&_rdr");
        driver.manage().window().maximize();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("0729418080");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass"))).sendKeys("Student@123");
        wait.until(ExpectedConditions.elementToBeClickable(By.name("login"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Marketplace')]"))).click();

        fb = new Facebook();
    }
/*

    @Test
    @Order(1)
    public void validTest1(){
        fb.searchItems("Laptop", driver);
    }

    @Test
    @Order(2)
    public void validTest2(){
        fb.searchItems("Honda CBR", driver);
    }
*/

    @Test
    @Order(3)
    public void invalidTest1(){
        fb.searchItems("@#$%^&*", driver);
        WebElement actualElement = driver.findElement(By.cssSelector("div[class='x1n2onr6 x1ja2u2z x78zum5 x2lah0s xl56j7k x6s0dn4 xozqiw3 x1q0g3np xi112ho x17zwfj4 x585lrc x1403ito x972fbf xcfux6l x1qhh985 xm0m39n x9f619 xn6708d x1ye3gou xtvsq51 x1r1pt67']"));
        boolean isElementPresent = actualElement.isDisplayed();
        Assert.assertEquals(true, isElementPresent);
    }

/*    @Test
    @Order(4)
    public void invalidTest2(){
        fb.searchItems("\"'; DROP DATABASE users;--\"", driver);
    }

    @Test
    @Order(5)
    public void edgeTest1(){
        fb.searchItems("Universe black hole", driver);
    }

    @Test
    @Order(6)
    public void edgeTest2(){
        fb.searchItems("IpHoNe", driver);
    }*/

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
