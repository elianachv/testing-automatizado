package reports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {

    static ExtentReports extent;


    public static ExtentReports getInstance() {

        if (extent == null) {
            extent = new ExtentReports();
            extent.setSystemInfo("Environment", "STG");
            extent.setSystemInfo("OS", "Windows");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }

    public static void setExtentTitle(String title) {
        if (extent == null) {
            getInstance();
        }

        extent.setSystemInfo("Purpose", title);
    }
}
