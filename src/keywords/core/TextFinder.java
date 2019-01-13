package keywords.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import core.Data;
import core.TextObject;
import core.WebObjectProperty;
import keywords.main.Utility;
import keywords.main.selenium.utils.FinderUtils;
import keywords.main.selenium.utils.VisibleUtils;
import keywords.main.selenium.utils.WebEleUtils;
import keywords.main.selenium.utils.XpathUtils;

public class TextFinder {

	// TODO: Handle nbsp; when finding text - partially Done
	// TODO: Use BeforeText and AfterText - DONE
	/*- TODO: TextToSearch ignore placeholder text when placeholder is not visible(i.e when some value is present in editbox) */
	/*- TODO: Ignore TitleEle text where Parent element has title and child element has text. (title and text are textToSearch) */

	private String textToSearch;
	private int index;
	private boolean isPartial;
	private String beforeText;
	private String afterText;

	private List<WebElement> foundEles = new ArrayList<>();

	public TextFinder(String textToSearch) {
		this.textToSearch = textToSearch;
	}

	public TextFinder(String textToSearch, int index) {
		this.textToSearch = textToSearch;
		this.index = index;
	}

	public TextFinder(String textToSearch, int index, boolean isPartial) {
		this.textToSearch = textToSearch;
		this.index = index;
		this.isPartial = isPartial;
	}

	public TextFinder(String textToSearch, int index, boolean isPartial, String beforeText, String afterText) {
		this.textToSearch = StringUtils.trim(textToSearch);
		this.index = index;
		this.isPartial = isPartial;
		this.beforeText = StringUtils.trim(beforeText);
		this.afterText = StringUtils.trim(afterText);
	}

	public TextFinder(TextObject object) {
		WebObjectProperty textToSearchProp = object.getTextToSearch();
		WebObjectProperty indexProp = object.getIndex();
		WebObjectProperty partialProp = object.getPartial();
		WebObjectProperty beforeTextProp = object.getBeforeText();
		WebObjectProperty afterTextProp = object.getAfterText();

		this.textToSearch = StringUtils.trim(textToSearchProp.getValueIfUsable());
		this.index = Data.getInt(indexProp.getValueIfUsable());
		this.isPartial = Data.getBoolean(partialProp.getValueIfUsable());
		this.beforeText = StringUtils.trim(beforeTextProp.getValueIfUsable());
		this.afterText = StringUtils.trim(afterTextProp.getValueIfUsable());
	}

	// find visible elements inside whole page (in all frame)
	public WebElement findElement() {

		long start = System.currentTimeMillis();

		WebElement textEle = null;

		if (!StringUtils.isEmpty(beforeText) || !StringUtils.isEmpty(afterText)) {

			/*- find element using all properties (textToSearch, index, isPartial, beforeText and afterText)*/
			textEle = findElementUsingBeforeAndAfter();
		} else {

			/*- find element using textToSearchProperties properties (textToSearch,index,isPartial) */
			textEle = findElementUsingTextToSearch();
		}

		System.out.println("Final Text Ele : Outer HTML -> " + WebEleUtils.getAttr(textEle, "outerHTML"));

		System.out.println("Find TextEle TIME : <" + (System.currentTimeMillis() - start) + ">");
		return textEle;
	}

	public WebElement findElementUsingTextToSearch() {

		foundEles.clear();
		foundEles = findElesInCurrentDom();

		if (foundEles.size() <= index) {
			System.out.println("Frame Switching needed");
			findElementsInAllFrames();
		} else {
			System.out.println("Frame Switching not needed");
		}

		WebElement textEle = Utility.getItemAt(foundEles, index);

		return textEle;
	}

	private List<WebElement> findElesInCurrentDom() {
		List<WebElement> eles = FinderUtils.findElements(getTextXpath());
		List<WebElement> visibleEle = VisibleUtils.getInstance().getAllVisibleEles(eles);
		eles = Utility.removeDuplicate(visibleEle);

		/*- TODO: Ignore Element - 
		 * 1. like when placeholder Element is visible but placeholder is not visible
		 * 2. dropdown element item except selected item   */

		return eles;
	}

	private void findElementsInAllFrames() {

		System.out.println("Finding Text Inside Frame");

		List<WebElement> frameEles = Driver.getDriver().findElements(By.xpath("//*[self::frame or self::iframe]"));

		System.out.println("Frame Size: " + frameEles.size());

		for (int i = 0; i < frameEles.size(); i++) {

			System.out.println("Switching Frame");
			Driver.getDriver().switchTo().frame(frameEles.get(i));

			System.out.println(Driver.getDriver().getPageSource().split("\\n")[0]);

			System.out.println("Before Size :" + foundEles.size());
			foundEles.addAll(findElesInCurrentDom());
			System.out.println("After Size :" + foundEles.size());

			if (foundEles.size() > index)
				return;

			// Recursive call
			findElementsInAllFrames();

			/*- if TextToSearch is found with given index then don't switch to parent frame 
			 *  we have to be on same frame when we are going to perform action on text element later 
			 *  or else we will get StaleElementException */
			if (foundEles.size() > index) {
				return;
			}

			System.out.println("Switching to Parent Frame");
			Driver.getDriver().switchTo().parentFrame();
		}
		return;
	}

	private WebElement findElementUsingBeforeAndAfter() {

		TextFinder beforeTF = new TextFinder(beforeText);
		TextFinder afterTF = new TextFinder(afterText);

		/*- if Both BeforeText and AfterText values are present  ============================================================ */
		if (!StringUtils.isBlank(beforeText) && !StringUtils.isBlank(afterText)) {

			System.out.println("Finding using ByText Before Text And After Text");

			/*- Find beforeEle (switch Frame If Needed) */
			WebElement beforeEle = beforeTF.findElementUsingTextToSearch();

			/*- Find afterEle (in same frame or default Content) where beforeEle is found) */
			List<WebElement> eles = VisibleUtils.getInstance().getAllVisibleEles(FinderUtils.findElements(afterTF.getTextXpath()));
			WebElement afterEle = Utility.getItemAt(eles, 0);

			/*- find textToSearch Eles (same frame or default Content) Using beforeEle */
			String xpath = getTextXpath(true);
			List<WebElement> textElesUsingBeforeEle = FinderUtils.findElements(beforeEle, xpath);

			/*- find textToSearch Eles (same frame or default Content) Using afterEle */
			xpath = getTextXpath(false);
			List<WebElement> textElesUsingAfterEle = FinderUtils.findElements(afterEle, xpath);

			/*- filter common textToSearch Eles found Using beforeEle and afterEle */
			List<WebElement> commonEleList = new ArrayList<>(textElesUsingBeforeEle);
			commonEleList.retainAll(textElesUsingAfterEle);

			return Utility.getItemAt(commonEleList, index);
		}

		/*- if BeforeText value is present ==================================================================================== */
		else if (!StringUtils.isBlank(beforeText)) {

			System.out.println("Finding using ByText Before Text");

			/*- Find beforeEle (switch Frame If Needed) */
			WebElement beforeEle = beforeTF.findElementUsingTextToSearch();

			/*- find textToSearch Eles (same frame or default Content) Using beforeEle */
			String xpath = getTextXpath(true);
			List<WebElement> textEles = FinderUtils.findElements(beforeEle, xpath);

			return Utility.getItemAt(textEles, index);
		}

		/*- if AfterText value is present  ====================================================================================== */
		else if (!StringUtils.isBlank(afterText)) {

			System.out.println("Finding using ByText After Text");

			/*- Find afterEle (switch Frame If Needed) */
			WebElement afterEle = afterTF.findElementUsingTextToSearch();

			/*- find textToSearch Eles (same frame or default Content) Using afterEle */
			String xpath = getTextXpath(false);
			List<WebElement> textEles = FinderUtils.findElements(afterEle, xpath);

			return Utility.getItemAt(textEles, index);
		}

		return null;

	}

	private String getTextXpath() {
		return getTextXpath(null);
	}

	private String getTextXpath(Boolean before) {

		String x;
		String notScriptHeadAndHidden = "not(self::script) and not(self::*[./ancestor::head]) and not(@type='hidden') ";

		/*- ============================================================= CONTAINS =============================================================== */

		String cText = XpathUtils.cAttrXpath_NS_CI("text()",textToSearch);

		String cButtonValue = XpathUtils.cAttrXpath_NS_CI_NBSP("@value", textToSearch) + " and self::input["
				+ XpathUtils.cAttrXpath_NS_CI_NBSP("@type", "button") + "]";

		String cSubmitValue = XpathUtils.cAttrXpath_NS_CI_NBSP("@value", textToSearch) + " and self::input["
				+ XpathUtils.cAttrXpath_NS_CI_NBSP("@type", "submit") + "]";

		String cTitle = XpathUtils.cAttrXpath_NS_CI_NBSP("@title", textToSearch);

		String cSpecialSpaceText = XpathUtils.cAttrXpath_NS_CI_NBSP("text()", textToSearch);

		String cPlaceholder = XpathUtils.cAttrXpath_NS_CI_NBSP("@placeholder", textToSearch);

		String cTextNodeXpath = "text()[" + XpathUtils.cAttrXpath_NS_CI_NBSP(".", textToSearch) + "]/parent::*[1]";

		/*- =========================================================== NOT_CONTAINS ============================================================= */

		String text = XpathUtils.attrXpath_NS_CI("text()",textToSearch) + " or text()=" + Quotes.escape(textToSearch);

		String buttonValue = XpathUtils.attrXpath_NS_CI_NBSP("@value", textToSearch) + " and self::input["
				+ XpathUtils.attrXpath_NS_CI_NBSP("@type", "button") + "]";

		String submitValue = XpathUtils.attrXpath_NS_CI_NBSP("@value", textToSearch) + " and self::input["
				+ XpathUtils.attrXpath_NS_CI_NBSP("@type", "submit") + "]";

		String title = XpathUtils.attrXpath_NS_CI_NBSP("@title", textToSearch);

		String specialSpaceText = XpathUtils.attrXpath_NS_CI_NBSP("text()", textToSearch);

		String placeholder = XpathUtils.attrXpath_NS_CI_NBSP("@placeholder", textToSearch);

		String textNodeXpath = "text()[" + XpathUtils.attrXpath_NS_CI_NBSP(".", textToSearch) + "]/parent::*[1]";

		/*- =========================================================== ************ ============================================================= */

		String xpathDirection = "//";

		if (before != null)
			xpathDirection = XpathUtils.xpathDirection(before);

		if (isPartial) {

			x = xpathDirection + "*[ " + notScriptHeadAndHidden + " and (   (" + cText + ") or (" + cButtonValue + ") or (" + cSubmitValue + ") or (" + cTitle
					+ ")  or (" + cSpecialSpaceText + ") or (" + cPlaceholder + ") or (" + cTextNodeXpath + ")  )]";
		} else {

			x = xpathDirection + "*[ " + notScriptHeadAndHidden + " and (   (" + text + ") or (" + buttonValue + ") or (" + submitValue + ") or (" + title
					+ ")  or (" + specialSpaceText + ") or (" + placeholder + ")  or (" + textNodeXpath + ")  )]";
		}

		System.out.println(x);
		return x;
	}

	public WebElement actionEle(WebElement textEle, boolean before, WebControls webControls) {

		/*- TODO : Handle Case where textToSearch is not actual text but it is attribute value like title, placeholder, submit etc */
		/*- TODO : Handle Case where textToSearch is checkbox, dropdown */

		/*- if textEle is input, then it is our action ele
		 *  this logic create error when input element is taken as label for dropdown, checkbox, radio - NEED_TO_FIX in future  - DONE*/

		long start = System.currentTimeMillis();

		WebElement componentEle = null;

		/*- if textEle is found using placeholder and keyword is Type then it is our action Ele
		 *  Return this. no need to go further */
		if (webControls == WebControls.TYPABLE && WebEleUtils.isTypable(textEle)) {
			componentEle = textEle;
		} else {

			System.out.println("Find ActionEle (isTypable) TIME : <" + (System.currentTimeMillis() - start) + ">");

			/*- Check if textEle have any textNode or child element */
			/*- BUG (On Salesforce this line (finding element using other element) taking 2 sec). Dont know why. But on other sites working fine. */
			boolean useTextXpath = FinderUtils.findElement(textEle, ".//*[self::text() or self::*]") == null ? false : true;

			System.out.println("Find ActionEle (useTextNodeXpath) TIME : <" + (System.currentTimeMillis() - start) + ">");

			String textNodeXpath = "";

			if (useTextXpath) {

				String textXpath = XpathUtils.attrXpath_NS_CI_NBSP(".", textToSearch, isPartial);
				textNodeXpath = "//text()[" + textXpath + "]";
			}

			String componentXpathWithDirection = XpathUtils.componentXpathWithDirection(before, webControls);
			System.out.println("ComponentXpath : " + componentXpathWithDirection);

			String textNodeComponentXpath = "." + textNodeXpath + componentXpathWithDirection.substring(1);
			System.out.println("Final Component Xpath : " + textNodeComponentXpath);

			System.out.println("Find ActionEle (Create TextNode Xpath) TIME : <" + (System.currentTimeMillis() - start) + ">");

			/*- find component element */
			List<WebElement> componentEles = FinderUtils.findElements(textEle, textNodeComponentXpath);

			System.out.println("Find ActionEle (find Components) TIME : <" + (System.currentTimeMillis() - start) + ">");

			if (before)
				componentEle = VisibleUtils.getInstance().getLastVisibleEle(componentEles);
			else
				componentEle = VisibleUtils.getInstance().getFirstVisibleEle(componentEles);

			System.out.println("Find ActionEle (Find Final Component) TIME : <" + (System.currentTimeMillis() - start) + ">");
		}

		System.out.println("Final Action Ele : " + WebEleUtils.getAttr(componentEle, "outerHTML"));

		System.out.println("Find ActionEle TIME : <" + (System.currentTimeMillis() - start) + ">");

		return componentEle;
	}

}
