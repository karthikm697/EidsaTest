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
import org.testng.asserts.SoftAssert;

import eidsa.EidsaTest.PageObjects.AddSitePage;
import eidsa.EidsaTest.PageObjects.AddSubject;
import eidsa.EidsaTest.TestComponents.BaseTest;

public class CreateStudyTest extends BaseTest {
	
	DataFormatter formatter=new DataFormatter();
	
//	@Test(priority = 1,dataProvider = "data")
//	public void createStudy(String usern, String pass,String sid,String sname,String pVisit,String vno,String vname,String type,String vperiodday,String sitecode,String sitename,String subid,String enroldate,String invname,String siteidcode,String patScrNo,String visitDate,String invInitials) throws InterruptedException
//	{
//	studypage.login(usern,pass);
//	studypage.createStudyIcon();
//	studypage.createStudy(sid,sname,pVisit);
//	
//	Assert.assertEquals("Study created successfully.", studypage.studyAlert());
//	Assert.assertTrue(studypage.verifyStudy(sid));
//	studypage.visitIcon(sid);
//	studypage.addVisit(vno,vname,type,vperiodday);
//	Assert.assertEquals("Visit added successfully.",studypage.visitAlert());
//	Assert.assertTrue(studypage.verifyVisit(vno));
//	studypage.userPrevilege();
//	studypage.setPrevilegeIcon(usern);
//	studypage.selPrevilege(sid);
//	studypage.setPrevilege();
//	Assert.assertEquals("User study privilege updated successfully.",studypage.userPrevilegeAlert());
//	}
	
	
	@Test(priority = 2,dataProvider = "data")
	public void AddSub(String usern, String pass,String sid,String sname,String pVisit,String vno,String vname,String type,String vperiodday,String sitecode,String sitename,String subid,String enroldate,String invname,String siteidcode,String patScrNo,String visitDate,String invInitials) throws Exception
	{
		studypage.login(usern,pass);
		AddSubject addsub=new AddSubject(driver);
		AddSitePage addsite=new AddSitePage(driver);
		//
//		addsite.studyTemplate();
//		addsite.selTemplate(sid);
//		Assert.assertEquals("Study template updated successfully", addsite.templateAlert());
		//
		addsite.studyDropdown(sid);
		driver.navigate().refresh();
		Thread.sleep(2000);
		addsite.siteMenu();
		addsite.addSite(sitecode, sitename);
		Assert.assertEquals("Site saved successfully.",addsite.siteAlert());
		Assert.assertTrue(addsite.verifySite(sitecode));
		//
//		addsub.assignVisitTemplate(vno);
//		addsub.assignTemplate("aa6e4d9c-e9e0-4a5c-946f-447585561f95");
//		Assert.assertEquals("Template assigned successfully",addsub.assignTemplateAlert());
		//
		addsub.subjectMenu();
		addsub.createSubjectIcon();
		addsub.createSubject(sitecode, subid, enroldate, invname);
		addsub.subjectSave();
		
		Assert.assertEquals("Subject added successfully.",addsub.subjectAlert());
		Assert.assertTrue(addsub.verifySubject(subid));
		addsub.scheduleIcon(subid);
		addsub.scheduleVisit();
		Assert.assertEquals(addsub.scheduleAlert(),"Schedule created successfully");
		addsub.scheduleIcon(subid);
		Assert.assertTrue(addsub.verifySchedule(subid,sitecode,vno));
	 
	}
	
	@DataProvider(name="data")
	public Object[][] getData() throws Exception 
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\DM Administrator\\Documents\\eidsadata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheetAt(0);
		int rowCount=sheet.getPhysicalNumberOfRows();
		XSSFRow row=sheet.getRow(1);
		int colCount=row.getLastCellNum();
		Object data[][]=new Object[rowCount-1][colCount];
		for(int i=0;i<(rowCount-1);i++)
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
