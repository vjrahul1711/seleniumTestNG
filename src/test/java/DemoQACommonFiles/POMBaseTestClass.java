package DemoQACommonFiles;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class POMBaseTestClass {
    public WebDriver driver = null;
   // WebDriverWait exWait;
    //String BrowserName = "firefox";


    //Getter
    public WebDriver getDriver() {
        return driver;
    }

    //Setter
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public void BrowserLaunch(String testUrl) throws IOException {
        //GETTING BROWSER NAME FROM PROPERTY FILE
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//DemoQACommonFiles//globalTestData.properties");
        prop.load(fis);
        String BrowserName;
        if(System.getProperty("BrowserName") !=null){
            BrowserName= System.getProperty("BrowserName");
        }else {
            BrowserName= prop.getProperty("BrowserName");
        }

        //String BrowserName= prop.getProperty("BrowserName");

        if (BrowserName.contains("chrome")) {
            setDriver(new ChromeDriver());
        } else if (BrowserName.contains("edge")) {
            setDriver(new EdgeDriver());
        } else if (BrowserName.contains("firefox")) {
            setDriver(new FirefoxDriver());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        }
        getDriver().get(testUrl);
        getDriver().manage().window().maximize();
    }
    @BeforeMethod(alwaysRun = true)
    public void LaunchApp() throws IOException {
        BrowserLaunch("https://rahulshettyacademy.com/client");
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown(){
        driver.close();
    }
    public void waitForWebelementToAppear(WebElement element) {
        WebDriverWait exWait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        exWait.until(ExpectedConditions.visibilityOf(element));
    }
//    public void waitForWebelementToBelocatedBy(WebElement element) {
//        WebDriverWait Wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
//        Wait.until(ExpectedConditions.visibilityOf(element));
//    }

    public void waitForWebelementToBeinvisible(WebElement element) {
        WebDriverWait exWait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        exWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void jsExecutorClick(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView()",element);
        js.executeScript("arguments[0].click()", element);
    }


}
