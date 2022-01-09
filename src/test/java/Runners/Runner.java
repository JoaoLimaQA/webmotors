package Runners;

import Tests.AbstractBaseTests.TestBase;
import Utils.CustomListener;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.annotations.Listeners;


@RunWith(Cucumber.class)
@CucumberOptions(

        features = "classpath:Features",
        glue = "Tests",
        plugin = {"pretty","com.cucumber.listener.ExtentCucumberFormatter:Evidencias/dashboard/cucumber-reports/report.html",}
         ,tags = {"@AnuncioVeicular"}

         )
@Listeners(CustomListener.class)
public class Runner extends TestBase {


}