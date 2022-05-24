package sbmscripts.tests;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sbmscripts.TestBase;
import sbmscripts.pages.LandingPage;
import sbmscripts.pages.LoginPage;
import sbmscripts.pages.Logout;
import sbmscripts.pages.OtpPage;
import sbmscripts.util.TestUtil;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginTest extends TestBase {
    LoginPage loginPage;
    LandingPage landingPage;
    OtpPage otpPage;
    Logout logout;
    boolean isMaker = true;

    public LoginTest() {
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization("Authorization and Authentication");
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void loginToUfs(){
        otpPage = loginPage.loginToUfs(isMaker);
        landingPage = otpPage.verifyOTP();
        logout = landingPage.getLogout();
        logout.logout();
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) throws IOException, MessagingException {
        TestUtil.captureEndOfTestResults(iTestResult);
        driver.quit();
        extentReports.flush();
    }

}
