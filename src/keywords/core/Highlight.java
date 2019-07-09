package keywords.core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import keywords.main.js.JSE;

public class Highlight {

	public static List<WebElement> highlightEles = new ArrayList<WebElement>();

	public static boolean addHighlightEle(WebElement ele) {

		if (ele == null)
			return false;

		return highlightEles.add(ele);
	}

	public static boolean highlightAll() {

		for (WebElement ele : highlightEles) {
			highlightEle(ele);
		}

		return true;
	}

	public static boolean dehighlightAll() {

		for (WebElement ele : highlightEles) {

			try {
				dehighlightEle(ele);
			} catch (StaleElementReferenceException e) {
				System.out.println("\tCant Defocus - Element is Stale");
			}
		}

		return true;
	}

	public static void highlightEle(WebElement ele) {
		System.out.println("============= Highlighting  ====================");
		String eleStyle = ele.getAttribute("style");
		String style = "'" + eleStyle + " " + highlightStyle() + "'";
		JSE.executeScript("arguments[0].setAttribute('style'," + style + " );", ele);
	}

	private static String highlightStyle() {
		return "border: 2px solid red;";
	}

	public static void dehighlightEle(WebElement ele) {
		System.out.println("============= Dehighlighting  ====================");
		String style = ele.getAttribute("style");
		style = "'" + style.replace(highlightStyle(), "") + "'";
		JSE.executeScript("arguments[0].setAttribute('style'," + style + " );", ele);
	}

}
