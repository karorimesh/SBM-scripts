package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;
import sbmscripts.util.OTPDTO;

public class OtpPage extends TestBase {

    OTPDTO OTPString;

    @FindBy(id = "no-paste")
    WebElement otpTextField;

    @FindBy(css = ".btn:nth-child(1)")
    WebElement verifyOtpButton;

    public OtpPage() {
        PageFactory.initElements(driver, this);
        this.OTPString = makeApiCall();
    }

    public LandingPage verifyOTP(){
        otpTextField.sendKeys(OTPString.getOTP());
        verifyOtpButton.click();
        return new LandingPage();
    }

    OTPDTO makeApiCall(){
        return restTemplate.getForObject(properties.getProperty("site.otp.uri"),
                OTPDTO.class);
    }
}
