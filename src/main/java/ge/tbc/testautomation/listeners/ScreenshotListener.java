package ge.tbc.testautomation.listeners;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.screenshot;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Test Failed: " + testName);

        try {
            byte[] screenshot = captureScreenshotAsBytes();

            if (screenshot != null) {
                Allure.addAttachment("Screenshot on Failure: " + testName, new ByteArrayInputStream(screenshot));
                System.out.println("Screenshot successfully added to Allure report.");
            } else {
                System.err.println("Failed to capture screenshot: Screenshot is null.");
            }
        } catch (Exception e) {
            System.err.println("Error while capturing screenshot: " + e.getMessage());
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] saveScreenshotOnFailure() {
        return screenshot(OutputType.BYTES);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
    }


    private byte[] captureScreenshotAsBytes() {
        try {
            if (Selenide.webdriver().object() instanceof TakesScreenshot) {
                return ((TakesScreenshot) Selenide.webdriver().object()).getScreenshotAs(OutputType.BYTES);
            } else {
                System.err.println("WebDriver does not support screenshot capturing.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
            return null;
        }
    }
}
