package keywords.main.selenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import keywords.main.Utility;
import keywords.main.selenium.utils.EleCollectionUtils;

public class Dropdown {

	/*- =====================================  DROPWDON  ============================================= */

	/**
	 * 
	 * @param ele
	 * @param text
	 */
	public static void selectByText(WebElement ele, String text) {

		Select dropdown = new Select(ele);
		dropdown.selectByVisibleText(text);
	}

	/**
	 * 
	 * @param ele
	 * @param value
	 */
	public static void selectByValue(WebElement ele, String value) {

		Select dropdown = new Select(ele);
		dropdown.selectByValue(value);
	}

	/**
	 * 
	 * @param ele
	 * @param index
	 */
	public static void selectByIndex(WebElement ele, int index) {

		Select dropdown = new Select(ele);
		dropdown.selectByIndex(index);
	}

	/**
	 * 
	 * @param ele
	 * @param valueOrText
	 */
	public static void selectByValueOrText(WebElement ele, String valueOrText) {

		try {
			selectByText(ele, valueOrText);
		} catch (NoSuchElementException e) { // if dropdown doesn't contain given text then search by value
			selectByValue(ele, valueOrText);
		}
	}

	/**
	 * 
	 * @param ele
	 * @return
	 */
	public static String getOptionsText(WebElement ele, String delimiter) {

		Select dropdown = new Select(ele);
		List<String> dropdownOptionsTextList = EleCollectionUtils.getEleTextList(dropdown.getOptions());
		return String.join(Utility.getDelimiter(delimiter), dropdownOptionsTextList);
	}

	/**
	 * 
	 * @param ele
	 * @return
	 */
	public static String getOptionsValue(WebElement ele, String delimiter) {

		Select dropdown = new Select(ele);
		List<String> dropdownOptionsTextList = EleCollectionUtils.getEleAttrList(dropdown.getOptions(), "value");
		return String.join(Utility.getDelimiter(delimiter), dropdownOptionsTextList);
	}

	/*- =====================================  MULTIPLE_DROPWDON ============================================= */

	/**
	 * 
	 * @param ele
	 */
	public static void deselectAll(WebElement ele) {

		Select dropdown = new Select(ele);
		dropdown.deselectAll();
	}

	/**
	 * 
	 * @param ele
	 * @param text
	 */
	public static void deselectByVisibleText(WebElement ele, String text) {

		Select dropdown = new Select(ele);
		dropdown.deselectByVisibleText(text);
	}

	/**
	 * 
	 * @param ele
	 * @param value
	 */
	public static void deselectByValue(WebElement ele, String value) {

		Select dropdown = new Select(ele);
		dropdown.deselectByValue(value);
	}

	/**
	 * 
	 * @param ele
	 * @param index
	 */
	public static void deselectByIndex(WebElement ele, int index) {

		Select dropdown = new Select(ele);
		dropdown.deselectByIndex(index);
	}

	/**
	 * 
	 * @param ele
	 * @return
	 */
	public static String getFirstSelectedOptionText(WebElement ele) {

		Select dropdown = new Select(ele);
		return dropdown.getFirstSelectedOption().getText();
	}

	/**
	 * 
	 * @param ele
	 * @return
	 */
	public static String getFirstSelectedOptionValue(WebElement ele) {

		Select dropdown = new Select(ele);
		return dropdown.getFirstSelectedOption().getAttribute("value");
	}

	/**
	 * 
	 * @param ele
	 * @param value
	 * @return
	 */
	public static String getAllSelectedOptionsText(WebElement ele) {

		Select dropdown = new Select(ele);
		List<String> dropdownOptionsTextList = EleCollectionUtils.getEleTextList(dropdown.getAllSelectedOptions());
		return String.join(Utility.getDelimiter(), dropdownOptionsTextList);
	}

	/**
	 * 
	 * @param ele
	 * @param value
	 * @return
	 */
	public static String getAllSelectedOptionsValue(WebElement ele) {

		Select dropdown = new Select(ele);
		List<String> dropdownOptionsValueList = EleCollectionUtils.getEleAttrList(dropdown.getAllSelectedOptions(), "value");
		return String.join(Utility.getDelimiter(), dropdownOptionsValueList);
	}

	/**
	 * 
	 * @param ele
	 * @param texts
	 * @param delimiter
	 * @param canContinue
	 */
	public static String selectMultipleText(WebElement ele, String texts, String delimiter, boolean canContinue) {

		Select dropdown = new Select(ele);
		delimiter = Utility.getDelimiter(delimiter);

		List<String> textList = Arrays.asList(texts.split(Pattern.quote(delimiter)));
		List<String> notFoundTextList = new ArrayList<>();

		for (String text : textList) {

			try {
				dropdown.selectByVisibleText(text);

			} catch (NoSuchElementException e) {

				if (canContinue)
					notFoundTextList.add(text);
				else
					throw new Error("No Dropdown Text<" + text + "> found");
			}
		}

		if (!notFoundTextList.isEmpty()) 
			return "Drodpwn Text/s : " + notFoundTextList.toString() + " not found";
		else 
			return "";
	}

}
