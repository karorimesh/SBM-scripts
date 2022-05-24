package sbmscripts.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import sbmscripts.TestBase;

public class NotificationMessage extends TestBase {
    WebElement notificationMessage;
    public NotificationMessage() {
        PageFactory.initElements(driver, this);
    }

    public String getNotificationMessage(){
        extentTest.info("Reading notification message");
        return notificationMessage.getText();
    }
}
