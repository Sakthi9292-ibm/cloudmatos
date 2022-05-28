package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun=false,features = {"src/test/resources/features"}, tags="@API",glue = "stepdefinitions",plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/cucumber-reports/CucumberTestReport.json"})

public class RunnerClass {
	

}
