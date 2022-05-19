package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;

public class LoginPage extends TestBase {

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(css = ".btn")
    WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public OtpPage loginToUfs(){
        emailField.sendKeys(properties.getProperty("site.user.email"));
        passwordField.sendKeys(properties.getProperty("site.user.password"));
        loginButton.click();
        return new OtpPage();
    }
}
