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

public class AddSubject extends AbstractComponent{
	WebDriver driver;
	public  AddSubject(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
//study template
	
	@FindBy(xpath = "//img[@alt='Study Menu']")
	WebElement studymenucollapse;
	@FindBy(xpath = "//img[@alt='Study Template Menu']")
	WebElement studyTemplate;
	@FindBy(xpath = "//select[@class='form-select mx-lg-12 my-lg-0']")
	WebElement selStudy;
	@FindBy(xpath = "//td[text()='Case Report']/preceding-sibling::td//input")
	WebElement template;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;
//main study dropdown		
	@FindBy(xpath = "//select[@class='study-select']")
	WebElement studydropdown;

	
//Add subject 	
	@FindBy(xpath="//img[@alt='Subjects Menu']")
	WebElement subjectmenu;
	@FindBy(xpath="//img[@alt='Create subject']")
	WebElement createsubject;
	@FindBy(xpath = "(//select[@name='account'])[2]")
	WebElement sitecode;
	@FindBy(xpath = "//label[contains(text(),'ID')]/following-sibling::input[1]")
	WebElement subid;
	@FindBy(xpath = "//input[@type='date']")
	WebElement enroldate;
	@FindBy(xpath = "(//select[@class='form-select'])[2]")
	WebElement investigator;
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement subsave;
//patient visit schedule 	
	@FindBy(xpath="//td[9]/i")
	List<WebElement> schedule;
	@FindBy(xpath="//img[@alt='Schedule patient']")
	WebElement scheduleVisit;
//Assign template	
	@FindBy(xpath="//img[@alt='Visits Menu']")
	WebElement visitmenu;
	@FindBy(xpath="//td[1]")
	List<WebElement> visitno;
	@FindBy(xpath="//td[13]/i")
	List<WebElement> assignTemplateicon;
	@FindBy(xpath = "//div[@class='modelbody']//select")
	WebElement selectTemplate;
	@FindBy(xpath="//button[contains(@class,'primary')]")
	WebElement assign;
//subject crud
	@FindBy(xpath="//td[2]")
	List<WebElement> subids;
	@FindBy(xpath="//tr")
	List<WebElement> subidsDel;
	@FindBy(xpath="//td[11]/i")
	List<WebElement> subedit;
	@FindBy(xpath="//td[14]")
	List<WebElement> subdel;
	@FindBy(xpath="//i")
	WebElement delicon;

	//subject Error 
	@FindBy(xpath="//label[contains(text(),'Code')]//following-sibling::span[@class='errorClass']")
	WebElement sitecodeErr;
	@FindBy(xpath="//label[contains(text(),'ID')]//following-sibling::span[@class='errorClass']")
	WebElement subidErr;
	@FindBy(xpath="//label[contains(text(),'Date')]//following-sibling::span[@class='errorClass']")
	WebElement subEnrDateErr;
	
	//subject search function
	
	@FindBy(xpath="//label[contains(text(),'Status')]/following-sibling::select")
	WebElement subStatusSearch;
	@FindBy(xpath="//span[contains(text(),'Search')]")
	WebElement searchbtn;
	
	@FindBy(xpath="//td[1]")
	List<WebElement> sitecodes;
	@FindBy(xpath="//td[3]")
	List<WebElement> substatus;
	//Schedule verification
	@FindBy(xpath="//td[3]")
	List<WebElement> visitnos;
	
	public void studyDropdown(String studId)
	{
		Select svalue=new Select(studydropdown);
		svalue.selectByVisibleText(studId);
	}
	
	public void assignVisitTemplate(String visitNo)
	{
		
		visitmenu.click();
		for(int i=0;i<visitno.size();i++)
		{
			String visitmatch=visitno.get(i).getText();
			if(visitmatch.contains(visitNo))
			{
				assignTemplateicon.get(i).click();
			}
			
		}
	}
	public void assignTemplate(String studytemplate) throws InterruptedException
	{
		Thread.sleep(3000);
		Select assignt=new Select(selectTemplate);
		assignt.selectByValue(studytemplate);
		assign.click();
	}
	
	public String assignTemplateAlert() throws InterruptedException
	{
		//
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}	
	public void subjectMenu()
	{
		subjectmenu.click();
	}
	public void createSubjectIcon() throws InterruptedException
	{
		Thread.sleep(3000);
		createsubject.click();
	}
	
	public void createSubject(String scode, String subjectid,String edate,String inv) throws InterruptedException
	{
		
		sitecode.click();
		Select sel=new Select(sitecode);
		sel.selectByVisibleText(scode);
		subid.sendKeys(subjectid);
		enroldate.sendKeys(edate);
		Select isel=new Select(investigator);
		isel.selectByVisibleText(inv);	
	}
	public void subjectSave() throws InterruptedException
	{
		Thread.sleep(2000);
		subsave.click();
	}
	public String subjectAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}
	
	public boolean verifySubject(String subjectid) throws InterruptedException 
	{
		Thread.sleep(2000);
		boolean subfound=false;
		int count = subids.size();
		for(int i=0;i<count;i++)
		{
			
			String text =  subids.get(i).getText();
			if(text.contains(subjectid))
			{
				subfound=true;
				break;
			}
		}
		return subfound;
	}
	public void scheduleIcon(String subid) throws InterruptedException
	{
		Thread.sleep(3000);
		int count=subids.size();
		for(int i=0;i<count;i++)
		{
			String sub=subids.get(i).getText();
			if(sub.equals(subid))
			{
				schedule.get(i).click();
				break;
			}
		}
	}
	public void scheduleVisit()
	{
		scheduleVisit.click();
	}
	public String scheduleAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}
	
	public boolean verifySchedule(String subjectid,String scode,String vno) throws InterruptedException 
	{
		Thread.sleep(3000);
		boolean schfound=false;
		int count = subids.size();
		for(int i=0;i<count;i++)
		{
			String sitecode=sitecodes.get(i).getText();
			String subid =  subids.get(i).getText();
			String visitno=visitnos.get(i).getText();
			
			if(sitecode.contains(scode)&&subid.contains(subjectid)&&visitno.contains(vno))
			{
				schfound=true;
				break;
			}
		}
		return schfound;
	}
	//subject crud
	public void subjectEdit(String subid)
	{
		int count = subids.size();
		for(int i=0;i<count;i++)
		{
			
			String text =  subids.get(i).getText();
			if(text.equals(subid))
			{
				subedit.get(i).click();
				break;
			}
		}
	}
	public void addEditSubject(String newSubId,String newEnrolDate) throws InterruptedException
	{
		Thread.sleep(2000);
		subid.clear();
		subid.sendKeys(newSubId);
		enroldate.clear();
		enroldate.sendKeys(newEnrolDate);
		Thread.sleep(3000);
		subsave.click();
	}
	public String subEditAlert() throws InterruptedException
	{
		Thread.sleep(2000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}
	public void delSubject(String newSubId)
	{
		
		int count = subids.size();
		for(int i=0;i<count;i++)
		{
			String text =  subids.get(i).getText();
			if(text.equals(newSubId))
			{
				subdel.get(i).findElement(By.tagName("i")).click();
				break;
			}
		}
	}
	public String delSubAlert() throws InterruptedException
	{
		Thread.sleep(2000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		Thread.sleep(1000);
		return msg;
	}
	
	//Error Validation
	
	public String sitecodeErrorMsg()
	{
		elementWait(sitecodeErr);
		return sitecodeErr.getText();
	}
	public String subIdErrorMsg()
	{
		elementWait(subidErr);
		return subidErr.getText();
	}
	public String subEnrDateErrorMsg()
	{
		elementWait(subEnrDateErr);
		return subEnrDateErr.getText();
	}
	
	public String duplicateSubAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		alt.accept();
		return msg;
	}
	
	//Subject search function
	public void siteCodeSearch(String scode) throws InterruptedException
	{
		Thread.sleep(3000);
		Select sel=new Select(sitecode);
		sel.selectByVisibleText(scode);
	}
	public void subIdSearch(String suid) throws InterruptedException
	{
		Thread.sleep(3000);
		subid.sendKeys(suid);
	}
	public void subStatusSearch(String sitestatus) throws InterruptedException
	{
		Thread.sleep(3000);
		Select sel=new Select(subStatusSearch);
		sel.selectByVisibleText(sitestatus);
	}
	public void subSearchBtn() throws InterruptedException
	{
		Thread.sleep(2000);
		searchbtn.click();
	}
	public Boolean siteCodeSearchValidation(String sicode) throws InterruptedException
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
	public Boolean siteNameSearchValidation(String suid) throws InterruptedException
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
	public Boolean siteStatusSearchValidation(String substat) throws InterruptedException
	{
		Thread.sleep(3000);
		boolean st = true;
		int count =substatus.size();
		if (count<1) 
		{
			st=false;
		}
		else 
		{
			for (int i=0; i<substatus.size();i++)
			{
				String scode=substatus.get(i).getText();
				if (!(scode.contains(substat))) 
				{
					st=false;
					break;
				}
			}
		}
		return st;
	}
	
}
