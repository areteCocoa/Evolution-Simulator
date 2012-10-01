package graphics.end;

import graphics.stats.OverviewViewController;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import main.Singleton;
// import graphics.stats.OverviewViewController;
import model.*;


public class EndView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JLabel nameLabel, durationDayLabel;
	
	public EndView(WorldData data) {
		setBorder(BorderFactory.createLineBorder(getBackground(), 10));
		setLayout(new BorderLayout(10, 10));
		
		nameLabel = new JLabel("Name: " + data.name);
		durationDayLabel = new JLabel(data.daysRun + "/" + data.intendedDuration);
		
		OverviewViewController overviewController = new OverviewViewController();
		
		// Initialize chart data
		DefaultCategoryDataset aliveDataset = new DefaultCategoryDataset(),
				deadDataset = new DefaultCategoryDataset(),
				totalDataset = new DefaultCategoryDataset();
		
		GraphDataInitializer.initDatasetFromWorldData(aliveDataset, data, GraphDataInitializer.TOTAL_EVER);
		GraphDataInitializer.initDatasetFromWorldData(deadDataset, data, GraphDataInitializer.TOTAL_DEAD);
		GraphDataInitializer.initDatasetFromWorldData(totalDataset, data, GraphDataInitializer.TOTAL_EVER);
		
		// Initialize and set chart
		JFreeChart aliveChart = ChartFactory.createLineChart("Total Organisms Alive per Day", "Day", "Alive Count", aliveDataset, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart deadChart = ChartFactory.createLineChart("Total Organisms Dead per Day", "Day", "Dead Count", deadDataset, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart totalChart = ChartFactory.createLineChart("Total Organisms Ever per Day", "Day", "Total Count", totalDataset, PlotOrientation.VERTICAL, true, true, false);
		
		// Initialize tab view for charts
		JTabbedPane tabView = new JTabbedPane();
		tabView.addTab("Alive", new ChartPanel(aliveChart));
		tabView.addTab("Dead", new ChartPanel(deadChart));
		tabView.addTab("Total", new ChartPanel(totalChart));
		
		customizeChart(aliveChart, data);
		
		this.add(nameLabel, BorderLayout.NORTH);
		this.add(tabView, BorderLayout.CENTER);
		this.add(overviewController, BorderLayout.EAST);
		this.add(durationDayLabel, BorderLayout.SOUTH);
	}
	
	
	private void customizeChart(JFreeChart chart, WorldData data) {
		CategoryPlot plot = chart.getCategoryPlot();
		
		// CategoryAxis domainAxis = plot.getDomainAxis();
		
		
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		
		CategoryItemRenderer render = plot.getRenderer();
		Color tempColor;
		for(int y=0; y<data.speciesStats.getSpeciesCount(); y++) {
			tempColor = Singleton.organismColorTable.get(y);
			render.setSeriesPaint(y, tempColor);
		}
	}
}
