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
		studypage.login("karthikm697@gmail.com","Dm#12345");
		studypage.createStudyIcon();
		studypage.searchStudyId("SID");
		//studypage.searchStudyStatus("Archived");
		studypage.studySearch();
		Assert.assertTrue(studypage.studySearchValidation("SID"));
		//Assert.assertTrue(studypage.studyStatusValidation("Archived"));
	}
	@Test
	public void siteSearch() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com","Dm#12345");
		AddSitePage addsite=new AddSitePage(driver);
		addsite.siteMenu();
		addsite.siteCodeSearch("SiteNo");
//		addsite.siteNameSearch("Edit");
		addsite.siteStatusSearch("Enrolled");
		addsite.siteSearchBtn();
		
		Assert.assertTrue(addsite.siteCodeSearchValidation("SiteNo"));
//		Assert.assertTrue(addsite.siteNameSearchValidation("Edit"));
		Assert.assertTrue(addsite.siteStatusSearchValidation("Enrolled"));
	}
	@Test
	public void subjectSearch() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com","Dm#12345");
		AddSubject addsub=new AddSubject(driver);
		addsub.subjectMenu();
		addsub.siteCodeSearch("SiteNo2");
		addsub.subIdSearch("sub");
		addsub.subStatusSearch("Enrolled");
		addsub.subSearchBtn();
		Assert.assertTrue(addsub.siteCodeSearchValidation("SiteNo2"));
		Assert.assertTrue(addsub.siteNameSearchValidation("sub"));
		Assert.assertTrue(addsub.siteStatusSearchValidation("Enrolled"));

	}
	@Test
	public void formsSearch() throws InterruptedException
	{
		studypage.login("karthikm697@gmail.com","Dm#12345");
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
					
	}
}
