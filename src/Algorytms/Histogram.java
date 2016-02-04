package Algorytms;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

public class Histogram {
	
	
	
	private JLabel lblRed;
	private JLabel lblGreen;
	private JLabel lblBlue;
	private JLabel lblGrey;
	
	private JLabel SredniaWartoscIntensywnosciRed;
	private JLabel SredniaWartoscIntensywnosciGreen;
	private JLabel SredniaWartoscIntensywnosciBlue;
	private JLabel SredniaWartoscIntensywnosciGray;
	
	private JLabel WariacijIntensywnosciRed;
	private JLabel WariacijIntensywnosciGreen;
	private JLabel WariacijIntensywnosciBlue;
	private JLabel WariacijIntensywnosciGray;
	
	
	public void setLabelsSredniaWartoscIntensywnosci(JLabel SredniaWartoscIntensywnosciRed, JLabel SredniaWartoscIntensywnosciGreen,
			JLabel SredniaWartoscIntensywnosciBlue, JLabel SredniaWartoscIntensywnosciGray,
			JLabel WariacijIntensywnosciRed, JLabel WariacijIntensywnosciGreen,
			JLabel WariacijIntensywnosciBlue, JLabel WariacijIntensywnosciGray){
		this.SredniaWartoscIntensywnosciRed = SredniaWartoscIntensywnosciRed;
		this.SredniaWartoscIntensywnosciGreen = SredniaWartoscIntensywnosciGreen;
		this.SredniaWartoscIntensywnosciBlue = SredniaWartoscIntensywnosciBlue;
		this.SredniaWartoscIntensywnosciGray = SredniaWartoscIntensywnosciGray;
		
		this.WariacijIntensywnosciRed = WariacijIntensywnosciRed;
		this.WariacijIntensywnosciGreen = WariacijIntensywnosciGreen;
		this.WariacijIntensywnosciBlue = WariacijIntensywnosciBlue;
		this.WariacijIntensywnosciGray = WariacijIntensywnosciGray;
	}
	
    
	public void setLabels(JLabel lblRed, JLabel lblGreen, JLabel lblBlue, JLabel lblGrey){
		this.lblRed = lblRed;
		this.lblGreen = lblGreen;
		this.lblBlue = lblBlue;
		this.lblGrey = lblGrey;
	}
//	
//	public void cashe(){
//		for(int y = 0;y < height; y++){
//	        for(int x = 0;x < width; ++x){
//	        	barChartRed.setRGB(x,y,new Color(255,255,255).getRGB());
//	        	barChartBlue.setRGB(x,y,new Color(255,255,255).getRGB());
//	        	barChartGreen.setRGB(x,y,new Color(255,255,255).getRGB());
//	        	barChartSzarosci.setRGB(x,y,new Color(255,255,255).getRGB());
//	        }
//	    }
//	}
	
	public void calculateHistogram(BufferedImage imgTemp){
		int width = 768, height = 600;
		BufferedImage barChartRed = new BufferedImage (width, height,BufferedImage.TYPE_INT_RGB );
		BufferedImage barChartGreen = new BufferedImage (width, height,BufferedImage.TYPE_INT_RGB );
		BufferedImage barChartBlue = new BufferedImage (width, height,BufferedImage.TYPE_INT_RGB );
		BufferedImage barChartSzarosci = new BufferedImage (width, height,BufferedImage.TYPE_INT_RGB );

		int R[] = new int[256];
	    int G[] = new int[256];
	    int B[] = new int[256];
	    int A[] = new int[256];
	    for (int i = 0; i < 256; ++i)
	    {
	        R[i] = 0;G[i] = 0;B[i] = 0;A[i] = 0;
	    }
	    // собираем статистику для изображения
	    int nHeight = imgTemp.getHeight();
	    int nWidth = imgTemp.getWidth();
	    for(int y = 0;y < nHeight; y++){
	        for(int x = 0;x < nWidth; ++x){
	        	Color color = new Color(imgTemp.getRGB(x, y));
	            int r = color.getRed();
	            int g = color.getGreen();
	            int b = color.getBlue();
	            R[color.getRed()]++;
	            G[color.getGreen()]++;
	            B[color.getBlue()]++;
	        }
	    }

	    for(int i = 0;i < 256; i++){
	        A[i] = (R[i]+G[i]+B[i])/3;
	    }

	    // находим самый высокий столбец, чтобы корректно масштабировать гистограмму по высоте
	    int max = 0;
	    for (int i = 0; i < 256; ++i){
	        if (R[i] > max)
	            max = R[i];
	        if (G[i] > max)
	            max = G[i];
	        if (B[i] > max)
	            max = B[i];
	    }
	    
	    // определяем коэффициент масштабирования по высоте
	    double point = (double)height / max;
	    //отрисовываем столбец за столбцом нашу гистограмму с учетом масштаба
	    for (int i = 0; i < width - 3; ++i)
	        for (int j = height - 1; j > height - R[i / 3] * point; --j)
	            barChartRed.setRGB(i,j,new Color(255,0,0).getRGB());
	    
	    for (int i = 0; i < width - 3; ++i)
	        for (int j = height - 1; j > height - G[i / 3] * point; --j)
	            barChartGreen.setRGB(i,j,new Color(0,255,0).getRGB());
	    
	    for (int i = 0; i < width - 3; ++i)
	        for (int j = height - 1; j > height - B[i / 3] * point; --j)
	            barChartBlue.setRGB(i,j,new Color(0,0,255).getRGB());
	        
	    for (int i = 0; i < width - 3; ++i)
	        for (int j = height - 1; j > height - A[i / 3] * point; --j)
	            barChartSzarosci.setRGB(i,j,new Color(0,0,0).getRGB());
	        
//	    barChartRed = scale(barChartBlue, BufferedImage.TYPE_INT_RGB, lblRed.getWidth(), lblRed.getHeight(), width, height);
	    
	    lblRed.setIcon(new ImageIcon(barChartRed.getScaledInstance(lblRed.getWidth(), lblRed.getHeight(),
	            Image.SCALE_SMOOTH)));
	    lblBlue.setIcon(new ImageIcon(barChartGreen.getScaledInstance(lblRed.getWidth(), lblRed.getHeight(),
	            Image.SCALE_SMOOTH)));
	    lblGreen.setIcon(new ImageIcon(barChartBlue.getScaledInstance(lblRed.getWidth(), lblRed.getHeight(),
	            Image.SCALE_SMOOTH)));
	    lblGrey.setIcon(new ImageIcon(barChartSzarosci.getScaledInstance(lblRed.getWidth(), lblRed.getHeight(),
	            Image.SCALE_SMOOTH)));
	    
	    setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(R, imgTemp, SredniaWartoscIntensywnosciRed, WariacijIntensywnosciRed);
	    setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(G, imgTemp, SredniaWartoscIntensywnosciGreen, WariacijIntensywnosciGreen);
	    setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(B, imgTemp, SredniaWartoscIntensywnosciBlue, WariacijIntensywnosciBlue);
	    setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(A, imgTemp, SredniaWartoscIntensywnosciGray, WariacijIntensywnosciGray);
	}
	
	public void setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(int A[], BufferedImage imgTemp, JLabel SWI, JLabel KWI){
	    double hN[] = new double[256];
	    for(int i = 0;i<256;i++){
	        hN[i] = (double)A[i]/(imgTemp.getWidth()*imgTemp.getHeight());
	    }
	    double h = 0;
	    for(int i = 0;i<256;i++){
	        h = h + i * hN[i];
	    }
	   // label1->setNum(h);
	    	SWI.setText(Integer.toString((int)h));
	    //Wariancja intensywności
	    double q2 = 0;
	    for(int i = 0;i<256;i++){
	        q2 = q2 + Math.pow((i - h),2)*hN[i];
	    }
	    KWI.setText(Integer.toString((int) q2));
	   // label2->setNum(q2);
	}
	
	public void drawHistogram(int[] k){
		double[] value = new double[100];
		Random generator = new Random();
		for (int i=1; i < 100; i++) {
			value[i] = generator.nextDouble();
			}
		int number = 10;
		HistogramDataset dataset = new HistogramDataset();
		dataset.setType(HistogramType.RELATIVE_FREQUENCY);
		dataset.addSeries("Histogram",value,number);
		String plotTitle = "Histogram"; 
		String xaxis = "number";
		String yaxis = "value"; 
		PlotOrientation orientation = PlotOrientation.VERTICAL; 
		boolean show = false; 
		boolean toolTips = false;
		boolean urls = false; 
		JFreeChart chart = ChartFactory.createHistogram( plotTitle, xaxis, yaxis, 
		dataset, orientation, show, toolTips, urls);
		int width = 500;
		int height = 300; 
		//ChartUtilities.saveChartAsPNG(new File("histogram.PNG"), chart, width, height);
		lblGrey.setIcon((Icon) chart);
		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		frame.setVisible(true);
	}
		
}
