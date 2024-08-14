package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\java\\features", plugin = "json:target/jsonReports/cumcumber-report.json", glue= {"stepDefinations"}/*, tags= "@DeletePlace"*/)
public class TestRunner {

}
 
// mvn test -Dcucumber.filter.tags="@AddPlace"