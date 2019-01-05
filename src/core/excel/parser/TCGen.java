package core.excel.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

import core.excel.utility.Excel;
import core.excel.utility.ExcelMap;
import core.excel.utility.core.ExcelCell;
import core.excel.utility.core.ExcelColumn;

public class TCGen {

	enum TCColumnHeader {

		KEYWORD_NAME, ARGUMENTS;
	}

	private static final String EXCEL_PATH = "D:\\Soft\\workspace\\plugin\\other\\Keywords.xlsx";
	private static final String KEYWORD_SHEET_NAME = "#TC1";
	private static final String EXCEL_REF = "MainExcel";

	private String keywordName;
	private List<String> argumentsValue = new ArrayList<>();
	private int lastRow;
	
	private Sheet sh;
	
	public TCGen() throws FileNotFoundException, InvalidFormatException, IOException {

		Excel getExcel = new Excel();
		getExcel.openExcel(EXCEL_REF, EXCEL_PATH, true);
		getExcel.initializeExcel(EXCEL_REF, KEYWORD_SHEET_NAME);

		Sheet sh = ExcelMap.getMap().get(EXCEL_REF).getSh();
		
		setLastRow(sh.getLastRowNum());
		
	}

	public String getKeywordName() {
		return keywordName;
	}

	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	public List<String> getArgumentsValue() {
		return argumentsValue;
	}

	public void setArgumentsValue(List<String> argumentsValue) {
		this.argumentsValue = argumentsValue;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public void generate(int rowNum) throws InvalidFormatException, IOException {

		ExcelColumn colObj = new ExcelColumn(sh);

		int keywordNameColNum = colObj.getColumnNum(0, TCColumnHeader.KEYWORD_NAME.toString(), 0);
		List<Integer> argumentsColNumList = colObj.getColumnsNum(0, TCColumnHeader.ARGUMENTS.toString());
		
		setKeywordName(new ExcelCell(sh).getCellValue(rowNum, keywordNameColNum));
		argumentsValue = argumentsColNumList.stream().map(i -> colObj.getCellValue(rowNum, i)).collect(Collectors.toList());
		argumentsValue.removeAll(Arrays.asList("", null));
		setArgumentsValue(argumentsValue);
		

	}

	@Override
	public String toString() {
		return "{ keywordName: " + keywordName + ", " + "argumentsValue: " + argumentsValue + " }";
	}

	public static void main(String[] args) throws InvalidFormatException, IOException {
		TCGen obj = new TCGen();
		obj.generate(1);
		System.out.println(obj);
	}

}
