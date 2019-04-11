package keywords.main.selenium;

import org.openqa.selenium.WebElement;

import keywords.core.WebControls;
import keywords.main.selenium.utils.FinderUtils;
import keywords.main.selenium.utils.VisibleUtils;

public class Action {

	public static void click(WebElement ele) {

		ele.click();
	}
	
	public static void type(WebElement ele, String value) {

		ele.clear();
		ele.sendKeys(value);
	}
	
	public static void type(WebElement parentEle, String textToType, int typableIndex) {
		WebElement typableEle = VisibleUtils.getInstance().getVisibleEleAt(FinderUtils.findElements(parentEle, WebControls.TYPABLE), typableIndex);
		if (typableEle == null)
			throw new Error(WebControls.TYPABLE + " Element not found");

		type(typableEle, textToType);
	}
	
	public static void selectCheckbox(WebElement parentEle, int checkboxIndex) {
		WebElement checkboxEle = VisibleUtils.getInstance().getVisibleEleAt(FinderUtils.findElements(parentEle, WebControls.CHECKBOX), checkboxIndex);
		if (checkboxEle == null)
			throw new Error(WebControls.CHECKBOX + " Element not found");

		click(checkboxEle);
	}

	public static void selectRadio(WebElement parentEle, int radioIndex) {
		WebElement radioEle = VisibleUtils.getInstance().getVisibleEleAt(FinderUtils.findElements(parentEle, WebControls.RADIO), radioIndex);
		if (radioEle == null)
			throw new Error(WebControls.RADIO + " Element not found");

		click(radioEle);
	}

	public static void selectDropdown(WebElement parentEle, int dropdownIndex, String valueToSelect) {
		WebElement dropdownEle = VisibleUtils.getInstance().getVisibleEleAt(FinderUtils.findElements(parentEle, WebControls.DROPDOWN), dropdownIndex);
		if (dropdownEle == null)
			throw new Error(WebControls.DROPDOWN + " Element not found");

		Dropdown.selectByValueOrText(dropdownEle, valueToSelect);
	}
	

	
}
