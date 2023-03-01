package eidsa.EidsaTest.PageObjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eidsa.EidsaTest.AbstractComponents.AbstractComponent;

public class SubmitFormPage extends AbstractComponent {
	WebDriver driver;
	public SubmitFormPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//img[@alt='Submit Form Menu']")
	WebElement submitFormMenu;

	@FindBy(xpath="//td[1]")
	List<WebElement> sitecode;
	@FindBy(xpath="//td[2]")
	List<WebElement> subid;
	@FindBy(xpath="//td[3]")
	List<WebElement> visitname;
	@FindBy(xpath="//td[6]/i")
	List<WebElement> submitformicon;


	@FindBy(xpath="//label[contains(text(),'Code')]/following-sibling::input[1]")
	WebElement siteIdCode;
	@FindBy(xpath="//label[contains(text(),'Screening Number')]/following-sibling::input[1]")
	WebElement patientScrNo;

	@FindBy(xpath="//label[contains(text(),'Date')]/following-sibling::input[1]")
	WebElement patVisitDate;
	@FindBy(xpath="//label[contains(text(),'Initials')]/following-sibling::input[1]")
	WebElement invinitials;

	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveform;
	@FindBy(xpath="//div[@id='getdfcontent']//button[contains(text(),'Review And Submit')]")
	WebElement reviewandSubmit;
	//Form submitted successfully.

	public void submitformMenu()
	{
		submitFormMenu.click();
	}
	public void submitformIcon(String siteId,String subjId,String visName) throws InterruptedException
	{
		Thread.sleep(3000);
		for(int i=0;i<sitecode.size();i++)
		{
			
			String scode=sitecode.get(i).getText();
			String suid=subid.get(i).getText();
			String vname=visitname.get(i).getText();
			if(scode.contains(siteId)&&suid.contains(subjId)&&vname.contains(visName))
			{
				submitformicon.get(i).click();
				break;
			}
		}
	}
	public void submitForm(String siteidcode,String patScreeningNo,String visDate,String invInitials) throws InterruptedException
	{
		Thread.sleep(2000);
//		Actions actions = new Actions(driver);
//		actions.moveToElement(siteIdCode).build().perform();
		elementWait(siteIdCode);
		siteIdCode.clear();
		siteIdCode.sendKeys(siteidcode);
		patientScrNo.clear();
		patientScrNo.sendKeys(patScreeningNo);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		patVisitDate.sendKeys(visDate);
		
		invinitials.clear();
		invinitials.sendKeys(invInitials);
	}
	public void saveForm()
	{
		saveform.click();
	}
	public void reviewandSubmit()
	{
		reviewandSubmit.click();
	}
	public String submitformAlert() throws InterruptedException
	{
		//	JavascriptExecutor jse = (JavascriptExecutor)driver;
		//	jse.executeScript("window.scrollBy(0,0)");
		Thread.sleep(2000);
		Alert alert=driver.switchTo().alert();
		String msg=alert.getText();
		alert.accept();
		return msg;
	}

}
