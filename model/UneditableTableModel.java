package model;

import javax.swing.table.AbstractTableModel;

public class UneditableTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	Object[][] data;
	String[] columnHeader;
	
	public UneditableTableModel(Object[][] data, String[] columnHeader) {
		this.data = data;
		this.columnHeader = columnHeader;
	}
	
	@Override
	public int getColumnCount() {
		return this.data[0].length;
	}

	@Override
	public int getRowCount() {
		return this.data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}
	
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

}
