package keywords.main.selenium;

import java.util.List;

import org.openqa.selenium.WebElement;

import keywords.main.selenium.table_adapter.TableAdapter;
import keywords.main.selenium.table_adapter.TableByQuery;

/*- 
 * 1. RowIndex and ColumnIndex starts from 0
 * 2. RowIndex 0 - Table Header Row
 */

public class Table {

	/*- ====================================  Non Action ======================================================== */

	public static String getCellText(WebElement tableEle, int rowIndex, int colIndex) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);
		return cellEle.getText();
	}

	public static void verifyCellText(WebElement tableEle, int rowIndex, int colIndex, String expectedText) {
		String actualText = getCellText(tableEle, rowIndex, colIndex);

		if (!expectedText.equals(actualText))
			throw new Error("Verification Failed | Actual<" + actualText + "> and Expected<" + expectedText + ">");

	}

	public static int getRowsCount(WebElement tableEle) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		return tableAdapter.getRows().size();
	}

	public static int getColumnsCount(WebElement tableEle) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		return tableAdapter.getRow(0).getCells().size();
	}

	public static List<String> getRowTextList(WebElement tableEle, int rowIndex) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		return tableAdapter.getRowTextList(rowIndex);
	}

	public static List<String> getColumnTextList(WebElement tableEle, int colIndex) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		return tableAdapter.getColumnTextList(colIndex);
	}

	public static int getRowIndex(WebElement tableEle, int colIndex, String textToSearch) {
		List<String> columnTextList = getColumnTextList(tableEle, colIndex);
		return columnTextList.indexOf(textToSearch);
	}

	public static int getColumnIndex(WebElement tableEle, int rowIndex, String textToSearch) {
		List<String> rowTextList = getRowTextList(tableEle, rowIndex);
		return rowTextList.indexOf(textToSearch);
	}

	/*- ===========================================  Action ===================================================== */

	public static void click(WebElement tableEle, int rowIndex, int colIndex) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);

		Action.click(cellEle);
	}

	public static void type(WebElement tableEle, int rowIndex, int colIndex, int typableIndex, String textToType) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);

		Action.type(cellEle, textToType, typableIndex);
	}

	public static void selectCheckbox(WebElement tableEle, int rowIndex, int colIndex, int checkboxIndex) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);

		Action.selectCheckbox(cellEle, checkboxIndex);
	}

	public static void selectRadio(WebElement tableEle, int rowIndex, int colIndex, int radioIndex) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);

		Action.selectRadio(cellEle, radioIndex);
	}

	public static void selectDropdown(WebElement tableEle, int rowIndex, int colIndex, int dropdownIndex, String valueToSelect) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);

		Action.selectDropdown(cellEle, dropdownIndex, valueToSelect);
	}

	/*- ===========================================  NonAction - ByQuery ================================================ */

	public static String getText(WebElement tableEle, String colName, String column1, String value1, String column2, String value2, String column3,
			String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery(tableEle).getCell(colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		return getTextHelper(cellEle);
	}

	public static void verifyText(WebElement tableEle, String colName, String expectedText, String column1, String value1, String column2, String value2,
			String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery(tableEle).getCell(colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		String actualText = getTextHelper(cellEle);

		if (!expectedText.equals(actualText))
			throw new Error("Verification Failed | Actual<" + actualText + "> and Expected<" + expectedText + ">");
	}

	/*- ===========================================  Action - ByQuery ================================================ */

	public static void clickByQuery(WebElement tableEle, String colName, String column1, String value1, String column2, String value2, String column3,
			String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery(tableEle).getCell(colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		Action.click(cellEle);
	}

	public static void typeByQuery(WebElement tableEle, String colName, int typableIndex, String textToType, String column1, String value1, String column2,
			String value2, String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery(tableEle).getCell(colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		Action.type(cellEle, textToType, typableIndex);
	}

	public static void selectCheckboxByQuery(WebElement tableEle, String colName, int checkboxIndex, String column1, String value1, String column2,
			String value2, String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery(tableEle).getCell(colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		Action.selectCheckbox(cellEle, checkboxIndex);
	}

	public static void selectRadioByQuery(WebElement tableEle, String colName, int radioIndex, String column1, String value1, String column2, String value2,
			String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery(tableEle).getCell(colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		Action.selectRadio(cellEle, radioIndex);
	}

	public static void selectDropdownByQuery(WebElement tableEle, String colName, int dropdownIndex, String textToSelect, String column1, String value1,
			String column2, String value2, String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery(tableEle).getCell(colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		Action.selectDropdown(cellEle, dropdownIndex, textToSelect);
	}

	/*- ===========================================  Helper ================================================ */

	private static String getTextHelper(WebElement cellEle) {

		return cellEle.getText();
	}

}
