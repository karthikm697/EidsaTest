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

import eidsa.EidsaTest.PageObjects.AddSubject;
import eidsa.EidsaTest.PageObjects.SubmitFormPage;
import eidsa.EidsaTest.TestComponents.BaseTest;

public class SubmitFormTest extends BaseTest {
	
	DataFormatter formatter=new DataFormatter();
	
	@Test(priority = 3,dataProvider = "data" )
	public void submitForm(String usern, String pass,String sid,String sname,String pVisit,String vno,String vname,String type,String vperiodday,String sitecode,String sitename,String subid,String enroldate,String invname,String siteidcode,String patScrNo,String visitDate,String invInitials) throws InterruptedException
	{
		studypage.login(usern,pass);
		AddSubject addsub=new AddSubject(driver);
		addsub.studyDropdown(sid);
		
		SubmitFormPage subform=new SubmitFormPage(driver);
		
		subform.submitformMenu();
		subform.submitformIcon(sitecode,subid,vname);
		subform.submitForm(siteidcode,patScrNo,visitDate,invInitials);
		subform.saveForm();
		Assert.assertEquals(subform.submitformAlert(),"Form saved successfully.");
		//subform.reviewandSubmit();
		//Assert.assertEquals("Form submitted successfully",subform.submitformAlert());
		
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
