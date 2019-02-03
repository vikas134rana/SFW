package keywords.main.selenium.table_adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

public class TableByQuery {

	private TableAdapter tableAdapter;
	private List<String> columnList = new ArrayList<>();
	private List<String> valueList = new ArrayList<>();
	private Map<String, String> columnMap = new HashMap<>();
	private List<Integer> columnIndexList = new ArrayList<>();
	private List<List<Integer>> matchingRowIndexesList = new ArrayList<List<Integer>>();
	private List<Integer> commonRowList = new ArrayList<>();
	private int rowIndex;
	private int colIndex;

	public WebElement getCell(WebElement tableEle, String colName, String column1, String value1, String column2, String value2, String column3, String value3,
			String column4, String value4, String column5, String value5) {

		tableAdapter = new TableAdapter(tableEle);
		columnList = Arrays.asList(column1, column2, column3, column4, column5);
		valueList = Arrays.asList(value1, value2, value3, value4, value5);

		colIndex = tableAdapter.getColumnIndex(colName);

		setColumnMap();
		setColumnIndexList();
		setMatchingRowIndexesList();

		List<Integer> commonRowList = getCommonMatchingRowIndex();
		rowIndex = commonRowList.get(0);

		System.out.println(toString());

		return tableAdapter.getCell(rowIndex, colIndex);
	}

	// set usable Column Name and Values Map
	private void setColumnMap() {

		for (int i = 0; i < columnList.size(); i++) {

			String column = columnList.get(i);
			String value = valueList.get(i);

			if (!column.trim().isEmpty())
				columnMap.put(column, value);
		}

		if (columnMap.isEmpty())
			throw new Error("Atleast 1 column-value pair is required for Query to work.");
	}

	// set column index in a list using columnName from columnMap
	private void setColumnIndexList() {

		/*- for (String key : columnMap.keySet())
			 columnIndexList.add(tableAdapter.getColumnIndex(key));*/

		columnIndexList = columnMap.keySet().stream().map(i -> tableAdapter.getColumnIndex(i)).collect(Collectors.toList());
	}

	private void setMatchingRowIndexesList() {

		for (Entry<String, String> entry : columnMap.entrySet()) {

			String column = entry.getKey();
			String value = entry.getValue();

			int colIndex = tableAdapter.getColumnIndex(column);
			List<String> columnTextList = tableAdapter.getColumnTextList(colIndex);
			List<Integer> matchingRowIndexList = new ArrayList<>();

			for (int i = 1; i < columnTextList.size(); i++) {
				if (columnTextList.get(i).equals(value)) {
					matchingRowIndexList.add(i);
				}
			}

			if (matchingRowIndexList.isEmpty())
				throw new Error("No Row Match with the Query(ColumnName<" + column + "> | Value<" + value + ">)");

			matchingRowIndexesList.add(matchingRowIndexList);
		}

		if (matchingRowIndexesList.isEmpty())
			throw new Error("No Row Match with the Query(ColumnName/Value).");
	}

	private List<Integer> getCommonMatchingRowIndex() {

		commonRowList.addAll(matchingRowIndexesList.get(0));

		for (int i = 0; i < matchingRowIndexesList.size(); i++) {
			List<Integer> list = matchingRowIndexesList.get(i);
			commonRowList.retainAll(list);
		}

		if (commonRowList.isEmpty())
			throw new Error("No Common Row found with the Query " + columnMap + " | MatchingRowIndex : " + matchingRowIndexesList);

		return commonRowList;
	}

	@Override
	public String toString() {

		// @formatter:off
		return 	"Column Map : " + columnMap +"\t"+
				"ColumnIndexList : " + columnIndexList+"\t"+
				"MatchingRowIndexesList : " + matchingRowIndexesList+"\t"+
				"CommonRowList : " + commonRowList+"\t"+
				"RowIndex : "+rowIndex+"\t"+
				"ColIndex : "+colIndex+"\t";
		// @formatter:on
	}
}
