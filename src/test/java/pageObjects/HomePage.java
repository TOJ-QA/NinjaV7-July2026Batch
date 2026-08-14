package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//i[@class='fa-solid fa-user']")
    WebElement link_MyAccount;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement link_login;

    public void clickMyAccount() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement myAccount = wait.until(
                ExpectedConditions.elementToBeClickable(
                        link_MyAccount
                )
        );

        myAccount.click();
    }

    public void goToLogin() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        By loginLocator =
                By.xpath("//a[normalize-space()='Login']");

        WebElement loginLink = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        loginLocator
                )
        );

        wait.until(
                ExpectedConditions.visibilityOf(loginLink)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                loginLink
        );

        loginLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        loginLocator
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                loginLink
        );
    }
}
