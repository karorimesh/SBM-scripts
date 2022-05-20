package sbmscripts.pages.tms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;
import sbmscripts.pages.NotificationMessage;

public class AddManufacturer extends TestBase {

    @FindBy(xpath = "//input[contains(@id,'make')]")
    WebElement manufacturerField;

    @FindBy(xpath = "//input[contains(@id,'vendorName')]")
    WebElement vendorField;

    @FindBy(xpath = "//textarea[contains(@id,'desc')]")
    WebElement descField;

    @FindBy(xpath = "//button[contains(@type,'reset')]")
    WebElement resetButton;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    WebElement createButton;

    public AddManufacturer() {
        PageFactory.initElements(driver, this);
    }

    public Manufactures createManufacturer(String manufacturerName,
                                                  String vendorName, String desc,
                                                  boolean reset){
        customWait(5);
        manufacturerField.sendKeys(manufacturerName);
        customWait();
        vendorField.sendKeys(vendorName);
        customWait();
        descField.sendKeys(desc);
        customWait();
        if (reset) {
            resetButton.click();
            customWait();
            driver.navigate().back();
            customWait(5);
        } else {
            createButton.click();
        }
        return new Manufactures();
    }

    public Manufactures editManufacturer(String manufacturerName,
                                         String vendorName,
                                         String desc,
                                         boolean reset){
        resetButton.click();
        return createManufacturer(manufacturerName, vendorName, desc, reset);
    }
}
