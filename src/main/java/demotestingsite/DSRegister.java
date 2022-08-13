package demotestingsite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import demositepage.DSRegisterPage;

public class DSRegister {
	public static WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentTest test;	
	
@BeforeTest
public static void launchBrowser() throws IOException {
	
		//Report section created and attached
		extentReports=new ExtentReports();
		extentReports.setSystemInfo("TesterName:", "MR:R.Manikandan");
		extentReports.setSystemInfo("TestingType:", "SmokeTesting");
		//String reportFile=System.getProperty("user-idir")+"//Reports/SparkReport.html";
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter("reportFile.html");
		sparkReporter.config().setReportName("Automation Testing site Demo");
		sparkReporter.config().setDocumentTitle("RegisterHomePage");
		extentReports.attachReporter(sparkReporter);
		
		//configfile created and values passed

		FileInputStream inputStream=new FileInputStream("config.properties");
		Properties property=new Properties();
		property.load(inputStream);
		String Browser=property.getProperty("browser");
		String URL=property.getProperty("url");
		String Location=property.getProperty("location");
		//Browser launch section
		if(Browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", Location);
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.get(URL);
		}
		else {
			System.setProperty("webdriver.gecko.driver", Location);
			WebDriver driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
			driver.get(URL);
		}
	PageFactory.initElements(driver, DSRegisterPage.class);	 
}

@Test
public static void registerPage() throws IOException
{
	test=extentReports.createTest("Register Testcase");
	DSRegisterPage.firstName.sendKeys("Ganeshan");
	DSRegisterPage.lastName.sendKeys("Velan");
	DSRegisterPage.address.sendKeys("3/4,nehruji Nagar,chennai-626 213");
	TakesScreenshot ts=(TakesScreenshot)driver;
	File soucefile=ts.getScreenshotAs(OutputType.FILE);
	File destionationFile=new File("Sample.png");
		FileHandler.copy(soucefile,destionationFile);
		test.addScreenCaptureFromPath("ReportSample.png");
}
@AfterTest
public static void closeBrowser() {
	extentReports.flush();
	driver.quit();
}
}
