package core;

public class TextObject extends WebObject {

	private WebObjectProperty textToSearch = new WebObjectProperty();
	private WebObjectProperty index = new WebObjectProperty();
	private WebObjectProperty partial = new WebObjectProperty();
	private WebObjectProperty beforeText = new WebObjectProperty();
	private WebObjectProperty afterText = new WebObjectProperty();

	public WebObjectProperty getTextToSearch() {
		return textToSearch;
	}

	public void setTextToSearch(WebObjectProperty textToSearch) {
		this.textToSearch = textToSearch;
	}

	public WebObjectProperty getIndex() {
		return index;
	}

	public void setIndex(WebObjectProperty index) {
		this.index = index;
	}

	public WebObjectProperty getPartial() {
		return partial;
	}

	public void setPartial(WebObjectProperty partial) {
		this.partial = partial;
	}

	public WebObjectProperty getBeforeText() {
		return beforeText;
	}

	public void setBeforeText(WebObjectProperty beforeText) {
		this.beforeText = beforeText;
	}

	public WebObjectProperty getAfterText() {
		return afterText;
	}

	public void setAfterText(WebObjectProperty afterText) {
		this.afterText = afterText;
	}

}
