package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Question3B {
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

        // Attendre que les résultats apparaissent
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("article[data-testid='result']")));

        // Récupérer les liens des résultats avec le bon sélecteur CSS
        List<WebElement> resultLinks = driver.findElements(By.cssSelector("h2"));
        System.out.println("🔍 Nombre de résultats trouvés : " + resultLinks.size());

        // Vérifier si des résultats existent
        if (resultLinks.isEmpty()) {
            System.out.println("⚠️ Aucun résultat trouvé. Vérifiez votre connexion internet ou le sélecteur CSS.");
            return;
        }

        String folderPath ="screenshots";
        File screenshotFolder = new File(folderPath);

        // Parcourir chaque lien et afficher le titre
        for (int i = 0; i < Math.min(resultLinks.size(), 10); i++) { // Limite à 10 pour éviter des erreurs
            // Recharger la liste des résultats après chaque retour
            resultLinks = driver.findElements(By.cssSelector("article[data-testid='result'] a[data-testid='result-title-a']"));

            // Sélectionner le lien actuel
            WebElement link = resultLinks.get(i);

            // Attendre que le lien soit cliquable
            wait.until(ExpectedConditions.elementToBeClickable(link));

            // Récupérer et afficher le texte du lien avant de cliquer
            System.out.println("🔗 Résultat " + (i + 1) + " : " + link.getText());

            // Cliquer sur le lien
            link.click();

            // Attendre que la page charge un titre différent de "DuckDuckGo"
            wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("DuckDuckGo — Privacy, simplified.")));

            // Afficher le titre de la page visitée
            String pageTitle = driver.getTitle();

            // Vérifier si le titre est vide ou inchangé
            if (pageTitle == null || pageTitle.trim().isEmpty() || pageTitle.equals("DuckDuckGo")) {
                System.out.println("Erreur : Aucun titre détecté pour la page " + (i + 1));
            } else {
                System.out.println("Titre de la page " + (i + 1) + " : " + pageTitle);
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotFolder, "screenshot"+i+".png");
            try {
                Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Revenir en arrière
            driver.navigate().back();

            // Attendre que la liste des résultats se recharge
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("article[data-testid='result']")));
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
