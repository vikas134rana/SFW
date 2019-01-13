package keywords.main.selenium;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import core.WebObject;
import core.WebObjectProperty;
import core.WebObjectUtils;
import keywords.core.Driver;
import keywords.core.other.Message;
import keywords.main.WindowMap;

public class Window {

	public static List<String> getWindowHandles() {
		return new ArrayList<>(Driver.getDriver().getWindowHandles());
	}

	public static String getWindowHandle() {
		return Driver.getDriver().getWindowHandle();
	}

	public static void switchWindowIfNeeded(WebObject object) {

		System.out.println("################  AUTO_WINDOW_SWITCH  ###############");

		boolean isWindowSwitched = shadow_switchWindowIfNeeded(object);

		System.out.println("After Window Switching");
		System.out.println("# Page Title : " + Driver.getDriver().getTitle());

		if (isWindowSwitched)
			System.out.println("# Overall -> " + Message.WINDOW_SWITCH_SUCCESSFUL);
		else
			System.out.println("# Overall -> " + Message.WINDOW_SWITCH_FAILED);

	}

	private static boolean shadow_switchWindowIfNeeded(WebObject object) {

		/*- Get WindowTitle and WindwoTitleIndex ================================================================
		 * (if title is null or title not usable, return) 
		 *  Title and Index property can be null if topmost object is not Html (i.e can be frame or something else)*/

		WebObjectProperty metaTitleProp = WebObjectUtils.getWindowTitleProp(object);
		WebObjectProperty metaTitleIndexProp = WebObjectUtils.getWindowTitleIndexProp(object);

		if (metaTitleProp == null || !metaTitleProp.isUsableWithEmptyValue())
			return false;

		String objectWindowTitle = metaTitleProp.getValue();
		int objectWindowTitleIndex = -1;

		if (metaTitleIndexProp != null && metaTitleIndexProp.isUsable()) {
			objectWindowTitleIndex = NumberUtils.isDigits(metaTitleIndexProp.getValue()) ? NumberUtils.createInteger(metaTitleIndexProp.getValue()) : -1;
		}

		System.out.println("# Page Title : " + Driver.getDriver().getTitle());
		System.out.println("# Object Title : " + objectWindowTitle);
		System.out.println("# Object TitleIndex : " + objectWindowTitleIndex);

		/*- Update OpenedWindowMap (i.e. RemoveClosedWindow and AddNewWindow) ===================================== */
		WindowMap.updateOpenedWindowMap();
		System.out.println(WindowMap.openedWindowsHandleAndTitleMap);

		/*- if only one window is opened, switch to it ============================================================ */

		if (Window.getWindowHandles().size() == 1) {
			switchWindow(Window.getWindowHandle());
			return true;
		}

		/*- Get given Title Handle and Switch to this Handle ======================================================
		 *  if windowHandles related to title is 1, then we will not look at index and switch to that handle
		 *  if windowHandles related to title is more than 1 and titleIndex not negative, switch to handle (with index)
		 * */

		List<String> titleHandles = WindowMap.getHandles(WindowMap.openedWindowsHandleAndTitleMap, objectWindowTitle);
		System.out.println("TitleHandles -> Size: " + titleHandles.size() + " Handles: " + titleHandles);

		if (titleHandles.size() == 1) {
			return switchWindow(titleHandles.get(0));
		} else {

			/*- titleIndex should be greater than 0 and less than titleHandles list size */
			if (objectWindowTitleIndex >= 0 && objectWindowTitleIndex < titleHandles.size()) {

				String handle = titleHandles.get(objectWindowTitleIndex);
				System.out.println("Multiple Titles Found -> Switching to Handle: " + handle);
				return switchWindow(handle);
			}
		}

		return false;
	}

	public static boolean switchWindow(String windowHandle) {
		Driver.getDriver().switchTo().window(windowHandle);
		return true;
	}

	public static void updateCurrentWindowInOpenedMap() {
		WindowMap.addInOpenedWindowMap(getWindowHandle(), Driver.getDriver().getTitle());
	}
}
