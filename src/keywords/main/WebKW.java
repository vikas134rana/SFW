package keywords.main;

import org.openqa.selenium.WebElement;

import core.Keyword;
import core.Keyword.KeywordRB;
import core.TextObject;
import core.WebObject;
import keywords.core.Driver;
import keywords.core.Finder;
import keywords.core.other.Message;
import keywords.main.js.JSE;
import keywords.main.selenium.Alert;
import keywords.main.selenium.Browser;
import keywords.main.selenium.Dropdown;
import keywords.main.selenium.Element;
import keywords.main.selenium.SpecialActions;
import keywords.main.selenium.TextKeywords;

public class WebKW {

	/*- ========================================= BROWSER ================================================== */

	public static Keyword openBrowser(String browserName, String url) {

		Browser.getInstance().openBrowser(browserName, url);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword closeAllBrowser() {

		Driver.getDriver().quit();

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	/*- ========================================= ACTION/FUNTION================================================== */
	public static Keyword click(WebObject object) {

		WebElement ele = Finder.findElement(object);
		Element.getInstance().clickElement(ele);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword clickByJS(WebObject object) {

		WebElement ele = Finder.findElement(object);
		JSE.getJSE().executeScript("arguments[0].click();", ele);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword type(WebObject object, String value) {

		WebElement ele = Finder.findElement(object);
		Element.getInstance().typeOnElement(ele, value);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword getText(WebObject object) {

		WebElement ele = Finder.findElement(object);
		String elementText = Element.getInstance().getElementText(ele).getValue();

		return KeywordRB.PASS().setOutput(elementText).setMessage(Message.DONE.toString()).build();
	}

	/*- ========================================= Dropdown ================================================== */

	public static Keyword selectDropdownUsingText(WebObject object, String text) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.selectByText(dropdownEle, text);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword selectDropdownByValue(WebObject object, String value) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.selectByValue(dropdownEle, value);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword selectDropdownByIndex(WebObject object, int index) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.selectByIndex(dropdownEle, index);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword selectDropdownByValueOrText(WebObject object, String valueOrText) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.selectByValueOrText(dropdownEle, valueOrText);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword getDropdownOptionsText(WebObject object, String delimiter) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.getOptionsText(dropdownEle, delimiter);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword getDropdownOptionsValue(WebObject object, String delimiter) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.getOptionsValue(dropdownEle, delimiter);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	// *********** Multiple Dropdwon **************

	public static Keyword deselectAll(WebObject object) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.deselectAll(dropdownEle);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword deselectDropdownUsingText(WebObject object, String text) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.deselectByVisibleText(dropdownEle, text);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword deselectDropdownByValue(WebObject object, String value) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.deselectByValue(dropdownEle, value);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword deselectDropdpwnByIndex(WebObject object, int index) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.deselectByIndex(dropdownEle, index);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword getDropdownFirstSelectedText(WebObject object) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.getFirstSelectedOptionText(dropdownEle);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword getDropdownFirstSelectedValue(WebObject object) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.getFirstSelectedOptionValue(dropdownEle);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword getDropdownAllSelectedText(WebObject object) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.getAllSelectedOptionsText(dropdownEle);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword getDropdownAllSelectedValue(WebObject object) {

		WebElement dropdownEle = Finder.findElement(object);
		Dropdown.getAllSelectedOptionsValue(dropdownEle);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword selectDropdwonMultipleText(WebObject object, String texts, String delimiter, boolean canContinue) {

		WebElement dropdownEle = Finder.findElement(object);
		String msg = Dropdown.selectMultipleText(dropdownEle, texts, delimiter, canContinue);

		return KeywordRB.PASS().setOutput(true).setMessage(msg.isEmpty() ? Message.DONE.toString() : msg).build();
	}

	// ====================================================================================

	public static Keyword rightClick(WebObject object) {

		WebElement ele = Finder.findElement(object);
		SpecialActions.rightClick(ele);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword acceptAlert() {

		Alert.accept();

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	/*- ========================================= By_Text ================================================== */

	public static Keyword clickByText(TextObject object) {

		TextKeywords.clickByText(object);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword typeByText(TextObject object, boolean before, String textToType) {

		TextKeywords.typeByText(object, before, textToType);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword selectDropdownByText(TextObject object, boolean before, String textToSelect) {

		TextKeywords.selectDropdownByText(object, before, textToSelect);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword selectCheckboxByText(TextObject object, boolean before) {

		TextKeywords.selectCheckboxByText(object, before);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword selectRadioButtonByText(TextObject object, boolean before) {

		TextKeywords.selectRadioButtonByText(object, before);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword mouseHoverOnText(TextObject object) {

		TextKeywords.mouseHoverOnText(object);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

}
