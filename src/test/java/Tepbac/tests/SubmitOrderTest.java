package Tepbac.tests;

import Tepbac.TestComponents.BaseTest;
import Tepbac.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {


    @Test (dataProvider = "getData")
    public void submitOrder(HashMap<String,String> input ) {

        String countryName = "india";
        String message = "thankyou for the order.".toUpperCase();

//        LandingPage landingPage = launchApplication();
        ProductCalogue productCalogue = landingPage.loginApplication(input.get("mail"), input.get("password"));
        productCalogue.getProductByName(input.get("productName"));
        productCalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = landingPage.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
    }

    @Test (dependsOnMethods = {"submitOrder"}, groups = {"purchase"})
    public void orderHistoryPage(){
        String productName = "zara coat 3";

        ProductCalogue productCalogue = landingPage.loginApplication("123456789aZ@gmail.com", "123456789aZ");
        OrdersPage ordersPage = productCalogue.goToOrdersPage();
        boolean verifyProductOrder = ordersPage.verifyOrderDisplay(productName);
        Assert.assertTrue(verifyProductOrder);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Tepbac\\data\\PurechaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)},{data.get(2)}};
    }





//    @DataProvider
//    public Object[][] getData(){
//        HashMap<String,String> map = new HashMap<>();
//        map.put("mail","123456789aZ@gmail.com");
//        map.put("password","123456789aZ");
//        map.put("productName","zara coat 3");
//
//        HashMap<String, String> map1 = new HashMap<>();
//        map1.put("mail","shetty@gmail.com");
//        map1.put("password","Iamking@000");
//        map1.put("productName","adidas original");
//
//        HashMap<String, String> map2 = new HashMap<>();
//        map2.put("mail","anshika@gmail.com");
//        map2.put("password","Iamking@000");
//        map2.put("productName","iphone 13 pro");
//
//        return new Object[][] {{map},{map1},{map2}};
//    }
}
