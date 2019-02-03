package keywords.main.selenium.table_adapter;

import org.jsoup.nodes.Element;

public class Cell {

	private String text;
	private Element ele;

	public Cell(Element jCellEle) {
		ele = jCellEle;
		text = jCellEle.text();
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Element getEle() {
		return ele;
	}

	public void setEle(Element ele) {
		this.ele = ele;
	}

}
