package model;

import javax.swing.table.AbstractTableModel;

public class BiomeFocusTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	Object[][] data;
	
	int biome;
	
	public BiomeFocusTableModel() {
		this(-1);
	}
	
	public BiomeFocusTableModel(int biome) {
		data = new String[2][2];
		
		data[0][0] = "Total #";
		
		if(biome >= 0) {
			data[0][1] = Integer.toString(BiomeStatsModel.totalBiomes[biome]);
		}
		
		this.biome = biome;
	}
	
	public void changeBiome(int biome) {
		if(biome != -1) {
			data[0][1] = Integer.toString(BiomeStatsModel.totalBiomes[biome]);
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
