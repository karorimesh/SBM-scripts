package sbmscripts.pages.tms;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;

public class TMSPage extends TestBase {
    @FindBy(xpath = "(//a[contains(@href, 'landing')])[1]")
    WebElement landingPageLink;

    @FindBy(xpath = "//img[@alt='tms']")
    WebElement tmsButton;

    @FindBy(xpath = "//a[contains(@href, 'system')]")
    WebElement systemSetUp;

    public TMSPage() {
        PageFactory.initElements(driver, this);
    }

    public TMSDashboard enterTMS(){
        extentTest.info("Enter the TMS dashboard");
        try {
            customWait(5);
            landingPageLink.click();
        } catch (Exception e) {
            extentTest.info("Already in Landing");
        }
        customWait();
        tmsButton.click();
        extentTest.pass("Entered TMS successfully");
        return new TMSDashboard();
    }

    public SystemSetUp getSystemSetUp(){
        customWait();
        enterTMS();
        extentTest.info("Enter system set up");
        systemSetUp.click();
        extentTest.pass("Successfully entered system set up");
        customWait();
        return new SystemSetUp();
    }
}
