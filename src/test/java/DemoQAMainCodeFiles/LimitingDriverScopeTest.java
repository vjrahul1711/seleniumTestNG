package DemoQAMainCodeFiles;

import DemoQACommonFiles.BaseTestClass1;
import DemoQACommonFiles.configuration;

import DemoQACommonFiles.POMBaseTestClass;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
//import org.apache.commons.io.FileUtils;


public class LimitingDriverScopeTest extends BaseTestClass1 {

   // LimitingDriverScopeTest(){
      //  setDriver(new ChromeDriver());
    //}
    @Test

    public void DriverScope() throws IOException {
        BrowserLaunch(configuration.TEST_URL_Automation_Practice);
        WebElement footer =getDriver().findElement(By.xpath("//div[@id='gf-BIG']"));
        WebElement firstColumn = footer.findElement(By.xpath("//tbody/tr/td/ul"));
        int j = firstColumn.findElements(By.tagName("a")).size();
        for (int i=1;i<j;i++){
            firstColumn.findElements(By.tagName("a")).get(i).sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
        }
        Set<String> windowHandles = getDriver().getWindowHandles();
        Iterator<String> itr= windowHandles.iterator();
        while (itr.hasNext()){
            getDriver().switchTo().window(itr.next());
            System.out.println(getDriver().getTitle());

        }
        getDriver().quit();

    }




}