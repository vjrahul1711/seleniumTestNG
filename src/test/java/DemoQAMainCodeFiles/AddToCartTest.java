package DemoQAMainCodeFiles;

import DemoQACommonFiles.BaseTestClass1;
import DemoQACommonFiles.POMBaseTestClass;
import DemoQACommonFiles.configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddToCartTest extends BaseTestClass1 {

    @Test
    public void AddToCartItems() throws IOException {
        BrowserLaunch(configuration.TEST_URL_RahulShettyAcademy);
        String[] testItemsToCart = {"Cucumber", "Brocolli", "Beetroot", "Beans","Tomato"};
        int j = 0;
        List<WebElement> productNameList = getDriver().findElements(By.xpath("//h4[@class='product-name']"));

        for (int i = 0; i < productNameList.size(); i++) {

            String[] ProductName = productNameList.get(i).getText().split("-");
            String formattedProductName = ProductName[0].trim();
            //System.out.println(ProductName[0]);
            List testItemsToCartList = Arrays.asList(testItemsToCart);
            if (testItemsToCartList.contains(formattedProductName)) {
                j++;
                getDriver().findElements(By.xpath("//div[@class='product-action']")).get(i).click();
                if (j == testItemsToCartList.size()) {
                    break;
                }
            }


        }
        getDriver().quit();


    }


}
