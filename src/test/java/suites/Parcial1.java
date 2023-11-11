package suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import pageObjects.RegisterPageTest;
import pageObjects.SearchPageTest;

@SelectClasses({RegisterPageTest.class, SearchPageTest.class})
@IncludeTags("Parcial1")
@SuiteDisplayName("Parcial1")
@Suite
public class Parcial1 {

}