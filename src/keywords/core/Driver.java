package keywords.core;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {

	private static WebDriver driver;

	private Driver() {
		// Singleton
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Driver.driver = driver;
	}

	/*- public static WebDriver startChrome(String url) {
		System.setProperty("webdriver.chrome.driver", "D:\\Soft\\workspace\\plugin\\lib\\drivers\\chrome\\2.44\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}*/

	public static Capabilities getDriverCapabilities() {
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		return caps;
	}

	public static void quit() {
		driver.quit();
	}

}
