package graphics.stats;

import graphics.ColorTableCell;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import main.*;
import model.tableModel.*;

public class OverviewPanel extends JPanel implements MouseListener, KeyListener, TableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	JTabbedPane tabbedView;
	JTable environmentTable, speciesTable;
	JTable[] tables;
	int selectedIndex, selectedTable;
	
	EnvironmentTableModel environmentTableModel;
	SpeciesTableModel speciesTableModel;
	
	public OverviewPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
		setLayout(new GridLayout(1, 1));
		
		selectedTable = 0;
		
		environmentTableModel = new EnvironmentTableModel();
		speciesTableModel = new SpeciesTableModel();
		
		environmentTable = new JTable(environmentTableModel);
		speciesTable = new JTable(speciesTableModel);
		
		tables = new JTable[2];
		tables[0] = environmentTable;
		tables[1] = speciesTable;
		
		// Configure tables
		for(int x=0; x<tables.length; x++) {
			tables[x].setRowHeight(50);
			tables[x].setFont(Singleton.defaultFont);
			tables[x].setOpaque(true);
			tables[x].setEnabled(true);
			tables[x].setColumnSelectionAllowed(false);
			tables[x].setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tables[x].getColumnModel().getColumn(2).setCellRenderer(this);
			tables[x].getTableHeader().setResizingAllowed(false);
			tables[x].getTableHeader().setReorderingAllowed(false);
			tables[x].addMouseListener(this);
			tables[x].addKeyListener(this);
		}
		
		tabbedView = new JTabbedPane();
		tabbedView.addTab("Environments", new JScrollPane(environmentTable));
		tabbedView.addTab("Species", new JScrollPane(speciesTable));
		tabbedView.addMouseListener(this);
		tabbedView.addKeyListener(this);
		this.add(tabbedView);
		tabbedView.setSelectedIndex(1);
	}
	
	public void resizeTable(int width) {
		int colorCellWidth = tables[0].getRowHeight(),
				maxWidth = (int)(width*.9)-colorCellWidth;
		for(int x=0; x<tables.length; x++) {
			tables[x].getColumnModel().getColumn(0).setPreferredWidth((int)(maxWidth*.3));
			tables[x].getColumnModel().getColumn(1).setPreferredWidth((int)(maxWidth*.7));
			tables[x].getColumnModel().getColumn(2).setPreferredWidth((int)(colorCellWidth));
		}
	}
	
	public void addMouseListener(MouseListener m) {
		super.addMouseListener(m);
		tabbedView.addMouseListener(m);
		
		for(int x=0; x<tables.length; x++) {
			tables[x].addMouseListener(m);
		}
	}
	
	public void addKeyListener(KeyListener l) {
		super.addKeyListener(l);
		tabbedView.addKeyListener(l);
		
		for(int x=0; x<tables.length; x++) {
			tables[x].addKeyListener(l);
		}
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
		selectedTable = tabbedView.getSelectedIndex();
		selectedIndex = tables[selectedTable].getSelectedRow();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		selectedTable = tabbedView.getSelectedIndex();
		selectedIndex = tables[selectedTable].getSelectedRow();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if(table == speciesTable && column == 2) {
			return new ColorTableCell((Color) speciesTable.getModel().getValueAt(row, 2));
		}
		else if(table == environmentTable && column == 2) {
			return new ColorTableCell((Color) environmentTable.getModel().getValueAt(row, 2));
		}
		
		return null;
	}
}
