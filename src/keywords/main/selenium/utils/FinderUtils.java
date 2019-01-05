package keywords.main.selenium.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import keywords.core.Driver;
import keywords.core.Log;
import keywords.main.Utility;

public class FinderUtils {

	private static WebElement ele = null;
	private static List<WebElement> eles = new ArrayList<>();

	private static final int POLLING_TIME_MS = 250;

	private static final String ELE_MSG = "Found ELE : ";
	private static final String ELES_MSG = "Found ELES : ";

	/*- *****************************  findElement  ********************************* */

	/**
	 * 
	 * @param xpath
	 * @return
	 */
	public static WebElement findElement(String xpath) {

		ele = null;
		try {
			long start = System.currentTimeMillis();
			ele = Driver.getDriver().findElement(By.xpath(xpath));
			System.out.println("Time Using Driver <" + (System.currentTimeMillis() - start) + ">");
		} catch (Exception e) {
		}
		Log.debug(ELE_MSG + ele);
		return ele;
	}

	/**
	 * 
	 * @param ele
	 * @param xpath
	 * @return
	 */
	public static WebElement findElement(WebElement baseEle, String xpath) {

		ele = null;
		try {
			long start = System.currentTimeMillis();
			ele = baseEle.findElement(By.xpath(xpath));
			System.out.println("Time Using BaseEle <" + (System.currentTimeMillis() - start) + ">");
		} catch (Exception e) {
		}
		Log.debug(ELE_MSG + ele);
		return ele;
	}

	/**
	 * 
	 * @param xpath
	 * @param timeoutInMillis
	 * @return
	 */
	public static WebElement findElement(String xpath, int timeoutInMillis) {

		ele = null;

		long startTime = System.currentTimeMillis();
		Log.debug("timeoutInMillis : " + timeoutInMillis);

		while (ele == null) {

			if ((System.currentTimeMillis() - startTime) > timeoutInMillis)
				return ele;

			ele = findElement(xpath);

			Utility.waitUntil(POLLING_TIME_MS);
			Log.debug("timeElapsed : " + (System.currentTimeMillis() - startTime));
		}
		return ele;
	}

	/**
	 * 
	 * @param ele
	 * @param xpath
	 * @param timeoutInMillis
	 * @return
	 */
	public static WebElement findElement(WebElement baseEle, String xpath, int timeoutInMillis) {

		ele = null;

		long startTime = System.currentTimeMillis();
		Log.debug("timeoutInMillis : " + timeoutInMillis);

		while (ele == null) {

			if ((System.currentTimeMillis() - startTime) > timeoutInMillis)
				return ele;

			ele = findElement(baseEle, xpath);

			Utility.waitUntil(POLLING_TIME_MS);
			Log.debug("timeElapsed : " + (System.currentTimeMillis() - startTime));
		}
		return ele;
	}

	/*- *****************************  findElements (List) ********************************* */

	/**
	 * 
	 * @param xpath
	 * @return
	 */
	public static List<WebElement> findElements(String xpath) {

		eles = Driver.getDriver().findElements(By.xpath(xpath));
		Log.debug(ELES_MSG + eles);
		return eles;
	}

	/**
	 * 
	 * @param ele
	 * @param xpath
	 * @return
	 */
	public static List<WebElement> findElements(WebElement baseEle, String xpath) {

		try {
			eles = baseEle.findElements(By.xpath(xpath));
		} catch (Exception e) {
		}
		Log.debug(ELES_MSG + eles);
		return eles;
	}

	/**
	 * 
	 * @param xpath
	 * @param timeoutInMillis
	 * @return
	 */
	public static List<WebElement> findElements(String xpath, int timeoutInMillis) {

		eles.clear();

		long startTime = System.currentTimeMillis();
		Log.debug("timeoutInMillis : " + timeoutInMillis);

		while (eles.isEmpty()) {

			if ((System.currentTimeMillis() - startTime) > timeoutInMillis)
				return eles;

			eles = findElements(xpath);

			Utility.waitUntil(POLLING_TIME_MS);
			Log.debug("timeElapsed : " + (System.currentTimeMillis() - startTime));
		}
		return eles;
	}

	/**
	 * 
	 * @param ele
	 * @param xpath
	 * @param timeoutInMillis
	 * @return
	 */
	public static List<WebElement> findElements(WebElement baseEle, String xpath, int timeoutInMillis) {

		eles.clear();

		long startTime = System.currentTimeMillis();
		Log.debug("timeoutInMillis : " + timeoutInMillis);

		while (eles.isEmpty()) {

			if ((System.currentTimeMillis() - startTime) > timeoutInMillis)
				return eles;

			eles = findElements(baseEle, xpath);

			Utility.waitUntil(POLLING_TIME_MS);
			Log.debug("timeElapsed : " + (System.currentTimeMillis() - startTime));
		}
		return eles;
	}

}
