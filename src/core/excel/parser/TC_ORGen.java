package core.excel.parser;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

import core.excel.utility.Excel;
import core.excel.utility.ExcelMap;
import core.excel.utility.core.ExcelCell;
import core.excel.utility.core.ExcelColumn;
import core.excel.utility.core.ExcelRow;

public class TC_ORGen {

	enum TC_ORColumnHeader {

		OBJECT_NAME, JSON;
	}

	private static final String EXCEL_PATH = "D:\\Soft\\workspace\\plugin\\other\\Keywords.xlsx";
	private static final String KEYWORD_SHEET_NAME = "#TC1_OR";
	private static final String EXCEL_REF = "MainExcel";

	private String objectName;
	private String json;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public void generate(String objectName) throws InvalidFormatException, IOException {

		Excel getExcel = new Excel();
		getExcel.openExcel(EXCEL_REF, EXCEL_PATH, true);
		getExcel.initializeExcel(EXCEL_REF, KEYWORD_SHEET_NAME);

		Sheet sh = ExcelMap.getMap().get(EXCEL_REF).getSh();
		ExcelColumn colObj = new ExcelColumn(sh);

		int objectNameColNum = colObj.getColumnNum(0, TC_ORColumnHeader.OBJECT_NAME.toString(), 0);
		int jsonColNum = colObj.getColumnNum(0, TC_ORColumnHeader.JSON.toString(), 0);

		ExcelRow rowObj = new ExcelRow(sh);
		int rowNum = rowObj.getRowNum(objectNameColNum, objectName, 0);

		setObjectName(new ExcelCell(sh).getCellValue(rowNum, objectNameColNum));
		setJson(new ExcelCell(sh).getCellValue(rowNum, jsonColNum));

	}

	@Override
	public String toString() {
		return "{ objectName: " + objectName + ", " + "json: " + json + " }";
	}

	public static void main(String[] args) throws InvalidFormatException, IOException {
		TC_ORGen obj = new TC_ORGen();
		obj.generate("Object2");
		System.out.println(obj);
	}

}
