package keywords.main.selenium;

import org.openqa.selenium.WebElement;

import keywords.core.TextFinder;
import keywords.core.WebControls;

public class TextKeywords {

	/**
	 * 
	 * @param textToSearch
	 * @param index
	 * @param isPartial
	 * @param beforeText
	 * @param afterText
	 */
	public static void clickByText(String textToSearch, int index, boolean isPartial, String beforeText,
			String afterText) {

		TextFinder textFinder = new TextFinder(textToSearch, index, isPartial, beforeText, afterText);
		WebElement textEle = textFinder.findElement();
		textEle.click();
	}

	/**
	 * 
	 * @param textToSearch
	 * @param index
	 * @param isPartial
	 * @param beforeText
	 * @param afterText
	 * @param before
	 * @param textToType
	 */
	public static void typeByText(String textToSearch, int index, boolean isPartial, String beforeText,
			String afterText, boolean before, String textToType) {

		long start = System.currentTimeMillis();
		TextFinder textFinder = new TextFinder(textToSearch, index, isPartial, beforeText, afterText);
		WebElement textEle = textFinder.findElement();

		WebElement typableEle = textFinder.actionEle(textEle, before, WebControls.TYPABLE);

		typableEle.clear();
		typableEle.sendKeys(textToType);
		System.out.println("TOTAL TIME : <" + (System.currentTimeMillis() - start) + ">");
	}

	/**
	 * 
	 * @param textToSearch
	 * @param index
	 * @param isPartial
	 * @param beforeText
	 * @param afterText
	 * @param before
	 * @param textToSelect
	 */
	public static void selectDropdownByText(String textToSearch, int index, boolean isPartial, String beforeText,
			String afterText, boolean before, String textToSelect) {

		TextFinder textFinder = new TextFinder(textToSearch, index, isPartial, beforeText, afterText);
		WebElement textEle = textFinder.findElement();

		WebElement dropdownEle = textFinder.actionEle(textEle, before, WebControls.DROPDOWN);

		Dropdown.selectByText(dropdownEle, textToSelect);
	}

	/**
	 * 
	 * @param textToSearch
	 * @param index
	 * @param isPartial
	 * @param beforeText
	 * @param afterText
	 * @param before
	 */
	public static void selectCheckboxByText(String textToSearch, int index, boolean isPartial, String beforeText,
			String afterText, boolean before) {

		TextFinder textFinder = new TextFinder(textToSearch, index, isPartial, beforeText, afterText);
		WebElement textEle = textFinder.findElement();

		WebElement checkboxEle = textFinder.actionEle(textEle, before, WebControls.CHECKBOX);

		checkboxEle.click();
	}

	/**
	 * 
	 * @param textToSearch
	 * @param index
	 * @param isPartial
	 * @param beforeText
	 * @param afterText
	 * @param before
	 */
	public static void selectRadioButtonByText(String textToSearch, int index, boolean isPartial, String beforeText,
			String afterText, boolean before) {

		TextFinder textFinder = new TextFinder(textToSearch, index, isPartial, beforeText, afterText);
		WebElement textEle = textFinder.findElement();

		WebElement radioButtonEle = textFinder.actionEle(textEle, before, WebControls.RADIO);

		radioButtonEle.click();
	}

	/**
	 * 
	 * @param textToSearch
	 * @param index
	 * @param isPartial
	 * @param beforeText
	 * @param afterText
	 */
	public static void mouseHoverOnText(String textToSearch, int index, boolean isPartial, String beforeText,
			String afterText) {

		TextFinder textFinder = new TextFinder(textToSearch, index, isPartial, beforeText, afterText);
		WebElement textEle = textFinder.findElement();

		SpecialActions.mouseHover(textEle);
	}

}