package Algorytms;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class BasicImageOperations {
	
	public int clamp(int v, int min, int max){
	    if(v>max) return max;
	    if(v<min) return min;
	    return v;
	}
	
	public BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	
	public BufferedImage createImageWithMirroredSides(BufferedImage oldImage, int radius) {
		int nHeight = oldImage.getHeight();
	    int nWidth = oldImage.getWidth();
		BufferedImage imgWithMirroredSides = new BufferedImage(
				nWidth + radius*2, nHeight + radius*2,
	    		BufferedImage.TYPE_INT_RGB );
		//create mirrored image on sides
	    //center
		for(int y = radius, oldY = 0;y < nHeight + radius; y++,oldY++){
	        for(int x = radius, oldX = 0;x < nWidth + radius; ++x,++oldX){
	        	imgWithMirroredSides.setRGB(x, y, oldImage.getRGB(oldX, oldY));
	        }
		}
		//left side
		for(int y = radius;y < nHeight + radius; y++){
	        for(int x = 0, oldX = radius-1;x < radius; ++x, oldX--){
	        	imgWithMirroredSides.setRGB(x, y, oldImage.getRGB(oldX, y - radius));
	        }
		}
		//right side
		for(int y = radius;y < nHeight + radius; y++){
	        for(int x = radius + nWidth, oldX = nWidth-1;x < radius*2 + nWidth; ++x, oldX--){
	        	imgWithMirroredSides.setRGB(x, y, oldImage.getRGB(oldX, y - radius));
	        }
		}
		//up side
		for(int y = 0, oldY = radius*2;y < radius; y++, oldY--){
	        for(int x = 0;x < nWidth + radius*2; ++x){
	        	imgWithMirroredSides.setRGB(x, y, imgWithMirroredSides.getRGB(x, oldY));
	        }
		}
		//bottom side
		for(int y = nHeight + radius, oldY = nHeight +radius -1;y < nHeight + radius*2; y++, oldY--){
	        for(int x = 0;x < nWidth + radius*2; ++x){
	        	imgWithMirroredSides.setRGB(x, y, imgWithMirroredSides.getRGB(x, oldY));
	        }
		}					   		
		return imgWithMirroredSides;
	}
	
	public double[] RGBtoXYZ(int R, int G, int B){
		double var_R = (double) R / 255 ;        //R from 0 to 255
		double var_G = ((double) G / 255 );        //G from 0 to 255
		double var_B = ((double) B / 255 );        //B from 0 to 255
		if ( var_R > 0.04045 ) {
			double temp = ( var_R + 0.055 ) / 1.055;
			var_R = Math.pow(temp, 2.4);
		}
		else {
			var_R = var_R / 12.92;
		}
		
		if (var_G > 0.04045){
			double temp = ( var_G + 0.055 ) / 1.055;
			var_G = Math.pow(temp, 2.4);
		}
		else {
			var_G = var_G / 12.92;
		}
		
		if ( var_B > 0.04045 ) {
			double temp = ( var_B + 0.055 ) / 1.055;
			var_B = Math.pow(temp, 2.4);
		}
		else {
			var_B = var_B / 12.92;
		}

		var_R = var_R * 100;
		var_G = var_G * 100;
		var_B = var_B * 100;

		//Observer. = 2°, Illuminant = D65, sRGB
		double X = var_R * 0.4124 + var_G * 0.3576 + var_B * 0.1805;
		double Y = var_R * 0.2126 + var_G * 0.7152 + var_B * 0.0722;
		double Z = var_R * 0.0193 + var_G * 0.1192 + var_B * 0.9505;
		double[] temp = {X, Y, Z};
		return temp;
	}
	
	public int[] XYZtoRGB(double X, double Y, double Z){
		double var_X = X / 100;        //X from 0 to  95.047      (Observer = 2°, Illuminant = D65)
		double var_Y = Y / 100;        //Y from 0 to 100.000
		double var_Z = Z / 100 ;       //Z from 0 to 108.883

		double var_R = var_X *  3.2406 + var_Y * -1.5372 + var_Z * -0.4986;
		double var_G = var_X * -0.9689 + var_Y *  1.8758 + var_Z *  0.0415;
		double var_B = var_X *  0.0557 + var_Y * -0.2040 + var_Z *  1.0570;

		if ( var_R > 0.0031308 ) {
			double temp = Math.pow(var_R, 1 / 2.4); 
			var_R = 1.055 * temp - 0.055;
		}
		else var_R = 12.92 * var_R;
		
		if ( var_G > 0.0031308 ) {
			double temp = Math.pow(var_G, 1 / 2.4);
			var_G = 1.055 * temp - 0.055;
		}
		else var_G = 12.92 * var_G;
		
		if ( var_B > 0.0031308 ) {
			double temp = Math.pow(var_B, 1 / 2.4);
			var_B = 1.055 * temp - 0.055;
		}
		else var_B = 12.92 * var_B;

		int R = (int) (var_R * 255);
		int G = (int) (var_G * 255);
		int B = (int) (var_B * 255);
		
		return new int[]{R, G, B};
	}
}
