package keywords.main.selenium.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

public class EleCollectionUtils {

	public static List<String> getEleTextList(List<WebElement> list) {

		return list.stream().map(i -> i.getText()).collect(Collectors.toList());
	}

	public static List<String> getEleAttrList(List<WebElement> list, String attr) {
		
		return list.stream().map(i -> i.getAttribute(attr)).collect(Collectors.toList());
	}

}
