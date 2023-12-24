package com.example.amadeus.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightSearchPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FlightSearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void selectFromAndTo(String from, String to) {
        WebElement fromInput = driver.findElement(By.xpath("//*[@id='headlessui-combobox-input-:Rq9lla:']"));
        WebElement toInput = driver.findElement(By.xpath("//*[@id='headlessui-combobox-input-:Rqhlla:']"));
        fromInput.clear();
        toInput.clear();
        fromInput.sendKeys(from);
        fromInput.sendKeys(Keys.ENTER);
        toInput.sendKeys(to);
        toInput.sendKeys(Keys.ENTER);
    }

    public String getInformationText() {
        WebElement informationTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mt-24')]")));
        return informationTextElement.getText();
    }

    public int extractItemCount(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}
