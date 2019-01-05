package core.excel.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

import core.excel.utility.Excel;
import core.excel.utility.ExcelMap;
import core.excel.utility.core.ExcelColumn;
import core.excel.utility.core.ExcelRow;

public class KeywordArgumentGen {

	public enum KeywordArgumentColumn {

		KEYWORD_GUID, ARGUMENT_TYPE, ARGUMENT_INDEX;

	}

	private static final String EXCEL_PATH = "D:\\Soft\\workspace\\plugin\\other\\Keywords.xlsx";
	private static final String KEYWORD_SHEET_NAME = "Keyword_Argument";
	private static final String EXCEL_REF = "MainExcel";

	private String keywordGuid;
	private List<String> argumentTypeList = new ArrayList<>();

	public String getKeywordGuid() {
		return keywordGuid;
	}

	public void setKeywordGuid(String keywordGuid) {
		this.keywordGuid = keywordGuid;
	}

	public List<String> getArgumentTypeList() {
		return argumentTypeList;
	}

	public void setArgumentTypeList(List<String> argumentTypeList) {
		this.argumentTypeList = argumentTypeList;
	}

	public void addToArgumentTypeList(String str) {
		this.argumentTypeList.add(str);
	}

	public void generate(String guid) throws InvalidFormatException, IOException {

		Excel getExcel = new Excel();
		getExcel.openExcel(EXCEL_REF, EXCEL_PATH, true);
		getExcel.initializeExcel(EXCEL_REF, KEYWORD_SHEET_NAME);

		Sheet sh = ExcelMap.getMap().get(EXCEL_REF).getSh();
		ExcelColumn colObj = new ExcelColumn(sh);

		int keywordGuidColNum = colObj.getColumnNum(0, KeywordArgumentColumn.KEYWORD_GUID.toString(), 0);
		int argumentTypeColNum = colObj.getColumnNum(0, KeywordArgumentColumn.ARGUMENT_TYPE.toString(), 0);

		ExcelRow rowObj = new ExcelRow(sh);
		List<Integer> rowsNumList = rowObj.getRowsNum(keywordGuidColNum, guid);

		setKeywordGuid(guid);

		argumentTypeList = rowsNumList.stream().map(i -> rowObj.getCellValue(i, argumentTypeColNum)).collect(Collectors.toList());
		setArgumentTypeList(argumentTypeList);

	}

	@Override
	public String toString() {
		return "{ keywordGuid: " + keywordGuid + ", " + "argumentTypeList: " + argumentTypeList + " }";
	}

	public static void main(String[] args) throws InvalidFormatException, IOException {
		KeywordArgumentGen keywordArgObj = new KeywordArgumentGen();
		keywordArgObj.generate("31c82731-bd14-470d-a126-c86c26131098");
		System.out.println(keywordArgObj);
	}
}
