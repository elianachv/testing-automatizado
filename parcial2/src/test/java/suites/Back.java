package suites;

import api.LoginBackTest;
import api.RegisterBackTest;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import pageObjects.AccountOverviewPageTest;
import pageObjects.NewAccountFormPageTest;
import pageObjects.RegisterPageTest;
import pageObjects.TransferFundsFormsPageTest;

@SelectClasses({
        RegisterBackTest.class,
        LoginBackTest.class})
@IncludeTags("Back")
@SuiteDisplayName("Back")
@Suite
public class Back {

}