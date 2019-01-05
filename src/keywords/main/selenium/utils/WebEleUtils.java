package keywords.main.selenium.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import keywords.core.Driver;
import keywords.core.Log;
import keywords.main.WebKW;

public class WebEleUtils {

	/**
	 * 
	 * @param ele
	 * @param attrName
	 * @return
	 */
	public static String getAttr(WebElement ele, String attrName) {

		/*-	Selenium getAttribute functionality (observation) 
		 *  1. if both attrName and attrValue is present in element then attrValue is return 
		 *  2. if attrName is present in element but not attr value then "" i.e. blank string is return
		 *  3. if attribute is not present in element but attrName is valid like 'id' then blank string is return 
		 *  4. if attribute is not present in element and attrName is also not valid like 'gjasgdjasd' then null is return
		 *  5. if tag=input and attrName=type then default attrValue=text return in case attrValue is blank or attrValue not valid 
		 *  6. There are some attributes where attrValue is not require (if attrValue is present then also it is not considered) like 'disabled'
		 *     in this case boolean(true/false) is return based on attrName is present on element
		 */

		/*- Example for above statements 
		 *  OuterHTML : <input type name='firstname' readonly='false' class>
		 *  1. getAttr(name) = "firstName"
		 *  2. getAttr(class) = ""
		 *  3. getAttr("id") = ""
		 *  4. getAttr(yiuyiuyiyiy) = null
		 *  5. getAttr(type) = "text"
		 *  6. getAttr(readonly) = "true" (in booleanAttribute attrValue is not considered)
		 *  */

		String attrValue = ele.getAttribute(attrName);
		return attrValue != null ? attrValue.trim() : attrValue;
	}

	/**
	 * 
	 * @param ele
	 * @return
	 */
	public static boolean isEditable(WebElement ele) {

		if (ele == null)
			return false;

		// TODO: Handle editable in case of other tags(other than input and textarea)
		// div[@contentEditable='true']

		/*- not enabled - return false*/
		if (!ele.isEnabled())
			return false;

		/*- element is readonly - return false */
		if (ele.getAttribute("readonly").equals("true"))
			return false;

		return true;
	}

	/**
	 * 
	 * @param ele
	 * @return
	 */
	public static boolean isTypable(WebElement ele) {

		if (ele == null)
			return false;

		String tagName = ele.getTagName().toLowerCase();

		/*- none of (input and textarea) tag - return false */
		if (StringUtils.equalsAny(tagName, "input", "textarea")) {

			/*- if input but type is not typable - return false */
			if (tagName.equals("input")) {

				String type = ele.getAttribute("type");
				type = type != null ? type.toLowerCase().trim() : type;
				List<String> typableInputTypeList = new ArrayList<>(
						Arrays.asList("type", "email", "password", "url", "number", "search"));

				if (typableInputTypeList.contains(type))
					return true;
			} else
				return true;
		}

		return false;
	}

	/**
	 * 
	 * @param ele
	 * @param waitTimeInMillis
	 * @return
	 */
	public static boolean waitForElementClickable(WebElement ele, int waitTimeInMillis) {

		if (ele == null)
			return false;

		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInMillis);

		try {
			wait.until(new Function<WebDriver, WebElement>() {

				@Override
				public WebElement apply(WebDriver d) {

					return ExpectedConditions.elementToBeClickable(ele).apply(d);
				}

			});

			return true;

		} catch (TimeoutException e) {
			Log.debug("Element is not Clickable");
			return false;
		}

	}

	/**
	 * 
	 * @param ele
	 * @param waitTimeInMillis
	 * @return
	 */
	public static boolean waitForElementVisiblility(WebElement ele, int waitTimeInMillis) {

		if (ele == null)
			return false;

		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInMillis);

		try {
			wait.until(new Function<WebDriver, WebElement>() {

				@Override
				public WebElement apply(WebDriver d) {

					return ExpectedConditions.visibilityOf(ele).apply(d);
				}

			});

			return true;

		} catch (TimeoutException e) {
			Log.debug("Element is not Visible");
			return false;
		}

	}

	/**
	 * 
	 * @param locator
	 * @param waitTimeInMillis
	 * @return
	 */
	public static boolean waitForElementExistence(By locator, int waitTimeInMillis) {

		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInMillis);

		try {
			wait.until(new Function<WebDriver, WebElement>() {

				@Override
				public WebElement apply(WebDriver d) {

					return ExpectedConditions.presenceOfElementLocated(locator).apply(d);
				}

			});

			return true;

		} catch (TimeoutException e) {
			Log.debug("Element does not Exist");
			return false;
		}

	}

	/**
	 * verify element is checkbox or radiobutton
	 * 
	 * @param ele
	 * @return
	 */
	public static boolean isCheckable(WebElement ele) {

		if (ele == null)
			return false;

		if (ele.getTagName().equalsIgnoreCase("input")) {
			if (StringUtils.equalsAnyIgnoreCase(getAttr(ele, "type"), "checkbox", "radio")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * verify element (checkbox or radiobutton) is checked
	 * 
	 * @param ele
	 * @return
	 */
	public static boolean isChecked(WebElement ele) {

		if (ele == null)
			return false;

		if (isCheckable(ele)) {
			return ele.isSelected();
		}
		return false;
	}

	/**
	 * Check if given ele is Dropdown
	 * 
	 * @param ele
	 * @return
	 */
	public static boolean isDropdown(WebElement ele) {

		if (ele == null)
			return false;

		return ele.getTagName().equalsIgnoreCase("select");
	}

	/**
	 * Check if dropdown value is selected
	 * 
	 * @param ele
	 * @param value : Case Sensitive
	 * @return
	 */
	public static boolean isDropdownValueSelected(WebElement ele, String value) {

		if (ele == null)
			return false;

		if (isDropdown(ele)) {
			List<WebElement> selectedEles = new Select(ele).getAllSelectedOptions();
			List<String> selectedTexts = EleCollectionUtils.getEleAttrList(selectedEles, "value");
			return selectedTexts.contains(value);
		}
		return false;
	}

	/**
	 * Check if dropdown text is selected
	 * 
	 * @param ele
	 * @param value : Case Sensitive
	 * @return
	 */
	public static boolean isDropdownTextSelected(WebElement ele, String value) {

		if (ele == null)
			return false;

		if (isDropdown(ele)) {
			List<WebElement> selectedEles = new Select(ele).getAllSelectedOptions();
			List<String> selectedTexts = EleCollectionUtils.getEleAttrList(selectedEles, "value");
			return selectedTexts.contains(value);
		}
		return false;
	}

	/**
	 * 
	 * @param ele
	 * @param value
	 * @return
	 */
	public static boolean isElementText(WebElement ele, String expectedText) {

		if (ele == null)
			return false;

		return ele.getText().equals(expectedText);
	}

	public static void main(String[] args) {

		WebKW.openBrowser("chrome", "file:///C:/Users/vikas.rana/Desktop/Notes/HTML/DisbaleButton.html");

		WebElement ele = FinderUtils.findElement("//button");

		waitForElementClickable(ele, 3);

		ele.click();

	}

}
