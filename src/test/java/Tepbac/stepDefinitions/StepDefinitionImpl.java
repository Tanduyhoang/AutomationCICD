package Tepbac.stepDefinitions;

import Tepbac.TestComponents.BaseTest;
import Tepbac.pageobjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCalogue productCalogue;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password){
        productCalogue = landingPage.loginApplication(username, password);
    }

    @When("I add product (.+) to cart$")
    public void i_add_product_to_cart(String productName) {
        productCalogue.getProductByName(productName);
        productCalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) {
        cartPage = productCalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string){
        Assert.assertEquals(confirmationPage.getConfirmationMessage(),string);
        driver.quit();
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String string){
        Assert.assertEquals(landingPage.message(), string);
        driver.quit();
    }
}
