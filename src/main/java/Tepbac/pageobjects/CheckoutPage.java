package Tepbac.pageobjects;

import Tepbac.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;


    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".form-group .input")
    private WebElement country;    //Encapsulation: using private, not other class access WebElement/ field...

    @FindBy(css=".ta-results button")
    private List<WebElement> buttons;

    @FindBy(css=".action__submit")
    WebElement submit;

    By results = By.cssSelector(".ta-results button");
    By selectCountry = By.cssSelector("span");

    public void selectCountry(String countryName){
        Actions actions = new Actions(driver);
        actions.sendKeys(country,countryName).build().perform();
        waitForElemnetAppear(results);

        List<WebElement> countryOptions = buttons;
        WebElement countrySelect = countryOptions.stream()
                .filter(option->option.findElement(selectCountry).getText().equalsIgnoreCase(countryName))
                .findFirst().orElse(null);
        countrySelect.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }
}
