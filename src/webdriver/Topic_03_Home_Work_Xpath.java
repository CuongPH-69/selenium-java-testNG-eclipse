package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Home_Work_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//update

	}

	@Test
	public void TC_01_Register_With_Empty_Data() {
//		Truy cập trang web
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
//		Click vào "Đăng ký" button
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
//		Verify error msg của từng field
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtFirstname']")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Nhập lại Email']/following-sibling::label")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='txtPhone']/following-sibling::label")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Register_With_Invalid_Email() {
//		Truy cập trang web
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
//		Input data cho từng field
		driver.findElement(By.xpath("//label[text()='Họ và tên']/following-sibling::input")).sendKeys("Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("abc.xyz");
		driver.findElement(By.name("txtCEmail")).sendKeys("abc.xyz");
		driver.findElement(By.id("txtPassword")).sendKeys("Cuong@123");
		driver.findElement(By.id("txtCPassword")).sendKeys("Cuong@123");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("0985123456");
//		Click vào "Đăng ký" button
		driver.findElement(By.xpath("//div[@class='form frmRegister']/div[@class='field_btn']/button")).click();
//		Verify error msg tại "Email" field
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");		
	}

	@Test
	public void TC_03_Register_With_Incorrect_ConfirmEmail() {
//		Truy cập trang web
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
//		Input data cho từng field
		driver.findElement(By.xpath("//label[text()='Họ và tên']/following-sibling::input")).sendKeys("Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("abc@xyz");
		driver.findElement(By.name("txtCEmail")).sendKeys("abc.xyz@123");
		driver.findElement(By.id("txtPassword")).sendKeys("Cuong@123");
		driver.findElement(By.id("txtCPassword")).sendKeys("Cuong@123");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("0985123456");
//		Click vào "Đăng ký" button
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
//		Verify error msg tại "Email" field
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");	
	}
	
	@Test
	public void TC_04_Register_With_Password_Less_Than_6_Character() {
//		Truy cập trang web
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
//		Input data cho từng field
		driver.findElement(By.xpath("//label[text()='Họ và tên']/following-sibling::input")).sendKeys("Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("abc@xyz");
		driver.findElement(By.name("txtCEmail")).sendKeys("abc@xyz");
		driver.findElement(By.id("txtPassword")).sendKeys("Cuong");
		driver.findElement(By.id("txtCPassword")).sendKeys("Cuong");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("0985123456");
//		Click vào "Đăng ký" button
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
//		Verify error msg tại "Email" field
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");	
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Nhập lại mật khẩu phải có ít nhất 6 ký tự");	
	}
	
	@Test
	public void TC_05_Register_With_Incorrect_ConfirmPassword() {
//		Truy cập trang web
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
//		Input data cho từng field
		driver.findElement(By.xpath("//label[text()='Họ và tên']/following-sibling::input")).sendKeys("Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("abc@xyz");
		driver.findElement(By.name("txtCEmail")).sendKeys("abc@xyz");
		driver.findElement(By.id("txtPassword")).sendKeys("Cuong@123");
		driver.findElement(By.id("txtCPassword")).sendKeys("Cuong@321");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("0985123456");
//		Click vào "Đăng ký" button
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
//		Verify error msg tại "Email" field
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");	
	}
	
	@Test
	public void TC_06_Register_With_Invalid_PhoneNumber() {
//		Truy cập trang web
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
//		Input data cho từng field
		driver.findElement(By.xpath("//label[text()='Họ và tên']/following-sibling::input")).sendKeys("Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("abc@xyz");
		driver.findElement(By.name("txtCEmail")).sendKeys("abc@xyz");
		driver.findElement(By.id("txtPassword")).sendKeys("Cuong@123");
		driver.findElement(By.id("txtCPassword")).sendKeys("Cuong@123");
//		Input invalid Phone Number (9 number)
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("098512345");
//		Click vào "Đăng ký" button
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
//		Verify error msg tại "Email" field
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");	
//		Input invalid Phone Number (12 number)
		driver.findElement(By.xpath("//input[@name='txtPhone']")).clear();
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("098512345234");
//		Verify error msg tại "Email" field
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");	
//		Input invalid Phone Number (Không đúng đầu số)
		driver.findElement(By.xpath("//input[@name='txtPhone']")).clear();
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("1285123452");
//		Verify error msg tại "Email" field
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}