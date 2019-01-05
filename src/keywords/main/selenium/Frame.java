package keywords.main.selenium;

import java.util.List;
import java.util.Stack;

import org.openqa.selenium.WebElement;

import core.WebObject;
import core.WebObject.MetaType;
import core.WebObjectProperty;
import keywords.core.Driver;
import keywords.core.Finder;
import keywords.core.Locator;
import keywords.core.other.Message;

public class Frame {

	public static void switchFrameIfNeeded(WebObject object) {

		System.out.println("################  AUTO_FRAME_SWITCH  ################");
		
		if (shadow_switchFrameIfNeeded(object))
			System.out.println("# Overall -> " + Message.FRAME_SWITCH_SUCCESSFUL);
		else
			System.out.println("# Overall -> " + Message.FRAME_SWITCH_FAILED);
	}

	public static boolean shadow_switchFrameIfNeeded(WebObject object) {

		Stack<WebObject> stack = new Stack<>();

		WebObject currentObject = object;

		while (currentObject.getParentObject() != null && currentObject.getParentObject().getMetaType().getValue().equals(MetaType.FRAME.toString())) {
			currentObject = currentObject.getParentObject();
			stack.push(currentObject);
		}

		framesLoop: while (stack.size() > 0) {

			WebObject frameObject = stack.pop();

			/*- @id ====================================================================================== */

			if (switchFrame(Locator.ID, frameObject.getId()))
				continue;

			/*- @name  ==================================================================================== */

			if (switchFrame(Locator.NAME, frameObject.getName()))
				continue;

			/*- @innerText  =============================================================================== */

			if (switchFrame(Locator.INNER_TEXT, frameObject.getInnerText()))
				continue;

			/*- @textContent  ============================================================================= */

			if (switchFrame(Locator.TEXT_CONTENT, frameObject.getTextContent()))
				continue;

			/*- @xpaths  ================================================================================== */

			for (WebObjectProperty xpathObjProp : frameObject.getXpaths()) {

				if (switchFrame(Locator.XPATH, xpathObjProp))
					continue framesLoop;
			}

			/*- @cssSelectors  ============================================================================ */

			for (WebObjectProperty cssObjProp : frameObject.getCssSelectors()) {

				if (switchFrame(Locator.CSS_SELECTOR, cssObjProp))
					continue framesLoop;
			}

			/*- @className  =============================================================================== */

			if (switchFrame(Locator.CLASSNAME, frameObject.getClassName()))
				continue;

			return false;
		}

		return true;
	}

	public static boolean switchFrame(Locator locator, WebObjectProperty objProp) {

		if (objProp.isUsable()) {

			return switchFrame(locator, objProp.getValue());
		}
		return false;
	}

	public static boolean switchFrame(Locator locator, String value) {

		List<WebElement> foundFrameEles = Finder.findElements(locator, value);
		System.out.println("\t"+"By Frame Property " + locator + " : \"" + value + "\" -> FoundElements : " + foundFrameEles.size());

		if (foundFrameEles.size() == 1) {

			WebElement frameEle = foundFrameEles.get(0);

			Driver.getDriver().switchTo().frame(frameEle);

			System.out.println(Message.FRAME_SWITCH_SUCCESSFUL);

			return true;
		}

		return false;
	}

	public static void switchToDefaultContent() {

		Driver.getDriver().switchTo().defaultContent();
	}

}
