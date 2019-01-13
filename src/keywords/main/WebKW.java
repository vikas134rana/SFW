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
import keywords.main.selenium.Element;
import keywords.main.selenium.SpecialActions;
import keywords.main.selenium.TextKeywords;

public class WebKW {

	public static Keyword openBrowser(String browserName, String url) {

		Browser.getInstance().openBrowser(browserName, url);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword click(WebObject object) {

		WebElement ele = Finder.findElement(object);
		Element.getInstance().clickElement(ele);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword clickByJS(WebObject object) {

		WebElement ele = Finder.findElement(object);
		JSE.get(Driver.getDriver()).executeScript("arguments[0].click();", ele);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword sendKeys(WebObject object, String value) {

		WebElement ele = Finder.findElement(object);
		Element.getInstance().typeOnElement(ele, value);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword getElementText(WebObject object) {

		WebElement ele = Finder.findElement(object);
		String elementText = Element.getInstance().getElementText(ele).getValue();

		return KeywordRB.PASS().setOutput(elementText).setMessage(Message.DONE.toString()).build();
	}

	public static Keyword closeAllBrowser() {

		Driver.getDriver().quit();

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}

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
	
	public static Keyword typeByText(TextObject object,boolean before, String textToType) {

		TextKeywords.typeByText(object, before, textToType);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}
	
	public static Keyword selectDropdownByText(TextObject object,boolean before, String textToSelect) {

		TextKeywords.selectDropdownByText(object, before, textToSelect);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}
	
	public static Keyword selectCheckboxByText(TextObject object,boolean before) {

		TextKeywords.selectCheckboxByText(object, before);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}
	
	public static Keyword selectRadioButtonByText(TextObject object,boolean before) {

		TextKeywords.selectRadioButtonByText(object, before);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}
	
	public static Keyword mouseHoverOnText(TextObject object) {

		TextKeywords.mouseHoverOnText(object);

		return KeywordRB.PASS().setOutput(true).setMessage(Message.DONE.toString()).build();
	}
	
}
