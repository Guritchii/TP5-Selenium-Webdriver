package org.example;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Alexis Feron / Jérémy Ducourthial
 */
public class TPsel23 {

    public static void main(String[] args) {

    }

    @Test
    public void test(){
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub

        // Creation d'une nouvelle instance du driver de firefox
        WebDriver driver;
        //System.setProperty("webdriver.gecko.driver", " ** A CHANGER ** /Users/ssalva/Downloads/geckodriver");
        driver =new FirefoxDriver();
        //driver = new ChromeDriver();

        //Attente implicite de 3 secondes
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // visite de Google
        driver.get("https://duckduckgo.com/");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("https://duckduckgo.com/");

        // Recherche d'un element ici par son nom,
        //peut etre fait par son id, par sa classe, par css, par xpath
        WebElement element = driver.findElement(By.name("q"));

        // On rentre le texte suivant dans l'element Web
        element.sendKeys("Sébastien Salva");

        //exemple attente explicite qui peut Ãªtre diffÃ©rente de la premiÃ¨re
        Wait<WebDriver> wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));

        // On submit le formulaire, Webriver le recherche pour vous
        element.submit();

        //attente que la page rÃ©sultat s'affiche avec attente explicite
        // vous pouvez aussi ne garder que l'implicite en commentant
        wait2.until(d -> driver.findElement(By.id("react-layout")).isDisplayed());

        // on affiche le titre de la page
        System.out.println("Titre : " + driver.getTitle());

        // Attente de chargement, timeout aprÂs 10 seconds
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement firstResult = driver.findElement(By.cssSelector("h2"));
        firstResult.click();
        System.out.println("Nouvelle page : " + driver.getTitle());

        Assertions.assertTrue(driver.getTitle().contains("Sébastien Salva"));

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            FileUtils.copyFile(scrFile, new File("screenshot.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Fermeture de Firefox
        driver.quit();
    }

}
