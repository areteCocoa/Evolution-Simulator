package model.tableModel;

import javax.swing.table.AbstractTableModel;

import main.Environment;
import main.Singleton;

public class EnvironmentTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	Object[][] tableData;
	final String[] columnHeader = {"Biome #", "Name", "Color"};
	
	public EnvironmentTableModel() {
		tableData = new Object[Environment.biomeCount+1][3];
		for(int y=0; y<tableData.length; y++) {
			tableData[y][0] = Integer.toString(y);
			tableData[y][1] = Singleton.biomeNameTable.get(y);
			tableData[y][2] = Singleton.biomeColorTable.get(y);
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
	
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	@Override
	public String getColumnName(int index) {
		return columnHeader[index];
	}

}
