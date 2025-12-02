package Tepbac.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws Exception {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
        driver.findElement(By.id("userEmail")).sendKeys("123456789aZ@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("123456789aZ");
        driver.findElement(By.id("login")).click();

        //get the list of all product
//        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        //find product that name "Zara Coat 3"
        WebElement prod = products.stream()
                .filter(product ->
                        product.findElement(By.cssSelector("b"))
                                .getText().equals("ZARA COAT 3"))
                .findFirst()
                .orElse(null);


        //Click on 'Add to cart' button in the found product

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //Method 1: using invisibilityOfElementLocated
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));

        //Method 2: Using invisibilityOf
        WebElement spinner = driver.findElement(By.cssSelector("ngx-spinner"));
        wait.until(ExpectedConditions.invisibilityOf(spinner));


        //Save product name
        String productName = prod.findElement(By.cssSelector("b")).getText();
        System.out.println(productName);


        //Click on cart symbol button
        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();


        //Wait the list of product in cart loading completedly
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".items")));

        //get list of products in the cart
        List<WebElement> items = driver.findElements(By.cssSelector(".items h3"));

        //Check if product is in the list
        Boolean match = items.stream().anyMatch(i -> i.getText().equalsIgnoreCase(productName));

        //validate result using assertion
        Assert.assertTrue(match, "product is not found in the cart");

        //continue: click checkout
        driver.findElement(By.cssSelector(".totalRow button")).click();


        //Select country using Actions Class
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector(".form-group .input")), "India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        List<WebElement> countryOptions = driver.findElements(By.cssSelector(".ta-results button"));
        WebElement countrySelect = countryOptions.stream()
                .filter(option -> option.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("India"))
                .findFirst()
                .orElse(null);
        countrySelect.click();


        //Place hodler
        driver.findElement(By.cssSelector(".action__submit")).click();

        //Verify confirmation message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        String message = driver.findElement(By.cssSelector("h1")).getText();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

        driver.quit();
    }
}
