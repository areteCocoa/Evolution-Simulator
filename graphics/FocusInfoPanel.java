package graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import model.FocusTableModel;

import java.awt.*;

public class FocusInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JTable infoTable;
	
	public FocusInfoPanel() {
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		infoTable = new JTable(new FocusTableModel());
		infoTable.setRowHeight(25);
		infoTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		infoTable.getColumnModel().getColumn(1).setPreferredWidth(125);
		infoTable.setFont(new Font("Helvetica", Font.PLAIN, 13));
		infoTable.setEnabled(false);
		
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();    
        dtcr.setHorizontalAlignment(SwingConstants.RIGHT);  
        infoTable.getColumnModel().getColumn(1).setCellRenderer(dtcr);  
		
		add(infoTable);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension((int)(StatsPanel.width*.4)-10, (MainViewController.panelHeight*2)/5);
	}
}
