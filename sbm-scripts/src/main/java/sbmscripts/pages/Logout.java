package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;

import java.net.URL;

public class Logout extends TestBase {

    @FindBy(xpath = "//img[@alt='tms']")
    WebElement tmsButton;

    @FindBy(xpath = "//a[contains(@class, 'dropdown-toggle')]/span")
    WebElement usernameToggle;

    @FindBy(xpath = "(//a[contains(@class, 'dropdown-toggle')]/" +
            "ancestor::ul[contains(@class,\"nav\")]//a[contains(@role," +
            " \"button\")])[2]")
    WebElement logoutButton;

    @FindBy(xpath = "(//a[contains(@href, 'landing')])[1]")
    WebElement landingPageLink;

    public Logout() {
        PageFactory.initElements(driver, this);
    }

    public void logout(){
        extentTest.info("Logging out");
        try {
            customWait(5);
            landingPageLink.click();
        } catch (Exception e) {
            System.out.println("Already on landing Page");
        }
        customWait(10);
        tmsButton.click();
        customWait(3);
        usernameToggle.click();
        customWait(3);
        logoutButton.click();
        customWait(10);
        extentTest.pass("Successfully logged out");
    }
}
