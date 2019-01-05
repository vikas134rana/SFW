package keywords.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.BuildInfo;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;

public class Info {

	public static String getAllInfo() {
		String info = getBuildInfo() + getSystemInfo() + getDriverInfo();
		return info;
	}

	public static String getBuildInfo() {
		return new BuildInfo().toString() + "\n";
	}

	public static String getSystemInfo() {

		Map<String, String> hostMap = getHostnameAndHostaddress();
		String hostName = hostMap.get("name");
		String hostAddress = hostMap.get("address");

		return String.format("System info: host: '%s', ip: '%s', os.name: '%s', os.arch: '%s', os.version: '%s', java.version: '%s'",
				new Object[] { hostName, hostAddress,

						System.getProperty("os.name"), System.getProperty("os.arch"), System.getProperty("os.version"), System.getProperty("java.version") })
				+ System.lineSeparator();
	}

	/*- public static String getAdditionalInformation() {
		return "Additional Info : " ;
	}*/

	public static String getDriverInfo() {

		Capabilities caps = Driver.getDriverCapabilities();

		String browserName = caps.getBrowserName();
		Platform platform = caps.getPlatform();
		String version = caps.getVersion();
		boolean isJSEnabled = caps.is("javascriptEnabled");

		/*- CHROME CAPABILITIES
		 *  [acceptInsecureCerts, acceptSslCerts, applicationCacheEnabled, browserConnectionEnabled, browserName, chrome, 
		 *  cssSelectorsEnabled, databaseEnabled, goog:chromeOptions, handlesAlerts, hasTouchScreen, javascriptEnabled, 
		 *  locationContextEnabled, mobileEmulationEnabled, nativeEvents, networkConnectionEnabled, pageLoadStrategy, 
		 *  platform, platformName, rotatable, setWindowRect, takesHeapSnapshot, takesScreenshot, unexpectedAlertBehaviour, 
		 *  unhandledPromptBehavior, version, webStorageEnabled] */

		/*- FIREFOX CAPABILITIES
		 *  [acceptInsecureCerts, browserName, browserVersion, javascriptEnabled, moz:accessibilityChecks, moz:geckodriverVersion,
		 *  moz:headless, moz:processID, moz:profile, moz:useNonSpecCompliantPointerOrigin, moz:webdriverClick, pageLoadStrategy,
		 *  platform, platformName, platformVersion, rotatable, timeouts] */

		/*- IE CAPABILITIES 
		 *  [acceptInsecureCerts, browserName, browserVersion, javascriptEnabled, pageLoadStrategy, platform, platformName, proxy,
		 *  se:ieOptions, setWindowRect, timeouts] */

		return "Driver info: [" + "BrowserName: " + browserName + " , " + "Version: " + version + " , " + "Platform: " + platform + " , " + "JSEnabled: "
				+ isJSEnabled + "]" + System.lineSeparator();
	}

	private static Map<String, String> getHostnameAndHostaddress() {

		Map<String, String> map = new HashMap<>();

		try {
			InetAddress ipObj = InetAddress.getLocalHost();
			String hostname = ipObj.getHostName();
			String ip = ipObj.getHostAddress();
			map.put("name", hostname);
			map.put("address", ip);

		} catch (UnknownHostException e) {

		}
		return map;
	}
}
