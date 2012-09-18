package model.tableModel;

import javax.swing.table.AbstractTableModel;

import model.EnvironmentStatsModel;

public class FocusTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	Object[][] tableData;
	
	EnvironmentStatsModel envStats;
	
	public FocusTableModel() {
		tableData = new String[4][2];
		
		// Label column
		tableData[0][0] = "Name";
		tableData[1][0] = "Coordinates";
		tableData[2][0] = "Resources";
		tableData[3][0] = "Resource Regen";
	}
	
	public void setNewEnvironment(EnvironmentStatsModel s) {
		envStats = s;
		this.updateTableData();
	}
	
	public void updateTableData() {
		if(envStats != null) {
			tableData[0][1] = envStats.name;
			tableData[1][1] = envStats.coordinates.x + " " + envStats.coordinates.y;
			tableData[2][1] = Integer.toString(envStats.resourceCount);
			tableData[3][1] = Integer.toString(envStats.resourceRate);
			
			fireTableDataChanged();
		}
	}
	
	@Override
	public int getColumnCount() {return tableData[0].length;}

	@Override
	public int getRowCount() {return tableData.length;}

	@Override
	public Object getValueAt(int arg0, int arg1) {return tableData[arg0][arg1];}
	
	public boolean isCellEditable(int arg0, int arg1) {return false;}
}
