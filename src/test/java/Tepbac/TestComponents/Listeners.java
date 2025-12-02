package Tepbac.TestComponents;

import Tepbac.resoucre.ExtentReporertNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReporertNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    @Override
    public void onTestStart(ITestResult result) {
//        extent = ExtentReporertNG.getReportObject();
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        test.log(Status.PASS, "Test Passed");
        extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        test.fail(result.getThrowable());
        extentTest.get().fail(result.getThrowable().getMessage());

        try {
            driver = (WebDriver) result.getTestClass()
                    .getRealClass()
                    .getField("driver")
                    .get(result.getInstance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String filePath = null;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(), driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        test.addScreenCaptureFromPath(filePath);
        extentTest.get().addScreenCaptureFromPath(filePath);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        extentTest.get().skip(result.getThrowable().getMessage());
        extentTest.get().skip("Skipped testcase");
    }
}
