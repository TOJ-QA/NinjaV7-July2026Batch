package pageObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-option-225']")
    WebElement input_DeliveryDate;

    @FindBy(xpath = "//button[@id='button-cart']")
    WebElement btn_AddToCart;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement alert_Success;

    @FindBy(xpath = "//a[@title='Checkout']//i[@class='fa-solid fa-share']")
    WebElement link_Checkout;

    @FindBy(xpath = "//div//button//i[@class='fa-solid fa-heart']")
    WebElement wishlistIcon;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement successMessage;

    public void addToWishlist() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        By wishlistLocator =
                By.xpath("//div//button//i[@class='fa-solid fa-heart']");

        WebElement wishlist = wait.until(
                ExpectedConditions.presenceOfElementLocated(wishlistLocator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                wishlist
        );

        wishlist = wait.until(
                ExpectedConditions.elementToBeClickable(wishlistLocator)
        );

        try {
            wishlist.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {

            WebElement button = wishlist.findElement(By.xpath("./.."));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    button
            );
        }
    }

    public void setDeliveryDate() {

        LocalDate date = LocalDate.now().plusDays(5);

        String formattedDate =
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        scrollToView(input_DeliveryDate);

        input_DeliveryDate.clear();
        input_DeliveryDate.sendKeys(formattedDate);
    }

    public void clickAddToCart() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        By cartLocator = By.id("button-cart");

        WebElement cartButton = wait.until(
                ExpectedConditions.elementToBeClickable(cartLocator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                cartButton
        );

        cartButton = wait.until(
                ExpectedConditions.elementToBeClickable(cartLocator)
        );

        cartButton.click();
    }

    public boolean isSuccessMessageDisplayed() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement success = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//div[@class='alert alert-success alert-dismissible']"
                        )
                )
        );

        return success.getText().contains("Success");
    }

    public void clickCheckout() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        By checkoutLocator =
                By.xpath("//a[@title='Checkout']//i[@class='fa-solid fa-share']");

        WebElement checkout = wait.until(
                ExpectedConditions.presenceOfElementLocated(checkoutLocator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                checkout
        );

        checkout = wait.until(
                ExpectedConditions.elementToBeClickable(checkoutLocator)
        );

        checkout.click();
    }

    private void scrollToView(WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
    }
}
