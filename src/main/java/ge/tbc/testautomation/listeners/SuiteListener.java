package ge.tbc.testautomation.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SuiteListener implements ISuiteListener {
    private long suiteStartTime;

    @Override
    public void onStart(ISuite suite) {
        suiteStartTime = System.currentTimeMillis();
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(suiteStartTime));
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.println("Starting Test Suite: '" + suite.getName() + "'");
        System.out.println("Start Time: " + startDate);
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");
    }

    @Override
    public void onFinish(ISuite suite) {
        long suiteEndTime = System.currentTimeMillis();
        long totalTime = suiteEndTime - suiteStartTime;

        long seconds = (totalTime / 1000) % 60;
        long minutes = (totalTime / (1000 * 60)) % 60;
        long hours = (totalTime / (1000 * 60 * 60)) % 24;

        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.println("Completed Test Suite: '" + suite.getName() + "'");
        System.out.printf("Total Execution Time: %02d hours, %02d minutes, %02d seconds.%n", hours, minutes, seconds);
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");
    }
}