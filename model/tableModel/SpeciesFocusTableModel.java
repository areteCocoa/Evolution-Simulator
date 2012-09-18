package model.tableModel;

import javax.swing.table.AbstractTableModel;

import model.SpeciesStatsModel;

public class SpeciesFocusTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	// private static SpeciesFocusTableModel activeModel;
	
	Object[][] tableData;
	int species;
	
	public SpeciesFocusTableModel() {
		tableData = new String[3][2];
		this.species = -1;
		
		setLabelColumn();
		
		tableData[0][1] = "";
		tableData[1][1] = "";
		tableData[2][1] = "";
	}
	
	public SpeciesFocusTableModel(int species) {
		tableData = new String[3][2];
		this.species = species;
		
		setLabelColumn();
		
		tableData[0][1] = SpeciesStatsModel.totalAlive[species];
		tableData[1][1] = SpeciesStatsModel.totalDead[species];
		tableData[2][1] = SpeciesStatsModel.totalEver[species];
	}
	
	public void setLabelColumn() {
		tableData[0][0] = "Alive";
		tableData[1][0] = "Dead";
		tableData[2][0] = "Total";
	}
	
	public void changeSpecies(int species) {
		this.species = species;
		if(species != -1) {
			tableData[0][1] = Integer.toString(SpeciesStatsModel.totalAlive[species]);
			tableData[1][1] = Integer.toString(SpeciesStatsModel.totalDead[species]);
			tableData[2][1] = Integer.toString(SpeciesStatsModel.totalEver[species]);
		}
		else {
			tableData[0][1] = "";
			tableData[1][1] = "";
			tableData[2][1] = "";
		}
		
		fireTableDataChanged();
	}
	
	public void updateData() {
		if(species != -1) {
			tableData[0][1] = Integer.toString(SpeciesStatsModel.totalAlive[species]);
			tableData[1][1] = Integer.toString(SpeciesStatsModel.totalDead[species]);
			tableData[2][1] = Integer.toString(SpeciesStatsModel.totalEver[species]);
		}
		
		fireTableDataChanged();
	}
	@Override
	public int getColumnCount() {
		return tableData[0].length;
	}

	@Override
	public int getRowCount() {
		return tableData.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return tableData[arg0][arg1];
	}

}
