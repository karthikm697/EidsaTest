package eidsa.EidsaTest.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import eidsa.EidsaTest.PageObjects.AddSitePage;
import eidsa.EidsaTest.PageObjects.AddSubject;
import eidsa.EidsaTest.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	
	@Test
	public void loginErrorValidation() throws InterruptedException
	{
		studypage.login("wrongmail@yopmail.com","123456");
		Assert.assertEquals(studypage.loginError(),"Invalid username or password.");
	}
	@Test
	public void studyFieldValidation() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com", "Dm#12345");
		studypage.createStudyIcon();
		studypage.createStudy("", "", "2");
		Assert.assertEquals(studypage.studyIdErrorMsg(), "Please enter a study ID");
		Assert.assertEquals(studypage.studyNameErrorMsg(), "Please enter a study name");
	}
	@Test
	public void siteFieldValidation() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com", "Dm#12345");
		AddSitePage addsite=new AddSitePage(driver);
		addsite.siteMenu();
		addsite.addSite("","SIteError");
		Assert.assertEquals(addsite.siteCodeErrorMsg(),"Please enter site code");
	}
	@Test
	public void subjectFieldValidation() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com", "Dm#12345");
		AddSubject addsub=new AddSubject(driver);
		addsub.subjectMenu();
		addsub.createSubjectIcon();
		addsub.subjectSave();
		//Assert.assertEquals(addsub.sitecodeErrorMsg(), "Please select a site code");
		Assert.assertEquals(addsub.subIdErrorMsg(), "Please enter a subject ID");
		Assert.assertEquals(addsub.subEnrDateErrorMsg(),"Please select enrolment date");
	}
	
}
