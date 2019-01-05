package core.excel.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	Logger logger = Logger.getLogger(Excel.class.getName());

	/*- private static Map<String, ExcelObject> map = new HashMap<>();
	
	
	public int mapSize() {
		return map.size();
	}
	
	public static Map<String, ExcelObject> getMap() {
		return map;
	}
	
	public void setMap(Map<String, ExcelObject> map) {
		Excel.map = map;
	}
	
	public ExcelObject getExcelObject(String key) {
		return map.get(key);
	}
	
	public void setExcelObjectToMap(String key, ExcelObject excelObject) {
		map.put(key, excelObject);
		System.out.println("mapSize: " + mapSize());
	}*/

	/**
	 * The excel reference will be mapped with excel path and later this excel can
	 * be used using reference (Remembering reference is convenient as compared to
	 * remember excel path).
	 * <p>
	 * Workbook and Sheet object are not set in OpenExcel
	 * 
	 * @param reference
	 *            - excel reference (key).
	 * @param excelPath
	 *            - full excel path (with extension)
	 * @throws IOException
	 *             -
	 * @throws InvalidFormatException
	 *             - if specified excel is not valid excel (extension should be xls
	 *             or xlsm)
	 */
	public void openExcel(String reference, String excelPath) throws IOException, InvalidFormatException {

		openExcel(reference, excelPath, false);
	}

	public void openExcel(String reference, String excelPath, boolean refOverwrite) throws IOException, InvalidFormatException {

		if (ExcelMap.getMap().containsKey(reference) && !refOverwrite)
			throw new IllegalArgumentException("Specified excel reference is already opened.");

		if (!("xls".equals(getExtension(excelPath)) || "xlsm".equals(getExtension(excelPath)) || "xlsx".equals(getExtension(excelPath))))
			throw new IllegalArgumentException("Check specified excel path (file extension should be one of xls,xlsx or xlsm)");

		ExcelObject excelObject = new ExcelObject(excelPath, reference);
		// System.out.println(excelObject);
		ExcelMap.addExcel(reference, excelObject);
	}

	/**
	 * Sets Workbook and Sheet object in excel object using excel reference (that
	 * was opened already)
	 * 
	 * @param reference
	 *            - excel reference (key)
	 * @param sheetName
	 *            - sheet name of excel file
	 * @throws FileNotFoundException
	 *             - excel file not found
	 * @throws IOException
	 *             - specified excel is not opened or already closed
	 * @throws InvalidFormatException
	 */
	public void initializeExcel(String reference, String sheetName) throws FileNotFoundException, IOException, InvalidFormatException {
		Workbook wb = null;
		ExcelObject excelObject = ExcelMap.getExcelObject(reference);

		if (excelObject == null)
			throw new IllegalArgumentException("Specified Excel is not opened or already closed");

		String excelPath = excelObject.getExcelPath();

		if ("xls".equals(excelObject.getExtension())) {
			wb = new HSSFWorkbook(new FileInputStream(excelPath));
		} else if ("xlsx".equals(excelObject.getExtension()))
			wb = new XSSFWorkbook(new File(excelPath));

		excelObject.setWb(wb);
		excelObject.setSh(excelObject.getWb().getSheet(sheetName));
//		System.out.println(excelObject);
		ExcelMap.addExcel(reference, excelObject);
	}

	/**
	 * write changes (cell value modified, cell color modified etc) to excel file
	 * 
	 * @param wb
	 *            - workbook object
	 * @param filePath
	 *            - excel file full path to be written
	 * @return
	 */
	public boolean writeExcel(Workbook wb, String filePath) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			wb.write(fos);
			wb.close();
			fos.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
		return true;
	}

	/**
	 * clears all open excel i.e. clears excel map(Workbook and Sheet object) - To
	 * save memory because large excel file takes too much memory
	 */
	public void clearWbSh() {
		for (Map.Entry<String, ExcelObject> entry : ExcelMap.getMap().entrySet()) {
			String key = entry.getKey();
			ExcelObject excelObject = entry.getValue();
			excelObject.setWb(null);
			excelObject.setSh(null);
			ExcelMap.addExcel(key, excelObject);
		}
	}

	public String getExtension(String excelPath) {
		return FilenameUtils.getExtension(excelPath);
	}
}
