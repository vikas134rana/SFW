package keywords.main.selenium.table_adapter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TablePrinter {

	List<Row> rowsList;

	public TablePrinter(TableAdapter table) {
		rowsList = table.getRows();
	}

	private int[] colWidths() {
		int cols = -1;
		/*- max column size */
		for (Row row : rowsList)
			cols = Math.max(cols, row.getCells().size());

		int[] widths = new int[cols];

		for (Row row : rowsList) {
			for (int colNum = 0; colNum < row.getCells().size(); colNum++) {
				widths[colNum] = Math.max(widths[colNum], StringUtils.length(row.getCells().get(colNum).getText()) + 3);
			}
		}
		return widths;
	}

	public void print() {
		StringBuilder buf = new StringBuilder();

		int[] colWidths = colWidths();

		for (Row row : rowsList) {
			for (int colNum = 0; colNum < row.getCells().size(); colNum++) {
				buf.append(StringUtils.rightPad(StringUtils.defaultString(row.getCells().get(colNum).getText()), colWidths[colNum]));
				buf.append(' ');
			}
			buf.append('\n');
		}
		System.out.println(buf);
	}

}
