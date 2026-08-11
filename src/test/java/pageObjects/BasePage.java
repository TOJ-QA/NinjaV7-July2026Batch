package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage 
{
//constructor

	WebDriver driver;
	BasePage(WebDriver driver) //This is the METHOD
	{
		this.driver = driver; //Variable 
		// This is the constructor for the class.
		// It takes a  WEBDRIVER object as an argument, 
		// which is used to interact with the browser.
		
		PageFactory.initElements(driver, this);
		//The Above line initializes the web elements defined in the 
		// in the class using Selenium's PageFactory.
		//PageFactory.initElements()tells Selenium to scan the current
		//class (this) for any @FindBy annotations
		//and connect them to actual elements on the page using the provided driver

	
	}
	
	
}
