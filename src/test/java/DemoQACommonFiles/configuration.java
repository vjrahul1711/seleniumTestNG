package DemoQACommonFiles;


import java.io.File;

public class configuration {



    public static String TEST_URL_DEMOQA = "https://demoqa.com/";
    public static String TEST_URL_RahulShettyAcademy = "https://rahulshettyacademy.com/seleniumPractise/#/";
    public static String TEST_URL_Automation_Practice = "https://rahulshettyacademy.com/AutomationPractice/";

    public static String TEST_UNAME_DEMOQA = "ranarahul";
    public static String TEST_UNAME_DEMOQA_Invalid = "vijay123";
    public static String TEST_PWD_DEMOQA = "Password@123";
    public static String TEST_PWD_DEMOQA_Invalid = "123456";
    public static String TEST_MSG_DEMOQA_Invalid = "Invalid username or password!";
    public static String SCREENSHOTS_DIR = System.getProperty("user.dir")
            + File.separator
            + "screenshots"
            + File.separator;


}