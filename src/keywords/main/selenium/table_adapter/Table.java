package keywords.main.selenium.table_adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import keywords.core.WebControls;
import keywords.main.selenium.Dropdown;
import keywords.main.selenium.Element;
import keywords.main.selenium.utils.FinderUtils;
import keywords.main.selenium.utils.VerificationUtils;
import keywords.main.selenium.utils.VisibleUtils;

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
		clickHelper(cellEle);
	}

	public static void type(WebElement tableEle, int rowIndex, int colIndex, int index, String textToType) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);

		typeHelper(cellEle, textToType, index);
	}

	public static void selectCheckbox(WebElement tableEle, int rowIndex, int colIndex, int index) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);

		selectCheckboxHelper(cellEle, index);
	}

	public static void selectRadio(WebElement tableEle, int rowIndex, int colIndex, int index) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);

		WebElement radioEle = VisibleUtils.getInstance().getVisibleEleAt(FinderUtils.findElements(cellEle, WebControls.RADIO), index);
		if (radioEle == null)
			throw new Error(WebControls.RADIO + " Element not found");

		Element.getInstance().clickElement(radioEle);
	}

	public static void selectDropdown(WebElement tableEle, int rowIndex, int colIndex, int index, String valueToSelect) {
		TableAdapter tableAdapter = new TableAdapter(tableEle);
		WebElement cellEle = tableAdapter.getCell(rowIndex, colIndex);

		selectDropdownHelper(cellEle, index, valueToSelect);
	}

	/*- ===========================================  NonAction - ByQuery ================================================ */

	public static String getText(WebElement tableEle, String colName, String column1, String value1, String column2, String value2, String column3,
			String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery().getCell(tableEle, colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		return getTextHelper(cellEle);
	}

	public static void verifyText(WebElement tableEle, String colName, String expectedText, String column1, String value1, String column2, String value2,
			String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery().getCell(tableEle, colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		String actualText = getTextHelper(cellEle);

		if (!expectedText.equals(actualText))
			throw new Error("Verification Failed | Actual<" + actualText + "> and Expected<" + expectedText + ">");
	}

	/*- ===========================================  Action - ByQuery ================================================ */

	public static void clickByQuery(WebElement tableEle, String colName, String column1, String value1, String column2, String value2, String column3,
			String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery().getCell(tableEle, colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		clickHelper(cellEle);
	}

	public static void typeByQuery(WebElement tableEle, String colName, int index, String textToType, String column1, String value1, String column2,
			String value2, String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery().getCell(tableEle, colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		typeHelper(cellEle, textToType, index);
	}

	public static void selectCheckboxByQuery(WebElement tableEle, String colName, int index, String column1, String value1, String column2, String value2,
			String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery().getCell(tableEle, colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		selectCheckboxHelper(cellEle, index);
	}

	public static void selectRadioByQuery(WebElement tableEle, String colName, int index, String column1, String value1, String column2, String value2,
			String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery().getCell(tableEle, colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		selectRadioHelper(cellEle, index);
	}

	public static void selectDropdownByQuery(WebElement tableEle, String colName, int index, String textToSelect, String column1, String value1, String column2,
			String value2, String column3, String value3, String column4, String value4, String column5, String value5) {

		WebElement cellEle = new TableByQuery().getCell(tableEle, colName, column1, value1, column2, value2, column3, value3, column4, value4, column5, value5);
		selectDropdownHelper(cellEle, index, textToSelect);
	}

	/*- ===========================================  Helper ================================================ */

	private static String getTextHelper(WebElement cellEle) {

		return cellEle.getText();
	}

	private static void clickHelper(WebElement cellEle) {

		Element.getInstance().clickElement(cellEle);
	}

	private static void typeHelper(WebElement cellEle, String textToType, int index) {
		WebElement typableEle = VisibleUtils.getInstance().getVisibleEleAt(FinderUtils.findElements(cellEle, WebControls.TYPABLE), index);
		if (typableEle == null)
			throw new Error(WebControls.TYPABLE + " Element not found");

		Element.getInstance().typeOnElement(typableEle, textToType);
	}

	private static void selectCheckboxHelper(WebElement cellEle, int index) {
		WebElement checkboxEle = VisibleUtils.getInstance().getVisibleEleAt(FinderUtils.findElements(cellEle, WebControls.CHECKBOX), index);
		if (checkboxEle == null)
			throw new Error(WebControls.CHECKBOX + " Element not found");

		Element.getInstance().clickElement(checkboxEle);
	}

	private static void selectRadioHelper(WebElement cellEle, int index) {
		WebElement radioEle = VisibleUtils.getInstance().getVisibleEleAt(FinderUtils.findElements(cellEle, WebControls.RADIO), index);
		if (radioEle == null)
			throw new Error(WebControls.RADIO + " Element not found");

		Element.getInstance().clickElement(radioEle);
	}

	private static void selectDropdownHelper(WebElement cellEle, int index, String valueToSelect) {
		WebElement dropdownEle = VisibleUtils.getInstance().getVisibleEleAt(FinderUtils.findElements(cellEle, WebControls.DROPDOWN), index);
		if (dropdownEle == null)
			throw new Error(WebControls.DROPDOWN + " Element not found");

		Dropdown.selectByValueOrText(dropdownEle, valueToSelect);
	}
}
