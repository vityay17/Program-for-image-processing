package Algorytms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import Main.Main;

public class ExpConGam {
	private BufferedImage img;
	private Main main;
	
	private int currentBrightnes = 0;
	private int currentContrast = 0;
	private int currentGamma = 0;
	
	public ExpConGam(Main main){
		this.main = main;
		img = main.getImg();
	}
	
	public void exposure(int db){
		currentBrightnes = db;
		ECGEngine(currentBrightnes, currentContrast, currentGamma);
	}
	public void contrast(int db){
		currentBrightnes = db;
		ECGEngine(currentBrightnes, currentContrast, currentGamma);
	}
	public void gamma(int db){
		currentBrightnes = db;
		ECGEngine(currentBrightnes, currentContrast, currentGamma);
	}
	
	private void ECGEngine(int brightness,int contrast, int gamma){
		BufferedImage imgTemp = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB );
	    int nHeight = imgTemp.getHeight();
	    int nWidth = imgTemp.getWidth();
	    //brightness
//	    	int rgb[] = img.getRaster().getDataBuffer().getOffsets();
//   		 for (int i=0; i<img.getHeight ()*img.getWidth(); i++) {
//   			 System.out.println(rgb[i]);
//   		 }
//	    
	    
//	    for(int y = 0;y < nHeight; y++){
//	        for(int x = 0;x < nWidth; ++x){
//	        	Color color = new Color(img.getRGB(x, y));
//	            int r = color.getRed();
//	            int g = color.getGreen();
//	            int b = color.getBlue();
//	            
//	            r = clamp(r+brightness, 0, 255);
//	            g = clamp(g+brightness, 0, 255);
//	            b = clamp(b+brightness, 0, 255);
//	            
//	            imgTemp.setRGB(x, y, new Color(r,g,b).getRGB());
//	        }
//	    }
////	    contrast
//	    contrast = -contrast;
//	    for(int y = 0;y < nHeight; y++){
//	        for(int x = 0;x < nWidth; ++x){
//	        	Color color = new Color(img.getRGB(x, y));
//	        	int r = color.getRed();
//	            int g = color.getGreen();
//	            int b = color.getBlue();
//	            
//	            r = clamp((r*(100-contrast)+128*contrast)/100, 0, 255);
//	            g = clamp((g*(100-contrast)+128*contrast)/100, 0, 255);
//	            b = clamp((b*(100-contrast)+128*contrast)/100, 0, 255);
//	            
//	            imgTemp.setRGB(x, y, new Color(r,g,b).getRGB());
//	        }
//	    }
//	    gamma
	    double k = (double)gamma/10.0;
	    for(int y = 0;y < nHeight; y++){
	        for(int x = 0;x < nWidth; ++x){
	        	Color color = new Color(img.getRGB(x, y));
	            int r = color.getRed();
	            int g = color.getGreen();
	            int b = color.getBlue();
	            
	            r = clamp((int) (255*Math.pow(r/255.0, k)), 0, 255);
	            g = clamp((int) (255*Math.pow(g/255.0, k)), 0, 255);
	            b = clamp((int) (255*Math.pow(b/255.0, k)), 0, 255);
	            
	            imgTemp.setRGB(x, y, new Color(r,g,b).getRGB());
	        }
	    }
        main.getImageLabel().setIcon(new ImageIcon(imgTemp));
//	    main.getHistogram().calculateHistogram(imgTemp);
//	    img = imgTemp;
//        main.getSplotFrame().drawHistogram(imgTemp);
	}
	public int clamp(int v, int min, int max){
	    if(v>max) return max;
	    if(v<min) return min;
	    return v;
	}
}
