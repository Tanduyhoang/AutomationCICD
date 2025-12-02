package Tepbac.pageobjects;

import Tepbac.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="div.cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css=".subtotal button")
    WebElement checkoutEle;

    By itemNames = By.cssSelector("div.cartSection h3");
    By checkout = By.cssSelector(".subtotal button");

    public boolean verifyProductDisplay(String productName){
        waitForElemnetAppear(itemNames);
        boolean match = cartProducts.stream().anyMatch(s-> s.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage goToCheckOut(){
        waitForElemnetAppear(checkout);
        checkoutEle.click();
        return new CheckoutPage(driver);
    }
}
