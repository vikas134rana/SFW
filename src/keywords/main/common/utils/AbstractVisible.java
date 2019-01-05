package keywords.main.common.utils;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface AbstractVisible {

	boolean isVisible(WebElement ele);

	WebElement getFirstVisibleEle(List<WebElement> eles);

	WebElement getLastVisibleEle(List<WebElement> eles);

	List<WebElement> getAllVisibleEles(List<WebElement> eles);

	WebElement getVisibleEleAt(List<WebElement> eles, int index);

}
