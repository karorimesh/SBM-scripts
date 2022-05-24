package sbmscripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import sbmscripts.util.WebEventsListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase{
    public static WebDriver driver;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static ExtentSparkReporter sparkReporter;
    public static Properties properties;
    public static EventFiringWebDriver firingWebDriver;
    public static WebEventsListener webEventsListener;
    public static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    public static RestTemplate restTemplate = new RestTemplateBuilder().build();
    public static String BASE_URL = null;
    public static String TEMPLATES_DIR;
    public static String IMAGES_DIR;

    public TestBase() {
        try {
            properties = new Properties();
            FileInputStream props = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/myconfigs.properties");
            properties.load(props);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the config file");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    public static void customWait(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println("Can't wait");
        }
    }
    public static void customWait(){
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                System.out.println("Can't wait");
            }
        }

    public static void initialization(String testName) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String browserName =  properties.getProperty("site.browser");
        boolean isLocal = properties.getProperty("site.driver").equals("local");
        desiredCapabilities.setPlatform(Platform.LINUX);
        BASE_URL = properties.getProperty("site.hub.url");
        TEMPLATES_DIR = System.getProperty("user.dir") + properties.getProperty("test.output.report");
        IMAGES_DIR = "https://www.dropbox.com/scl/fo/rfzahlbybnjc1yjttfj2h/h?dl=0&rlkey=f4otys8od5hog8yml4762ksy1";

        String customCss = ".test-list {width:25% !important} img {width:800px} .test-content {width: 75% !important}";
        sparkReporter = new ExtentSparkReporter( TEMPLATES_DIR + "results " + format.format(new Date()) + ".html");
        sparkReporter.config().setCss(customCss);
        extentReports = new ExtentReports();
        extentTest = extentReports.createTest(testName);

        switch (browserName){
            case "FF":
                desiredCapabilities.setBrowserName("firefox");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--incognito");
                firefoxOptions.setAcceptInsecureCerts(true);
                firefoxOptions.merge(desiredCapabilities);
                try {
                    if (isLocal){
                        System.setProperty("webdriver.gecko.driver",
                                System.getProperty("user.dir") +
                                        "/src/main/resources/FirefoxDriver/geckodriver.exe");
                        driver = new ChromeDriver();
                    }else {
                        driver = new RemoteWebDriver(new URL(properties.getProperty("site.hub.url"))
                                , firefoxOptions);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                desiredCapabilities.setBrowserName("chrome");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                chromeOptions.setAcceptInsecureCerts(true);
                chromeOptions.merge(desiredCapabilities);
                try {
                    if (isLocal){
                        System.setProperty("webdriver.chrome.driver",
                                System.getProperty("user.dir") +
                                        "/src/main/resources/ChromeDriver/chromedriver.exe");
                        driver = new ChromeDriver(chromeOptions);
                    }else {
                        driver = new RemoteWebDriver(new URL(properties.getProperty("site.hub.url"))
                                , chromeOptions);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
        }

//        Event Fire Listener
        firingWebDriver = new EventFiringWebDriver(driver);
        webEventsListener = new WebEventsListener();
        firingWebDriver.register(webEventsListener);
        driver = firingWebDriver;

        extentReports.attachReporter(sparkReporter);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("site.test.site.url"));
    }


}
