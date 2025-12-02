package Tepbac.tests;

import Tepbac.TestComponents.BaseTest;
import Tepbac.TestComponents.Retry;
import Tepbac.pageobjects.CartPage;
import Tepbac.pageobjects.ProductCalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws InterruptedException {
        landingPage.loginApplication("abc@gmail.com","123456");
        String messageText = landingPage.message();
        Assert.assertEquals(messageText,"Incorrect email or password.");
    }

    @Test(retryAnalyzer = Retry.class)
    public void productErrorValidation() throws InterruptedException{
        String productName = "ZARA COAT 3";
        ProductCalogue productCalogue = landingPage.loginApplication("123456789aZ@gmail.com","123456789aZ");
        productCalogue.getProductsList();
        productCalogue.getProductByName(productName);
        productCalogue.addProductToCart(productName);
        CartPage cartPage = landingPage.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(productName+"3");
        Assert.assertFalse(match);
    }
}
