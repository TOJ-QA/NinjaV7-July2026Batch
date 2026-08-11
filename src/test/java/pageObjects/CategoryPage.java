package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPage extends BasePage {

	public CategoryPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[normalize-space()='Laptops & Notebooks']")
	WebElement link_Laptops;

	@FindBy(xpath = "//a[normalize-space()='Show All Laptops & Notebooks']")
	WebElement link_ShowAll;

	@FindBy(xpath = "//a[normalize-space()='HP LP3065']")
	WebElement product_HP;

	public void clickLaptopsAndNotebooks() throws InterruptedException {

	    Thread.sleep(1000);

	    WebElement laptops = driver.findElement(
	            org.openqa.selenium.By.xpath("//a[normalize-space()='Laptops & Notebooks']")
	    );

	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView(true);", laptops);

	    Thread.sleep(500);

	    laptops.click();
	}

	public void clickShowAll() {
		link_ShowAll.click();
	}

	public void selectHPProduct1() throws InterruptedException {
		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product_HP);

		Thread.sleep(1000);

		product_HP.click();
	}

	public void selectHPProduct() throws InterruptedException {
		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product_HP);

		Thread.sleep(1000);

		product_HP.click();
	}
}