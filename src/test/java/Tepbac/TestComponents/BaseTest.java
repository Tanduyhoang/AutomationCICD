    package Tepbac.TestComponents;

    import Tepbac.pageobjects.LandingPage;
    import com.fasterxml.jackson.core.type.TypeReference;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import io.github.bonigarcia.wdm.WebDriverManager;
    import org.apache.commons.io.FileUtils;
    import org.openqa.selenium.Dimension;
    import org.openqa.selenium.OutputType;
    import org.openqa.selenium.TakesScreenshot;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;
    import org.openqa.selenium.edge.EdgeDriver;
    import org.openqa.selenium.edge.EdgeOptions;
    import org.openqa.selenium.firefox.FirefoxDriver;
    import org.testng.annotations.AfterMethod;
    import org.testng.annotations.BeforeMethod;

    import java.io.File;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.nio.charset.StandardCharsets;
    import java.time.Duration;
    import java.util.*;

    public class BaseTest {
        public WebDriver driver;
        public LandingPage landingPage;
        Properties prop;
        FileInputStream fis;

        public WebDriver initializeDriver() throws IOException {
            prop = new Properties();
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Tepbac/resoucre/GlobalData.properties");
            prop.load(fis);

            String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

            if (browserName.contains("edge")){
                EdgeOptions options = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                if (browserName.contains("headless")){
                    options.addArguments("headless");
                }
                driver = new EdgeDriver(options);
                driver.manage().window().setSize(new Dimension(1440,900));
            } else if (browserName.contains("chrome")) {
                ChromeOptions opt = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                if(browserName.contains("headless")){
                    opt.addArguments("headless");
                }
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.password_manager_leak_detection", false);
                prefs.put("profile.credentails_enable_service", false);
                opt.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(opt);
                driver.manage().window().setSize(new Dimension(1440,900));
            } else if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            //driver.manage().window().maximize();
            if(!browserName.contains("headless")){
                driver.manage().window().maximize();
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            return driver;
        }




        public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
            //read Json to String
            String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

            //String to Hashmap - Jackson Databind
            ObjectMapper mapper = new ObjectMapper();
            List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<>() {});

            //{map,map}
            return data;
        }

        public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
            TakesScreenshot ts = (TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
            FileUtils.copyFile(source,file);
            return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";
        }

        @BeforeMethod (alwaysRun = true)
        public LandingPage launchApplication() throws IOException{
            driver = initializeDriver();
            landingPage = new LandingPage(driver);
            landingPage.goTo(prop.getProperty("url"));
            return landingPage;
        }

        @AfterMethod (alwaysRun = true)
        public void tearDown(){
            driver.quit();
        }

    }
