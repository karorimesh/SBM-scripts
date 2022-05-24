package sbmscripts.pages.tms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;

public class SystemSetUp extends TestBase {

    @FindBy(xpath = "(//a[contains(@id, 'parent-link')])[1]/i")
    WebElement masterRecords;

    @FindBy(xpath = "//a[contains(@href, 'device-make')]")
    WebElement manufacturers;

    public WebElement getManufacturer() {
        return manufacturers;
    }

    public SystemSetUp() {
        PageFactory.initElements(driver, this);
    }

    public Manufactures getManufacturers(){
        extentTest.info("Manufacturer page navigation");
        customWait();
        masterRecords.click();
        try {
            customWait();
            manufacturers.click();
        } catch (Exception e) {
            customWait();
            masterRecords.click();
            customWait();
            manufacturers.click();
        }
        extentTest.info("Manufacturer navigation successful");
        return new Manufactures();
    }
}
