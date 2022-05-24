package sbmscripts.util;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.testng.ITestResult;
import sbmscripts.SbmScriptsApplication;
import sbmscripts.TestBase;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.URI;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class TestUtil extends TestBase {
    public static Workbook workbook;
    public static Sheet sheet;
    public static String TEST_DATA_FOLDER = System.getProperty("user.dir")
                                            + "/src/main/java/sbmscripts/test.data/";


    public static boolean stringMatcher(String text){
        String regex = "\bSuccessfully\b";
        Pattern successPattern = Pattern.compile(regex);
        return successPattern.matcher(text).find();
    }



    public static String takeAndSaveScreenshot(String screenshotName) throws IOException {
        File imageSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = screenshotName +
                System.currentTimeMillis() + ".png";
        String fileDest = IMAGES_DIR + fileName;
        FileUtils.copyFile(imageSrc, new File(fileDest));
        return  "/images/" + fileName;
    }

    public static void captureEndOfTestResults(ITestResult iTestResult) throws IOException {
        String screenshotPath = takeAndSaveScreenshot(iTestResult.getName());
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, "Test Case failed" + iTestResult.getName());
            extentTest.log(Status.FAIL, "Details of Failure" + iTestResult.getThrowable());
            extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else if (iTestResult.getStatus() == ITestResult.SKIP){
            extentTest.log(Status.FAIL, "Test Case skipped" + iTestResult.getName());
            extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }


    //    Data driven get test data from excel
    public static Object[][] readDataFromFile(String sheetFile, String sheetName){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(TEST_DATA_FOLDER + sheetFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook = WorkbookFactory.create(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheet(sheetName);

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++){
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++){
                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
            }
        }
        return data;
    }

}
