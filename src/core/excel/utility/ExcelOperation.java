package core.excel.utility;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelOperation {

	public static final String EXCEL_50K = "C:\\Users\\vikas.rana\\Downloads\\50k.xls";

	public static void main(String[] args) throws InvalidFormatException, IOException, InterruptedException {

		Excel getExcel = new Excel();

		// getExcel.openExcel("Excel1",
		// "C:\\Users\\vikas.rana\\Desktop\\Notes\\Excel\\Get.xlsx");
		getExcel.openExcel("Excel1", EXCEL_50K);

		long start = System.currentTimeMillis();
		getExcel.initializeExcel("Excel1", "Sample-spreadsheet-file");
		System.out.println("initializeExcel Time : " + (System.currentTimeMillis() - start) + " ms");

		System.out.println(ExcelMap.getMap());

		Set<String> keys = ExcelMap.getMap().keySet();

		for (String key : keys) {
			System.out.println("key : " + key);
			System.out.println(ExcelMap.getMap().get(key));
		}

		Sheet sh = ExcelMap.getMap().get("Excel1").getSh();

		/*-	System.out.println("Sheet : " + sh);
		
			- CELL 
			System.out.println("*******************  GetCellValue(1,1)  *************************");
			System.out.println(new ExcelCell(sh).getCellValue(1, 1));
		
			System.out.println("*******************  GetCellValue(10,10)  *************************");
			System.out.println(new ExcelCell(sh).getCellValue(10, 10));
		
			System.out.println("*******************  GetRowColNum(33)  *************************");
			System.out.println(new ExcelCell(sh).getRowColNum("33", 0));
		
			System.out.println("*******************  GetRowColNum(33)  *************************");
			System.out.println(new ExcelCell(sh).getRowColNum("33", 1));
		
			- ROW 
			System.out.println("*******************  GetRowValues(2) Non-Empty *************************");
			System.out.println(new ExcelRow(sh).getRowValues(2, "%"));
			System.out.println("*******************  GeRowValues(10)  *************************");
			System.out.println(new ExcelRow(sh).getRowValues(10, "%"));
		
			System.out.println("*******************  GetRowNum(43)  *************************");
			System.out.println(new ExcelRow(sh).getRowNum(2, "43", 0));
			System.out.println("*******************  GetRowNum(43)  *************************");
			System.out.println(new ExcelRow(sh).getRowNum(4, "43", 0));
		
			System.out.println("*******************  GetLastRowNum()  *************************");
			System.out.println(new ExcelRow(sh).getTotalRowsCount());
		
			System.out.println("*******************  GetLastCellNum(2)  *************************");
			System.out.println(new ExcelRow(sh).getLastCellNum(2));
		
			- COLUMNS 
			System.out.println("*******************  GetColumnValues(1) Contains Empty also  *************************");
			System.out.println(new ExcelColumn(sh).getColumnValues(1, "%"));
		
			System.out.println("*******************  GetColumnValues(10)  *************************");
			System.out.println(new ExcelColumn(sh).getColumnValues(10, "%"));
		
			System.out.println("*******************  GetColumnNum(43)  *************************");
			System.out.println(new ExcelColumn(sh).getColumnNum(3, "43", 0));
		
			System.out.println("*******************  GetColumnNum(43)  *************************");
			System.out.println(new ExcelColumn(sh).getColumnNum(4, "43", 0));
		
			System.out.println("*******************  GetLastColNum  *************************");
			System.out.println(new ExcelColumn(sh).getLastColNum());
		
			- SHEET 
			System.out.println("*******************  getSheetStartRow  *************************");
			System.out.println(new ExcelSheet().getSheetStartRow(sh));
		
			System.out.println("*******************  getSheetStartColumn  *************************");
			System.out.println(new ExcelSheet().getSheetStartColumn(sh));
		
			System.out.println("*******************  getSheetEndRow  *************************");
			System.out.println(new ExcelSheet().getSheetEndRow(sh));
		
			System.out.println("*******************  getSheetEndColumn  *************************");
			System.out.println(new ExcelSheet().getSheetEndColumn(sh)); */
	}

}

interface Function {

	public void run();

}

abstract class TimeCal {

	abstract public void innerRun();

	public long calculate() {
		long start = System.currentTimeMillis();
		innerRun();
		return System.currentTimeMillis() - start;
	}

}