package sbmscripts.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sbmscripts.TestBase;
import sbmscripts.pages.LandingPage;
import sbmscripts.pages.LoginPage;
import sbmscripts.pages.Logout;
import sbmscripts.pages.OtpPage;

public class LoginTest extends TestBase {
    LoginPage loginPage;
    LandingPage landingPage;
    OtpPage otpPage;
    Logout logout;

    public LoginTest() {
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void loginToUfs(){
        otpPage = loginPage.loginToUfs();
        landingPage = otpPage.verifyOTP();
        logout.logout();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
