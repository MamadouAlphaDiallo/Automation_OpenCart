package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseClass {
    public WebDriver driver;
    public Logger logger;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        logger = LogManager.getLogger(this.getClass());
        //ChromeOptions options = new ChromeOptions();
        //options.setExperimentalOption("ExcludeSwitches", new String[] {"enable-automation"});

        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            //WebDriverManager.chromedriver().setup();  // Problème avec le driver de chrome
            System.out.println("Chrome Browser launch");
            driver = new ChromeDriver();
        } else if (browser.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            System.out.println("Edge Browser launch");
            driver = new EdgeDriver();
        } else {
            System.out.println("Firefox Browser launch");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:8081/opencart/upload/");
        driver.manage().window().maximize();
    }
    @AfterClass
    public void teardown() {
        //driver.quit();
    }

    public String randomeString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return (generatedString);
    }

    public String randomeNumber() {
        String generatedString2 = RandomStringUtils.randomNumeric(10);
        return (generatedString2);
    }

    public String randomAlphaNumeric() {
        String st = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(3);

        return (st+"@"+num);
    }

}
