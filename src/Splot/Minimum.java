package Splot;
import java.awt.Color;
import java.awt.image.BufferedImage;

import Algorytms.BasicImageOperations;

public class Minimum {
	protected BufferedImage img;
	protected BufferedImage imgOriginal;
	protected BasicImageOperations basicImgOper = new BasicImageOperations();
	
	public Minimum(BufferedImage img){
		this.img = img;
		imgOriginal = basicImgOper.deepCopy(img);
	}
		
	public BufferedImage run(int[][] maska){
		return runEngine(maska, img);
	}
	
	public BufferedImage runEngine(int[][] maska, BufferedImage imgTemp){
		int radius = (maska.length-1)/2;
	    BufferedImage imgWithMirroredSides = basicImgOper.createImageWithMirroredSides(imgTemp,radius);
	    	    
		for(int y = 0;y < imgTemp.getHeight(); y++){
	        for(int x = 0;x < imgTemp.getWidth(); ++x){
	        	int r = engineForPixel(imgWithMirroredSides, x + radius, y + radius, maska, 1);//1 means Red
	        	int g = engineForPixel(imgWithMirroredSides, x + radius, y + radius, maska, 2);//2 means Green
	        	int b = engineForPixel(imgWithMirroredSides, x + radius, y + radius, maska, 3);//3 means Blue
	        	imgTemp.setRGB(x,y,new Color(r,g,b).getRGB());
	        }
		}
		return imgTemp;
		}
		
		/*
		 * @param int color - 1 if red, 2 if green or 3 if Blue
		 */
	protected int engineForPixel(BufferedImage imgTemp, int X, int Y, int[][] maska, int color){
		int t = (maska.length-1)/2; 
		int s = t;
		int min = 255;
		for(int i = -s;i <= s; i++){
	        for(int j = -t;j <= t; j++){
	        	int clr = 0;
	        	if(color == 1) 			clr = new Color(imgTemp.getRGB(X + i, Y + j)).getRed();
	        	else if (color == 2) 	clr = new Color(imgTemp.getRGB(X + i, Y + j)).getGreen();
	        	else 					clr = new Color(imgTemp.getRGB(X + i, Y + j)).getBlue();
	        	if(maska[j+t][i+t] == 1)
	        		if (min>=clr) 
	        			min = clr;
	        }
		}
		return basicImgOper.clamp(min,0,255);
	}
	
	public BufferedImage getOriginalImage(){
		return img = basicImgOper.deepCopy(imgOriginal);
	}
}
