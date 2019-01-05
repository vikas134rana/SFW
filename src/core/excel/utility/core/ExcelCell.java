package core.excel.utility.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.NotImplemented;

public class ExcelCell {

	Sheet sh;

	public ExcelCell(Sheet sh) {
		super();
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
	 * Gets Cell object using row and column number
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public Cell getCell(int row, int col) {
		Cell cell;
		cell = new ExcelRow(sh).getRow(row).getCell(col);
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

	@NotImplemented
	public Cell getCell(String cellReference) {
		CellReference cr = new CellReference("A1");
		Row rowObj = sh.getRow(cr.getRow());
		return rowObj.getCell(cr.getCol());
	}

	public List<String> getCellsValueList(List<Cell> cellsList) {
		List<String> cellsValueList = new ArrayList<>();
		for (Cell cell : cellsList) {
			cellsValueList.add(getCellValue(cell));
		}
		return cellsValueList;
	}
}
