package keywords.main.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import keywords.core.Driver;
import keywords.core.Info;

public class Browser {

	static Browser obj;

	public static Browser getInstance() {
		if (obj == null)
			obj = new Browser();

		return obj;
	}

	public enum BrowserType {
		CHROME, FIREFOX, IE;
	}

	public void openBrowser(String browserName, String url) {

		browserName = browserName.trim();

		BrowserType browserType = getBrowserType(browserName);

		WebDriver driver = getDriver(browserType);

		Driver.setDriver(driver);

		System.out.println(Info.getAllInfo());

		driver.manage().window().maximize();

		driver.navigate().to(url);

		Window.updateCurrentWindowInOpenedMap();
	}

	private BrowserType getBrowserType(String browserName) {
		BrowserType browserType = null;

		if (browserName.equalsIgnoreCase("chrome"))
			browserType = BrowserType.CHROME;

		else if (browserName.equalsIgnoreCase("firefox"))
			browserType = BrowserType.FIREFOX;

		else if (browserName.equalsIgnoreCase("ie") || browserName.equalsIgnoreCase("internet explorer"))
			browserType = BrowserType.IE;

		else
			throw new IllegalArgumentException("Browser name not valid");

		return browserType;
	}

	private WebDriver getDriver(BrowserType browserType) {

		WebDriver driver = null;

		switch (browserType) {

		case CHROME:
			driver = getChromeDriver();
			break;

		case FIREFOX:
			driver = getFirefoxDriver();
			break;

		case IE:
			driver = getIEDriver();
			break;
		}

		return driver;
	}

	/*- gets Driver ================================================================================== */
	private WebDriver getChromeDriver() {

		System.setProperty("webdriver.chrome.driver", getChromeDriverPath());

		ChromeOptions options = getChromeOptions();

		WebDriver driver = new ChromeDriver(options);
		return driver;
	}

	private WebDriver getFirefoxDriver() {

		System.setProperty("webdriver.gecko.driver", getFirefoxDriverPath());

		FirefoxOptions options = getFirefoxOptions();

		WebDriver driver = new FirefoxDriver(options);
		return driver;
	}

	private WebDriver getIEDriver() {

		System.setProperty("webdriver.ie.driver", getIEDriverPath());

		InternetExplorerOptions options = getIEOptions();

		WebDriver driver = new InternetExplorerDriver(options);
		return driver;
	}

	/*- gets Driver Path ============================================================================= */
	private String getChromeDriverPath() {
		return "D:\\Soft\\automation\\drivers\\chrome\\2.45\\chromedriver.exe";
	}

	private String getFirefoxDriverPath() {
		return "D:\\Soft\\Selenium\\Drivers\\Firefox\\.23.0\\geckodriver_64.exe";
	}

	private String getIEDriverPath() {
		return "D:\\Soft\\Selenium\\Drivers\\IE\\3.14\\IEDriverServer_32.exe";
	}

	/*- gets Driver Options ========================================================================== */
	private ChromeOptions getChromeOptions() {
		DesiredCapabilities capabilities = getChromeCapabilities();
		ChromeOptions options = new ChromeOptions();

		// TODO : Add Chrome Options

		options.merge(capabilities);
		return options;
	}

	private FirefoxOptions getFirefoxOptions() {
		DesiredCapabilities capabilities = getFirefoxCapabilities();
		FirefoxOptions options = new FirefoxOptions();

		// TODO : Add Firefox Options

		options.merge(capabilities);
		return options;
	}

	private InternetExplorerOptions getIEOptions() {
		DesiredCapabilities capabilities = getIECapabilities();
		InternetExplorerOptions options = new InternetExplorerOptions();

		// TODO : Add IE Options

		options.merge(capabilities);
		return options;
	}

	/*- gets Driver Capabilities ===================================================================== */
	private DesiredCapabilities getChromeCapabilities() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		// TODO : Add Chrome Capability

		return capabilities;
	}

	private DesiredCapabilities getFirefoxCapabilities() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();

		// TODO : Add Firefox Capabilities

		return capabilities;
	}

	private DesiredCapabilities getIECapabilities() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

		// TODO : Add IE Capabilities

		return capabilities;
	}

}
