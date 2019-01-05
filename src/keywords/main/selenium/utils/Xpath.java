package keywords.main.selenium.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import keywords.core.Driver;
import keywords.core.Log;
import keywords.main.Utility;

public class Xpath {

	/*- *****************************  findElement  ********************************* */

	public static WebElement findElement(String xpath) {
		WebElement element = null;
		// new Utils().waitForAjaxAndJqueryToLoad();

		try {
			element = Driver.getDriver().findElement(By.xpath(xpath));
			Log.debug("element found is : " + element);
		} catch (Exception e) {
			Log.debug("returning null");
			return null;
		}
		return element;
	}

	public static WebElement findElement(WebElement ele, String xpath) {
		WebElement element = null;
		// new Utils().waitForAjaxAndJqueryToLoad();

		try {
			element = ele.findElement(By.xpath(xpath));
			Log.debug("element found is : " + element);
		} catch (Exception e) {
			Log.debug("returning null");
			return null;
		}
		return element;
	}

	public static WebElement findElement(String xpath, int timeoutInMillis) {
		WebElement element = null;

		long startTime = System.currentTimeMillis();
		Log.debug("timeoutInMillis : " + timeoutInMillis);

		while (element == null) {

			if ((System.currentTimeMillis() - startTime) > timeoutInMillis)
				return element;

			element = findElement(xpath);

			Utility.waitUntil(250);

			Log.debug("timeElapsed : " + (System.currentTimeMillis() - startTime));
		}
		return element;
	}

	public static WebElement findElement(WebElement ele, String xpath, int timeoutInMillis) {
		WebElement element = null;

		long startTime = System.currentTimeMillis();
		Log.debug("timeoutInMillis : " + timeoutInMillis);

		while (element == null) {

			if ((System.currentTimeMillis() - startTime) > timeoutInMillis)
				return element;

			element = findElement(ele, xpath);

			Utility.waitUntil(250);

			Log.debug("timeElapsed : " + (System.currentTimeMillis() - startTime));
		}
		return element;
	}

	/*- *****************************  findElements (List) ********************************* */

	public static List<WebElement> findElements(String xpath) {
		// new Utils().waitForAjaxAndJqueryToLoad();

		try {
			List<WebElement> elements = Driver.getDriver().findElements(By.xpath(xpath));
			Log.debug("elements found are : " + elements);
			return elements;
		} catch (Exception e) {
			Log.debug("returning null");
			return null;
		}
	}

	public static List<WebElement> findElements(WebElement ele, String xpath) {
		// new Utils().waitForAjaxAndJqueryToLoad();

		try {
			List<WebElement> elements = ele.findElements(By.xpath(xpath));
			Log.debug("elements found are : " + elements);
			return elements;
		} catch (Exception e) {
			Log.debug("returning null");
			return null;
		}
	}

	public static List<WebElement> findElements(String xpath, int timeoutInMillis) {
		List<WebElement> elements = null;

		long startTime = System.currentTimeMillis();
		Log.debug("timeoutInMillis : " + timeoutInMillis);

		while (elements == null) {

			if ((System.currentTimeMillis() - startTime) > timeoutInMillis)
				return elements;

			elements = findElements(xpath);

			Utility.waitUntil(250);

			Log.debug("timeElapsed : " + (System.currentTimeMillis() - startTime));
		}
		return elements;
	}

	public static List<WebElement> findElements(WebElement ele, String xpath, int timeoutInMillis) {
		List<WebElement> elements = null;

		long startTime = System.currentTimeMillis();
		Log.debug("timeoutInMillis : " + timeoutInMillis);

		while (elements == null) {

			if ((System.currentTimeMillis() - startTime) > timeoutInMillis)
				return elements;

			elements = findElements(ele, xpath);

			Utility.waitUntil(250);

			Log.debug("timeElapsed : " + (System.currentTimeMillis() - startTime));
		}
		return elements;
	}

}
