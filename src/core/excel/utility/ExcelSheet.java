package core.excel.utility;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelSheet {

	public String getSheetDimension(Sheet sh) {
		return ((XSSFSheet) sh).getCTWorksheet().getDimension().getRef();
	}

	public String getSheetStartDimension(Sheet sh) {
		return getSheetDimension(sh).split(":")[0];
	}

	public String getSheetEndDimension(Sheet sh) {
		return getSheetDimension(sh).split(":")[1];
	}

	public int getSheetStartRow(Sheet sh) {
		return new CellReference(getSheetStartDimension(sh)).getRow();
	}

	public int getSheetStartColumn(Sheet sh) {
		return new CellReference(getSheetStartDimension(sh)).getCol();
	}

	public int getSheetEndRow(Sheet sh) {
		return new CellReference(getSheetEndDimension(sh)).getRow();
	}

	public int getSheetEndColumn(Sheet sh) {
		return new CellReference(getSheetEndDimension(sh)).getCol();
	}

}
