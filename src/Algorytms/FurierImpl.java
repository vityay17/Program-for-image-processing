package Algorytms;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.jtransforms.fft.DoubleFFT_2D;

public class FurierImpl {
	private BufferedImage img;
	
	double red[][];
    double green[][];
    double blue[][];
    BufferedImage imgFurierRealis;
    BufferedImage imgFurierImaginalis;
    BufferedImage imgFurierSpektrum;
    BufferedImage imgFurierFaza;
     
    
	public FurierImpl(BufferedImage img){
		this.img = img;
		calculateFurier();
	}
	

	public BufferedImage getRealisImage(){
		return moveParts(imgFurierRealis);
	}
	
	public BufferedImage getImaginalisImage(){
		return moveParts(imgFurierImaginalis);
	}
	
	public BufferedImage getSpektrumImage(){
		return moveParts(imgFurierSpektrum);
	}
	
	public BufferedImage getFazaImage(){
		return moveParts(imgFurierFaza);
	}
	
	public void calculateFurier(){
		int height = img.getHeight();
		int width = img.getWidth();
		red = new double[height][width*2];
		green = new double[height][2*width];
		blue = new double[height][2*width];
		DoubleFFT_2D doubleFFT_2D = new DoubleFFT_2D(height, width);  
		
	    for(int y = 0;y < height; y++){
	        for(int x = 0;x < width; ++x){
	        	Color color = new Color(img.getRGB(x, y));
	            int r = color.getRed();
	            int g = color.getGreen();
	            int b = color.getBlue();
				red[y][x*2] = (double)r/255.0;
				red[y][x*2+1] = 0.0;				
				green[y][x*2] = (double)g/255.0;
				green[y][x*2 +1] = 0.0;
				blue[y][x*2] = (double)b/255.0;
				blue[y][x*2+1]  = 0.0;
		     }
		  }
		doubleFFT_2D.complexForward(red);
		doubleFFT_2D.complexForward(green);
		doubleFFT_2D.complexForward(blue);
		
		calculateRealisImage();
		calculateImaginalisImage();
		calculateSpektrumImage();
		calculateFazaImage();
	}

	public void calculateRealisImage(){
		int height = img.getHeight();
		int width = img.getWidth();
		imgFurierRealis = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB );
		
		 double[][] tempArrR = new double[height][width];
		 double[][] tempArrG = new double[height][width];
		 double[][] tempArrB = new double[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
	        	double tempR = (Math.abs(red[row][col*2])); 
	        	double tempG = (Math.abs(green[row][col*2])); 
	        	double tempB = (Math.abs(blue[row][col*2]));
	        	tempR = Math.log(tempR +1);
	        	tempG = Math.log(tempG +1);
	        	tempB = Math.log(tempB +1);
	        	tempArrR[row][col] = tempR;
	        	tempArrG[row][col] = tempG;
	        	tempArrB[row][col] = tempB;
      	
	        }
		}
		double 	maxR = 0.0,
				maxG = 0.0,
				maxB = 0.0;
		 for(int y = 0;y < height; y++){
	        for(int x = 0;x < width; x++){
        	if(Double.compare(maxR, tempArrR[y][x])<0){
        		maxR = tempArrR[y][x];
        		}
        	if(Double.compare(maxG, tempArrG[y][x])<0){
        		maxG = tempArrG[y][x];
        		}
        	if(Double.compare(maxB, tempArrB[y][x])<0){
        		maxB = tempArrB[y][x];
        		}
	        }
		}
		 for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
		        	double tempR =  tempArrR[row][col];
		        	double tempG = tempArrG[row][col]; 
		        	double tempB = tempArrB[row][col];
		        	tempR = tempR*255/maxR;
			    	tempG = tempG*255/maxG;
			    	tempB = tempB*255/maxB;
			    	int r = clamp((int) tempR,0,255);
			        int g = clamp((int) tempG,0,255);
			        int b = clamp((int) tempB,0,255);
			        imgFurierRealis.setRGB(col, row, new Color(r,g,b).getRGB());
		        }
			}
	}
	
	public void calculateImaginalisImage(){		
		int height = img.getHeight();
		int width = img.getWidth();
		imgFurierImaginalis = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB );
		
		 double[][] tempArrR = new double[height][width];
		 double[][] tempArrG = new double[height][width];
		 double[][] tempArrB = new double[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
	        	double tempR = (Math.abs(red[row][col*2+1])); 
	        	double tempG = (Math.abs(green[row][col*2+1])); 
	        	double tempB = (Math.abs(blue[row][col*2+1]));
	        	tempR = Math.log(tempR +1);
	        	tempG = Math.log(tempG +1);
	        	tempB = Math.log(tempB +1);
	        	tempArrR[row][col] = tempR;
	        	tempArrG[row][col] = tempG;
	        	tempArrB[row][col] = tempB;
	        }
		}
		double 	maxR = 0.0,
				maxG = 0.0,
				maxB = 0.0;
		 for(int y = 0;y < height; y++){
	        for(int x = 0;x < width; x++){
        	if(Double.compare(maxR, tempArrR[y][x])<0)
        		maxR = tempArrR[y][x];
        	if(Double.compare(maxG, tempArrG[y][x])<0)
        		maxG = tempArrG[y][x];
        	if(Double.compare(maxB, tempArrB[y][x])<0)
        		maxB = tempArrB[y][x];
	        }
		}
		 for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
		        	double tempR =  tempArrR[row][col];
		        	double tempG = tempArrG[row][col]; 
		        	double tempB = tempArrB[row][col];
		        	tempR = tempR*255/maxR;
			    	tempG = tempG*255/maxG;
			    	tempB = tempB*255/maxB;
			    	int r = clamp((int) tempR,0,255);
			        int g = clamp((int) tempG,0,255);
			        int b = clamp((int) tempB,0,255);
			        imgFurierImaginalis.setRGB(col, row, new Color(r,g,b).getRGB());
		        }
			}	
	}
	
	public void calculateSpektrumImage(){
		int height = img.getHeight();
		int width = img.getWidth();
		imgFurierSpektrum = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB );
		double redF[][] = new double[height][width];
		double greenF[][] = new double[height][width];
		double blueF[][] = new double[height][width];
	    for(int y = 0;y < height; y++){
	        for(int x = 0;x < width; x++){
	        	redF[y][x] = Math.sqrt(Math.pow(red[y][x*2],2)+Math.pow(red[y][x*2+1],2));
	        	greenF[y][x] = Math.sqrt(Math.pow(green[y][x*2],2)+Math.pow(green[y][x*2+1],2));
	        	blueF[y][x] = Math.sqrt(Math.pow(blue[y][x*2],2)+Math.pow(blue[y][x*2+1],2));
	        	redF[y][x] = Math.log(redF[y][x] +1);
	        	greenF[y][x] = Math.log(greenF[y][x] +1);
	        	blueF[y][x] = Math.log(blueF[y][x] +1);
	        }
	    }
	    double 	maxR = 0.0,
				maxG = 0.0,
				maxB = 0.0;
		 for(int y = 0;y < height; y++){
		    for(int x = 0;x < width; x++){
			if(Double.compare(maxR, redF[y][x])<0)
				maxR = redF[y][x];
			if(Double.compare(maxG, greenF[y][x])<0)
				maxG = greenF[y][x];
			if(Double.compare(maxB, blueF[y][x])<0)
				maxB = blueF[y][x];
		    }
		}

		 for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
		        	double tempR = redF[row][col]; 
		        	double tempG = greenF[row][col]; 
		        	double tempB = blueF[row][col];

		        	tempR = tempR*255/maxR;
		        	tempG = tempG*255/maxG;
		        	tempB = tempB*255/maxB;
		        	int r = clamp((int) tempR,0,255);
		            int g = clamp((int) tempG,0,255);
		            int b = clamp((int) tempB,0,255);
		            imgFurierSpektrum.setRGB(col, row, new Color(r,g,b).getRGB());
		        }
			}
	}
	
	public void calculateFazaImage(){
		int height = img.getHeight();
		int width = img.getWidth();
		imgFurierFaza = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB );
		double redF[][] = new double[height][width];
		double greenF[][] = new double[height][width];
		double blueF[][] = new double[height][width];
	    for(int y = 0;y < height; y++){
	        for(int x = 0;x < width; x++){
	        	double tempR = redF[y][x] = Math.atan(red[y][x*2]/red[y][x*2+1]);
	        	double tempG = greenF[y][x] = Math.atan(green[y][x*2]/green[y][x*2+1]);
	        	double tempB = blueF[y][x] = Math.atan(blue[y][x*2]/blue[y][x*2+1]);
	        	tempR = Math.log(tempR +1);
	        	tempG = Math.log(tempG +1);
	        	tempB = Math.log(tempB +1);
	        }
	    }
	    double 	maxR = 0.0,
				maxG = 0.0,
				maxB = 0.0;
		 for(int y = 0;y < height; y++){
		    for(int x = 0;x < width; x++){
			if(Double.compare(maxR, redF[y][x])<0){
				maxR = redF[y][x];
				}
			if(Double.compare(maxG, greenF[y][x])<0){
				maxG = greenF[y][x];
				}
			if(Double.compare(maxB, blueF[y][x])<0){
				maxB = blueF[y][x];
				}
		    }
		}

		 for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
		        	double tempR = redF[row][col]; 
		        	double tempG = greenF[row][col]; 
		        	double tempB = blueF[row][col];
		        	
		        	tempR = tempR*255/maxR;
		        	tempG = tempG*255/maxG;
		        	tempB = tempB*255/maxB;
		        	int r = clamp((int) tempR,0,255);
		            int g = clamp((int) tempG,0,255);
		            int b = clamp((int) tempB,0,255);
		            imgFurierFaza.setRGB(col, row, new Color(r,g,b).getRGB());
		        }
			}
	}
	
	public BufferedImage moveParts(BufferedImage img){
		int height = img.getHeight();
		int width = img.getWidth();
		BufferedImage imgTemp = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB );
		for(int y = 0;y < height; y++){
	        for(int x = 0;x < width; x++){
	        	Color color = new Color(img.getRGB(x, y));
	            int r = color.getRed();
	            int g = color.getGreen();
	            int b = color.getBlue();
	        	if(x>=0 && x<width/2 && y>=0 && y<height/2){//1 part
	        		imgTemp.setRGB(x+width/2, y+height/2, new Color(r,g,b).getRGB());
	        	}
	        	if(x>=width/2 && x<width && y>=0 && y<height/2){//2 part
	        		imgTemp.setRGB(x-width/2, y+height/2, new Color(r,g,b).getRGB());
	        	}
	        	if(x>=0 && x<width/2 && y>=height/2 && y<height){//3 part
	        		imgTemp.setRGB(x+width/2, y-height/2, new Color(r,g,b).getRGB());
	        	}
	        	if(x>=width/2 && x<width && y>=height/2 && y<height){//4 part
	        		imgTemp.setRGB(x-width/2, y-height/2, new Color(r,g,b).getRGB());
	        	}
	        }
		}
		return imgTemp;
	}
	
	public int clamp(int v, int min, int max){
	    if(v>max) return max;
	    if(v<min) return min;
	    return v;
	}

}
