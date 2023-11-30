package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_button {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By loginButton;
//	Khởi tạo 1 biến có kiểu dữ liệu là JavascriptExecutor và import thư viện
	JavascriptExecutor jsExecutor;
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

//		driver = new FirefoxDriver();
		driver = new ChromeDriver();
//		Khai báo sau khi khởi tạo biến driver nếu không sẽ báo lỗi
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//update
		 
	}
	public void sleepTimeSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	
//	@Test
	public void TC_01_Check_button_disable() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		loginButton = By.cssSelector("button.fhs-btn-login");
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		Assert.assertTrue(driver.findElement(loginButton).getCssValue("background").contains("rgb(224, 224, 224)"));
		driver.findElement(By.id("login_username")).sendKeys("0368452369");
		driver.findElement(By.id("login_password")).sendKeys("Test@1234");
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		String buttonColour = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println(buttonColour);
		Color loginButtonColour = Color.fromString(buttonColour);
		Assert.assertEquals(loginButtonColour.asHex().toUpperCase(),"#C92127");
	}

//	@Test
	public void TC_02() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By dualCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input");
		driver.findElement(dualCheckbox).click();
		Assert.assertTrue(driver.findElement(dualCheckbox).isSelected());
		sleepTimeSecond(2);
		driver.findElement(dualCheckbox).click();
		Assert.assertFalse(driver.findElement(dualCheckbox).isSelected());
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By carEngineRadioButton = By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input");
		driver.findElement(carEngineRadioButton).click();
		Assert.assertTrue(driver.findElement(carEngineRadioButton).isSelected());
		sleepTimeSecond(2);
	}

//	@Test
	public void TC_03() {
		driver.get("https://material.angular.io/components/radio/examples");
		By favoriteSeasonRadioButton = By.xpath("//label[normalize-space()='Summer']//preceding-sibling::div/input");
		driver.findElement(favoriteSeasonRadioButton).click();
		Assert.assertTrue(driver.findElement(favoriteSeasonRadioButton).isSelected());
		sleepTimeSecond(2);
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckbox = By.xpath("//label[text()='Checked']//preceding-sibling::div/input");
		By IndeterminateCheckbox = By.xpath("//label[text()='Indeterminate']//preceding-sibling::div/input");
		driver.findElement(checkedCheckbox).click();
		driver.findElement(IndeterminateCheckbox).click();
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(IndeterminateCheckbox).isSelected());
		sleepTimeSecond(2);
		
		driver.findElement(checkedCheckbox).click();
		driver.findElement(IndeterminateCheckbox).click();
		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(IndeterminateCheckbox).isSelected());		
	}
	
//	@Test
	public void TC_04() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> allCheckbox = driver.findElements(By.cssSelector("input[type='checkbox']"));
		for (WebElement webElement : allCheckbox) {
			if (webElement.getAttribute("value").equals("Gallstones")) {
				webElement.click();
			}
		}
//		for (Int i = 0; i < allCheckbox.size(); i++) {
//			driver.findElements(By.cssSelector("input[type='checkbox']"))[i].click();
//		}
//		for (WebElement webElement : allCheckbox) {
//			Assert.assertTrue(webElement.isSelected());
//			System.out.println("Checkbox is checked");
//		}
		sleepTimeSecond(2);
	}

//	@Test
	public void TC_05() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		By buttonRadio = By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div/input");
//		Câu lệnh dùng để sử dụng javascript thực hiện click lên 1 element bị che 
//		(arguments[0].click(),driver.findElement(buttonRadio)) dùng để chỉ index đầu tiên buttonRadio và thực hiện hành động click
//		Ngoài ra nếu có thêm nhiều element thì tăng số lượng index cũng như có thể thay đổi hành động đối với index khác nhau
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(buttonRadio));
		sleepTimeSecond(2);
	}
	
	@Test
	public void TC_06() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By cityRadioButton = By.xpath("//div[@class='d7L4fc bJNwt  FXLARc aomaEc ECvBRb']");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(cityRadioButton));
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='true']")).isDisplayed());
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}