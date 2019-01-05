package keywords.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import keywords.core.Driver;
import keywords.main.selenium.Window;

public class WindowMap {

	public static Map<String, String> allWindowsHandleAndTitleMap = new LinkedHashMap<>();

	public static Map<String, String> openedWindowsHandleAndTitleMap = new LinkedHashMap<>();

	private static final String DELIMITER = "@#@";

	public static String getTitleWithCurrentTime(String title) {

		String titleWithTime = title + DELIMITER + String.valueOf(System.currentTimeMillis());
		return titleWithTime;
	}

	public static String getTitle(String titleWithTime) {

		String title = titleWithTime.split(DELIMITER)[0];
		return title;
	}

	/**
	 * 
	 * @param map
	 * @param title
	 *            - title (without Time)
	 * @return - all window handles that have specified title
	 */
	public static List<String> getHandles(Map<String, String> map, String title) {
		List<String> list = map.entrySet().stream().filter(i -> i.getValue().split(DELIMITER)[0].equals(title)).map(i -> i.getKey())
				.collect(Collectors.toList());
		return list;
	}

	/*- public getTitle(Map<String, String> map,String handle) {
		map.entrySet().stream().filter(i->i.getValue().split(DELIMITER)[0].equals(title)).collect(Collectors.toList());
	}*/

	public static void addInOpenedWindowMap(String handle, String title) {

		String titleWithTime = getTitleWithCurrentTime(title);
		openedWindowsHandleAndTitleMap.put(handle, titleWithTime);
	}

	public boolean openedWindowMapContains(String title) {

		List<String> list = openedWindowsHandleAndTitleMap.values().stream().filter(i -> {

			if (title.contains(DELIMITER))
				return i.equals(title);
			else
				return i.split(DELIMITER)[0].equals(title);

		}).collect(Collectors.toList());

		if (list.size() == 1)
			return true;

		return false;
	}

	public static void updateOpenedWindowMap() {

		String currentWindowHandle = Window.getWindowHandle();
		List<String> windowHandles = Window.getWindowHandles();

		List<String> toBeAddedInOpenedHandles = CollectionUtils.subtract(windowHandles, openedWindowsHandleAndTitleMap.keySet()).stream()
				.collect(Collectors.toList());

		List<String> toBeRemovedFromOpenedHandles = CollectionUtils.subtract(openedWindowsHandleAndTitleMap.keySet(), windowHandles).stream()
				.collect(Collectors.toList());

		/*- remove old window handles from map */
		for (String handle : toBeRemovedFromOpenedHandles) {
			openedWindowsHandleAndTitleMap.remove(handle);
		}

		/*- add new window handles to map */
		for (String handle : toBeAddedInOpenedHandles) {
			Window.switchWindow(handle);
			String title = Driver.getDriver().getTitle();
			addInOpenedWindowMap(handle, title);
		}

		try {
			Window.switchWindow(currentWindowHandle);
		} catch (Exception e) {
			// MayBe Window is closed - Do Nothing
		}
	}

	public static void main(String[] args) throws InterruptedException {

		addInOpenedWindowMap("101", "google");
		Thread.sleep(100);
		addInOpenedWindowMap("110", "facebook");
		Thread.sleep(100);
		addInOpenedWindowMap("105", "google");
		Thread.sleep(100);
		addInOpenedWindowMap("116", "microsoft");
		Thread.sleep(100);
		addInOpenedWindowMap("103", "google");
		Thread.sleep(100);

		System.out.println("Map : " + openedWindowsHandleAndTitleMap);

		List<String> list = getHandles(openedWindowsHandleAndTitleMap, "google");

		System.out.println(list);

		List<String> windowHandlesList = new ArrayList<>(Arrays.asList("2", "3", "4"));
		List<String> openHandlesList = new ArrayList<>(Arrays.asList("1", "2", "3"));

		Collection<String> list3 = CollectionUtils.subtract(openHandlesList, windowHandlesList);
		System.out.println(list3);

	}

}
