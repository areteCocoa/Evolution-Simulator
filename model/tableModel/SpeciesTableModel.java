package model.tableModel;

import java.awt.Color;

import javax.swing.table.AbstractTableModel;

import main.Organism;
import main.Singleton;

public class SpeciesTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	Object[][] tableData;
	
	final String[] columnHeader = {"Species #", "Name", "Color"};
	
	public SpeciesTableModel() {
		tableData = new Object[Organism.speciesCount+1][3];
		for(int y=0; y<tableData.length; y++) {
			tableData[y][0] = Integer.toString(y);
			tableData[y][1] = Singleton.organismNameTable.get(y);
			tableData[y][2] = (Color)(Singleton.organismColorTable.get(y));
		}
	}
	
	@Override
	public int getColumnCount() {
		return tableData[0].length;
	}

	@Override
	public int getRowCount() {
		return tableData.length-1;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return tableData[arg0][arg1];
	}

	@Override
	public String getColumnName(int index) {
		return columnHeader[index];
	}
	
}
