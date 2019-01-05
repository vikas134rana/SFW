package temp_test;

import core.WebObject;
import core.WebObject.MetaType;
import core.WebObjectProperty;
import keywords.main.Utility;
import keywords.main.WebKW;

public class ActionsTest {

	public static void main(String[] args) {

		// Open Browser
		WebKW.openBrowser("chrome", "http://swisnl.github.io/jQuery-contextMenu/demo.html");

		// right click
		WebObject object = new WebObject();
		object.addXpaths(new WebObjectProperty("xpath", "//span[text()='right click me']", true, false));
		WebKW.rightClick(object);

		Utility.waitUntil(2000);
		
		//accept popup
		WebKW.acceptAlert();
	}

}
