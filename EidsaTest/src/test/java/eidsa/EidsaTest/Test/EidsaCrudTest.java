package eidsa.EidsaTest.Test;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eidsa.EidsaTest.PageObjects.AddSitePage;
import eidsa.EidsaTest.PageObjects.AddSubject;
import eidsa.EidsaTest.TestComponents.BaseTest;

public class EidsaCrudTest extends BaseTest{
	
	DataFormatter formatter=new DataFormatter();
	
	@Test(dataProvider = "CrudData")
	public void studyCrud(String usern,String pass,String crudstudyId,String crudstudyname,String postvisit,String editstudyId,String editstudyName,String crudSitecode,String crudSitename,String editSitecode,String editSitename,String SiteIddropdown,String crudSubId,String crudEnroldate,String editSubid,String editEnroldate) throws InterruptedException
	{
		studypage.login(usern,pass);
		studypage.createStudyIcon();
		studypage.createStudy(crudstudyId,crudstudyname,postvisit);
		Assert.assertEquals("Study created successfully.", studypage.studyAlert());
		log.info("Crud study created");
		studypage.editStudyIcon(crudstudyId);
		studypage.editStudy(editstudyId, editstudyName);
		Assert.assertEquals("Study updated successfully.",studypage.editStudyAlert());
		log.info("Study updated");
		studypage.delStudyIcon(editstudyId);
		Assert.assertEquals("Are you sure you want to delete this study?",studypage.delStudyAlert());	
		log.info("Study deleted successfully");
	}
	
	
	@Test(dataProvider = "CrudData")
	public void siteCrud(String usern,String pass,String crudstudyId,String crudstudyname,String postvisit,String editstudyId,String editstudyName,String crudSitecode,String crudSitename,String editSitecode,String editSitename,String SiteIddropdown,String crudSubId,String crudEnroldate,String editSubid,String editEnroldate) throws InterruptedException
	{
	studypage.login(usern,pass);
	AddSitePage addsite=new AddSitePage(driver);
	//addsite.studyDropdown("SID12");
	addsite.siteMenu();
	Thread.sleep(2000);
	addsite.addSite(crudSitecode,crudSitename);
	Assert.assertEquals("Site saved successfully.",addsite.siteAlert());
	log.info("Crud site added");
	Thread.sleep(3000);
	addsite.siteEdit(crudSitecode);
	addsite.addEdit(editSitecode,editSitename);
	Assert.assertEquals("Site updated successfully.",addsite.editSiteAlert());
	log.info("Site updated");
	addsite.delEdit(editSitecode);
	Assert.assertEquals("Are you sure you want to delete this site?",addsite.delSiteAlert());
	log.info("Site deleted successfully");
	}
	
	@Test(dataProvider = "CrudData")
	public void subjectCrud(String usern,String pass,String crudstudyId,String crudstudyname,String postvisit,String editstudyId,String editstudyName,String crudSitecode,String crudSitename,String editSitecode,String editSitename,String SiteIddropdown,String crudSubId,String crudEnroldate,String editSubid,String editEnroldate) throws Exception
	{
		studypage.login(usern,pass);
		AddSubject addsub=new AddSubject(driver);
		//addsub.studyDropdown("sid11");
		
		addsub.subjectMenu();
		addsub.createSubjectIcon();
		addsub.createSubject(SiteIddropdown,crudSubId,crudEnroldate, "Karthik M");
		addsub.subjectSave();
		Assert.assertEquals("Subject added successfully.",addsub.subjectAlert());
		log.info("Crud Subject added");
		addsub.subjectEdit(crudSubId);
		addsub.addEditSubject(editSubid,editEnroldate);
		Assert.assertEquals("Subject updated successfully.",addsub.subEditAlert());
		log.info("Subject updated ");
		Thread.sleep(2000);
		addsub.delSubject(editSubid);
		Assert.assertEquals("Are you sure you want to delete this patient?",addsub.delSubAlert());
		log.info("Subject deleted successfully");
	}
	
	@DataProvider(name="CrudData")
	public Object[][] getData() throws Exception 
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\DM Administrator\\Documents\\eidsadata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheetAt(1);
		int rowCount=sheet.getPhysicalNumberOfRows();
		XSSFRow row=sheet.getRow(1);
		int colCount=row.getLastCellNum();
		Object data[][]=new Object[rowCount-1][colCount];
		for(int i=0;i<rowCount-1;i++)
		{
			row=sheet.getRow(i+1);
			for(int j=0;j<colCount;j++)
			{
				XSSFCell cell=row.getCell(j);
				data[i][j]=formatter.formatCellValue(cell);
			}
		}
		return data;	
	}	
}
