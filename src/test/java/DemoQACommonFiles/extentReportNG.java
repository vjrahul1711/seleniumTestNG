package DemoQACommonFiles;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReportNG {

    public static ExtentReports getReportObject(){

        String reportPath= System.getProperty("user.dir")+"//extentReportsFolder//index.html";
        ExtentSparkReporter extentReporter = new ExtentSparkReporter(reportPath);
        extentReporter.config().setReportName("TestExecutionReport");
        extentReporter.config().setDocumentTitle("ExtentReportTitle");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentReporter);
        extentReports.setSystemInfo("Tester","Vijay");

        return extentReports;
    }

}
