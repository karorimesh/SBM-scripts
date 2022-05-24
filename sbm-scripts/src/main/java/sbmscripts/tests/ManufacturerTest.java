package sbmscripts.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import sbmscripts.TestBase;
import sbmscripts.pages.*;
import sbmscripts.pages.tms.*;

public class ManufacturerTest extends TestBase {
    LoginPage loginPage;
    OtpPage otpPage;
    LandingPage landingPage;
    TMSPage tmsPage;
    SystemSetUp systemSetUp;
    Manufactures manufactures;
    AddManufacturer addManufacturer;
    ApprovalForm approvalForm;
    Logout logout;
    boolean isMaker = true;
    boolean isNotDeleted = true;

    //Test Data
    String manufacturer = "Ingdev 1.0",
            vendorName = "Ingvend1.0",
            desc = "A description";

    public ManufacturerTest() {
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization("Master records manufacturer");
        loginPage = new LoginPage();
        otpPage = loginPage.loginToUfs(isMaker);
        landingPage = otpPage.verifyOTP();
        tmsPage = landingPage.getTmsPage();
        systemSetUp = tmsPage.getSystemSetUp();
        manufactures = systemSetUp.getManufacturers();
    }

    @AfterMethod
    public void tearDown(){
        logout = landingPage.getLogout();
        logout.logout();
        driver.quit();
        extentReports.flush();
        isMaker = !isMaker;
    }


    @Test(priority = 1, groups = {"Maker"})
    public void testAddManufacturer(){
        addManufacturer = manufactures.addManufacturer();
        manufactures = addManufacturer.createManufacturer(manufacturer,vendorName, desc, false);
        Assert.assertTrue(manufactures.isManufacturerThere(manufacturer), "Manufacturer created");
    }

    @Test(priority = 2, groups = {"Checker"})
    public void approveManufacturer(){
        approvalForm = manufactures.approveOrDeclineManufacturer(manufacturer.substring(0,5), true);
        manufactures = approvalForm.approve("Approved", systemSetUp.getManufacturer());
        Assert.assertEquals(manufactures.isManufacturerThere(manufacturer), isNotDeleted,
                            isNotDeleted ? "Creation or Edit approved" : "Deletion Approved");
    }

    @Test(priority = 3, groups = {"Maker"})
    public void editManufacturer(){
        addManufacturer = manufactures.editManufacturer(manufacturer);
        manufacturer += " edited";
        manufactures = addManufacturer.editManufacturer(manufacturer, vendorName, desc, false);
//        Assert.assertTrue(manufactures.isManufacturerThere(manufacturer), "Manufacturer Edited");
    }

    @Test(priority = 4, groups = {"Checker"})
    public void approveEdit(){
        approveManufacturer();
    }

    @Test(priority = 5, groups = {"Maker"})
    public void deleteManufacturer(){
        approvalForm = manufactures.deleteManufacturer(manufacturer);
        manufactures = approvalForm.approve("Approved",systemSetUp.getManufacturer());
        isNotDeleted = !isNotDeleted;
    }

    @Test(priority = 6, groups = {"Checker"})
    public void approveDeleteManufacturer(){
        approveManufacturer();
    }
}
