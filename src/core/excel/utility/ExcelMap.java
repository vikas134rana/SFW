package core.excel.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ExcelMap {

	static Logger logger = Logger.getLogger(ExcelObject.class.getName());

	private static Map<String, ExcelObject> excelMap = new HashMap<String, ExcelObject>();

	public static int mapSize() {
		return excelMap.size();
	}

	public static Map<String, ExcelObject> getMap() {
		return excelMap;
	}

	public static void setMap(Map<String, ExcelObject> map) {
		ExcelMap.excelMap = map;
	}

	public static ExcelObject getExcelObject(String key) {
		return excelMap.get(key);
	}

	public static void addExcel(String key, ExcelObject excelObject) {
		excelMap.put(key, excelObject);
	}

	/**
	 * removes the excel object from map based on excel reference (key) specified
	 * 
	 * @param key
	 *            - excel reference
	 * @return - true if excel reference (key) is present in map otherwise false
	 */
	public boolean removeExcelObject(String key) {
		return excelMap.remove(key) == null ? false : true;
	}

	public static void printMap() {
		for (Map.Entry<String, ExcelObject> entry : excelMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().getExcelPath();
			String msg = key + " " + value;
			logger.fine(msg);
		}
	}

}
