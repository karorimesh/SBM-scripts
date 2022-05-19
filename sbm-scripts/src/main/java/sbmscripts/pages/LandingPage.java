package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;

public class LandingPage extends TestBase {

    @FindBy(xpath = "./something")
    WebElement pageHeader;

    public LandingPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean landingPageHeaderValid(){
        return properties.getProperty("site.landing.header.title")
                .equals(pageHeader.getText());
    }


}
