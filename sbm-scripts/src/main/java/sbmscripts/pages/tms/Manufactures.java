package sbmscripts.pages.tms;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;
import sbmscripts.pages.ApprovalForm;

public class Manufactures extends TestBase {

    @FindBy(xpath = "(//td[contains(text(),'')])[2]")
    WebElement singleManufacturer;

    @FindBy(xpath = "(//td)[2]")
    WebElement specificManufacturer;

    @FindBy(xpath = "//a[contains(text(), 'Add')]//i")
    WebElement addManufacturerButton;

    @FindBy(xpath = "(//i[@title='Edit'])[1]")
    WebElement editManufacturerButton;

    @FindBy(xpath = "//a[contains(text(), 'Approve Manufacturer')]")
    WebElement approveManufacturerPageButton;

    @FindBy(xpath = "//button[contains(text(), 'Approve')]")
    WebElement approveManufacturer;

    @FindBy(xpath = "//button[contains(text(), 'Decline')]")
    WebElement declineManufacturer;

    @FindBy(xpath = "//button[contains(text(), 'Delete')]")
    WebElement deleteManufacturerButton;

    @FindBy(xpath = "//input[@type=\"search\"]")
    WebElement searchField;

    @FindBy(xpath = "(//div[contains(@class, 'dt-buttons')]//button)[1]")
    WebElement copyButton;

    @FindBy(xpath = "(//div[contains(@class, 'dt-buttons')]//button)[2]")
    WebElement printButton;

    @FindBy(xpath = "(//div[contains(@class, 'dt-buttons')]//button)[3]")
    WebElement pdfButton;

    @FindBy(xpath = "(//div[contains(@class, 'dt-buttons')]//button)[4]")
    WebElement csvButton;

    @FindBy(xpath = "//input[contains(@type,'checkbox')]")
    WebElement selectAllCheckbox;

    public Manufactures() {
        PageFactory.initElements(driver, this);
    }

    public AddManufacturer addManufacturer(){
        extentTest.info("Navigate to add Manufacturer Button");
        customWait(5);
        addManufacturerButton.click();
        customWait(5);
        extentTest.pass("Navigation to add manufacturer form successful");
        return new AddManufacturer();
    }

    public AddManufacturer editManufacturer(String manufacturer){
        extentTest.info("Navigate to edit manufacturer page");
        customWait(2);
        searchField.sendKeys(manufacturer);
        customWait(5);
        editManufacturerButton.click();
        customWait(5);
        extentTest.pass("Edit manufacturer page navigation successful");
        return new AddManufacturer();
    }

    public boolean isManufacturerThere(String manufacturerName){
        extentTest.info("Search for a manufacturer");
        customWait();
        searchField.sendKeys(manufacturerName);
        customWait(10);
        try {
            specificManufacturer.isDisplayed();
            extentTest.pass("Manufacturer is present");
            return true;
        } catch (NoSuchElementException e){
            extentTest.info("Manufacturer not there");
            return false;
        }

    }

    public ApprovalForm approveOrDeclineManufacturer(String manufacturerName, boolean toApprove){
        extentTest.info("Approving Manufacturer");
        customWait();
        approveManufacturerPageButton.click();
        customWait();
        searchField.sendKeys(manufacturerName);
        customWait();
        specificManufacturer.click();
        customWait();
        if (toApprove) {
            approveManufacturer.click();
            extentTest.pass("Manufacturer approved");
        } else {
            declineManufacturer.click();
            extentTest.pass("Manufacturer declined");
        }
        return new ApprovalForm();
    }

    public ApprovalForm deleteManufacturer(String manufacturerName){
        extentTest.info("Delete manufacturer");
        customWait();
        searchField.sendKeys(manufacturerName);
        customWait();
        specificManufacturer.click();
        customWait();
        deleteManufacturerButton.click();
        customWait();
        extentTest.pass("Deletion successful");
        return new ApprovalForm();
    }

}
