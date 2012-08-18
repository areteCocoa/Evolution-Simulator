package model;

import javax.swing.table.AbstractTableModel;

public class BiomeFocusTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	Object[][] data;
	
	public BiomeFocusTableModel() {
		data = new String[4][2];
		
		data[0][0] = "Made up info";
		data[1][0] = "Replace me";
		data[2][0] = "TODO: DISPLAY";
		data[3][0] = "REAL INFO";
	}
	
	@Override
	public int getColumnCount() {
		return data[0].length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}

}
