package DemoQACommonFiles;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listners extends BaseTestClass1 implements ITestListener{


    ExtentTest test;
    ExtentReports extent = extentReportNG.getReportObject();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").
                    get(result.getInstance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String filePath = null;
        try {
            filePath = Capture(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

    }
}
