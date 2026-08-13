package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//strong[normalize-space()='login page']")
    WebElement loginPageLink;

    @FindBy(id = "input-shipping-address")
    WebElement shippingAddressDropdown;

    @FindBy(id = "button-shipping-methods")
    WebElement shippingMethodsButton;

    @FindBy(id = "button-shipping-method")
    WebElement flatShippingButton;

    @FindBy(id = "button-payment-methods")
    WebElement paymentMethodsButton;

    @FindBy(id = "button-payment-method")
    WebElement codButton;

    @FindBy(xpath = "//div[@class='text-end']//button[contains(text(),'Confirm')]")
    WebElement confirmButton;

    public void clickLogin() {
        loginPageLink.click();}

    public void completeCheckout() throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    By shippingAddress = By.id("input-shipping-address");
    By shippingMethods = By.id("button-shipping-methods");
    By flatShipping = By.id("button-shipping-method");
    By paymentMethods = By.id("button-payment-methods");
    By cod = By.id("button-payment-method");
    By confirm = By.xpath("//div[@class='text-end']//button[contains(text(),'Confirm')]");

    wait.until(ExpectedConditions.visibilityOfElementLocated(shippingAddress));

    Select select = new Select(
            wait.until(ExpectedConditions.elementToBeClickable(shippingAddress))
    );
    select.selectByIndex(1);

    wait.until(ExpectedConditions.elementToBeClickable(shippingMethods)).click();

    wait.until(ExpectedConditions.elementToBeClickable(flatShipping)).click();

    wait.until(ExpectedConditions.elementToBeClickable(paymentMethods)).click();

    wait.until(ExpectedConditions.elementToBeClickable(cod)).click();

       WebElement confirmButton = wait.until(
            ExpectedConditions.elementToBeClickable(confirm)
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            confirmButton
    );

    wait.until(ExpectedConditions.elementToBeClickable(confirm)).click();
    }
}
