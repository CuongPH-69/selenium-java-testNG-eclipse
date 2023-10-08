package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_home_work_web_element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextBox = By.id("mail");
	By passwordTextBoxDisable = By.id("disable_password");
	By under18Radio = By.xpath("//label[@for='under_18']");
	By radioButtonDisable = By.xpath("//label[@for='radio-disabled']");
	By biographyTextArea = By.id("bio");
	By jobRole1Dropdown = By.id("job1");
	By jobRole2Dropdown = By.id("job2");
	By jobRole3DropdownDisable = By.id("job3");
	By interestCheckbox = By.xpath("//label[@for='development']");
	By interestcheckboxDisable = By.xpath("//label[@for='check-disbaled']");
	By slide1 = By.id("slider-1");
	By slide2Disable = By.id("slider-2");
	By educationTextArea = By.id("edu");
	By nameUser = By.xpath("//h5[text()='Name: User5']");
	By passwordTextBox = By.id("new_password");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
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
	public void TC_01_Check_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(emailTextBox).isDisplayed()) {
			driver.findElement(emailTextBox).sendKeys("Cuong@gmail.com");
			System.out.println("Email field is enable");
		} else {
			System.out.println("Email field is Disable");
		}
		if (driver.findElement(under18Radio).isDisplayed()) {
			driver.findElement(under18Radio).click();
			System.out.println("Under 18 radio button is Enable");
		} else {
			System.out.println("Under 18 radio button is Disable");
		}
		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Automation");
			System.out.println("Education text area is Enable");
		} else {
			System.out.println("Education text area is Disable");
		}
		if (driver.findElement(nameUser).isDisplayed()) {
			System.out.println("Name is Enable");
		} else {
			System.out.println("Name is Disable");
		}
	}

//	@Test
	public void TC_02_Check_Enable_Disable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(emailTextBox).isEnabled()) {
			System.out.println("Email is enable");
		} else {
			System.out.println("Email is disable");
		}
		if (driver.findElement(passwordTextBoxDisable).isEnabled()) {
			System.out.println("Password is enable");
		} else {
			System.out.println("Password is disable");
		}
		if (driver.findElement(under18Radio).isEnabled()) {
			System.out.println("Radio button under 18 is enable");
		} else {
			System.out.println("Radio button under 18 is disable");
		}
		if (driver.findElement(radioButtonDisable).isEnabled()) {
			System.out.println("Radio button is enable");
		} else {
			System.out.println("Radio button is disable");
		}
		if (driver.findElement(educationTextArea).isEnabled()) {
			System.out.println("Education is enable");
		} else {
			System.out.println("Education is disable");
		}
		if (driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("Biography is enable");
		} else {
			System.out.println("Biography is disable");
		}
		if (driver.findElement(jobRole1Dropdown).isEnabled()) {
			System.out.println("Job Role 1 is enable");
		} else {
			System.out.println("Job Role 1 is disable");
		}
		if (driver.findElement(jobRole2Dropdown).isEnabled()) {
			System.out.println("Job Role 2 is enable");
		} else {
			System.out.println("Job Role 2 is disable");
		}
		if (driver.findElement(jobRole3DropdownDisable).isEnabled()) {
			System.out.println("Job Role 3 is enable");
		} else {
			System.out.println("Job Role 3 is disable");
		}
		if (driver.findElement(interestCheckbox).isEnabled()) {
			System.out.println("Development checkbox is enable");
		} else {
			System.out.println("Development checkbox is disable");
		}
		if (driver.findElement(interestcheckboxDisable).isEnabled()) {
			System.out.println("Checkbox is enable");
		} else {
			System.out.println("Checkbox is disable");
		}
		if (driver.findElement(slide1).isEnabled()) {
			System.out.println("Slide 1 is enable");
		} else {
			System.out.println("Slide 1 is disable");
		}
		if (driver.findElement(slide2Disable).isEnabled()) {
			System.out.println("Slide 2 is enable");
		} else {
			System.out.println("Slide 2 is disable");
		}
	}

//	@Test
	public void TC_03_Check_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepTimeSecond(1);
		Assert.assertFalse(driver.findElement(under18Radio).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[@for='java']")).isSelected());
		sleepTimeSecond(1);
		driver.findElement(under18Radio).click();
		driver.findElement(By.xpath("//label[@for='java']")).click();
		sleepTimeSecond(1);
		Assert.assertFalse(driver.findElement(under18Radio).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[@for='java']")).isSelected());
		driver.findElement(By.xpath("//label[@for='java']")).click();
		sleepTimeSecond(1);
		Assert.assertFalse(driver.findElement(By.xpath("//label[@for='java']")).isSelected());
	}
	
	@Test
	public void TC_04_Check_Login() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("hungcuong.dn6991@gmail.com");
		driver.findElement(By.id("new_password")).sendKeys("123456");
		sleepTimeSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("cuong");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("CUONG");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("!@#$%");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("Cuong1234");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}