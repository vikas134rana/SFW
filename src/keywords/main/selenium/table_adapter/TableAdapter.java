package keywords.main.selenium.table_adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import keywords.main.Utility;
import keywords.main.selenium.utils.FinderUtils;
import keywords.main.selenium.utils.XpathUtils;

public class TableAdapter {

	private List<Row> rows = new ArrayList<>();
	private int COLUMN_COUNT;

	public TableAdapter(WebElement tableEle) {

		if (tableEle == null)
			throw new Error("TableEle is null");

		if (!tableEle.getTagName().equalsIgnoreCase("table"))
			throw new Error("TableEle is null");

		String tableOuterHtml = tableEle.getAttribute("outerHTML");

//		String tableOuterHtml = complexTableHTML();
		Document doc = Jsoup.parse(tableOuterHtml);
		Element jTable = doc.select("table").get(0);

		Elements jRows = jTable.select("tr");

		for (Element jRow : jRows) {
			rows.add(new Row(jRow));
		}

		if (rows.isEmpty())
			throw new Error("Table is empty");

		COLUMN_COUNT = rows.get(0).getCells().size();

	}

	public static void main(String[] args) {
//		TableAdapter tableAdapter = new TableAdapter(null);
//		TablePrinter tp = new TablePrinter(tableAdapter);
//		tp.print();

		System.out.println(tableHTML());

	}

	public Element getElementCell(int rowIndex, int colIndex) {

		Row rowObj = getRow(rowIndex);

		Cell cellObj = Utility.getItemAt(rowObj.getCells(), colIndex);
		if (cellObj == null)
			throw new Error("Cell not found with index [Row,Column] - [" + rowIndex + "," + colIndex + "]");

		return cellObj.getEle();
	}

	/**
	 * Find cell(WebElement) using Jsoup CssSelector first. If no element found then
	 * use Xpath to find Cell(WebElement)
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	public WebElement getCell(int rowIndex, int colIndex) {

		Element jCell = getElementCell(rowIndex, colIndex);
		String cellCssSelector = jCell.cssSelector();
		System.out.println("Using CssSelector : " + cellCssSelector);
		WebElement cellEle = FinderUtils.findElement(By.cssSelector(cellCssSelector));

		String cellXpath = "";

		if (cellEle == null) {
			cellXpath = XpathUtils.createXpath(jCell);
			System.out.println("Using Xpath : " + cellXpath);
			cellEle = FinderUtils.findElement(cellXpath);
		}

		if (cellEle == null)
			throw new Error("Cell Element not found with index [Row,Column] - [" + rowIndex + "," + colIndex + "]");

		return cellEle;
	}

	public Row getRow(int rowIndex) {
		Row rowObj = Utility.getItemAt(rows, rowIndex);
		if (rowObj == null)
			throw new Error("Row not found with Row Index : " + rowIndex);
		return rowObj;
	}

	public List<Cell> getColumn(int colIndex) {

		if (colIndex >= COLUMN_COUNT)
			throw new Error("Given ColumnIndex is " + colIndex + ". Max ColumnIndex for given Table can be " + (COLUMN_COUNT - 1));

		List<Cell> columns = new ArrayList<>();

		for (Row row : rows) {
			columns.add(row.getCells().get(colIndex));
		}

		return columns;
	}

	public List<Row> getRows() {
		return new ArrayList<Row>(rows);
	}

	public List<String> getRowTextList(int rowIndex) {
		Row rowObj = getRow(rowIndex);
		return rowObj.getCells().stream().map(cell -> cell.getText()).collect(Collectors.toList());
	}
	
	public List<String> getColumnTextList(int colIndex) {
		return getColumn(colIndex).stream().map(cell -> cell.getText()).collect(Collectors.toList());
	}

	
	public int getColumnIndex(String columnName) {
		
		List<String> columnHeaders = getRowTextList(0);
		int colIndex = columnHeaders.indexOf(columnName);
		
		if(colIndex==-1)
			throw new Error("ColumnName<"+columnName+"> not found");
		
		return colIndex;
	}

	static String tableHTML() {

		return "<table id=\"customers\">\r\n" + "  <tbody><tr>\r\n" + "    <th>Company</th>\r\n" + "    <th>Contact</th>\r\n" + "    <th>Country</th>\r\n"
				+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Alfreds Futterkiste</td>\r\n" + "    <td>Maria Anders</td>\r\n" + "    <td>Germany</td>\r\n"
				+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Centro comercial Moctezuma</td>\r\n" + "    <td>Francisco Chang</td>\r\n" + "    <td>Mexico</td>\r\n"
				+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Ernst Handel</td>\r\n" + "    <td>Roland Mendel</td>\r\n" + "    <td>Austria</td>\r\n" + "  </tr>\r\n"
				+ "  <tr>\r\n" + "    <td>Island Trading</td>\r\n" + "    <td>Helen Bennett</td>\r\n" + "    <td>UK</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n"
				+ "    <td>Laughing Bacchus Winecellars</td>\r\n" + "    <td>Yoshi Tannamuri</td>\r\n" + "    <td>Canada</td>\r\n" + "  </tr>\r\n"
				+ "  <tr>\r\n" + "    <td>Magazzini Alimentari Riuniti</td>\r\n" + "    <td>Giovanni Rovelli</td>\r\n" + "    <td>Italy</td>\r\n"
				+ "  </tr>\r\n" + "</tbody></table>";

	}

	String complexTableHTML() {
		return "<table border=\"1\">\r\n" + "\r\n" + "<!-- First row -->\r\n" + "\r\n" + "<tr>\r\n" + "<td>1</td>\r\n" + "<td colspan=\"2\">2 and 3</td>\r\n"
				+ "<td>4</td>\r\n" + "</tr>\r\n" + "\r\n" + "<!-- Second row -->\r\n" + "\r\n" + "<tr>\r\n" + "<td rowspan=\"3\">5, 9 and 13</td>\r\n"
				+ "<td>6</td>\r\n" + "<td>7</td>\r\n" + "<td>8</td>\r\n" + "</tr>\r\n" + "\r\n" + "<!-- Third row -->\r\n" + "\r\n" + "<tr>\r\n"
				+ "<td>10</td>\r\n" + "<td>11</td>\r\n" + "<td>12</td>\r\n" + "</tr>\r\n" + "\r\n" + "<!-- Fourth row -->\r\n" + "\r\n" + "<tr>\r\n"
				+ "<td colspan=\"3\">14, 15 and 16</td>\r\n" + "</tr>\r\n" + "\r\n" + "</table>";
	}

}
