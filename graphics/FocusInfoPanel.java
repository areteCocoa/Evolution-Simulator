package graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import main.Environment;
import model.FocusTableModel;

import java.awt.*;

public class FocusInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Environment lastClickedEnvironment;
	
	JTable infoTable;
	FocusTableModel focusTableModel;
	
	public FocusInfoPanel() {
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		focusTableModel = new FocusTableModel();
		
		infoTable = new JTable(focusTableModel);
		infoTable.setRowHeight(25);
		infoTable.setFont(new Font("Helvetica", Font.PLAIN, 13));
		infoTable.setEnabled(false);
		
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();    
        dtcr.setHorizontalAlignment(SwingConstants.RIGHT);  
        infoTable.getColumnModel().getColumn(1).setCellRenderer(dtcr);  
		
		add(infoTable);
	}
	
	public void resizeTable(int width) {
		int maxWidth = (int)(width*.9);
		
		infoTable.getColumnModel().getColumn(0).setPreferredWidth((int)(maxWidth*.4));
		infoTable.getColumnModel().getColumn(1).setPreferredWidth((int)(maxWidth*.6));
	}
	
	public void setLastClickedEnvironment(Environment e) {
		lastClickedEnvironment = e;
		focusTableModel.setNewEnvironment(e.getEnvironmentStats());
	}
	
	public void updateData() {
		if(lastClickedEnvironment != null) {
			focusTableModel.setNewEnvironment(lastClickedEnvironment.getEnvironmentStats());
			focusTableModel.updateTableData();
		}
	}
}
