package sbmscripts.test.cases;


import org.testng.annotations.Test;
import sbmscripts.TestBase;

public class UtilityTests {
    TestBase testBase = new TestBase();
    @Test
    public void setTestBase(){
        TestBase.initialization("Some test");
    }
}
