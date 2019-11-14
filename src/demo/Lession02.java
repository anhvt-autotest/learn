package demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertEquals;

import java.awt.Dialog;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class Lession02 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Chay bang IE
//		System.setProperty("webdriver.ie.driver", "C:\\Users\\anhvt\\Downloads\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();

		// Chay bang Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anhvt\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();

		// Chay bang Firefox
//		System.setProperty("webdriver.gecko.driver","C:\\Users\\anhvt\\Downloads\\geckodriver.exe");
//		driver = new FirefoxDriver();

		// mo URL
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void ValidateEmptyUserPass() {
		// Click button My Account
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		// Click button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String emptyEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		assertEquals("This is a required field.", emptyEmail);
		String emptyPass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		assertEquals("This is a required field.", emptyPass);

		System.out.println("End testcase 01");
	}

	@Test
	public void ValidateFormatEmail() {
		driver.findElement(By.xpath("//*[@type='email' and @name='login[username]']")).sendKeys("4423492223.234234@3244234234234.42343242");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String errFormatEmail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		assertEquals("Please enter a valid email address. For example johndoe@domain.com.", errFormatEmail);

		System.out.println("End testcase 02");
	}

	@Test
	public void ValidateCountCharacterPass() {
		driver.findElement(By.xpath("//input[@name='login[password]' and @id='pass']")).sendKeys("12345");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String errPw = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		assertEquals("Please enter 6 or more characters without leading or trailing spaces.", errPw);

		System.out.println("End testcase 03");
	}

	@Test
	public void ValidateLoginFail() {
		driver.findElement(By.xpath("//*[@type='email' and @name='login[username]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[password]' and @id='pass']")).clear();
		driver.findElement(By.xpath("//*[@type='email' and @name='login[username]']")).sendKeys("tuan.anh1215@gmail.com");
		driver.findElement(By.xpath("//input[@name='login[password]' and @id='pass']")).sendKeys("654321");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String wrongPw = driver.findElement(By.xpath("//span[contains(string(),'Invalid login or password.')]")).getText();
		assertEquals("Invalid login or password.", wrongPw);

		System.out.println("End testcase 04");

	}

	@Test
	public void ValidateLoginSuccess() {
		driver.findElement(By.xpath("//*[@type='email' and @name='login[username]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[password]' and @id='pass']")).clear();
		driver.findElement(By.xpath("//*[@type='email' and @name='login[username]']")).sendKeys("tuan.anh1215@gmail.com");
		driver.findElement(By.xpath("//input[@name='login[password]' and @id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String fullName = driver.findElement(By.xpath("//*[@class='box-content']//p[contains(text(),'Anh Anh Anh')]")).getText();
		assertEquals("Anh Anh Anh", fullName);

		System.out.println("End testcase 05");

	}

	@Test
	public void CheckRegister() {
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//*[contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//*[contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Register']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Vu");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Tuan");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Anh");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("tuan" + randomNumber() + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
		driver.findElement(By.xpath("//button[@type='submit'and @title='Register']")).click();
		String welcomeText = driver.findElement(By.xpath("//span[contains(string(),'Thank you for registering with Main Website Store.')]")).getText();
		assertEquals("Thank you for registering with Main Website Store.", welcomeText);
		System.out.println("Login success");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();

	}

	public static int randomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(999999);
		return randomNumber;

	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

}
