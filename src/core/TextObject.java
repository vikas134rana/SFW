package core;

public class TextObject extends WebObject {

	private WebObjectProperty textToSearch = new WebObjectProperty();
	private WebObjectProperty index = new WebObjectProperty();
	private WebObjectProperty partial = new WebObjectProperty();
	private WebObjectProperty beforeText = new WebObjectProperty();
	private WebObjectProperty afterText = new WebObjectProperty();

	public TextObject() {
		// TODO Auto-generated constructor stub
	}

	public TextObject(String textToSearch, int index, boolean partial, String beforeText, String afterText) {

		this.textToSearch = new WebObjectProperty("textToSearch", textToSearch);
		this.index = new WebObjectProperty("index", String.valueOf(index));
		this.partial = new WebObjectProperty("partial", String.valueOf(partial));
		this.beforeText = new WebObjectProperty("beforeText", beforeText);
		this.afterText = new WebObjectProperty("afterText", afterText);
	}

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
