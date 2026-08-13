package pageObjects;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//i[@class='fa-solid fa-user']")
    WebElement link_MyAccount;

    @FindBy(xpath="//a[normalize-space()='Login']")
    WebElement link_login;

    public void clickMyAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(link_MyAccount));
        link_MyAccount.click();
    }
    public void goToLogin() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    By loginLocator = By.xpath("//a[normalize-space()='Login']");
    WebElement loginLink = wait.until(
    ExpectedConditions.elementToBeClickable(loginLocator)
    );
    loginLink.click();
  
    }
}
