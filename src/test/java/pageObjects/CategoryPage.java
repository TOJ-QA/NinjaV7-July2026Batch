package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Laptops & Notebooks']")
    WebElement laptopsAndNotebooks;

    @FindBy(xpath = "//a[normalize-space()='Show All Laptops & Notebooks']")
    WebElement showAll;

    @FindBy(xpath = "//a[normalize-space()='HP LP3065']")
    WebElement product_HP;

    public void clickLaptopsAndNotebooks() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement laptops = wait.until(
                ExpectedConditions.elementToBeClickable(
                        laptopsAndNotebooks
                )
        );

        laptops.click();
    }

    public void clickShowAll() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement showAllLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        showAll
                )
        );

        showAllLink.click();
    }

    public void selectHPProduct() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        By hpProductLocator =
                By.xpath("//a[normalize-space()='HP LP3065']");

        // Wait for the HP product to exist
        WebElement hpProduct = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        hpProductLocator
                )
        );

        // Scroll the product into the center of the screen
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                hpProduct
        );

        // Find the element again after scrolling
        hpProduct = wait.until(
                ExpectedConditions.elementToBeClickable(
                        hpProductLocator
                )
        );

        try {

            // Normal Selenium click
            hpProduct.click();

        } catch (org.openqa.selenium.ElementNotInteractableException e) {

            // Fallback if Chrome reports the element as non-interactable
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    hpProduct
            );
        }
    }
}
