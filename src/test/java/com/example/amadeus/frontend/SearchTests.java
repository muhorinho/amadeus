package com.example.amadeus.frontend;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTests extends BaseTest {
    @Test
    public void testSearchSameFromTo() {
        FlightSearchPage searchPage = new FlightSearchPage(driver, wait);
        String selectedCity = "Istanbul";
        searchPage.selectFromAndTo(selectedCity, selectedCity);

        assertFalse(searchPage.getInformationText().contains("Bu iki şehir arasında uçuş bulunmuyor."), "Aynı şehir için uçuş araması yapılamamalı.");
    }
    @Test
    public void testSearchDifferentCities() {
        FlightSearchPage searchPage = new FlightSearchPage(driver, wait);
        String fromCity = "Istanbul";
        String toCity = "Los Angeles";
        searchPage.selectFromAndTo(fromCity, toCity);

        WebElement foundItemsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.mb-10")));
        int foundItemsCount = searchPage.extractItemCount(foundItemsElement.getText());
        assertTrue(foundItemsCount > 0, "Istanbul ve Los Angeles arasında uçuşlar bulunmalı.");
    }
}
