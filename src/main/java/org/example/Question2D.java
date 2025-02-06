package org.example;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Alexis Feron / Jérémy Ducourthial
 */
public class Question2D {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void testNavigationDansLesResultats() {
        // Aller sur DuckDuckGo
        driver.get("https://duckduckgo.com/");

        // Trouver la barre de recherche et effectuer une recherche
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Sébastien Salva");
        searchBox.submit();

        // Attendre que les résultats s'affichent
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".react-results--main")));

        // Récupérer les liens des résultats
        List<WebElement> resultLinks = driver.findElements(By.cssSelector("h2"));


        System.out.println("🔍 Nombre de résultats trouvés : " + resultLinks.size());

        // Parcourir chaque lien, cliquer dessus et afficher le titre
        for (int i = 0; i < resultLinks.size(); i++) {
            WebElement link = resultLinks.get(i);

            // Ouvrir le lien dans la même page
            link.click();

            // Attendre que la page se charge
            wait.until(ExpectedConditions.titleIs(driver.getTitle()));

            // Afficher le titre de la page
            String pageTitle = driver.getTitle();
            System.out.println("Titre de la page " + (i + 1) + " : " + pageTitle);

            // Vérifier que la page a bien changé
            assertTrue(pageTitle.length() > 0, "La page n'a pas de titre !");

            // Revenir en arrière pour afficher les autres résultats
            driver.navigate().back();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".react-results--main")));

            // Recharger la liste des résultats car la page a été rechargée
            resultLinks = driver.findElements(By.cssSelector(".result__title a"));
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
