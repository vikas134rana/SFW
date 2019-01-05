package keywords.main;

import org.openqa.selenium.WebElement;

import core.Keyword;
import core.Keyword.KeywordRB;
import core.WebObject;
import keywords.core.Driver;
import keywords.core.Finder;
import keywords.core.other.Message;
import keywords.main.js.JSE;
import keywords.main.selenium.Alert;
import keywords.main.selenium.Browser;
import keywords.main.selenium.Element;
import keywords.main.selenium.SpecialActions;

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

	public static void main(String[] args) {

		// Keyword keywordResult = click();

		// System.out.println(keywordResult);

		/*-System.out.println("Status : "+keywordResult.getStatus());
		System.out.println("Output : "+keywordResult.getOutput());
		System.out.println("Message : "+keywordResult.getMessage());
		System.out.println("ScreenshotPath : "+keywordResult.getScreenshotPath());*/

	}
}
