package Tepbac.pageobjects;

import Tepbac.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCalogue extends AbstractComponent {
    WebDriver driver;

    public ProductCalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-tns-c11-0.ng-star-inserted")
    WebElement snipper;


    By productBy = By.cssSelector(".mb-3");

    By addToCartButton = By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProductsList() {
        waitForElemnetAppear(productBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = getProductsList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) {
        getProductByName(productName).findElement(addToCartButton).click();
        waitForElemnetDisappear(snipper);
    }

}
