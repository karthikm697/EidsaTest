package eidsa.EidsaTest.PageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddVisitPage {
	
	WebDriver driver;
	
	public AddVisitPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath = "//input[@type='email']")
	WebElement email;
	@FindBy(xpath = "//input[@type='password']")
	WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;
	
	@FindBy(xpath="//a[@href='/studylist']")
	WebElement study;
	
	@FindBy(xpath="//table[contains(@class,'dataTables')]//td[1]")
	List<WebElement> studyid;
	
	@FindBy(xpath="//table[contains(@class,'dataTables')]//td[9]//i")
	WebElement visiticon;
	
	public void createStudy1(String studId,String studName,String postvisit) throws InterruptedException
	{
		Thread.sleep(5000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,600)");
		Thread.sleep(5000);
		study.click();
		Thread.sleep(3000);
		jse.executeScript("window.scrollBy(0,0)");
		Thread.sleep(3000);
		
	}
	public void addVisit1(String studId)
	{

		int count = studyid.size();
		for(int i=0;i<count;i++)
		{
			String text =  studyid.get(i).getText();
			if(text.contains(studId))
			{
				visiticon.click();
				break;
			}
		}
	}
}
