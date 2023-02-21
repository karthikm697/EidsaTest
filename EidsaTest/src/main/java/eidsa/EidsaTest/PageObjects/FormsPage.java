package eidsa.EidsaTest.PageObjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FormsPage {
	WebDriver driver;
	public FormsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//img[@alt='Form Menu']")
	WebElement FormMenu;

	@FindBy(xpath="//td[1]")
	List<WebElement> sitecodes;
	@FindBy(xpath="//td[2]")
	List<WebElement> subids;
	@FindBy(xpath="//td[3]")
	List<WebElement> visitnames;
	@FindBy(xpath="//td[4]")
	List<WebElement> status;
	@FindBy(xpath="//td[8]/i")
	List<WebElement> viewicons;

	@FindBy(xpath="//div[@class='ibox-content mainbgchange bottommargin']//label[contains(text(),'ID')]//following-sibling::input[1]")
	WebElement siteIdCode;
	@FindBy(xpath="//div[@class='ibox-content mainbgchange bottommargin']//label[contains(text(),'Screening')]//following-sibling::input[1]")
	WebElement patScrNo;

	@FindBy(xpath="//div[@class='modal__containers']//button[contains(text(),'Validate')]")
	WebElement validateButton;

	@FindBy(xpath="//i[@class='fa fa-check']")
	WebElement verifyicon;

	//Forms Search functionality
	@FindBy(xpath="//label[contains(text(),'Code')]/following-sibling::select")
	WebElement siteCode;
	@FindBy(xpath="//label[contains(text(),'ID')]/following-sibling::select")
	WebElement subId;
	
	@FindBy(xpath="//label[contains(text(),'Name')]/following-sibling::input")
	WebElement visitName;
	@FindBy(xpath="//label[contains(text(),'Status')]/following-sibling::select")
	WebElement formStatus;
	@FindBy(xpath="//span[contains(text(),'Search')]")
	WebElement searchbtn;
	

	public void formMenu()
	{
		FormMenu.click();
	}
	public void viewForm(String siteId,String subjId,String visName) throws InterruptedException
	{
		Thread.sleep(3000);
		for(int i=0;i<sitecodes.size();i++)
		{
			String scode=sitecodes.get(i).getText();
			String suid=subids.get(i).getText();
			String vname=visitnames.get(i).getText();
			if(scode.contains(siteId)&&suid.contains(subjId)&&vname.contains(visName))
			{
				viewicons.get(i).click();
				break;
			}
		}
	}
	public void verifysiteid() throws InterruptedException
	{
		Thread.sleep(3000);
		siteIdCode.click();
		Thread.sleep(3000);
		validateButton.click();
	}
	public void verifyPatScrNo() throws InterruptedException
	{
		Thread.sleep(2000);
		patScrNo.click();
		Thread.sleep(2000);
		validateButton.click();

	}
	public void verifyForm()
	{
		verifyicon.click();
	}
	public String formAlert() throws InterruptedException
	{
		Thread.sleep(2000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}
	public String successAlert() throws InterruptedException
	{
		Thread.sleep(2000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}
	//form Search 
	
	public void formsSiteCodeSearch(String scode) throws InterruptedException
	{
		Thread.sleep(3000);
		Select sel=new Select(siteCode);
		sel.selectByVisibleText(scode);
	}
	public void formsSubIdSearch(String suid) throws InterruptedException
	{
		Thread.sleep(3000);
		Select sel=new Select(subId);
		sel.selectByVisibleText(suid);
	}
	public void formsVisitNameSearch(String visname) throws InterruptedException
	{
		Thread.sleep(2000);
		visitName.sendKeys(visname);
	}
	public void formStatusSearch(String formstat) throws InterruptedException
	{
		Thread.sleep(3000);
		Select sel=new Select(formStatus);
		sel.selectByVisibleText(formstat);
	}
	public void formSearchBtn() throws InterruptedException
	{
		Thread.sleep(2000);
		searchbtn.click();
	}
	public Boolean formsSiteCodeValidation(String sicode) throws InterruptedException
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
				if (!(scode.contains(sicode))) 
				{
					st=false;
					break;
				}
			}
		}
		return st;
	}
	public Boolean formsSubIdValidation(String suid) throws InterruptedException
	{
		Thread.sleep(3000);
		boolean st = true;
		int count =subids.size();
		if (count<1) 
		{
			st=false;
		}
		else 
		{
			for (int i=0; i<subids.size();i++)
			{
				String scode=subids.get(i).getText();
				if (!(scode.contains(suid))) 
				{
					st=false;
					break;
				}
			}
		}
		return st;
	}
	public Boolean formsVisitNameValidation(String visname) throws InterruptedException
	{
		Thread.sleep(3000);
		boolean st = true;
		int count =visitnames.size();
		if (count<1) 
		{
			st=false;
		}
		else 
		{
			for (int i=0; i<visitnames.size();i++)
			{
				String scode=visitnames.get(i).getText();
				if (!(scode.contains(visname))) 
				{
					st=false;
					break;
				}
			}
		}
		return st;
	}
	public Boolean formsStatusValidation(String stat) throws InterruptedException
	{
		Thread.sleep(3000);
		boolean st = true;
		int count =status.size();
		if (count<1) 
		{
			st=false;
		}
		else 
		{
			for (int i=0; i<status.size();i++)
			{
				String scode=status.get(i).getText();
				if (!(scode.contains(stat))) 
				{
					st=false;
					break;
				}
			}
		}
		return st;
	}
}