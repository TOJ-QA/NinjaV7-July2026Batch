package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener, ISuiteListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;

    private String repName;


    // =========================================================
    // SUITE START
    // Creates ONE report for the entire TestNG suite
    // =========================================================

    @Override
    public void onStart(ISuite suite) {

        String timeStamp =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                        .format(new Date());

        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter =
                new ExtentSparkReporter(".\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "CloudBerry Store");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
    }


    // =========================================================
    // TEST PASSED
    // =========================================================

    @Override
    public void onTestSuccess(ITestResult result) {

        String browser =
                result.getTestContext()
                      .getCurrentXmlTest()
                      .getParameter("browser");

        ExtentTest test =
                extent.createTest(
                        result.getTestClass().getName()
                        + " - "
                        + result.getName());

        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.INFO, "Browser: " + browser);

        test.log(
                Status.PASS,
                result.getName() + " got successfully executed");
    }


    // =========================================================
    // TEST FAILED
    // =========================================================

    @Override
    public void onTestFailure(ITestResult result) {

        String browser =
                result.getTestContext()
                      .getCurrentXmlTest()
                      .getParameter("browser");

        ExtentTest test =
                extent.createTest(
                        result.getTestClass().getName()
                        + " - "
                        + result.getName());

        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.INFO, "Browser: " + browser);

        test.log(
                Status.FAIL,
                result.getName() + " got failed");

        if (result.getThrowable() != null) {

            test.log(
                    Status.INFO,
                    result.getThrowable().getMessage());
        }

        try {

            String imgPath =
                    new BaseClass().captureScreen(result.getName());

            if (imgPath != null) {
                test.addScreenCaptureFromPath(imgPath);
            }

        } catch (Exception e) {

            test.log(
                    Status.WARNING,
                    "Unable to capture screenshot: "
                    + e.getMessage());
        }
    }


    // =========================================================
    // TEST SKIPPED
    // =========================================================

    @Override
    public void onTestSkipped(ITestResult result) {

        String browser =
                result.getTestContext()
                      .getCurrentXmlTest()
                      .getParameter("browser");

        ExtentTest test =
                extent.createTest(
                        result.getTestClass().getName()
                        + " - "
                        + result.getName());

        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.INFO, "Browser: " + browser);

        test.log(
                Status.SKIP,
                result.getName() + " got skipped");

        if (result.getThrowable() != null) {

            test.log(
                    Status.INFO,
                    "Reason: "
                    + result.getThrowable().getMessage());
        }
    }


    // =========================================================
    // SUITE FINISH
    // Flushes and opens ONE report
    // =========================================================

    @Override
    public void onFinish(ISuite suite) {

        extent.flush();

        String pathOfExtentReport =
                System.getProperty("user.dir")
                + "\\reports\\"
                + repName;

        File extentReport =
                new File(pathOfExtentReport);

        try {

            Desktop.getDesktop()
                   .browse(extentReport.toURI());

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}