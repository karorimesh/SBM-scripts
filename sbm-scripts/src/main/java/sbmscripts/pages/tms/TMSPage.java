package sbmscripts.pages.tms;

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
        try {
            customWait(5);
            landingPageLink.click();
        } catch (Exception e) {
            System.out.println("Already in Landing");
        }
        customWait();
        tmsButton.click();
        return new TMSDashboard();
    }

    public SystemSetUp getSystemSetUp(){
        customWait();
        enterTMS();
        systemSetUp.click();
        customWait();
        return new SystemSetUp();
    }
}
