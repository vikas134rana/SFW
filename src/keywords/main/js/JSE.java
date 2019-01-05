package keywords.main.js;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JSE {

	static JavascriptExecutor jse;

	public static JavascriptExecutor get(WebDriver driver) {
		if (jse == null)
			jse = (JavascriptExecutor)driver;
		return jse;
	}

}
