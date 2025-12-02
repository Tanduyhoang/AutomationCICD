package Tepbac.AbstractComponents;

import Tepbac.pageobjects.CartPage;
import Tepbac.pageobjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    public WebDriver driver;
    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="button[routerlink='/dashboard/cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink='/dashboard/myorders']")
    WebElement ordersHeader;


    public void waitForElemnetAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForElemnetDisappear(WebElement element){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void threadSleep(int seconds) throws InterruptedException {
        Thread.sleep(seconds);
    }

    public void waitForWebElement(WebElement findElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findElement));
    }

    public CartPage goToCartPage() {
        cartHeader.click();
        return new CartPage(driver);
    }

    public OrdersPage goToOrdersPage(){
        waitForWebElement(ordersHeader);
        ordersHeader.click();
        return new OrdersPage(driver);
    }
}
