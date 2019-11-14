package demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class NewTest {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anhvt\\Downloads\\chromedriver.exe");

		driver = new ChromeDriver();

		// mo URL
		driver.get("https://vnexpress.net/");
	}

	@Test
	public void f() {
		 System.out.println("Start test");
	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

}
