package core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Object")
public class WebObject {

	public enum MetaType {
		HTML, FRAME;
	}

	private WebObjectProperty metaUrl = new WebObjectProperty();
	private WebObjectProperty metaSrc = new WebObjectProperty();
	private WebObjectProperty metaTitle = new WebObjectProperty();
	private WebObjectProperty metaTitleIndex = new WebObjectProperty();
	private WebObjectProperty metaType = new WebObjectProperty();
	private WebObjectProperty metaTag = new WebObjectProperty();

	private WebObjectProperty tag = new WebObjectProperty();
	private WebObjectProperty id = new WebObjectProperty();
	private WebObjectProperty name = new WebObjectProperty();
	private WebObjectProperty innerText = new WebObjectProperty();
	private WebObjectProperty textContent = new WebObjectProperty();
	private List<WebObjectProperty> cssSelectors = new ArrayList<>();
	private List<WebObjectProperty> xpaths = new ArrayList<>();
	private WebObjectProperty className = new WebObjectProperty();

	private List<WebObjectProperty> allWebObjectProperty = new ArrayList<>();
	private WebObject parentObject = null;

	/*- ************************************  GETTER and SETTER start  ************************************** */

	/*- Parent Properties =================================== */

	public WebObjectProperty getMetaUrl() {
		return metaUrl;
	}

	public void setMetaUrl(WebObjectProperty metaUrl) {
		this.metaUrl = metaUrl;
	}

	public WebObjectProperty getMetaSrc() {
		return metaSrc;
	}

	public void setMetaSrc(WebObjectProperty metaSrc) {
		this.metaSrc = metaSrc;
	}

	public WebObjectProperty getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(WebObjectProperty metaTitle) {
		this.metaTitle = metaTitle;
	}

	public WebObjectProperty getMetaTitleIndex() {
		return metaTitleIndex;
	}

	public void setMetaTitleIndex(WebObjectProperty metaTitleIndex) {
		this.metaTitleIndex = metaTitleIndex;
	}

	public WebObjectProperty getMetaType() {
		return metaType;
	}

	public void setMetaType(WebObjectProperty metaType) {
		this.metaType = metaType;
	}

	public WebObjectProperty getMetaTag() {
		return metaTag;
	}

	public void setMetaTag(WebObjectProperty metaTag) {
		this.metaTag = metaTag;
	}

	/*- Parent Properties ======================================= */

	public WebObjectProperty getTag() {
		return tag;
	}

	public void setTag(WebObjectProperty tag) {
		this.tag = tag;
	}

	@XmlElement(name = "ObjectProperty")
	public List<WebObjectProperty> getAllWebObjectProperty() {
		return allWebObjectProperty;
	}

	public void setAllWebObjectProperty(List<WebObjectProperty> allWebObjectProperty) {
		this.allWebObjectProperty = allWebObjectProperty;
	}

	public WebObjectProperty getId() {
		return id;
	}

	public void setId(WebObjectProperty id) {
		this.id = id;
	}

	public WebObjectProperty getName() {
		return name;
	}

	public void setName(WebObjectProperty name) {
		this.name = name;
	}

	public WebObjectProperty getInnerText() {
		return innerText;
	}

	public void setInnerText(WebObjectProperty innerText) {
		this.innerText = innerText;
	}

	public WebObjectProperty getTextContent() {
		return textContent;
	}

	public void setTextContent(WebObjectProperty textContent) {
		this.textContent = textContent;
	}

	public List<WebObjectProperty> getCssSelectors() {
		return cssSelectors;
	}

	public void setCssSelectors(List<WebObjectProperty> cssSelectors) {
		this.cssSelectors = cssSelectors;
	}

	public void addCssSelectors(WebObjectProperty cssSelector) {
		this.cssSelectors.add(cssSelector);
	}

	public List<WebObjectProperty> getXpaths() {
		return xpaths;
	}

	public void setXpaths(List<WebObjectProperty> xpaths) {
		this.xpaths = xpaths;
	}

	public void addXpaths(WebObjectProperty xpath) {
		this.xpaths.add(xpath);
	}

	public WebObjectProperty getClassName() {
		return className;
	}

	public void setClassName(WebObjectProperty className) {
		this.className = className;
	}

	public WebObject getParentObject() {
		return parentObject;
	}

	public void setParentObject(WebObject parentObject, MetaType metaType) {
		this.parentObject = parentObject;
		parentObject.metaType = new WebObjectProperty("MetaType", metaType.toString(), true, false);
	}

	@Override
	public String toString() {
		return "MetaType: " + metaType + System.lineSeparator() + "Xpath: " + xpaths + System.lineSeparator() + "parentObject: " + parentObject
				+ System.lineSeparator();

	}

	/*- ******************  GETTER and SETTER end  ***************** */

	/*- public WebObject() {
	
		this.id= new WebObjectProperty("id", "", isUsed, isRegex)
	
	}*/

}
