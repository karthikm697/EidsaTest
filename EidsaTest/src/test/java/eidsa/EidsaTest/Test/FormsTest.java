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

import eidsa.EidsaTest.PageObjects.AddSubject;
import eidsa.EidsaTest.PageObjects.FormReviewPage;
import eidsa.EidsaTest.PageObjects.FormsPage;
import eidsa.EidsaTest.TestComponents.BaseTest;

public class FormsTest extends BaseTest {
	
	DataFormatter formatter=new DataFormatter();
	
	@Test(priority = 4,dataProvider = "data")
	public void FormReviewTest(String usern,String pass,String sid,String sname,String pVisit,String vno,String vname,String type,String vperiodday,String sitecode,String sitename,String subid,String enroldate,String invname,String siteidcode,String patScrNo,String visitDate,String invInitials) throws InterruptedException
	{
		studypage.login(usern,pass);
		AddSubject addsub=new AddSubject(driver);
		addsub.studyDropdown(sid);
		FormReviewPage formreview=new FormReviewPage(driver);
		
		formreview.formReviewMenu();
		formreview.viewReviewForm(sitecode,subid,vname);
		formreview.reviewandSubmit();
		Assert.assertEquals("Form submitted successfully.",formreview.submitformAlert());
		
	}
	
	@Test(priority = 5,dataProvider = "data")
	public void verifyForms(String usern,String pass,String sid,String sname,String pVisit,String vno,String vname,String type,String vperiodday,String sitecode,String sitename,String subid,String enroldate,String invname,String siteidcode,String patScrNo,String visitDate,String invInitials) throws InterruptedException
	{
		studypage.login(usern,pass);
		AddSubject addsub=new AddSubject(driver);
		addsub.studyDropdown(sid);
		FormsPage formpage=new FormsPage(driver);
		formpage.formMenu();
		formpage.viewForm(sitecode,subid,vname);

		formpage.verifysiteid();		
		formpage.verifyPatScrNo();
		formpage.verifyForm();
		Assert.assertEquals(formpage.formAlert(),"Status of some of the fields are not validated or incorrect. Do you want to verify this form?");
		Assert.assertEquals(formpage.successAlert(),"From verified successfully");
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
