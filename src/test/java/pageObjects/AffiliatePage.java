package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AffiliatePage extends BasePage {

    public AffiliatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Affiliate']")
    WebElement affiliateLink;

    @FindBy(id = "input-company")
    WebElement inputCompany;

    @FindBy(id = "input-website")
    WebElement inputWebsite;

    @FindBy(id = "input-tax")
    WebElement inputTax;

    @FindBy(id = "input-cheque")
    WebElement inputCheque;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement continueButton;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement successMessage;

    public void navigateToAffiliateForm() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By affiliateLocator =
                By.xpath("//a[normalize-space()='Affiliate']");

        System.out.println(">>> Waiting for Affiliate link");

        WebElement affiliate = wait.until(
                ExpectedConditions.elementToBeClickable(affiliateLocator)
        );

        System.out.println(">>> Affiliate link found");

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                affiliate
        );

        Thread.sleep(500);

        // Find it fresh again after scrolling
        affiliate = wait.until(
                ExpectedConditions.elementToBeClickable(affiliateLocator)
        );

        System.out.println(">>> About to click Affiliate");

        affiliate.click();

        System.out.println(">>> Affiliate click completed");
    }

    public void fillAffiliateDetails(String company, String website, String tax, String chequeName)
            throws InterruptedException {

        inputCompany.clear();
        inputCompany.sendKeys(company);

        inputWebsite.clear();
        inputWebsite.sendKeys(website);

        inputTax.clear();
        inputTax.sendKeys(tax);

        scrollToView(inputCheque);
        Thread.sleep(300);

        inputCheque.clear();
        inputCheque.sendKeys(chequeName);

        scrollAndClick(continueButton);
    }


    public boolean isAffiliateAdded() {
        return successMessage.isDisplayed();
    }


    private void scrollToView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                element
        );
    }


    private void scrollAndClick(WebElement element) throws InterruptedException {

        scrollToView(element);
        Thread.sleep(500);
        element.click();
    }
}