package keywords.main.selenium.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import keywords.core.Driver;

public class VerificationUtils {

	public static String verify(String actual, String expected) {
		return "Actual : <" + actual + ">" + " and Expected : <" + expected + ">";
	}

	/**
	 * 
	 * @param ele
	 */
	public static void eleEditable(WebElement ele) {

		if (ele == null)
			throw new Error("Element is null");

		// TODO: Handle editable in case of other tags(other than input and textarea)
		// div[@contentEditable='true']

		/*- not enabled - return false*/
		if (!ele.isEnabled())
			throw new Error("Element not Enabled");

		String tagName = ele.getTagName().toLowerCase();

		/*- none of (input and textarea) tag - return false */
		if (!StringUtils.equalsAny(tagName, "input", "textarea"))
			throw new Error("Element is neither input nor textarea");

		/*- if input but type is not typable - return false */
		if (tagName.equals("input")) {

			String type = ele.getAttribute("type");
			type = type != null ? type.toLowerCase().trim() : type;
			List<String> editableInputTypeList = new ArrayList<>(Arrays.asList("type", "email", "password", "url", "number", "search"));

			if (editableInputTypeList.contains(type))
				throw new Error("Element is not Typable");
		}

		/*- element is readonly - return false */
		if (ele.getAttribute("readonly").equals("true"))
			throw new Error("Element is read-only");

	}

	/**
	 * 
	 * @param ele
	 */
	public static void eleChecked(WebElement ele) {

		if (ele == null)
			throw new Error("Element is null");

		if (WebEleUtils.isCheckable(ele)) {
			if (!ele.isSelected())
				throw new Error("Element is not checked");
		} else {
			throw new Error("Element is not radio or checkbox");
		}

	}

	/**
	 * 
	 * @param ele
	 * @param expectedText
	 */
	public static void eleText(WebElement ele, String expectedText) {

		if (ele == null)
			throw new Error("Element is null");

		String eleText = ele.getText();

		if (!eleText.equals(expectedText))
			throw new Error("Element Text not match. " + verify(eleText, expectedText));

	}

	/**
	 * 
	 * @param expectedTitle
	 */
	public static void windowTitle(String expectedTitle) {

		String windowTitle = Driver.getDriver().getTitle();

		if (!windowTitle.equals(expectedTitle))
			throw new Error("Window Title not match. " + verify(windowTitle, expectedTitle));

	}

	/**
	 * 
	 * @param ele
	 */
	public static void eleVisible(WebElement ele) {

		if (ele == null)
			throw new Error("Element is null");

		if (!ele.isDisplayed())
			throw new Error("Element is not Visible");

	}

	/**
	 * 
	 * @param ele
	 */
	public static void eleEnabled(WebElement ele) {

		if (ele == null)
			throw new Error("Element is null");

		if (!ele.isEnabled())
			throw new Error("Element is not Enabled");

	}

	/**
	 * 
	 * @param locator
	 */
	public static void eleExists(By locator) {

		if (locator == null)
			throw new Error("Locator is null");

		try {
			Driver.getDriver().findElement(locator);
		} catch (Exception e) {
			throw new Error("Element does not Exists");
		}

	}

	/**
	 * 
	 * @param ele
	 */
	public static void eleClickable(WebElement ele) {

		if (ele == null)
			throw new Error("Element is null");

		ele = ExpectedConditions.elementToBeClickable(ele).apply(Driver.getDriver());

		if (ele == null)
			throw new Error("Element is not Clickable");
	}

	/**
	 * 
	 * @param ele
	 */
	public static void eleTypable(WebElement ele) {

		if (ele == null)
			throw new Error("Element is null");

		String tagName = ele.getTagName();

		if (StringUtils.containsAny(tagName, "input", "textarea")) {

			if (ele.getTagName().equals("input")) {

				String type = ele.getAttribute("type");
				type = type != null ? type.toLowerCase().trim() : type;
				List<String> editableInputTypeList = new ArrayList<>(Arrays.asList("type", "email", "password", "url", "number", "search"));

				if (editableInputTypeList.contains(type))
					return;
			}
		} else
			throw new Error("Element is not Typable");
	}

	/**
	 * 
	 * @param ele
	 * @param expectedText
	 */
	public static void editboxText(WebElement ele, String expectedText) {

		if (ele == null)
			throw new Error("Element is null");

		try {
			eleTypable(ele);
		} catch (Exception e) {
			throw new Error("Element is not EditBox");
		}

		String editboxText = ele.getAttribute("value");

		if (!editboxText.equals(expectedText))
			throw new Error("EditBox Text not match. " + verify(editboxText, expectedText));
	}

	/**
	 * 
	 * @param ele
	 * @param attrName
	 * @param expectedAttrValue
	 */
	public static void eleAttrValue(WebElement ele, String attrName, String expectedAttrValue) {

		if (ele == null)
			throw new Error("Element is null");

		String attrValue = ele.getAttribute("attrName");

		if (!attrValue.equals(expectedAttrValue))
			throw new Error("Element Attribute Value not match. " + verify(attrValue, expectedAttrValue));
	}

	/**
	 * 
	 * @param ele
	 * @param expectedAttrValue
	 */
	public static void eleTooltip(WebElement ele, String expectedAttrValue) {

		if (ele == null)
			throw new Error("Element is null");

		String attrValue = ele.getAttribute("title");

		if (!attrValue.equals(expectedAttrValue))
			throw new Error("Element Attribute Value not match. " + verify(attrValue, expectedAttrValue));
	}

}
