package Tepbac.pageobjects;

import Tepbac.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //WebElement userName = driver.findElement(By.id("username"));

    //pageFactory
    @FindBy (id = "userEmail")
    WebElement userEmail;

    @FindBy(id= "userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    public WebElement login;

    @FindBy(css = "#toast-container")
    WebElement errorMessage;


    By emailLocator = By.id("userEmail");

    public ProductCalogue loginApplication(String email, String password){
        waitForElemnetAppear(emailLocator);
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        waitForWebElement(login);
        login.click();
        ProductCalogue productCalogue =  new ProductCalogue(driver);
        return productCalogue;
    }


    public void goTo(String url){
        driver.get(url);
    }

    public String message(){
        waitForWebElement(errorMessage);
        return errorMessage.getText();
    }
}
