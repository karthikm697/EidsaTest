
package eidsa.EidsaTest.PageObjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import eidsa.EidsaTest.AbstractComponents.AbstractComponent;

public class AddSitePage extends AbstractComponent {
	
	WebDriver driver;
	public AddSitePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath = "//img[@alt='Study Template Menu']")
	WebElement studyTemplate;
	@FindBy(xpath = "//select[@class='form-select mx-lg-12 my-lg-0']")
	WebElement selStudy;
	@FindBy(xpath = "//td[text()='Case Report']/preceding-sibling::td//input")
	WebElement template;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;
		
	@FindBy(xpath = "//select[@class='study-select']")
	WebElement studydropdown;
	@FindBy(linkText = "Sites")
	WebElement sites;
	@FindBy(xpath = "//img[@alt='Create site']")
	WebElement createsite;  
	
	@FindBy(xpath = "//label[contains(text(),'Code')]/following-sibling::input[1]")
	WebElement siteCode;
	@FindBy(xpath="//label[contains(text(),'Name')]/following-sibling::input[1]")
	WebElement siteName;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveSite;
	//Site Error validation
	@FindBy(xpath="//label[contains(text(),'Code')]//following-sibling::span[@class='errormsg animated bounce']")
	WebElement sitecodeErr;
	//CRUD
	
	@FindBy(xpath="//div[@class='table-responsive']//td[1]")
	List<WebElement> sitecodes;
	@FindBy(xpath="//div[@class='table-responsive']//td[8]/i")
	List<WebElement> editSite;	
	@FindBy(xpath="//div[@class='table-responsive']//td[11]")
	List<WebElement> delSite;
	
	//Site search function
	@FindBy(xpath="//label[contains(text(),'Status')]/following-sibling::select")
	WebElement siteStatusSearch;
	@FindBy(xpath="//span[contains(text(),'Search')]")
	WebElement searchbtn;

	@FindBy(xpath="//td[2]")
	List<WebElement> sitenames;
	@FindBy(xpath="//td[3]")
	List<WebElement> sitestatus;
	
	 public void studyTemplate() throws InterruptedException
	 {
		 Thread.sleep(3000);
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(3000);
			studyTemplate.click();		
			Thread.sleep(3000);
			jse.executeScript("window.scrollBy(0,0)");
			Thread.sleep(3000);
	 }
	 public void selTemplate(String studId)
	 {
		 Select value=new Select(selStudy);
		 value.selectByVisibleText(studId);
		 template.click();
		 submit.click();
	 }
	 public String templateAlert() throws InterruptedException
	 {
		 Thread.sleep(3000);
			Alert alert=driver.switchTo().alert();
			String msg=alert.getText();
			alert.accept();
			return msg;
	 }
	public void studyDropdown(String studId)
	{
		Select svalue=new Select(studydropdown);
		svalue.selectByVisibleText(studId);
	}
	public void siteMenu()
	{
		sites.click();
	}
	public void addSite(String sitecode, String sitename)
	{
		
		createsite.click();
		siteCode.sendKeys(sitecode);
		siteName.sendKeys(sitename);
		saveSite.click();
	}
	public String siteAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	
	}
	
	public String siteCodeErrorMsg()
	{
		elementWait(sitecodeErr);
		return sitecodeErr.getText();
	}
	public void siteEdit(String sitecode)
	{

		int count = sitecodes.size();
		for(int i=0;i<count;i++)
		{
			
			String text =  sitecodes.get(i).getText();
			if(text.equals(sitecode))
			{
				editSite.get(i).click();
				break;
			}
		}
	}
	public void addEdit(String newSiteCode,String newSiteName) throws InterruptedException
	{
		Thread.sleep(2000);
		siteCode.clear();
		siteCode.sendKeys(newSiteCode);
		siteName.clear();
		siteName.sendKeys(newSiteName);
		Thread.sleep(2000);
		saveSite.click();
	}
	public String editSiteAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}
	
	public void delEdit(String newsitecode) throws InterruptedException
	{
		Thread.sleep(2000);
		int count = sitecodes.size();
		for(int i=0;i<count;i++)
		{
			
			String text =  sitecodes.get(i).getText();
			if(text.equals(newsitecode))
			{
				delSite.get(i).findElement(By.tagName("i")).click();
				break;
			}
		}
	}
	
	public String delSiteAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}
	
	//site search function
	public void siteCodeSearch(String sitecode) throws InterruptedException
	{
		Thread.sleep(3000);
		siteCode.sendKeys(sitecode);
	}
	public void siteNameSearch(String sitename) throws InterruptedException
	{
		Thread.sleep(3000);
		siteName.sendKeys(sitename);
	}
	public void siteStatusSearch(String sitestatus) throws InterruptedException
	{
		Thread.sleep(3000);
		Select sel=new Select(siteStatusSearch);
		sel.selectByVisibleText(sitestatus);
	}
	public void siteSearchBtn() throws InterruptedException
	{
		Thread.sleep(2000);
		searchbtn.click();
	}
	public Boolean siteCodeSearchValidation(String sitecode) throws InterruptedException
	{
		Thread.sleep(3000);
		boolean st = true;
		int count =sitecodes.size();
		if (count<1) 
		{
			st=false;
		}
		else 
		{
			for (int i=0; i<sitecodes.size();i++)
			{
				String scode=sitecodes.get(i).getText();
				if (!(scode.contains(sitecode))) 
				{
					st=false;
					break;
				}
			}
		}
		return st;
	}
	public Boolean siteNameSearchValidation(String sitename) throws InterruptedException
	{
		Thread.sleep(3000);
		boolean st = true;
		int count =sitenames.size();
		if (count<1) 
		{
			st=false;
		}
		else 
		{
			for (int i=0; i<sitenames.size();i++)
			{
				String scode=sitenames.get(i).getText();
				if (!(scode.contains(sitename))) 
				{
					st=false;
					break;
				}
			}
		}
		return st;
	}
	public Boolean siteStatusSearchValidation(String sitestat) throws InterruptedException
	{
		Thread.sleep(3000);
		boolean st = true;
		int count =sitestatus.size();
		if (count<1) 
		{
			st=false;
		}
		else 
		{
			for (int i=0; i<sitestatus.size();i++)
			{
				String scode=sitestatus.get(i).getText();
				if (!(scode.contains(sitestat))) 
				{
					st=false;
					break;
				}
			}
		}
		return st;
	}
}

