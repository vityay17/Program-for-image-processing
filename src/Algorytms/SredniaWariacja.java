package Algorytms;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import GUI.MainGUI;
import Main.Main;

public class SredniaWariacja {
	private JLabel SredniaWartoscIntensywnosciRed;
	private JLabel SredniaWartoscIntensywnosciGreen;
	private JLabel SredniaWartoscIntensywnosciBlue;
	private JLabel SredniaWartoscIntensywnosciGray;
	
	private JLabel WariacijIntensywnosciRed;
	private JLabel WariacijIntensywnosciGreen;
	private JLabel WariacijIntensywnosciBlue;
	private JLabel WariacijIntensywnosciGray;	
	
	public SredniaWariacja(Main main){
		MainGUI mainGui =  main.getMainGui();
		SredniaWartoscIntensywnosciRed = mainGui.getLabelSredniaWartoscIntensywnosciRed();
		SredniaWartoscIntensywnosciGreen = mainGui.getLabelSredniaWartoscIntensywnosciGreen();
		SredniaWartoscIntensywnosciBlue = mainGui.getLabelSredniaWartoscIntensywnosciBlue();
		SredniaWartoscIntensywnosciGray = mainGui.getLabelSredniaWartoscIntensywnosciGray();
		
		WariacijIntensywnosciRed = mainGui.getLabelWariacijIntensywnosciRed();
		WariacijIntensywnosciGreen = mainGui.getLabelWariacijIntensywnosciGreen();
		WariacijIntensywnosciBlue = mainGui.getLabelWariacijIntensywnosciBlue();
		WariacijIntensywnosciGray = mainGui.getLabelWariacijIntensywnosciGray();	
	}
	
	public void calculate(BufferedImage imgTemp){
		int R[] = new int[256];
	    int G[] = new int[256];
	    int B[] = new int[256];
	    int A[] = new int[256];
	    
	    for (int i = 0; i < 256; ++i){
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
	    setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(R, imgTemp, SredniaWartoscIntensywnosciRed, WariacijIntensywnosciRed);
	    setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(G, imgTemp, SredniaWartoscIntensywnosciGreen, WariacijIntensywnosciGreen);
	    setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(B, imgTemp, SredniaWartoscIntensywnosciBlue, WariacijIntensywnosciBlue);
	    setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(A, imgTemp, SredniaWartoscIntensywnosciGray, WariacijIntensywnosciGray);
	}
	
	private void setSredniaWartoscIntensywnosciHistogramuTaWariacijIntensywnosci(int T[], BufferedImage imgTemp, JLabel SWI, JLabel KWI){
	    double hN[] = new double[256];
	    for(int i = 0;i<256;i++){
	        hN[i] = (double)T[i]/(imgTemp.getWidth()*imgTemp.getHeight());
	    }
	    double h = 0;
	    for(int i = 0;i<256;i++){
	        h = h + i * hN[i];
	    }
    	SWI.setText(Integer.toString((int)h));
	    //Wariacja intensywności
	    double q2 = 0;
	    for(int i = 0;i<256;i++){
	        q2 = q2 + Math.pow((i - h),2)*hN[i];
	    }
	    KWI.setText(Integer.toString((int) q2));
	}
	
		
}
