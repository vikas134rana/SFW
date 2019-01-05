package core.excel.utility.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import core.excel.utility.ExcelSheet;
import core.excel.utility.Utils0;

public class ExcelColumn extends ExcelCell {

	public ExcelColumn(Sheet sh) {
		super(sh);
	}

	/**
	 * Gets Column values separated by delimiter (by default delimiter ;) using
	 * column number
	 * 
	 * @param col
	 * @param delimiter
	 * @return
	 */
	public String getColumnValues(int col, String delimiter) {
		List<String> columnValues = new ArrayList<>();
		if (delimiter.isEmpty())
			delimiter = ";";
		for (Row r : sh) {
			Cell cell = getCell(r.getRowNum(), col);
			columnValues.add(getCellValue(cell));
		}
		return String.join(delimiter, columnValues);
	}

	/**
	 * Gets Column number using row, value and index.
	 * 
	 * @param row
	 *            - row number
	 * @param value
	 *            - value
	 * @param index
	 *            - value index (row can contain multiple duplicate values)
	 * @return column number
	 */
	public int getColumnNum(int row, String value, int index) {
		int countIndex = -1;
		int colNum = -1;
		Row rowObj = new ExcelRow(sh).getRow(row);
		for (Cell c : rowObj) {
			if (getCellValue(c).trim().equalsIgnoreCase(value.trim()) && (++countIndex) == index) {
				colNum = c.getColumnIndex();
			}
		}
		return colNum;
	}

	public List<Integer> getColumnsNum(int row, String value) {

		List<Integer> columnsNumList = new ArrayList<>();

		Row rowObj = new ExcelRow(sh).getRow(row);
		for (Cell c : rowObj) {
			if (getCellValue(c).trim().equalsIgnoreCase(value.trim())) {
				columnsNumList.add(c.getColumnIndex());
			}
		}
		return columnsNumList;
	}

	/**
	 * Sets Column values
	 * 
	 * @param col
	 *            - column number
	 * @param value
	 *            - value(s)
	 * @param delimiter
	 *            - delimiter to separate value(s)
	 * @return
	 */
	public boolean setColumnValue(int col, String value, String delimiter) {
		try {
			List<Cell> colCells = getColumnCells(col);
			String[] values = Utils0.getValues(value, delimiter);
			// createColumnCells(col, values.length);
			int i = -1;
			for (Cell c : colCells) {
				setCellValue(c, values[++i]);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Create Column cells in the specified Column if Column current cell size is
	 * less the n (cell to be created in Column). Cell will be created in Column
	 * (difference of n and current cell size in Column).<br>
	 * If n=50 (cells to be created) and Column already contain 30 cell then cells
	 * will be created from 30 to 50 in that Column.
	 * 
	 * @param col
	 * @param n
	 * @return
	 */
	boolean createColumnCells(int col, int n) {
		try {
			int lastRowNum = sh.getLastRowNum();
			// n = new last row
			if (n > lastRowNum) {
				for (int i = lastRowNum; i < n; i++) {
					Row rowObj = new ExcelRow(sh).getRow(i);
					rowObj.createCell(col);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Gets List of Cells object from the specified column
	 * 
	 * @param col
	 * @return
	 */
	List<Cell> getColumnCells(int col) {
		List<Cell> cellList = new ArrayList<>();
		for (Row r : sh) {
			Cell cell = getCell(r.getRowNum(), col);
			cellList.add(cell);
		}
		return cellList;
	}

	/**
	 * 
	 * @return Gets Last column in excel (starts with 1)
	 */
	public int getLastColNum() {
		return new ExcelSheet().getSheetEndColumn(sh) + 1;
	}

	@Deprecated
	public int getColumnNum(int row, String value) {
		int colNum = -1;
		Row rowObj = new ExcelRow(sh).getRow(row);
		for (Cell c : rowObj) {
			if (getCellValue(c).trim().equalsIgnoreCase(value.trim()))
				colNum = c.getColumnIndex();
		}
		return colNum;
	}

}
