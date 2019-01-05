package core.excel.utility;

import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;

public class Utils0 {

	/**
	 * Get values counts separated by delimiter
	 * 
	 * @param text
	 * @param delimiter
	 * @return
	 */
	public static int getValuesCount(String text, String delimiter) {
		return getValues(text, delimiter).length;
	}

	/**
	 * Get values separated by delimiter
	 * 
	 * @param text
	 * @param delimiter
	 * @return
	 */
	public static String[] getValues(String text, String delimiter) {
		return text.split(Pattern.quote(delimiter));
	}

	/**
	 * Delete all sheets in the workbook except the specified sheet
	 * 
	 * @param sheetName
	 * @param wb
	 * @return Workbook (with deleted sheets)
	 */
	public static Workbook deleteSheetExcept(String sheetName, Workbook wb) {
		for (int i = wb.getNumberOfSheets() - 1; i >= 0; i--) {
			if (!wb.getSheetName(i).contentEquals(sheetName)) {
				wb.removeSheetAt(i);
			}
		}
		return wb;
	}

	public static String getExtension(String path) {
		return FilenameUtils.getExtension(path);
	}

}
