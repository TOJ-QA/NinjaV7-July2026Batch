package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class AccountPage extends BasePage
{
	// constructor
public AccountPage(WebDriver driver){
	super(driver);
}	
	// Locators
	@FindBy(xpath="//h1[normalize-space()='My Account']")
	WebElement confirmationText_MyAccount;
	
	@FindBy(xpath = "//li[@class='list-inline-item']//i[@class='fa-solid fa-caret-down']")
	WebElement dropdown_MyAccount;

	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Logout']")
	WebElement link_Logout;
	
	// Action Method
	public WebElement getMyAccountConfirmation(){
		return confirmationText_MyAccount;
	}

    public void clickMyAccountDropdown() {
        dropdown_MyAccount.click();
        }
    public void clickLogout() {	
    	link_Logout.click();
      }

		}
