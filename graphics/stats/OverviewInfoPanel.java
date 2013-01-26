package graphics.stats;

import java.awt.*;
import javax.swing.*;

import main.World;
import model.tableModel.*;

public class OverviewInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JTable speciesTable, biomeTable;
	JTable[] tables;
	
	JScrollPane scrollPane;
	
	SpeciesFocusTableModel speciesModel;
	BiomeFocusTableModel biomeModel;
	
	public OverviewInfoPanel(World world) {
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new GridLayout(1,1));
		
		speciesModel = new SpeciesFocusTableModel();
		biomeModel = new BiomeFocusTableModel(world.getWorldData().getBiomeStatsModel());
		
		scrollPane = new JScrollPane();
		speciesTable = new JTable(speciesModel);
		biomeTable = new JTable(biomeModel);
		
		tables = new JTable[2];
		tables[0] = biomeTable;
		tables[1] = speciesTable;
		
		for(int x=0; x<tables.length; x++) {
			tables[x].setFont(new Font("Helvetica", Font.PLAIN, 13));
			tables[x].setRowHeight(30);
			tables[x].setEnabled(false);
			tables[x].getColumnModel().setColumnMargin(1);
			tables[x].getTableHeader().setResizingAllowed(false);
			tables[x].getTableHeader().setReorderingAllowed(false);
		}
		
		scrollPane.setViewportView(biomeTable);
		this.add(scrollPane);
	}
	
	public void setTableView(int tableIndex) {
		if(tableIndex < tables.length) {
			scrollPane.setViewportView(tables[tableIndex]);
		}
	}
	
	public void updateTableInfo(int tableIndex, int selectedIndex) {
		if(tableIndex == 0) {
			biomeModel.changeBiome(selectedIndex);
		}
		else if(tableIndex == 1) {
			speciesModel.changeSpecies(selectedIndex);
		}
	}
	
	public void updateData() {
		biomeModel.updateData();
		speciesModel.updateData();
	}
}
