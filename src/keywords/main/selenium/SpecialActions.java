package keywords.main.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import keywords.core.Driver;

public class SpecialActions {

	private static Actions actions = new Actions(Driver.getDriver());

	public static void doubleClick(WebElement ele) {

		actions.doubleClick(ele).build().perform();
	}

	public static void rightClick(WebElement ele) {

		//not working
		actions.contextClick(ele).build().perform();
	}

	public static void mouseHover(WebElement ele) {

		actions.moveToElement(ele).build().perform();
	}

	public static void dragAndDrop(WebElement dragEle, WebElement dropEle) {

		actions.dragAndDrop(dragEle, dropEle).build().perform();
	}

}
