package DemoQAMainCodeFiles;

import DemoQACommonFiles.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import static org.testng.Assert.assertEquals;


public class FormDropDownTest extends BaseTestClass1 {

    @Test(retryAnalyzer = Retry.class)
    public void staticDropdown() throws IOException {
        BrowserLaunch(configuration.TEST_URL_DEMOQA);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        JavascriptExecutor js3 = (JavascriptExecutor) getDriver();
        WebElement element1 = getDriver().findElement(By.xpath("//div/h5[text()=\"Forms\"]"));
        js3.executeScript("arguments[0].click()", element1);
        WebElement element2 = getDriver().findElement(By.xpath("//span[text()=\"Practice Form\"]"));
        js3.executeScript("arguments[0].click()", element2);
        //Form
        getDriver().findElement(By.id("firstName")).sendKeys(Form1data.FORM_First_Name);
        getDriver().findElement(By.id("lastName")).sendKeys(Form1data.FORM_Last_Name);
        getDriver().findElement(By.id("userEmail")).sendKeys(Form1data.FORM_Email_Id);
        js3.executeScript("arguments[0].click()", getDriver().findElement(By.id("gender-radio-1")));
        getDriver().findElement(By.id("userNumber")).sendKeys(Form1data.FORM_Mobile_Number);
        js3.executeScript("arguments[0].click()", getDriver().findElement(By.cssSelector("#dateOfBirthInput")));
        WebDriverWait wt = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".react-datepicker__day--selected"))).click();
        WebElement element5 = getDriver().findElement(By.xpath("//input[@id='subjectsInput']"));
        wt.until(ExpectedConditions.elementToBeClickable(element5));
        js3.executeScript("arguments[0].scrollIntoView()", element5);
        element5.sendKeys(Form1data.FORM_Subject_Input);
        element5.sendKeys(Keys.ENTER);
        js3.executeScript("arguments[0].click()", getDriver().findElement(By.id("hobbies-checkbox-2")));
        getDriver().findElement(By.id("uploadPicture")).sendKeys(Form1data.FORM_Upload_image_path);
        getDriver().findElement(By.id("currentAddress")).sendKeys(Form1data.FORM_Current_Address);


        //Dropdown-State-City
        WebElement element3 = getDriver().findElement(By.id("state"));
        js3.executeScript("arguments[0].scrollIntoView();", element3);
        wt.until(ExpectedConditions.elementToBeClickable(By.id("state")));
        js3.executeScript("arguments[0].click()", element3);
        WebElement element4 = getDriver().findElement(By.xpath("//*[@id=\"state\"]/div/div/div"));
        wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"state\"]/div/div/div")));
        element4.click();
        getDriver().findElement(By.id("react-select-3-input")).sendKeys(Form1data.FORM_State);
        getDriver().findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);
        getDriver().findElement(By.id("city")).click();
        getDriver().findElement(By.id("react-select-4-input")).sendKeys(Form1data.FORM_City);
        getDriver().findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);
        getDriver().findElement(By.id("submit")).click();
        //Taking Screenshot

        LimitingDriverScopeTest s = new LimitingDriverScopeTest();
        s.Capture(getDriver());

        String completionMsgActual = wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg"))).getText();

        //Verifying the form submission
        assertEquals(completionMsgActual, Form1data.FORM_CompletionMsgActual);
        js3.executeScript("arguments[0].click()", getDriver().findElement(By.id("closeLargeModal")));

        getDriver().quit();
    }


}
