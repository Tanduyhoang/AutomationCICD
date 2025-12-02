package Tepbac.pageobjects;

import Tepbac.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="h1")
    WebElement messageText;

    By message = By.cssSelector("h1");


    public String getConfirmationMessage(){
        waitForElemnetAppear(message);
        return messageText.getText();
    }

}
