package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
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

        // Wait until HP LP3065 is visible
        WebElement hpProduct = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        hpProductLocator
                )
        );

        // Scroll HP LP3065 to the center of the viewport
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                hpProduct
        );

        // Find the element fresh after scrolling
        hpProduct = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        hpProductLocator
                )
        );

        try {

            // Wait until Selenium considers it clickable
            hpProduct = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            hpProductLocator
                    )
            );

            hpProduct.click();

        } catch (ElementNotInteractableException e) {

            // JavaScript fallback
            hpProduct = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            hpProductLocator
                    )
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    hpProduct
            );
        }
    }
}
