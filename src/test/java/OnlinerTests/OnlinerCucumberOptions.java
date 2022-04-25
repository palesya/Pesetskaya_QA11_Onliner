package OnlinerTests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/resources/feature/Onliner_Test.feature"},
        glue = {"CucumberSteps/Onliner"},plugin={"pretty","html:target/cucumber.html","json:target/cucumber.json"})
public class OnlinerCucumberOptions extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios(){
        return super.scenarios();
    }

}
