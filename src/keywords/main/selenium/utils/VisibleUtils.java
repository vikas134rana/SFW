package keywords.main.selenium.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import keywords.main.common.utils.AbstractVisible;

public class VisibleUtils implements AbstractVisible {

	static VisibleUtils obj;

	public static VisibleUtils getInstance() {
		if (obj == null)
			obj = new VisibleUtils();

		return obj;
	}

	@Override
	public boolean isVisible(WebElement ele) {

		return ele.isDisplayed();
	}

	@Override
	public WebElement getFirstVisibleEle(List<WebElement> eles) {

		if (eles == null)
			return null;

		return eles.stream().filter(i -> isVisible(i)).findFirst().orElse(null);
	}

	@Override
	public WebElement getLastVisibleEle(List<WebElement> eles) {

		if (eles == null)
			return null;

		Collections.reverse(eles);
		WebElement result = eles.stream().filter(i -> isVisible(i)).findFirst().orElse(null);
		Collections.reverse(eles);

		return result;
	}

	@Override
	public List<WebElement> getAllVisibleEles(List<WebElement> eles) {

		if (eles == null)
			return new ArrayList<>();

		return eles.stream().filter(i -> isVisible(i)).collect(Collectors.toList());
	}

	@Override
	public WebElement getVisibleEleAt(List<WebElement> eles, int index) {
		
		if (eles == null || index < 0)
			return null;

		return eles.stream().filter(i -> isVisible(i)).skip(index).findFirst().orElse(null);
	}

}
