package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;

public class LoginPage extends TestBase {
    String makerEmail = properties.getProperty("site.user.email"),
            makerPass = properties.getProperty("site.user.password"),
            checkerEmail = properties.getProperty("site.user.email.checker"),
            checkerPass = properties.getProperty("site.user.password.checker");

    OtpPage otpPage = new OtpPage();

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(css = ".btn")
    WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public OtpPage loginToUfs(boolean isMaker){
        extentTest.info("Logging in to UFS");
        customWait(5);
        emailField.sendKeys(isMaker ? makerEmail : checkerEmail);
        customWait();
        passwordField.sendKeys(isMaker ? makerPass : checkerPass);
        loginButton.click();
        customWait();
        extentTest.info("Receiving OTP");
        otpPage.setEmailTo(isMaker ? makerEmail : checkerEmail);
        return otpPage;
    }
}
