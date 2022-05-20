package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;
import sbmscripts.pages.tms.TMSPage;

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

    public Logout getLogout() {
        return new Logout();
    }

    public TMSPage getTmsPage() {
        return new TMSPage();
    }
}
