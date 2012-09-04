package graphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import main.*;
import model.*;

public class OverviewPanel extends JPanel implements MouseListener, TableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	public static OverviewPanel activePanel;
	
	World world;
	JTabbedPane tabbedView;
	JTable environmentTable, speciesTable;
	JTable[] tables;
	int selectedIndex, selectedTable;
	
	public OverviewPanel(World w) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
		setLayout(new GridLayout(1, 1));
		
		selectedTable = 0;
		
		environmentTable = new JTable(new EnvironmentTableModel());
		speciesTable = new JTable(new SpeciesTableModel());
		
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
			tables[x].addMouseListener(this);
		}
		
		tabbedView = new JTabbedPane();
		tabbedView.addTab("Environments", new JScrollPane(environmentTable));
		tabbedView.addTab("Species", new JScrollPane(speciesTable));
		tabbedView.addMouseListener(this);
		this.add(tabbedView);
		
		world = w;
		
		activePanel = this;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		// Fill background
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Display Environment Biomes
		/* for(int x=0; x<Environment.biomeCount; x++) {
			g.setColor(Color.black);
			g.drawString("Biome #" + Integer.toString(x) + ": " + Singleton.biomeNameTable.get(x), 20, 30*(x+1));
			g.setColor(Singleton.biomeColorTable.get(x));
			g.fillRect(this.getWidth()-50, 30*(x)+16, 16, 16);
		} */
		
		// Display Species
		/* for(int x=0; x<Organism.speciesCount; x++) {
			g.setColor(Color.black);
			g.drawString(("Species #" + Integer.toString(x)) + ": " + Singleton.organismNameTable.get(x), 20, 30*(x+1)+150);
			g.setColor(Singleton.stringToColor(Singleton.organismColorTable.get(x)));
			g.fillRect(this.getWidth()-50, 30*(x)+16+150, 16, 16);
		} */
		
		// Display resolution vs window size
		/* g.setColor(Color.black);
		g.drawString("Screen resolution:" + Integer.toString(Toolkit.getDefaultToolkit().getScreenSize().height) + " x " + Integer.toString(Toolkit.getDefaultToolkit().getScreenSize().width), 20, 500);
		g.drawString("Window Size: " + MainViewController.height + " x " + MainViewController.width, 20, 525); */
	}
	
	public void addMouseListener(MouseListener m) {
		super.addMouseListener(m);
		tabbedView.addMouseListener(m);
		
		for(int x=0; x<tables.length; x++) {
			tables[x].addMouseListener(m);
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
