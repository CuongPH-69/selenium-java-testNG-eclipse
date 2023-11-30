package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Handle_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	Random rand;
	String email;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

//			Code turn off notification in Firefox
//			FirefoxOptions options = new FirefoxOptions();
//			options.setProfile(new FirefoxProfile());
//			options.addPreference("dom.webnotifications.enabled",false);
//			driver = new FirefoxDriver();

//			Code turn off notification in Chrome
		Map<String, Integer> prefs = new HashMap<String, Integer>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);

		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		

	}

	public void sleepTimeSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void TC_01_Fix_in_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.cssSelector("div#modal-login-v1");
		By accountTextbox = By.cssSelector("input#account-input");
		By passwordTextbox = By.cssSelector("input#password-input");
		By loginButton = By.cssSelector("button.btn-login-v1");
		By errorMessage = By.cssSelector("div#modal-login-v1 div.error-login-panel");

//		Verify popup display
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
//		Click login button
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepTimeSecond(2);
//		Verify popup display
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
//		submit form
		driver.findElement(accountTextbox).sendKeys("automationtest@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("12345678");
		driver.findElement(loginButton).click();
		sleepTimeSecond(2);
		Assert.assertEquals(driver.findElement(errorMessage).getText(), "Tài khoản không tồn tại!");
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepTimeSecond(2);
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	};

//	@Test
	public void TC_02_Fix_in_DOM() {
		driver.get("https://skills.kynaenglish.vn/");
		By popupLogin = By.cssSelector("div#k-popup-account-login");
		Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepTimeSecond(3);
		Assert.assertTrue(driver.findElement(popupLogin).isDisplayed());
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("12345678");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepTimeSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepTimeSecond(2);
		Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());
	}

//	@Test
	public void TC_03_Not_in_DOM() {
		driver.get("https://tiki.vn/");
		By loginPopupTiki = By.cssSelector("div.ReactModal__Content");
		Assert.assertEquals(driver.findElements(loginPopupTiki).size(),0);
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleepTimeSecond(2);
		Assert.assertEquals(driver.findElements(loginPopupTiki).size(),1);
		driver.findElement(By.xpath("//button[text()='Tiếp Tục']")).click();
		sleepTimeSecond(2);		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.error-mess")).getText(), "Số điện thoại không được để trống");
		driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("0987654321");
		driver.findElement(By.xpath("//button[text()='Tiếp Tục']")).click();
		sleepTimeSecond(2);
		driver.findElement(By.xpath("//button[text()='Đăng Nhập']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span.error-mess")).getText(), "Mật khẩu không được để trống");
		driver.findElement(By.cssSelector("button.btn-close")).click();
		sleepTimeSecond(2);
		Assert.assertEquals(driver.findElements(loginPopupTiki).size(),0);
	}

//	@Test
	public void TC_04_Random_Fix_in_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepTimeSecond(30);
		By vnkPopup = By.cssSelector("div.tve-page-section-in div.tcb-flex-col:first-child>div");	
		if (driver.findElement(vnkPopup).isDisplayed()) {
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
		};
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		sleepTimeSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch Khai Giảng");
	}
	
	@Test
	public void TC_05_Random_Fix_not_in_DOM_KMPlayer() {
		driver.get("https://dehieu.vn/");
		sleepTimeSecond(10);
		By deHieuPopup = By.cssSelector("img.img-popup");	
		if (driver.findElements(deHieuPopup).size()>0 && driver.findElements(deHieuPopup).get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("autoTest");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("autoTest@gmail.com");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0987654321");
			sleepTimeSecond(2);
			driver.findElement(By.cssSelector("button#close-popup")).click();
		};
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		sleepTimeSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.form-login>h2")).getText(), "Đăng nhập");
	}
	
//	@Test
	public void TC_06_Random_Fix_not_in_DOM_DeHieu() {
		driver.get("https://www.kmplayer.com/home#layer-pc");
		sleepTimeSecond(10);
		By KMPlayerPopup = By.cssSelector("img#support-home");	
		if (driver.findElements(KMPlayerPopup).size()>0 && driver.findElements(KMPlayerPopup).get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("div.close")).click();
		};
		driver.findElement(By.cssSelector("li.faq")).click();
		sleepTimeSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.faq_main")).getText(), "FAQ");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}