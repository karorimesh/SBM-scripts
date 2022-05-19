package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;

public class Logout extends TestBase {

    @FindBy(xpath = "//img[@alt='tms']")
    WebElement tmsButton;

    @FindBy(xpath = "//a[contains(@class, 'dropdown-toggle')]/span")
    WebElement usernameToggle;

    @FindBy(xpath = "(//a[contains(@class, 'dropdown-toggle')]/" +
            "ancestor::ul[contains(@class,\"nav\")]//a[contains(@role," +
            " \"button\")])[2]")
    WebElement logoutButton;

    public Logout() {
        PageFactory.initElements(driver, this);
    }

    public void logout(){
        driver.navigate().to(BASE_URL + "/landing-page");
        tmsButton.click();
        usernameToggle.click();
        logoutButton.click();
    }
}
