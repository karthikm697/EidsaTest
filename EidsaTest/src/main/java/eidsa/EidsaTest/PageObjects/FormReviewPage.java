package eidsa.EidsaTest.PageObjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormReviewPage {
	
	WebDriver driver;
	public FormReviewPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//a[@href='/reviewFormList']")
	WebElement formReviewMenu;
	@FindBy(xpath="//div[@class='ibox-content']//td[1]")
	List<WebElement> sitecode;
	@FindBy(xpath="//div[@class='ibox-content']//td[2]")
	List<WebElement> subid;
	@FindBy(xpath="//div[@class='ibox-content']//td[3]")
	List<WebElement> visitname;
	@FindBy(xpath="//div[@class='ibox-content']//td[4]")
	List<WebElement> status;
	@FindBy(xpath="//div[@class='ibox-content']//td[8]/i")
	List<WebElement> viewicon;
	
	@FindBy(xpath="//button[contains(text(),'Review And Submit')]")
	WebElement reviewandSubmit;
	
	
	
	public void formReviewMenu()
	{
		formReviewMenu.click();
	}
	
	//
	public void viewReviewForm(String siteid,String subjid,String visName) throws InterruptedException
	{
		Thread.sleep(3000);
		for(int i=0;i<sitecode.size();i++)
		{
			String scode=sitecode.get(i).getText();
			String suid=subid.get(i).getText();
			String vname=visitname.get(i).getText();
			if(scode.contains(siteid)&&suid.contains(subjid)&&vname.contains(visName))
			{
				viewicon.get(i).click();
				break;
			}
		}
	}
	
	public void reviewandSubmit() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,600)");
		Thread.sleep(2000);
		reviewandSubmit.click();
	}
	public String submitformAlert() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,0)");
		Thread.sleep(2000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}
}
