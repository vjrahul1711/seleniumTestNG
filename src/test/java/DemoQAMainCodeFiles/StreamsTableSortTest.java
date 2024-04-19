package DemoQAMainCodeFiles;

import DemoQACommonFiles.BaseTestClass1;
import DemoQACommonFiles.POMBaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsTableSortTest extends BaseTestClass1 {

    @Test
    public void streamSortWebTable() throws IOException {
        BrowserLaunch("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        getDriver().findElement(By.cssSelector("th>span")).click();
        List<WebElement> elementls = getDriver().findElements(By.xpath("//tbody/tr/td[1]"));
        List<String> originalList = elementls.stream().map(s -> s.getText()).toList();
        List<String> newList = originalList.stream().sorted().toList();
        Assert.assertTrue(originalList.equals(newList));
        System.out.println("AAAA");
        getDriver().quit();
    }

    @Test
    public void pagination() throws IOException {
        BrowserLaunch("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        List<String> priceList;
        do {
            List<WebElement> elementLs = getDriver().findElements(By.xpath("//tbody/tr/td[1]"));
            List<String> originalList = elementLs.stream().map(s -> s.getText()).collect(Collectors.toList());
            originalList.stream().forEach(s -> System.out.println(s));
            priceList = elementLs.stream().filter(s -> s.getText().contains("Cherry")).
                    map(s -> veggiePrice(s)).collect(Collectors.toList());
            priceList.stream().forEach(s -> System.out.println("Desired price is"+ s));
            if (priceList.isEmpty()) {
                getDriver().findElement(By.cssSelector("a[aria-label='Next']")).click();
            }
        } while (priceList.isEmpty());
        getDriver().quit();
    }
    private String veggiePrice(WebElement s) {
        return s.findElement(By.xpath("following-sibling::td[1]")).getText();
    }



}
