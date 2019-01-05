package core.excel.parser;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

import core.excel.utility.Excel;
import core.excel.utility.ExcelMap;
import core.excel.utility.core.ExcelCell;
import core.excel.utility.core.ExcelColumn;
import core.excel.utility.core.ExcelRow;

public class KeywordGen {

	public enum KeywordColumn {

		KEYWORD_NAME, METHOD_NAME, KEYWORD_GUID;

	}

	private static final String EXCEL_PATH = "D:\\Soft\\workspace\\plugin\\other\\Keywords.xlsx";
	private static final String KEYWORD_SHEET_NAME = "Keyword";
	private static final String EXCEL_REF = "MainExcel";

	private String keywordName;
	private String methodName;
	private String keywordGuid;

	public String getKeywordName() {
		return keywordName;
	}

	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getKeywordGuid() {
		return keywordGuid;
	}

	public void setKeywordGuid(String keywordGuid) {
		this.keywordGuid = keywordGuid;
	}

	public void generate(String keywordName) throws InvalidFormatException, IOException {

		Excel getExcel = new Excel();
		getExcel.openExcel(EXCEL_REF, EXCEL_PATH, true);
		getExcel.initializeExcel(EXCEL_REF, KEYWORD_SHEET_NAME);

		Sheet sh = ExcelMap.getMap().get(EXCEL_REF).getSh();
		ExcelColumn colObj = new ExcelColumn(sh);

		int keywordNameColNum = colObj.getColumnNum(0, KeywordColumn.KEYWORD_NAME.toString(), 0);
		int methodNameColNum = colObj.getColumnNum(0, KeywordColumn.METHOD_NAME.toString(), 0);
		int keywordGuidColNum = colObj.getColumnNum(0, KeywordColumn.KEYWORD_GUID.toString(), 0);

		ExcelRow rowObj = new ExcelRow(sh);
		int rowNum = rowObj.getRowNum(keywordNameColNum, keywordName, 0);

		setKeywordName(keywordName);
		setMethodName(new ExcelCell(sh).getCellValue(rowNum, methodNameColNum));
		setKeywordGuid(new ExcelCell(sh).getCellValue(rowNum, keywordGuidColNum));

	}

	@Override
	public String toString() {
		return "{ KeywordName: " + keywordName + ", " + "MethodName: " + methodName + ", " + "GuidName: " + keywordGuid + " }";
	}

	public static void main(String[] args) throws InvalidFormatException, IOException {
		KeywordGen keywordObj = new KeywordGen();
		keywordObj.generate("Web_Click");
		System.out.println(keywordObj);
	}

}
