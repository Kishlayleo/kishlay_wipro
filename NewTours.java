package wipro123.wipro1234;

import java.io.FileInputStream;

import java.io.IOException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


public class NewTours {

	static WebDriver driver;
	NewRegistration registrationPage;
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/KI20463597/Downloads/chromedriver_mac_arm64/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/newtours/register.php");
		registrationPage = NewRegistration(driver);
	}
	private NewRegistration NewRegistration(WebDriver driver2) {
		// TODO Auto-generated method stub
		return null;
	}
	@DataProvider(name="rData")
		public Object[][] getRegistrationData() throws IOException {
		String filepath= "//Users/KI20463597/Downloads/file1.xlsx";
		FileInputStream fis= new FileInputStream(filepath);
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sht= wb.getSheetAt(0);
		int rowc= sht.getLastRowNum();
		int colc= sht.getRow(0).getLastCellNum();
		Object[][] data= new Object[rowc][colc];
		for(int i=1;i<=rowc;i++) {
			Row row=sht.getRow(i);
			for(int j=0;j<colc;j++) {
				Cell cell=row.getCell(j);
				data[i-1][j]= cell.getStringCellValue();
			}
		}
		wb.close();
		fis.close();
		return data;
	}
	@Test(dataProvider="rData")
	public void regiserUser(String firstname,String lastname, String email,String password,String cpassword) {

		registrationPage.enterthefn(firstname);
		registrationPage.entertheln(lastname);
		registrationPage.enterthepw(password);
		registrationPage.enterthecpw(cpassword);
		registrationPage.clickRegister();
	}
	
	@AfterMethod
	public void shut() {
		driver.close();
		 }
	
	}


