package oc.testcases.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; // log4j
	public Properties prop;

	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"OS","Browser"})
	public void setup(String OS, String Browser) throws IOException
	{
		logger =LogManager.getLogger(this.getClass());
		String file =".//src//test//resources//Config.properties";
		FileInputStream fis = new FileInputStream(file);
		prop =new Properties();
		prop.load(fis);
		
		switch(Browser.toLowerCase())
		{
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver =new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser name...");
			return;
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url")); //reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	public String randomString()
	{
		String generatedString =RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	@SuppressWarnings("deprecation")
	public String randomNumber()
	{
		
		String generatedNumber =RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric()
	{
		String generatedString =RandomStringUtils.randomAlphabetic(3);
		String generatedNumber =RandomStringUtils.randomNumeric(3);
		return (generatedString+generatedNumber);	
	}
	
	public String captureScreen(String testname) throws IOException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss"); 
		Date dt = new Date();
		String timestamp =sdf.format(dt);
		TakesScreenshot tsc = ((TakesScreenshot)driver);
		File src =tsc.getScreenshotAs(OutputType.FILE);
		String targetfilepath = System.getProperty("user.dir")+"//screenshots//"+testname+"_"+timestamp+".png";
		File dest = new File(targetfilepath);
		FileUtils.copyFile(src, dest);
		return targetfilepath;
	}
}
