package keywords.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ImmutableMap;

import core.WebObject;
import core.WebObjectProperty;
import keywords.main.selenium.Frame;
import keywords.main.selenium.Window;

public class Finder {

	private static List<WebElement> tempElementsInBucket = new ArrayList<>();

	public static WebElement findElement(WebObject object) {

		System.out.println("\n ===================================================================================");

		/*- Gson gson = new GsonBuilder().create();
		String json = gson.toJson(object);
		System.out.println(json);*/

		tempElementsInBucket.clear();
		List<WebElement> elementsInBucket = new ArrayList<>();

		// WINDOW HANDLING
		Window.switchWindowIfNeeded(object);

		// FRAME HANDLING
		Frame.switchFrameIfNeeded(object);

		System.out.println("##################  FINDING_ELEMENT  ################");

		Map<Locator, List<WebObjectProperty>> locatorAndPropertyMap = getLocatorAndPropertyMap(object);

		for (Entry<Locator, List<WebObjectProperty>> entry : locatorAndPropertyMap.entrySet()) {

			for (WebObjectProperty prop : entry.getValue()) {
				elementsInBucket = findElementAndUpdateBuckets(prop, entry.getKey());

				if (elementsInBucket.size() == 1)
					return elementsInBucket.get(0);
			}

		}

		/*- ******************************************************************************************** */

		/*- if(elementsInBucket.isEmpty()) {
			throw ObjectNotFoundException;
		}*/

		if (elementsInBucket.size() == 1)
			return elementsInBucket.get(0);

		return tempElementsInBucket.get(0);
	}

	private static List<WebElement> findElementAndUpdateBuckets(WebObjectProperty objProp, Locator locator) {

		List<WebElement> elementsInBucket = new ArrayList<>();

		if (objProp.isUsable()) {

			elementsInBucket = findElements(locator, objProp.getValue());
			tempElementsInBucket = updateTempBucket(elementsInBucket, tempElementsInBucket);

			System.out.println("\t" + "By Property " + locator + " : \"" + objProp.getValue() + "\" -> FoundElements : " + elementsInBucket.size()
					+ " , TempElements : " + tempElementsInBucket.size());
		}

		return elementsInBucket;
	}

	public static List<WebElement> findElements(Locator by, String value) {
		return findElements(by, value, "*");
	}

	public static List<WebElement> findElements(Locator by, String value, String tag) {

		List<WebElement> elementsInBucket = new ArrayList<>();

		switch (by) {
		case ID:
			elementsInBucket = Driver.getDriver().findElements(By.id(value));
			break;

		case NAME:
			elementsInBucket = Driver.getDriver().findElements(By.name(value));
			break;

		case INNER_TEXT:
			elementsInBucket = Driver.getDriver().findElements(By.xpath("//" + tag + "[text()= '" + value + "']"));
			break;

		case TEXT_CONTENT:
			elementsInBucket = Driver.getDriver().findElements(By.xpath("//" + tag + "[text()= '" + value + "']"));
			break;

		case XPATH:
			elementsInBucket = Driver.getDriver().findElements(By.xpath(value));
			break;

		case CSS_SELECTOR:
			elementsInBucket = Driver.getDriver().findElements(By.cssSelector(value));
			break;

		case CLASSNAME:
			elementsInBucket = Driver.getDriver().findElements(By.className(value));
			break;
		}

		return elementsInBucket;

	}

	private static List<WebElement> updateTempBucket(List<WebElement> elementsInBucket, List<WebElement> tempElementsInBucket) {
		if (tempElementsInBucket == null || tempElementsInBucket.isEmpty() || elementsInBucket.size() < tempElementsInBucket.size()) {
			tempElementsInBucket = elementsInBucket;
		}
		return tempElementsInBucket;
	}

	private static Map<Locator, List<WebObjectProperty>> getLocatorAndPropertyMap(WebObject object) {

		Map<Locator, List<WebObjectProperty>> map = new HashMap<Locator, List<WebObjectProperty>>();

		map.put(Locator.ID, Collections.singletonList(object.getId()));
		map.put(Locator.NAME, Collections.singletonList(object.getName()));
		map.put(Locator.INNER_TEXT, Collections.singletonList(object.getInnerText()));
		map.put(Locator.TEXT_CONTENT, Collections.singletonList(object.getTextContent()));
		map.put(Locator.XPATH, object.getXpaths());
		map.put(Locator.CSS_SELECTOR, object.getCssSelectors());
		map.put(Locator.CLASSNAME, Collections.singletonList(object.getClassName()));

		return map;
	}

}
