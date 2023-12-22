package com.example.amadeus;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightAppUITests {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://flights-app.pages.dev/");
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testSearchSameFromTo() {
        String selectedCity = "Istanbul";
        selectFromAndTo(selectedCity, selectedCity);
        WebElement informationTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mt-24')]")));
        assertFalse(informationTextElement.getText().contains("Bu iki şehir arasında uçuş bulunmuyor."), "Aynı şehir için uçuş araması yapılamamalı.");
    }

    @Test
    public void testSearchDifferentCities() {
        String fromCity = "Istanbul";
        String toCity = "Los Angeles";
        selectFromAndTo(fromCity, toCity);
        WebElement foundItemsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.mb-10")));
        int foundItemsCount = extractItemCount(foundItemsElement.getText());
        assertTrue(foundItemsCount > 0, "Istanbul ve Los Angeles arasında uçuşlar bulunmalı.");
    }

    private void selectFromAndTo(String from, String to) {
        WebElement fromInput = driver.findElement(By.xpath("//*[@id='headlessui-combobox-input-:Rq9lla:']"));
        WebElement toInput = driver.findElement(By.xpath("//*[@id='headlessui-combobox-input-:Rqhlla:']"));
        fromInput.clear();
        toInput.clear();
        fromInput.sendKeys(from);
        fromInput.sendKeys(Keys.ENTER);
        toInput.sendKeys(to);
        toInput.sendKeys(Keys.ENTER);
    }

    private int extractItemCount(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
