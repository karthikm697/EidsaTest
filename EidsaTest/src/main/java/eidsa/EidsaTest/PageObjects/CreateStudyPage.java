package eidsa.EidsaTest.PageObjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import eidsa.EidsaTest.AbstractComponents.AbstractComponent;

public class CreateStudyPage extends AbstractComponent{

	WebDriver driver;

	public CreateStudyPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath = "//input[@type='email']")
	WebElement email;
	@FindBy(xpath = "//input[@type='password']")
	WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;
	@FindBy(xpath="//span[@class='errmsg']")
	WebElement loginerr;

	@FindBy(xpath="//a[@href='/studylist']")
	WebElement study;
	@FindBy(xpath="//img[@alt='Create study']")
	WebElement createStudy;

	@FindBy(xpath = "//label[contains(text(),'ID')]/following-sibling::input[1]")
	WebElement studyId;
	@FindBy(xpath="//label[contains(text(),'Name')]/following-sibling::input[1]")
	WebElement studyName;
	@FindBy(xpath="//label[contains(text(),'Post')]/following-sibling::input[1]")
	WebElement postVisit;

	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveStudy;

	@FindBy(xpath="//table[contains(@class,'dataTables')]//td[1]")
	List<WebElement> studyid;

	@FindBy(xpath="//table[contains(@class,'dataTables')]//td[2]")
	List<WebElement> studynames;
	@FindBy(xpath="//table[contains(@class,'dataTables')]//td[9]//i")
	List<WebElement> visiticon;

	@FindBy(xpath="//img[@alt='Add visit']")
	WebElement addvisit;

	@FindBy(xpath = "//label[contains(text(),'No')]/following-sibling::input[1]")
	WebElement visitno;
	@FindBy(xpath="//label[contains(text(),'Name')]/following-sibling::input[1]")
	WebElement visitname;
	@FindBy(xpath="//select[@class='form-select']")
	WebElement visittype;
	@FindBy(xpath="//label[contains(text(),'Period(Days)')]/following-sibling::input[1]")
	WebElement visitPeriodDay;
	@FindBy(xpath="//button[@type='submit']")
	WebElement saveVisit;

	@FindBy(xpath="(//img[@alt='Users Menu'])[2]")
	WebElement users;
	@FindBy(xpath="//button[@class='Page']")
	List<WebElement> pages ;
	//study ids
	@FindBy(xpath="//td[1]")
	List<WebElement> ids;
	//Study names
	@FindBy(xpath="//td[2]")
	List<WebElement> names;
	@FindBy(xpath="//td[8]//i")
	List<WebElement> previlegeicon;
	@FindBy(xpath="(//li[@class='PaginationControl'])[3]")
	WebElement next;


	@FindBy(xpath="//div[@class='multiselect multiselect-theme']")
	WebElement previlegeDropdown;
	@FindBy(xpath="//li[@class='multiselect-option']")
	List<WebElement> selPrevilege;
	@FindBy(xpath="//button[@class='btn btn-primary save_btn px-3 mx-0 w-100']")
	WebElement setPrevilege;

	//study crud
	@FindBy(xpath="//table[contains(@class,'dataTables')]//td[11]/i")
	List<WebElement> studyedit;
	@FindBy(xpath="//table[contains(@class,'dataTables')]//td[13]/i")
	List<WebElement> studydel;

	//study Error validation

	@FindBy(xpath="//label[contains(text(),'ID')]//following-sibling::span[@class='errormsg']")
	WebElement studyidErr;
	@FindBy(xpath="//label[contains(text(),'Name')]//following-sibling::span[@class='errormsg']")
	WebElement studynameErr;

	//study search functionality

	@FindBy(xpath="//label[contains(text(),'ID')]//following-sibling::input[1]")
	WebElement studyidsearch;
	@FindBy(xpath="//label[contains(text(),'Status')]//following-sibling::select")
	WebElement studystatusSearch;
	@FindBy(xpath="//span[@type='button']")
	WebElement studySearch;

	@FindBy(xpath="//td[3]")
	List<WebElement> studyStatus;

	public void login(String emailid, String pass) throws InterruptedException
	{
		email.sendKeys(emailid);
		password.sendKeys(pass);
		submit.click();
		Thread.sleep(3000);
	}
	public String loginError()
	{
		elementWait(loginerr);
		return loginerr.getText();
	}
	//
	public void createStudyIcon() throws InterruptedException
	{
		
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700)");
		Thread.sleep(3000);
		elementWait(study);
		study.click();
		Thread.sleep(3000);
		//		jse.executeScript("window.scrollBy(0,0)");
		//		Thread.sleep(2000);
	}
	public void createStudy(String studId,String studName,String postvisit) throws InterruptedException
	{
		//
		Thread.sleep(3000);
		createStudy.click();
		studyId.sendKeys(studId);
		studyName.sendKeys(studName);
		postVisit.sendKeys(postvisit);
		saveStudy.click();
	}
	public String studyAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}

	//Error validation
	public String studyIdErrorMsg()
	{
		elementWait(studyidErr);
		return studyidErr.getText();
	}
	public String studyNameErrorMsg()
	{
		elementWait(studynameErr);
		return studynameErr.getText();
	}

	public void visitIcon(String studId)
	{

		int count = studyid.size();
		for(int i=0;i<count;i++)
		{

			String text =  studyid.get(i).getText();
			if(text.contains(studId))
			{
				visiticon.get(i).click();
				break;
			}
		}
	}

	public void addVisit(String vno,String vname,String type,String vperiodDay)
	{
		addvisit.click();
		visitno.sendKeys(vno);
		visitname.sendKeys(vname);
		Select activ=new Select(visittype);
		activ.selectByVisibleText(type);
		visitPeriodDay.sendKeys(vperiodDay);
		saveVisit.click();
	}
	public String visitAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String vmsg=alert.getText();
		alert.accept();
		return vmsg;
	}
	public void userPrevilege() throws InterruptedException
	{
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);
		users.click();
		Thread.sleep(3000);
		jse.executeScript("window.scrollBy(0,0)");
		Thread.sleep(3000);
	}
	public void setPrevilegeIcon(String email) throws InterruptedException
	{

		int j=0;
		int i=0;
		while(j<pages.size())
		{
			int namecount=0;
			while(i<10)
			{
				Thread.sleep(500);
				String name =  names.get(i).getText();
				if(name.contains(email))
				{
					Thread.sleep(2000);
					previlegeicon.get(i).click();
					namecount=1;
					break;
				}
				else
				{
					i++;		
				}
			}
			if(namecount==0)
			{
				next.click();
				i=0;
			}	
			j++;		
		}
	}
	public void selPrevilege(String studyname)
	{
		previlegeDropdown.click();
		for(int k=0;k<selPrevilege.size();k++)
		{
			String sname=selPrevilege.get(k).getText();
			if(sname.equals(studyname))
			{
				selPrevilege.get(k).click();
			}
		}
	}
	public void setPrevilege() throws InterruptedException
	{
		setPrevilege.click();
		Thread.sleep(3000);
	}
	public String userPrevilegeAlert() throws InterruptedException
	{
		//
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String vmsg=alert.getText();
		alert.accept();
		return vmsg;
	}

	//study crud
	public void editStudyIcon(String crudstudyid)
	{
		for(int i=0;i<studyid.size();i++)
		{
			String text=studyid.get(i).getText();
			if(text.equals(crudstudyid))
			{
				studyedit.get(i).click();
				break;
			}
		}
	}
	public void editStudy(String newStudyId,String newStudyName) throws InterruptedException
	{
		Thread.sleep(2000);
		studyId.clear();
		studyId.sendKeys(newStudyId);
		studyName.clear();
		studyName.sendKeys(newStudyName);
		saveStudy.click();	
	}
	public String editStudyAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}
	public void delStudyIcon(String editedstudy) throws InterruptedException
	{
		Thread.sleep(1000);
		int count=studyid.size();
		for(int i=0;i<count;i++)
		{
			String studylist=studyid.get(i).getText();
			System.out.println(studylist+i);
			if(studylist.contains(editedstudy))
			{
				System.out.println(studydel.get(i));
				studydel.get(i).click();
				break;
			}
		}
	}
	public String delStudyAlert() throws InterruptedException
	{
		Thread.sleep(2000);
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		alt.accept();
		return msg;
	}

	public void searchStudyId(String studysearch) throws InterruptedException
	{
		Thread.sleep(2000);
		studyidsearch.sendKeys(studysearch);
	}
	public void searchStudyStatus(String studystatus)
	{
		Select sel=new Select(studystatusSearch);
		sel.selectByVisibleText(studystatus);
	}
	public void studySearch()
	{
		studySearch.click();
	}
	public Boolean studySearchValidation(String search) throws InterruptedException
	{
		Thread.sleep(3000);
		Boolean present=true;
		int count=ids.size();
		if(count<1)
		{ 
			present=false;
		}
		else
		{
			for(int i=0;i<ids.size();i++)
			{
				String name=names.get(i).getText();
				String id=ids.get(i).getText();
				//System.out.println(id);
				if (!(id.contains(search)||(name.contains(search)))) 
				{
					System.out.println(id);
					present = false;
					break;
				}
			}
			if((present==true)&&(next.isDisplayed()))
			{
				next.click();
			}
		}
		return present;
	}
	public Boolean studyStatusValidation(String status) throws InterruptedException
	{
		Thread.sleep(3000);
		boolean st = true;
		int count =studyStatus.size();
		if (count<1) 
		{
			st=false;
		}
		else 
		{
			for (int i=0; i<studyStatus.size();i++)
			{
				String stat=studyStatus.get(i).getText();
				if (!(stat.contains(status))) 
				{
					st=false;
					break;
				}
			}
		}
		return st;
	}
}
