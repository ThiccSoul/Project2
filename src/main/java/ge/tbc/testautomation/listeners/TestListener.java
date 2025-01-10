package ge.tbc.testautomation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private long testStartTime;
    private long methodStartTime;

    @Override
    public void onStart(ITestContext context) {
        testStartTime = System.currentTimeMillis();
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(testStartTime));
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.println("Starting Test Tag: '" + context.getName() + "'");
        System.out.println("Start Time: " + startDate);
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");
    }

    @Override
    public void onFinish(ITestContext context) {
        long testEndTime = System.currentTimeMillis();
        long totalTime = testEndTime - testStartTime;

        String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(testEndTime));
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.println("Finished Test Tag: '" + context.getName() + "'");
        System.out.println("End Time: " + endDate);
        logExecutionTime("Test Tag", totalTime);
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");
    }

    @Override
    public void onTestStart(ITestResult result) {
        methodStartTime = System.currentTimeMillis();
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(methodStartTime));
        System.out.println("Test Method Started: " + result.getMethod().getMethodName());
        System.out.println("Start Time: " + startDate);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logTestMethodEnd(result, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logTestMethodEnd(result, "Test Failed");
        System.out.println("Error Details: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logTestMethodEnd(result, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logTestMethodEnd(result, "Test Failed Within Success Percentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logTestMethodEnd(result, "Test Timed Out");
    }

    private void logTestMethodEnd(ITestResult result, String status) {
        long methodEndTime = System.currentTimeMillis();
        long totalTime = methodEndTime - methodStartTime;
        String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(methodEndTime));

        System.out.println(status + ": " + result.getMethod().getMethodName());
        System.out.println("End Time: " + endDate);
        logExecutionTime("Test Method", totalTime);
        System.out.println("/////////////////////////////////////");
    }

    private void logExecutionTime(String entity, long totalTime) {
        long seconds = (totalTime / 1000) % 60;
        long minutes = (totalTime / (1000 * 60)) % 60;
        long hours = (totalTime / (1000 * 60 * 60)) % 24;

        System.out.printf("%s Execution Time: %02d hours, %02d minutes, %02d seconds.%n", entity, hours, minutes, seconds);
    }
}
