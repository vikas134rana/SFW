package temp_test;

import org.openqa.selenium.WebDriver;

import core.WebObject;
import core.WebObject.MetaType;
import core.WebObjectProperty;
import keywords.core.Driver;
import keywords.main.Utility;
import keywords.main.WebKW;
import keywords.main.selenium.Button;
import keywords.main.selenium.Textbox;
import keywords.main.selenium.Window;
import keywords.main.selenium.utils.FinderUtils;

public class TestPlugin {

	static WebDriver driver;

	public static void main(String[] args) {

		try {
			startExecution1();
		} catch (Exception e) {
			e.printStackTrace();

			if (driver != null)
				driver.quit();
		}

	}

	public static void startExecution() {
		// Open Browser
		// driver = Driver.startChrome("http://toolsqa.com/automation-practice-form/");

		// Type FirstName
		Textbox.getInstance().typeOnTextBox(FinderUtils.findElement("//input[@name='firstname']"), "vikas");

		// Switch wrong window
		Window.switchWindow("abc");

		// Get FirstName
		System.out.println("FirstName : " + Textbox.getInstance().getTextBoxValue(FinderUtils.findElement("//input[@name='firstname']")).getValue());

		// Type LastName
		Textbox.getInstance().typeOnTextBox(FinderUtils.findElement("//input[@name='lastname']"), "rana");

		// Select Male
		Button.getInstance().clickButton(FinderUtils.findElement("//*[@id='sex-0']"));

		// Select Experience
		Button.getInstance().clickButton(FinderUtils.findElement("//*[@id='exp-1']"));

		// Get Link Text
		System.out.println(
				"Link Text : " + Button.getInstance().getButtonText(FinderUtils.findElement("//*[text()='Selenium Automation Hybrid Framework']")).getValue());

		// Click Link
		Button.getInstance().clickButton(FinderUtils.findElement("//*[text()='Selenium Automation Hybrid Framework']"));

		// Sleep 5sec
		Utility.waitUntil(5000);

		// Close Browser
		Driver.quit();
	}

	public static void startExecution1() {
		// Open Browser
		WebKW.openBrowser("chrome", "http://toolsqa.com/automation-practice-form/");

		// Driver.quit();

		// Type FirstName
		WebObject object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath:tag", "//div", true, false));
		object.addXpaths(new WebObjectProperty("xpath:tag", "//input", true, false));
		object.addXpaths(new WebObjectProperty("xpath", "//input[@name='firstname']", true, false));
		WebKW.type(object, "vikas");

		// Get FirstName
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath:tag", "//input", true, false));
		object.addXpaths(new WebObjectProperty("xpath:tag", "//div", true, false));
		object.addXpaths(new WebObjectProperty("xpath", "//input[@name='firstname']", true, false));
		System.out.println("FirstName : " + WebKW.getText(object).getOutput());

		// Type LastName
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//input[@name='lastname']", true, false));
		WebKW.type(object, "rana");

		// Select Male
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//*[@id='sex-0']", true, false));
		WebKW.click(object);

		// Select Experience
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//*[@id='exp-1']", true, false));
		WebKW.click(object);

		// Get Link Text
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//*[text()='Selenium Automation Hybrid Framework']", true, false));
		System.out.println("Link Text : " + WebKW.getText(object).getOutput());

		// Click Link
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//*[text()='Selenium Automation Hybrid Framework']", true, false));
		WebKW.click(object);

		// Close Browser
		Driver.quit();

		// Navigate To (Frame Link)
		WebKW.openBrowser("chrome", "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_iframe");

		// Click (Frame Ele - 2 level frame)
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//a[@title='Search W3Schools']", true, false));

		WebObject parentframeObject = new WebObject();
		parentframeObject.addXpaths(new WebObjectProperty("xpath", "//iframe[@src='https://www.w3schools.com']", true, false));

		WebObject gParentFrameObject = new WebObject();
		gParentFrameObject.addXpaths(new WebObjectProperty("xpath", "//iframe[@id='iframeResult']", true, false));

		object.setParentObject(parentframeObject, MetaType.FRAME);
		parentframeObject.setParentObject(gParentFrameObject, MetaType.FRAME);

		WebKW.click(object);

		// Sleep 5sec
		Utility.waitUntil(8000);

		// Close Browser
		Driver.quit();
	}

}
