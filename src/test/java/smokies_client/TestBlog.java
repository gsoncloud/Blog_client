package smokies_client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBlog {
	private WebDriver driver;
	private static String OS = System.getProperty("os.name").toLowerCase();

	@Test
	public void testTitle() {
		driver.get("http://35.226.111.9/");
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Post Blog"));
	}

	@BeforeTest
	public void beforeTest() {
		if ((OS.indexOf("win") >= 0))
			System.setProperty("webdriver.chrome.driver", "chromedriver78.exe");
		else
			System.setProperty("webdriver.chrome.driver", "chromedriver-linux");
		driver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}