package core.excel.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Utils0copy {

	Sheet sh = null;
	Logger logger = Logger.getLogger(Utils0copy.class.getName());

	public Utils0copy(Sheet sh) {
		this.sh = sh;
	}

	/**
	 * Gets cell value based on row and column (both starts from 0)
	 * 
	 * @param row
	 *            - row number
	 * @param col
	 *            - column number
	 * @return - CellValue (String)
	 */
	public String getCellValue(int row, int col) {
		String cellData = "";
		try {
			Cell cell = getCell(row, col);
			cellData = getCellValue(cell);
		} catch (Exception ex) {
			cellData = "";
		}
		return cellData;
	}

	/**
	 * Gets specified row values separated by specified delimiter(by default
	 * delimiter is ; )
	 * 
	 * @param row
	 *            - row number
	 * @param delimiter
	 *            - to separate value (eg ; )
	 * @return row values separated by delimiter
	 */
	public String getRowValues(int row, String delimiter) {
		Row rowObj = getRow(row);
		return getRowValues(rowObj, delimiter);
	}

	/**
	 * Gets Cell object using row and column number
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public Cell getCell(int row, int col) {
		Cell cell;
		cell = getRow(row).getCell(col);
		if (cell == null)
			cell = sh.getRow(row).createCell(col);
		return cell;
	}

	/**
	 * Gets Cell value using Cell object
	 * 
	 * @param cell
	 * @return
	 */
	public String getCellValue(Cell cell) {
		return new DataFormatter().formatCellValue(cell);
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
	 * Get row values separated by given delimiter(by default delimiter is ; )
	 * 
	 * @param rowObj
	 * @param delimiter
	 * @return
	 */
	public String getRowValues(Row rowObj, String delimiter) {
		List<String> cellValues = new ArrayList<>();
		if (delimiter.isEmpty())
			delimiter = ";";
		for (Cell c : rowObj) {
			cellValues.add(getCellValue(c));
		}
		return String.join(delimiter, cellValues);
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

	// unusual behavior (Last row is sometime greater than actual. May because of
	// some space at lower cell)
	public int getLastRowNum(Sheet sh) {
		return sh.getLastRowNum();
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
		Row rowObj = getRow(row);
		for (Cell c : rowObj) {
			if (getCellValue(c).trim().equalsIgnoreCase(value.trim()) && (++countIndex) == index) {
				colNum = c.getColumnIndex();
			}
		}
		return colNum;
	}

	/**
	 * 
	 * Gets Row number using col, value and index.
	 * 
	 * @param col
	 *            - row number
	 * @param value
	 *            - value
	 * @param index
	 *            - value index (column can contain multiple duplicate values)
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
	 * Get RowNumber and ColumnNumber separated by ','.<br>
	 * Searching value row wise.
	 * 
	 * @param value
	 * @param index
	 * @return
	 */
	public String getRowColNum(String value, int index) {
		int countIndex = -1;
		String rowColNum = "";
		for (Row r : sh) {
			for (Cell c : r) {
				Cell cell = getCell(r.getRowNum(), c.getColumnIndex());
				if (getCellValue(cell).trim().equalsIgnoreCase(value.trim()) && (++countIndex) == index) {
					rowColNum = r.getRowNum() + "," + cell.getColumnIndex();
				}
			}
		}
		return rowColNum;
	}

	/**
	 * Sets Cell value using row, col
	 * 
	 * @param row
	 * @param col
	 * @param value
	 * @return
	 */
	public boolean setCellValue(int row, int col, String value) {
		try {
			Cell cell = getCell(row, col);
			setCellValue(cell, value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Sets Cell value using cell object
	 * 
	 * @param cell
	 * @param value
	 * @return
	 */
	public boolean setCellValue(Cell cell, String value) {
		try {
			cell.setCellValue(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Sets Row values
	 * 
	 * @param row
	 *            - row number
	 * @param value
	 *            - value(s)
	 * @param delimiter
	 *            - delimiter to separate value(s)
	 * @return
	 */
	public boolean setRowValues(int row, String value, String delimiter) {
		try {
			List<Cell> rowCells = getRowCells(row);
			String[] values = getValues(value, delimiter);
			// createRowCells(row, values.length);
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
			String[] values = getValues(value, delimiter);
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
	 * Get values counts separated by delimiter
	 * 
	 * @param text
	 * @param delimiter
	 * @return
	 */
	int getValuesCount(String text, String delimiter) {
		return getValues(text, delimiter).length;
	}

	/**
	 * Get values separated by delimiter
	 * 
	 * @param text
	 * @param delimiter
	 * @return
	 */
	String[] getValues(String text, String delimiter) {
		return text.split(Pattern.quote(delimiter));
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
	boolean createRowCells(int row, int n) {
		try {
			Row rowObj = getRow(row);
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
					Row rowObj = getRow(i);
					rowObj.createCell(col);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// **************************************************************************************

	/**
	 * Delete all sheets in the workbook except the specified sheet
	 * 
	 * @param sheetName
	 * @param wb
	 * @return Workbook (with deleted sheets)
	 */
	public Workbook deleteSheetExcept(String sheetName, Workbook wb) {
		for (int i = wb.getNumberOfSheets() - 1; i >= 0; i--) {
			if (!wb.getSheetName(i).contentEquals(sheetName)) {
				wb.removeSheetAt(i);
			}
		}
		return wb;
	}

	/**
	 * Gets List of Cells object from the specified row
	 * 
	 * @param row
	 * @return
	 */
	List<Cell> getRowCells(int row) {
		List<Cell> cellList = new ArrayList<>();
		Row rowObj = getRow(row);
		for (Cell c : rowObj) {
			cellList.add(c);
		}
		return cellList;
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

	public String getExtension(String path) {
		return FilenameUtils.getExtension(path);
	}

	@Deprecated
	public int getColumnNum(int row, String value) {
		int colNum = -1;
		Row rowObj = getRow(row);
		for (Cell c : rowObj) {
			if (getCellValue(c).trim().equalsIgnoreCase(value.trim()))
				colNum = c.getColumnIndex();
		}
		return colNum;
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
	// **************************************************************************************
}
