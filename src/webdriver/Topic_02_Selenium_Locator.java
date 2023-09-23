package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
//		Mở trang Register
		driver.get("https://demo.nopcommerce.com/register");

	}
//	Code HTML của field "First name":
//	<input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
//	Tên thẻ (TagName):input
//	Tên thuộc tính (Attribute Name): type data-val data-val-required id name
//	Giá trị của thuộc tính (Attribute Value): text true First name is required. FirstName FirstName

	@Test
	public void TC_01_ID() {
//		Các bước thực hiện:
//		B1: Tìm element cần thao tác thông qua driver.findElement
//		B2: Tìm thuộc tính cần thao tác: find Id/Class/Name/Tagname/XPath/...
//		B3: Thực hiện hành động đối với element đó: Clicks/sendKeys/...
		driver.findElement(By.id("FirstName")).sendKeys("CuongPH");
	}

	@Test
	public void TC_02_Class() {
//		Truy cập trang search
		driver.get("https://demo.nopcommerce.com/search");
		
//		Tìm element theo className là "search-text"
//		Thực hiện input dữ liệu "iphone"
		driver.findElement(By.className("search-text")).sendKeys("iphone");
	}

	@Test
	public void TC_03_Name() {
//		Thực hiện click vào "Advance Search" checbox
		driver.findElement(By.name("advs")).click();
	}

	@Test
	public void TC_04_TagName() {
//		Tìm bao nhiêu thẻ có tagName là "input" trong trang search
		driver.findElements(By.tagName("input")).size();
	}

	@Test
	public void TC_05_LinkText() {
//		Click vào đường link có tên là "apple"
//		LinkText bắt buộc nhập đường link "Tuyệt đối"
		driver.findElement(By.linkText("Apple")).click();
	}

	@Test
	public void TC_06_PartialLinkText() {
//		Click vào đường link có tên là "apple"
//		PartialLinkText chỉ cần nhập đường link "Tương đối"
		driver.findElement(By.partialLinkText("Gift")).click();
	}

	@Test
	
	public void TC_07_Css() {
//		Mở lại trang register
		driver.get("https://demo.nopcommerce.com/register");
		
//		Cách 1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Cuong");
		
//		Cách 2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Pham");
		
//		Cách 3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("cuongph@gmail.com");
	}

	@Test
	public void TC_08_XPath() {
//		Cách 1
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Cuong");
		
//		Cách 2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Pham");
		
//		Cách 3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("cuongph@gmail.com");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}