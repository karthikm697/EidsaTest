package eidsa.EidsaTest.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import eidsa.EidsaTest.PageObjects.AddSitePage;
import eidsa.EidsaTest.PageObjects.AddSubject;
import eidsa.EidsaTest.PageObjects.FormsPage;
import eidsa.EidsaTest.TestComponents.BaseTest;

public class SearchFunctionTest extends BaseTest{
	
	@Test
	public void studySearch() throws Exception
	{
		studypage.login("karthikm697@gmail.com","Dm#123456");
		studypage.createStudyIcon();
		studypage.searchStudyId("S");
		studypage.searchStudyStatus("Open");
		studypage.studySearch();
		Assert.assertTrue(studypage.studySearchValidation("SID"));
		Assert.assertTrue(studypage.studyStatusValidation("Open"));
		log.info("Study search functionality validated");
	}
	@Test
	public void siteSearch() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com","Dm#123456");
		AddSitePage addsite=new AddSitePage(driver);
		addsite.siteMenu();
		addsite.siteCodeSearch("S");
		addsite.siteNameSearch("S");
		addsite.siteStatusSearch("Enrolled");
		addsite.siteSearchBtn();
		
		Assert.assertTrue(addsite.siteCodeSearchValidation("S"));
		Assert.assertTrue(addsite.siteNameSearchValidation("S"));
		Assert.assertTrue(addsite.siteStatusSearchValidation("Enrolled"));
		log.info("Site search functionality validated");
	}
	@Test
	public void subjectSearch() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com","Dm#123456");
		AddSubject addsub=new AddSubject(driver);
		addsub.subjectMenu();
		addsub.siteCodeSearch("SiteNo2");
		addsub.subIdSearch("sub");
		addsub.subStatusSearch("Enrolled");
		addsub.subSearchBtn();
		Assert.assertTrue(addsub.siteCodeSearchValidation("SiteNo2"));
		Assert.assertTrue(addsub.siteNameSearchValidation("sub"));
		Assert.assertTrue(addsub.siteStatusSearchValidation("Enrolled"));
		log.info("Subject search functionality validated");
	}
	@Test
	public void formsSearch() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com","Dm#123456");
		FormsPage formpg=new FormsPage(driver);
		formpg.formMenu();
		formpg.formsSiteCodeSearch("SiteDemo25");
		formpg.formsSubIdSearch("sub4");
		formpg.formsVisitNameSearch("visit2");
		formpg.formStatusSearch("Verified");
		formpg.formSearchBtn();
		Assert.assertTrue(formpg.formsSiteCodeValidation("SiteDemo25"));
		Assert.assertTrue(formpg.formsSubIdValidation("sub4"));
		Assert.assertTrue(formpg.formsVisitNameValidation("visit2"));
		Assert.assertTrue(formpg.formsStatusValidation("Verified"));
		log.info("Forms search functionality validated");			
	}
}
