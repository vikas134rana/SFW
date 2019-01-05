package core.excel.utility.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import core.excel.utility.Utils0;

public class ExcelRow extends ExcelCell {

	public ExcelRow(Sheet sh) {
		super(sh);
	}

	/**
	 * Gets Row object using row number
	 * 
	 * @param row
	 * @return
	 */
	public Row getRow(int row) {
		Row rowObj;
		rowObj = sh.getRow(row);
		if (rowObj == null)
			rowObj = sh.createRow(row);
		return rowObj;
	}

	/**
	 * Get non-empty row values separated by given delimiter(by default delimiter is
	 * ; )
	 * 
	 * @param rowObj
	 * @param delimiter
	 * @return
	 */

	/**
	 * Gets List of Cells (All - empty, non-empty cells) object from the specified
	 * row
	 * 
	 * @param rowObj
	 * @return
	 */
	List<Cell> getRowCells(Row rowObj) {
		List<Cell> rowCellsList = new ArrayList<>();
		int lastCell = rowObj.getLastCellNum();
		for (int col = 0; col < lastCell; col++) {
			rowCellsList.add(getCell(rowObj.getRowNum(), col));
		}
		return rowCellsList;
	}

	/**
	 * Gets List of Cells (only non-empty cells) object from the specified row
	 * 
	 * @param rowObj
	 * @return
	 */
	public List<Cell> getNonEmptyRowCells(Row rowObj) {
		List<Cell> cellList = new ArrayList<>();
		rowObj.forEach(cell -> cellList.add(cell));
		return cellList;
	}

	/**
	 * 
	 * @param rowObj
	 * @return
	 */
	List<String> getRowValues(Row rowObj) {
		List<Cell> rowCellsList = getRowCells(rowObj);
		return getCellsValueList(rowCellsList);
	}

	/**
	 * 
	 * @param row
	 * @return
	 */
	List<String> getRowValues(int row) {
		return getRowValues(getRow(row));
	}

	/**
	 * 
	 * @param rowObj
	 * @return
	 */
	List<String> getNonEmptyRowValues(Row rowObj) {
		List<Cell> rowCellsList = getNonEmptyRowCells(rowObj);
		return getCellsValueList(rowCellsList);
	}

	/**
	 * 
	 * @param rowObj
	 * @return
	 */
	List<Cell> getEmptyRowCells(Row rowObj) {
		List<Cell> rowCellsList = new ArrayList<>();
		int lastCell = rowObj.getLastCellNum();
		for (int col = 0; col < lastCell; col++) {
			Cell cell = rowObj.getCell(col);
			if (cell == null || getCellValue(cell).trim().isEmpty())
				rowCellsList.add(cell);
		}
		return rowCellsList;
	}

	/**
	 * Get row values separated by given delimiter(by default delimiter is ; )
	 * 
	 * @param rowObj
	 * @param delimiter
	 * @return
	 */
	public String getRowValues(Row rowObj, String delimiter) {
		if (delimiter.isEmpty())
			delimiter = ";";

		return String.join(delimiter, getRowValues(rowObj));
	}

	/**
	 * 
	 * @param row
	 * @param delimiter
	 * @return
	 */
	public String getRowValues(int row, String delimiter) {
		if (delimiter.isEmpty())
			delimiter = ";";

		return String.join(delimiter, getRowValues(row));
	}

	/**
	 * 
	 * @param rowObj
	 * @param delimiter
	 * @return
	 */
	public String getNonEmptyRowValues(Row rowObj, String delimiter) {
		if (delimiter.isEmpty())
			delimiter = ";";

		return String.join(delimiter, getNonEmptyRowValues(rowObj));
	}

	/**
	 * 
	 * @param rowObj
	 * @param delimiter
	 * @return
	 */
	@Deprecated
	public String getNonEmptyRowValuesOld(Row rowObj, String delimiter) {
		List<String> cellValues = new ArrayList<>();
		if (delimiter.isEmpty())
			delimiter = ";";
		for (Cell c : rowObj) {
			cellValues.add(getCellValue(c));
		}
		return String.join(delimiter, cellValues);
	}

	/**
	 * 
	 * Gets Row number using column, value and index.
	 * 
	 * @param col
	 *            - row number
	 * @param value
	 *            - value
	 * @param index
	 *            - value index (column can contain multiple duplicate values).
	 *            Index starts from 0.
	 * @return row number
	 */
	public int getRowNum(int col, String value, int index) {
		int countIndex = -1;
		int rowNum = -1;
		for (Row r : sh) {
			Cell cell = getCell(r.getRowNum(), col);
			if (getCellValue(cell).trim().equalsIgnoreCase(value.trim()) && (++countIndex) == index) {
				rowNum = r.getRowNum();
			}
		}
		return rowNum;
	}

	/**
	 * Clear Row and Sets Row values
	 * 
	 * @param row
	 *            - row number
	 * @param value
	 *            - value(s)
	 * @param delimiter
	 *            - delimiter to separate value(s)
	 * @return
	 */
	public boolean setRowValues(Row rowObj, String value, String delimiter) {
		try {
			List<Cell> rowCells = getRowCells(rowObj);
			String[] values = Utils0.getValues(value, delimiter);
			int i = -1;
			for (Cell c : rowCells) {
				setCellValue(c, values[++i]);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param rowObj
	 */
	public void clearRow(Row rowObj) {
		sh.removeRow(rowObj);
	}

	/**
	 * Create Row cells in the specified row if rows current cell size is less the n
	 * (cell to be created in row). Cell will be created in row (difference of n and
	 * current cell size in row).<br>
	 * If n=50 (cells to be created) and row already contain 30 cell then cells will
	 * be created from 30 to 50 in that row.
	 * 
	 * @param row
	 * @param n
	 * @return
	 */
	boolean createRowCells(Row rowObj, int n) {
		try {
			int lastCellNum = rowObj.getLastCellNum();
			if (n > lastCellNum) {
				for (int i = lastCellNum; i < n; i++) {
					rowObj.createCell(i);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return Total row count in excel (starts from 1)
	 */
	public int getTotalRowsCount() {
		int lastRow = sh.getLastRowNum();
		while (true) {
			if (!"".equals(getRowValues(getRow(lastRow), " ").trim()))
				break;
			lastRow--;
		}
		return lastRow + 1;
	}

	/**
	 * 
	 * @param row
	 * @return
	 */
	public int getLastCellNum(int row) {
		return getLastCellNum(getRow(row));
	}

	/**
	 * Gets total number of cells in row (starts from 1)
	 * 
	 * @param row
	 *            - row number
	 * @return
	 */
	public int getLastCellNum(Row rowObj) {
		return rowObj.getLastCellNum();
	}

	/**
	 * Gets non-empty cells count in a row
	 * 
	 * @param row
	 *            - row number
	 * @return
	 */
	public int getNonEmptyCellsCount(Row rowObj) {
		return rowObj.getPhysicalNumberOfCells();
	}

	public List<Integer> getRowsNum(int col, String value) {

		List<Integer> rowsList = new ArrayList<>();

		for (Row r : sh) {
			Cell cell = getCell(r.getRowNum(), col);
			if (getCellValue(cell).trim().equalsIgnoreCase(value.trim())) {
				rowsList.add(r.getRowNum());
			}
		}
		return rowsList;
	}

	/*- ******************************************   Delete   ********************************************** */

	public void clearAndSetRowValue(Row rowObj, String value, String delimiter) {
		clearRow(rowObj);
		setRowValues(rowObj, value, delimiter);
	}

	@Deprecated
	public int getRowNum(int col, String value) {
		int rowNum = -1;
		for (Row r : sh) {
			Cell cell = getCell(r.getRowNum(), col);
			if (getCellValue(cell).trim().equalsIgnoreCase(value.trim()))
				rowNum = r.getRowNum();
		}
		return rowNum;
	}

}
