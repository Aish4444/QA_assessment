package webPages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.TestUtil;
import util.WebEventListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;


public abstract class TestBase {
	
	 public static Properties prop;
	 public static FileInputStream fip;
	 public static String Browser;
	 public static WebDriver dr;
	 public static Logger l;  //important that import apachelog4j only
	 public static SoftAssert st;
	 public  static EventFiringWebDriver e_driver;
	 public static WebEventListener eventListener;
     public ExtentReports extent;
     public ExtentTest extentTest;

	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/BlazeUIAutomation.html", true);
		extent.addSystemInfo("Host Name", "Ash");
		extent.addSystemInfo("User Name", "Aishu");
		extent.addSystemInfo("Environment", "BlazedemoAutomation");
	}

	@AfterTest
	public void endReport(){
	//	d.close();
		extent.flush();
			}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/src/test/FailedDashboardScreenshots" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@BeforeSuite 
	public static void OpenBrowser() throws Throwable {
		st= new SoftAssert();
		l=Logger.getLogger("devpinoyLogger");
		fip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/properties/defaultproperties");
		prop = new Properties();
		prop.load(fip);
		Browser=prop.getProperty("browser");
		l.info("Browser chosen is" +Browser);
			if(Browser.equalsIgnoreCase("GoogleChrome")){
			WebDriverManager.chromedriver().setup();
			dr=new ChromeDriver();
			l.info("chrome is launched");
		}else if(Browser.equalsIgnoreCase("Firefox")){
			WebDriverManager.firefoxdriver().setup();
			dr=new FirefoxDriver();
			l.info("firefox is launched");
		}else if(Browser.equalsIgnoreCase("InternetExplore")){
			WebDriverManager.iedriver().setup();
			dr=new InternetExplorerDriver();
			l.info("ie is launched");
		}

		e_driver = new EventFiringWebDriver(dr);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		dr = e_driver;

		dr.manage().window().maximize();
		dr.manage().deleteAllCookies();
		dr.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		dr.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		dr.get(prop.getProperty("url"));
		
    }

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{

		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "Testcase failed for "+result.getName());
			extentTest.log(LogStatus.FAIL, "Testcase failed for  "+result.getThrowable());

			String screenshotPath = TestBase.getScreenshot(dr, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
			extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Testcase skipped for " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case passed for " + result.getName());

		}
		extent.endTest(extentTest);
		}
@AfterSuite
    public static void CloseBrowser() {
	    dr.quit();
		l.info("browser closed");
	}


}
