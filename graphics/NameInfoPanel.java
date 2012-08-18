package graphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import model.*;

public class NameInfoPanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	public static NameInfoPanel activePanel;
	
	JTable speciesTable, biomeTable;
	JScrollPane scrollPane;
	
	SpeciesFocusTableModel speciesModel;
	BiomeFocusTableModel biomeModel;
	
	public NameInfoPanel() {
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new GridLayout(1,1));
		
		speciesModel = new SpeciesFocusTableModel();
		biomeModel = new BiomeFocusTableModel();
		
		scrollPane = new JScrollPane();
		speciesTable = new JTable(speciesModel);
		biomeTable = new JTable(biomeModel);
		
		JTable[] tables = new JTable[2];
		tables[0] = speciesTable;
		tables[1] = biomeTable;
		
		for(int x=0; x<tables.length; x++) {
			tables[x].setRowHeight(150);
			tables[x].setFont(new Font("Helvetica", Font.PLAIN, 13));
			tables[x].setEnabled(false);
			tables[x].getColumnModel().setColumnMargin(1);
		}
		
		scrollPane.setViewportView(biomeTable);
		this.add(scrollPane);
		
		activePanel = this;
		NamePanel.activePanel.addMouseListener(this);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension((int)(StatsPanel.width*.4)-10, 500);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(NamePanel.activePanel.selectedTable == 0){
			scrollPane.setViewportView(biomeTable);
		}
		else if(NamePanel.activePanel.selectedTable == 1) {
			scrollPane.setViewportView(speciesTable);
			speciesModel.changeSpecies(NamePanel.activePanel.selectedIndex);
		}
	}
}
