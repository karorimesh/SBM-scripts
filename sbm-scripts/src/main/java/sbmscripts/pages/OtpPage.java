package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import sbmscripts.TestBase;
import sbmscripts.util.OTPDTO;

public class OtpPage extends TestBase {

    OTPDTO OTPString;
    String emailTo = "";

    @FindBy(id = "no-paste")
    WebElement otpTextField;

    @FindBy(css = ".btn:nth-child(1)")
    WebElement verifyOtpButton;

    public OtpPage() {
        PageFactory.initElements(driver, this);
    }

    public LandingPage verifyOTP(){
        this.OTPString = makeApiCall();
        customWait(5);
        extentTest.info("Verifying OTP");
        otpTextField.sendKeys(OTPString.getOTP());
        customWait();
        verifyOtpButton.click();
        customWait();
        extentTest.pass("OTP verification successful");
        return new LandingPage();
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    OTPDTO makeApiCall(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        JSONObject mailPropsObject = new JSONObject();
        try {
            mailPropsObject.put("emailTo", emailTo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpEntity<String> request = new HttpEntity<String>(mailPropsObject.toString(), httpHeaders);
        return restTemplate.postForObject(properties.getProperty("site.otp.uri"), request,
                OTPDTO.class);
    }
}
