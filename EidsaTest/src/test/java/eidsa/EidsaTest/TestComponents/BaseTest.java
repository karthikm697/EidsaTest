package eidsa.EidsaTest.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import eidsa.EidsaTest.PageObjects.CreateStudyPage;

public class BaseTest 
{
	public WebDriver driver;
	public CreateStudyPage studypage;

	public WebDriver initializeDriver() throws IOException
	{
		Properties prop =new Properties();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/eidsa/EidsaTest/Resources/Globaldata.properties");
		prop.load(fis);
		String browserName= System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");

		///String browserName = prop.getProperty("browser");

		if(browserName.contains("chrome"))
		{
			ChromeOptions options= new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver=new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.contains("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browserName.contains("edge"))
		{
			driver=new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://eidsa.dev.datamatica.uk/");
		return driver;	
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

	@BeforeMethod(alwaysRun = true)
	public void CreateStudyPage() throws Exception
	{
		driver=initializeDriver();
		studypage = new CreateStudyPage(driver);
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}

}

