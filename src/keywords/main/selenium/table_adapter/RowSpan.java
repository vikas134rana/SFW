package keywords.main.selenium.table_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;

public class RowSpan {

	public static List<RowSpan> rowspanList = new ArrayList<>();
	public static Map<Integer, Integer> rowIndexAndCurrentRowspanMap = new HashMap<>();

	private int rowIndex;
	private int rowspan;
	private Element jCell;

	public RowSpan(int rowIndex, int rowspan, Element jCell) {
		this.rowIndex = rowIndex;
		this.rowspan = rowspan;
		this.jCell = jCell;
		rowIndexAndCurrentRowspanMap.put(rowIndex, rowspan);
	}

	public static List<RowSpan> getRowspanList() {
		return rowspanList;
	}

	public static void setRowspanList(List<RowSpan> rowspanList) {
		RowSpan.rowspanList = rowspanList;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspanValue) {
		this.rowspan = rowspanValue;
	}

	public Element getjCell() {
		return jCell;
	}

	public void setjCell(Element jCell) {
		this.jCell = jCell;
	}

	public static void add(RowSpan rowspanObj) {
		rowspanList.add(rowspanObj);
	}

	public static RowSpan getCurrentRowSpanObj(int rowIndex) {
		RowSpan rowspanObj = rowspanList.stream().filter(i -> i.getRowIndex() == rowIndex).findFirst().orElse(null);
		return rowspanObj;
	}

	public static int getAndDecreaseRowspanValue(int rowIndex) {
		
		Integer currentRowspanValue = rowIndexAndCurrentRowspanMap.get(rowIndex);

		if (currentRowspanValue == null)
			return -1;

		rowIndexAndCurrentRowspanMap.put(rowIndex, --currentRowspanValue);

		return currentRowspanValue;
	}

}
