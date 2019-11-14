package smokies_client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
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
		System.out.println(title);
		Assert.assertTrue(title.contains("Blog"));
	}

	@BeforeTest
	public void beforeTest() {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setHeadless(true);
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		options.setBinary("/usr/bin/google-chrome-stable");
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("window-size=1024,768"); // Bypass OS security model
		options.addArguments("--log-level=3"); // set log level
		options.addArguments("--silent");//
		options.setCapability("chrome.verbose", false); //disable logging
		if ((OS.indexOf("win") >= 0))
			System.setProperty("webdriver.chrome.driver", "chromedriver78.exe");
		else
			System.setProperty("webdriver.chrome.driver", "chromedriver-linux");
		driver = new ChromeDriver(options);

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
