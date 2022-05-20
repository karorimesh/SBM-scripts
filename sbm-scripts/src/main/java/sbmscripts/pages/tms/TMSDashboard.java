package sbmscripts.pages.tms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import sbmscripts.TestBase;

public class TMSDashboard extends TestBase {

    @FindBy(xpath = "//h2")
    WebElement pageTitle;


    public TMSDashboard() {
        PageFactory.initElements(driver, this);
    }

    public boolean verifyDashboard(){
        return pageTitle.isDisplayed();
    }
}
