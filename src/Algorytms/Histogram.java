package Algorytms;

import java.awt.Color;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import Main.Main;

public class Histogram extends Thread{

    private static final int BINS = 256;
    private BufferedImage image;
    private JPanel panelForHistogram;
    private Main main;
    
    public Histogram(Main main) {
		this.main = main;
		image = main.getImg();
		panelForHistogram = main.getPanelHistogram();
//		drawHistogram(image);
	}
    public void run(){
//    	drawHistogram(main.getImg());
    	display();
    }
	public void drawHistogram(BufferedImage image) {
        this.image = image;
//        display();
    }

    private ChartPanel createChartPanel() {
        // dataset
        HistogramDataset dataset = new HistogramDataset();
        Raster raster = image.getRaster();
        final int w = image.getWidth();
        final int h = image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        dataset.addSeries("Red", r, BINS);
        r = raster.getSamples(0, 0, w, h, 1, r);
        dataset.addSeries("Green", r, BINS);
        r = raster.getSamples(0, 0, w, h, 2, r);
        dataset.addSeries("Blue", r, BINS);
//        r = raster.getSamples(0, 0, w, h, 3, r);
//        dataset.addSeries("Grey", r, BINS);
        // chart
        JFreeChart chart = ChartFactory.createHistogram(null, null,
            null, dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());
        // translucent red, green & blue
        Paint[] paintArray = {
            new Color(0x80ff0000, true),
            new Color(0x8000ff00, true),
            new Color(0x800000ff, true)
        };
        plot.setDrawingSupplier(new DefaultDrawingSupplier(
            paintArray,
            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private void display() {
    	panelForHistogram.removeAll();
    	panelForHistogram.add(createChartPanel());
    	panelForHistogram.repaint();
    	panelForHistogram.validate();
    }

   
}
