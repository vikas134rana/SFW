package keywords.main.selenium.table_adapter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import core.Data;

public class Row {

	private List<Cell> cells = new ArrayList<Cell>();

	public Row(Element jRowEle) {

		Elements jCells = jRowEle.select("th,td");

		for (int i = 0; i < jCells.size(); i++) {

			Element jCell = jCells.get(i);

			int rowspan = Data.getInt(jCell.attr("rowspan"));
			int colspan = Data.getInt(jCell.attr("colspan"));
			int currentRowspanValue = -1;

			/*- ================================== ROW_SPAN  ========================================== */

			if (rowspan > 1)
				RowSpan.add(new RowSpan(i, rowspan, jCell));
			else
				currentRowspanValue = RowSpan.getAndDecreaseRowspanValue(i);

			if (currentRowspanValue >= 1)
				cells.add(new Cell(RowSpan.getCurrentRowSpanObj(i).getjCell()));

			/*- ================================== COL_SPAN  ========================================== */

			if (colspan > 1) {

				for (int j = 2; j <= colspan; j++)
					cells.add(new Cell(jCell));
			}

			/*- ==================================  ADD_CELL ========================================== */
			cells.add(new Cell(jCell)); // add cell
		}

	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

}
