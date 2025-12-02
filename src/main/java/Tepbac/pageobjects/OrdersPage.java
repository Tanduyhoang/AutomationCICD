package Tepbac.pageobjects;

import Tepbac.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;
    public OrdersPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".ng-star-inserted td:nth-child(3)")
    List<WebElement> cartProducts;

    public boolean verifyOrderDisplay(String productName){
        waitForWebElement(cartProducts.get(0));
        boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }

}
