package suites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;
import reports.ExtentFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class BeforeAfterSuiteListener implements TestExecutionListener {

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        System.out.println("Before suite");
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {

        testPlan.getRoots().forEach(System.out::println);

        System.out.println("Generating General Report");
        ExtentSparkReporter parcial = new ExtentSparkReporter("target/reports/Parcial1.html");
        ExtentFactory.setExtentTitle("Parcial 1");
        ExtentReports extentMerged = ExtentFactory.getInstance();

        File jsonDirectory = new File("target/json");
        if (jsonDirectory.exists()) {
            Arrays.stream(Objects.requireNonNull(jsonDirectory.listFiles())).forEach(jsonFile -> {
                try {
                    extentMerged.createDomainFromJsonArchive(jsonFile.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        extentMerged.attachReporter(parcial);
        extentMerged.flush();
    }
}
