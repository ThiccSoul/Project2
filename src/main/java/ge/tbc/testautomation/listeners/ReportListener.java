package ge.tbc.testautomation.listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Set;

public class ReportListener implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.println("Failed Tests Report");
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][]");

        for (ISuite suite : suites) {
            System.out.println("Suite: " + suite.getName());
            boolean hasFailures = false;

            for (ISuiteResult result : suite.getResults().values()) {
                ITestContext testContext = result.getTestContext();

                Set<ITestResult> failedTests = testContext.getFailedTests().getAllResults();
                if (!failedTests.isEmpty()) {
                    hasFailures = true;
                    System.out.println("  Test Tag: " + testContext.getName());

                    for (ITestResult failedTest : failedTests) {
                        System.out.println("Test Method: " + failedTest.getMethod().getMethodName());
                        System.out.println("Description: " + failedTest.getMethod().getDescription());
                        System.out.println("Error: " + failedTest.getThrowable());
                    }
                }
            }

            if (!hasFailures) {
                System.out.println("No Failures in Suite: " + suite.getName());
            }
        }
        System.out.println("//////////////////////////////////////////////////");
    }
}
