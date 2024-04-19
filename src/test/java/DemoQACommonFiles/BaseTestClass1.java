package DemoQACommonFiles;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BaseTestClass1 {
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
    //@BeforeMethod
    public void LaunchApp() throws IOException {
        BrowserLaunch("https://rahulshettyacademy.com/client");
    }

    //@AfterMethod
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

    public String Capture(WebDriver driver) throws IOException{
        TakesScreenshot camera = (TakesScreenshot) driver;
        File saveFile = camera.getScreenshotAs(OutputType.FILE);
        String path = String.format("%s%s-%s.png", configuration.SCREENSHOTS_DIR, "dashboard", System.currentTimeMillis());
        boolean fileSaved= saveFile.renameTo(new File(path));
        String Saved = (fileSaved) ? "imgSaved" :"imgNotSaved";
        System.out.println(Saved);
        return path;
    }


}
