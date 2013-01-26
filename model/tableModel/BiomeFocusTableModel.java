package model.tableModel;

import javax.swing.table.AbstractTableModel;

import model.stats.BiomeStatsModel;

public class BiomeFocusTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	Object[][] data;
	
	int biome;
	private BiomeStatsModel model;
	
	public BiomeFocusTableModel(BiomeStatsModel model) {
		this(-1, model);
	}
	
	public BiomeFocusTableModel(int biome, BiomeStatsModel model) {
		this.model = model;
		data = new String[2][2];
		
		data[0][0] = "Total #";
		
		changeBiome(biome);
	}
	
	public void changeBiome(int biome) {
		if(biome != -1) {
			data[0][1] = Integer.toString(model.getTotalBiomes(biome));
		}
		else {
			data[0][1] = "";
		}
		
		this.biome = biome;
	}
	
	public void updateData() {
		this.changeBiome(biome);
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
