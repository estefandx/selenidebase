package selenidebase.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import selenidebase.utils.ScreenshotUtil;

public class ScreenshotListener  implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        String screenshotPath = ScreenshotUtil.generateScreenshotFilename(result.getName(), "FAILED");


        ScreenshotUtil.takeScreenshot(screenshotPath);


        String relativeImagePath = "../" + screenshotPath;

        String imageHtmlTag = "<img src='" + relativeImagePath + "' width='500' height='300'/>";
        Reporter.log("Test failed. See screenshot below: " + imageHtmlTag);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String screenshotPath = ScreenshotUtil.generateScreenshotFilename(result.getName(), "PASSED");


        ScreenshotUtil.takeScreenshot(screenshotPath);


        String relativeImagePath = "../" + screenshotPath;

        String imageHtmlTag = "<img src='" + relativeImagePath + "' width='500' height='300'/>";
        Reporter.log("Test passed. See screenshot below: " + imageHtmlTag);
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
    }

}
