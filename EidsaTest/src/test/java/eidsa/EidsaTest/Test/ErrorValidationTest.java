package eidsa.EidsaTest.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import eidsa.EidsaTest.PageObjects.AddSitePage;
import eidsa.EidsaTest.PageObjects.AddSubject;
import eidsa.EidsaTest.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	
	@Test(priority = 1,groups = {"Login Error validation"})
	public void loginErrorValidation() throws InterruptedException
	{
		studypage.login("wrongg@yopmail.com","1234");
		Assert.assertEquals(studypage.loginError(),"Invalid username or password.");
		System.out.println("Invalid credentials");
	}
	@Test(priority = 2)
	public void studyFieldValidation() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com", "Dm#123456");
		studypage.createStudyIcon();
		studypage.createStudy("", "", "2");
		Assert.assertEquals(studypage.studyIdErrorMsg(), "Please enter a study ID");
		Assert.assertEquals(studypage.studyNameErrorMsg(), "Please enter a study name");
		System.out.println("Study mandatory fields validation");
	}
	@Test(priority = 3)
	public void siteFieldValidation() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com", "Dm#123456");
		AddSitePage addsite=new AddSitePage(driver);
		addsite.siteMenu();
		addsite.addSite("","SIteError");
		Assert.assertEquals(addsite.siteCodeErrorMsg(),"Please enter site code");
		System.out.println("Site mandatory fields validation");
	}
	@Test(priority = 4)
	public void subjectFieldValidation() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com", "Dm#123456");
		AddSubject addsub=new AddSubject(driver);
		addsub.subjectMenu();
		addsub.createSubjectIcon();
		addsub.subjectSave();
		//Assert.assertEquals(addsub.sitecodeErrorMsg(), "Please select a site code");
		Assert.assertEquals(addsub.subIdErrorMsg(), "Please enter a subject ID");
		Assert.assertEquals(addsub.subEnrDateErrorMsg(),"Please select enrolment date");
		System.out.println("Subject mandatory fields validation");
	}
	@Test(priority = 5,groups = {"duplicate data validation"})
	public void duplicateStudyValidation() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com", "Dm#123456");
		studypage.createStudyIcon();
		studypage.createStudy("SID22", "sss", "2");
		Assert.assertEquals(studypage.duplicateStudyAlert(),"Study ID already exists.");
		System.out.println("Duplicate Study Id validation");
	}
	
	@Test(priority = 6,groups = {"duplicate data validation"})
	public void duplicateSiteValidation() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com", "Dm#123456");
		AddSitePage addsite=new AddSitePage(driver);
		addsite.siteMenu();
		addsite.addSite("SiteNo2","SIteError");
		Assert.assertEquals(addsite.duplicateSiteAlert(),"Site code already exists");
		System.out.println("Duplicate Site code validation");
	}
	
	@Test(priority = 7,groups = {"duplicate data validation"})
	public void duplicateSubValidation() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com", "Dm#123456");
		AddSubject addsub=new AddSubject(driver);
		addsub.subjectMenu();
		addsub.createSubjectIcon();
		addsub.createSubject("SiteNo2", "sub1", "02022023", "Karthik M");
		addsub.subjectSave();
		Assert.assertEquals(addsub.duplicateSubAlert(),"Subject ID already exists");
		System.out.println("Duplicate Subject Id validation");
	}
	
}
