package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Select {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select; // Khởi tạo biến có kiểu select và import thư viện select

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
//		Không new select ở dây vì khi new sẽ không tìm kiếm được element
//		Step này chỉ mới mở trình duyệt, chưa truy cập vào trang có chứa element cần thao tác
//		Chỉ new trong testcase chỗ trang chứa element cần thao tác
//		select = new Select(driver.findElement(By.id("firstName")));
		

	}
	public void sleepTimeSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TC_01() {
		driver.get("https://test.map4d.vn");
		select = new Select(driver.findElement(By.id("firstName")));
		
//		Dùng để bỏ chọn tất cả
		select.deselectAll();
		
//		Dùng để bỏ chọn theo: index/ value/ text
		select.deselectByIndex(0);
		select.deselectByValue("");
		select.deselectByVisibleText("");
		
//		Dùng để chọn option theo: index/ value/ text
		select.selectByIndex(0);
		select.selectByValue("");
		select.selectByVisibleText("");
		
//		Dùng để check các option đã chọn
//		Trả về kiểu dữ liệu dang list webElement
		select.getAllSelectedOptions();
		
//		Dùng để verify 1 option đã được chọn hay chưa
//		Option đã chọn sẽ hiển thị lên đầu danh sách
//		Trả về kiểu dữ liệu webElement
		select.getFirstSelectedOption();
		
//		Check xem dropdown list có bao nhiêu option
//		Trả về kiểu dữ liệu dạng list webElement
		select.getOptions();
		
//		Dùng để check xem dropdown có cho phép chọn nhiều hay không
//		Trả về kiểu dữ liệu boolean
		select.isMultiple();
	}

	@Test
	public void TC_02() {
		
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}