package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC05_AddToWishList extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testAddToWishList() throws InterruptedException {

        log.info("===== TC05_AddToWishList STARTED =====");

        try {

            // Step 1: Navigate to Login
            log.debug("Initializing HomePage");
            HomePage hp = new HomePage(getDriver());

            log.info("Clicking My Account");
            hp.clickMyAccount();

            log.info("Navigating to Login page");
            hp.goToLogin();

            // Step 2: Login
            log.debug("Initializing LoginPage");
            LoginPage lp = new LoginPage(getDriver());

            log.info("Entering email");
            lp.setEmail("tracyojoseph@yahoo.com");

            log.info("Entering password");
            lp.setPwd("Password11");

            log.info("Submitting login");
            lp.clickLogin();

            // Step 3: Navigate to Laptops and Notebooks
            log.debug("Initializing CategoryPage");
            CategoryPage cp = new CategoryPage(getDriver());

            log.info("Clicking Laptops and Notebooks");
            cp.clickLaptopsAndNotebooks();

            log.info("Clicking Show All");
            cp.clickShowAll();

            Thread.sleep(500);

            // Step 4: Select HP product
            log.info("Selecting HP product");
            cp.selectHPProduct();

            // Step 5: Add product to wishlist
            log.debug("Initializing ProductPage");
            ProductPage pp = new ProductPage(getDriver());

            log.info("Clicking Add to Wishlist");
            pp.addToWishlist();

            // Step 6: Verify wishlist success message
            boolean wishlistStatus = pp.isSuccessMessageDisplayed();

            log.info("Wishlist success message displayed: {}", wishlistStatus);

            log.debug("Asserting wishlist success message");
            Assert.assertTrue(
                    wishlistStatus,
                    "Wishlist message not shown."
            );

            log.info("✅ Assertion PASSED: Product added to wishlist");

        } catch (Exception e) {

            log.error(
                    "Unexpected exception occurred during TC05_AddToWishList",
                    e
            );

            String screenshotPath =
                    captureScreen("TC05_AddToWishList_EXCEPTION");

            log.info(
                    "Screenshot captured at: {}",
                    screenshotPath
            );

            throw e;

        } finally {

            log.info("===== TC05_AddToWishList FINISHED =====");
        }
    }
}
}
