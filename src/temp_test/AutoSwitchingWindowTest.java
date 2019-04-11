package temp_test;

import java.util.concurrent.TimeUnit;

import core.WebObject;
import core.WebObjectProperty;
import core.WebObject.MetaType;
import keywords.core.Driver;
import keywords.main.Utility;
import keywords.main.WebKW;

public class AutoSwitchingWindowTest {

	public static void main(String[] args) {

		try {
			startExecution();
		} catch (Exception e) {
			e.printStackTrace();

			if (Driver.getDriver() != null)
				Driver.getDriver().quit();
		}

	}

	public static void startExecution() {
		// Open Browser
		WebKW.openBrowser("chrome", "https://login.salesforce.com/");

		// set Implicit Wait to find Element
		Driver.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		Utility.waitUntil(5000);

		WebObject object = null;
		WebObject parentObject = null;
		WebObject gParentObject = null;

		// Type Username (Window 1)
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath:tag", "//div", true, false));
		object.addXpaths(new WebObjectProperty("xpath:tag", "//input", true, false));
		object.addXpaths(new WebObjectProperty("xpath", "//input[@name='username']", true, false));
		parentObject = new WebObject();
		parentObject.setMetaTitle(new WebObjectProperty("Title", "Login | Salesforce", true, false));
		object.setParentObject(parentObject, MetaType.HTML);
		WebKW.type(object, "vikas");
		Utility.waitUntil(1000);

		// Click TakeTheTour (Window 1)
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//a[normalize-space(.)='Take the Tour']", true, false));
		parentObject = new WebObject();
		parentObject.addXpaths(new WebObjectProperty("xpath", "//iframe[@title='Marketing']", true, false));
		object.setParentObject(parentObject, MetaType.FRAME);
		gParentObject = new WebObject();
		gParentObject.setMetaTitle(new WebObjectProperty("Title", "Login | Salesforce", true, false));
		parentObject.setParentObject(gParentObject, MetaType.HTML);
		WebKW.clickByJS(object);

		Utility.waitUntil(8000);

		// Type FirstName (Window 2)
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//*[@id='UserFirstName']", true, false));
		parentObject = new WebObject();
		parentObject.setMetaTitle(new WebObjectProperty("Title",
				"Interactive CRM Guided Tour from Salesforce.com - Customer Relationship Management Free Trial & Live Demo. - Salesforce.com", true, false));
		object.setParentObject(parentObject, MetaType.HTML);
		WebKW.type(object, "vikas");
		Utility.waitUntil(1000);

		// Click TakeTheTour (Window 1) (FRAME AND WINDOW SWITCHING)
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//a[normalize-space(.)='Take the Tour']", true, false));
		parentObject = new WebObject();
		parentObject.addXpaths(new WebObjectProperty("xpath", "//iframe[@title='Marketing']", true, false));
		object.setParentObject(parentObject, MetaType.FRAME);
		gParentObject = new WebObject();
		gParentObject.setMetaTitle(new WebObjectProperty("Title", "Login | Salesforce", true, false));
		parentObject.setParentObject(gParentObject, MetaType.HTML);
		WebKW.clickByJS(object);

		// Type LastName (Window 3) - MULTIPLE WINDOW WITH SAME TITLE
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//*[@id='UserLastName']", true, false));
		parentObject = new WebObject();
		parentObject.setMetaTitle(new WebObjectProperty("Title",
				"Interactive CRM Guided Tour from Salesforce.com - Customer Relationship Management Free Trial & Live Demo. - Salesforce.com", true, false));
		parentObject.setMetaTitleIndex(new WebObjectProperty("TitleIndex", "1", true, false));
		object.setParentObject(parentObject, MetaType.HTML);
		WebKW.type(object, "vikas");

		/*- Click (Terms and Privacy Statement) (Window 3) - WINDOW TITLE UPDATED ON SAME HANDLE */
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//a[text()='Terms and Privacy Statement']", true, false));
		parentObject = new WebObject();
		parentObject.setMetaTitle(new WebObjectProperty("Title",
				"Interactive CRM Guided Tour from Salesforce.com - Customer Relationship Management Free Trial & Live Demo. - Salesforce.com", true, false));
		parentObject.setMetaTitleIndex(new WebObjectProperty("TitleIndex", "1", true, false));
		object.setParentObject(parentObject, MetaType.HTML);
		WebKW.click(object);

		/*- Click (About Us) (Window 3) */
		object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//a[text()='About Us']", true, false));
		parentObject = new WebObject();
		parentObject.setMetaTitle(new WebObjectProperty("Title", "Privacy Policy - Salesforce.com", true, false));
		object.setParentObject(parentObject, MetaType.HTML);
		WebKW.click(object);

		// Sleep 5sec
		Utility.waitUntil(8000);

		// Close Browser
		Driver.quit();
	}
}
