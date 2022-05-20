package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;
import sbmscripts.pages.tms.Manufactures;

public class ApprovalForm extends TestBase {

    @FindBy(xpath = "//textarea[contains(@id, 'mat') or contains(@name, 'notes') or contains(@rows, '5')]")
    WebElement approveField;

    @FindBy(xpath = "//mat-dialog-actions//button[contains(@type, 'submit')]")
    WebElement approveButton;

    @FindBy(xpath = "//mat-dialog-actions//button[contains(@type, 'button')]")
    WebElement closeModal;

    public ApprovalForm() {
        PageFactory.initElements(driver, this);
    }

    public Manufactures approve(String message, WebElement manufactures){
        customWait();
        approveField.sendKeys(message);
        customWait();
        approveButton.click();
        manufactures.click();
        return new Manufactures();
    }
}
