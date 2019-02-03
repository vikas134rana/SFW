package temp_test;

import org.openqa.selenium.WebElement;

import keywords.main.WebKW;
import keywords.main.selenium.table_adapter.Table;
import keywords.main.selenium.table_adapter.TableByQuery;
import keywords.main.selenium.utils.FinderUtils;

public class TableTest {

	public static void main(String[] args) {

		WebKW.openBrowser("chrome", "file:///D:/Work/EclipseWorkspace/plugin/other/Table.html");

		WebElement tableEle = FinderUtils.findElement("//table");

		long start = System.currentTimeMillis();

		System.out.println("\n******** GET_CELL_TEXT (1,1) *******");
		System.out.println(Table.getCellText(tableEle, 1, 1));

		System.out.println("\n******** GET_TOTAL_ROWS_COUNT *******");
		System.out.println(Table.getRowsCount(tableEle));

		System.out.println("\n******** GET_TOTAL_COLUMN_COUNT *******");
		System.out.println(Table.getColumnsCount(tableEle));

		System.out.println("\n******** GET_ROW_TEXT_LIST (1)*******");
		System.out.println(Table.getRowTextList(tableEle, 1));

		System.out.println("\n******** GET_COLUMN_TEXT_LIST (1) *******");
		System.out.println(Table.getColumnTextList(tableEle, 1));

		System.out.println("\n******** GET_ROW_INDEX (1,Yoshi Tannamuri) *******");
		System.out.println(Table.getRowIndex(tableEle, 1, "Yoshi Tannamuri"));

		System.out.println("\n******** GET_COLUMN_INDEX (4,UK) *******");
		System.out.println(Table.getColumnIndex(tableEle, 4, "UK"));

		/*- ************************************************************* */

		System.out.println("\n******** TYPE_IN_TABLE_CELL *******");
		Table.type(tableEle, 1, 3, 0, "vikas");

		System.out.println("\n******** SELECT_CHECKBOX_IN_TABLE_CELL *******");
		Table.selectCheckbox(tableEle, 2, 3, 1);

		System.out.println("\n******** SELECT_RADIO_IN_TABLE_CELL *******");
		Table.selectRadio(tableEle, 3, 3, 1);

		System.out.println("\n******** SELECT_DROPDOWN_IN_TABLE_CELL *******");
		Table.selectDropdown(tableEle, 4, 3, 0, "UK");

		System.out.println("\n******** CLICK_IN_TABLE_CELL *******");
		Table.click(tableEle, 5, 3);

		System.out.println("TIME :<" + (System.currentTimeMillis() - start) + ">");

		/*- **************************      ByQuery      ********************************** */

		// @formatter:off
		
		System.out.println("\n******** QUERY *******");
		new TableByQuery().getCell(tableEle, "Country", "Company", "Island Trading", "", "", "Contact", "Helen Bennett", "", "", "", "");
		
		System.out.println("\n******** (-ve WrongColumn) QUERY  *******");
		try{new TableByQuery().getCell(tableEle, "Countr", "Company", "Island Trading", "", "", "Contact", "Helen Bennett", "", "", "", "");}catch (Throwable e) {
			System.out.println(e.getMessage());
		};
		
		System.out.println("\n******** (-ve Wrong 1st pair colName) QUERY  *******");
		try{new TableByQuery().getCell(tableEle, "Country", "Compan", "Island Trading", "", "", "Contact", "Helen Bennett", "", "", "", "");}catch (Throwable e) {
			System.out.println(e.getMessage());
		};
		
		System.out.println("\n******** (-ve Wrong 1st pair colValue) QUERY  *******");
		try{new TableByQuery().getCell(tableEle, "Country", "Company", "Island Tradin", "", "", "Contact", "Helen Bennett", "", "", "", "");}catch (Throwable e) {
			System.out.println(e.getMessage());
		};
		
		System.out.println("\n******** (-ve Wrong AnyPair colName) QUERY  *******");
		try{new TableByQuery().getCell(tableEle, "Country", "Compan", "Island Trading", "", "", "Contac", "Helen Bennett", "", "", "", "");}catch (Throwable e) {
			System.out.println(e.getMessage());
		};
		
		System.out.println("\n******** (-ve Wrong AnyPair colValue) QUERY  *******");
		try{new TableByQuery().getCell(tableEle, "Country", "Company", "Island Tradin", "", "", "Contact", "Helen Bennet", "", "", "", "");}catch (Throwable e) {
			System.out.println(e.getMessage());
		};
		
		try{System.out.println("\n******** (-ve Different Rows (NoCommonRows)) QUERY  *******");}catch (Throwable e) {
			System.out.println(e.getMessage());
		};
		try{new TableByQuery().getCell(tableEle, "Country", "Company", "Island Trading", "", "", "Contact", "Francisco Chang", "", "", "", "");}catch (Throwable e) {
			System.out.println(e.getMessage());
		};
		
		// @formatter:on
	}

}
