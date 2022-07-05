package com.example.demo;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class SeleniumTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        System.setProperty("webdriver.chrome.driver", "/home/vasilii/data/lexpro-gitlab/lexpro/src/main/resources/chromedriver");

        options.addArguments("--ignore-certificate-errors");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        // options.addArguments( "--disable-gpu", "--headless", "--no-sandbox");
        cap.setCapability(ChromeOptions.CAPABILITY, options);


        WebDriver driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void selenium() {
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.findElement(By.cssSelector(".webix_button")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".webix_button"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".webix_list_item:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".webix_list_item:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".webix_view:nth-child(1) > .webix_template")).click();
        driver.findElement(By.cssSelector(".webix_view:nth-child(4) > .webix_view:nth-child(2) > .webix_template")).click();
    }
}
