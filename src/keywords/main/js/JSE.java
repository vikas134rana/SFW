package keywords.main.js;

import org.openqa.selenium.JavascriptExecutor;

import keywords.core.Driver;

public class JSE {

	static JavascriptExecutor jse;

	public static JavascriptExecutor getJSE() {
		if (jse == null)
			jse = (JavascriptExecutor) Driver.getDriver();
		return jse;
	}

	public static Object executeScript(String script, Object... args) {
		return getJSE().executeScript(script, args);
	}

	public static Object executeAsyncScript(String script, Object... args) {
		return getJSE().executeAsyncScript(script, args);
	}

}
