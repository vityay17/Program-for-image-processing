package Lab;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.ImageIcon;

import Algorytms.BasicImageOperations;
import Main.Main;

public class Lab {
	private BufferedImage img;
	private Main main;
	private int currentL = 0,
				currentA = 0,
				currentB = 0;
				
	public Lab(Main main) {
		this.main = main;
		img = main.getImg();
	}
	void setL(int L){
		currentL = L;
		engine();
	}
	void setA(int A){
		currentL = A;
		engine();
	}
	void setB(int B){
		currentL = B;
		engine();
	}
	private void engine(){
		BufferedImage imgTemp = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB );
	    int nHeight = imgTemp.getHeight();
	    int nWidth = imgTemp.getWidth();
	    for(int y = 0;y < nHeight; y++){
	        for(int x = 0;x < nWidth; ++x){
	        	Color color = new Color(img.getRGB(x, y));
	            int r = color.getRed();
	            int g = color.getGreen();
	            int b = color.getBlue();
	            double[] XYZ = new BasicImageOperations().RGBtoXYZ(r, g, b);
	            double[] LAB = XYZtoLAB(XYZ[0], XYZ[1], XYZ[2]);
	            LAB[0] += currentL;
	            LAB[1] += currentA;
	            LAB[2] += currentB;
	            XYZ = LABtoXYZ(LAB[0], LAB[1], LAB[2]);
	            int[] rgb = new BasicImageOperations().XYZtoRGB(XYZ[0], XYZ[1], XYZ[2]);
	            r = new BasicImageOperations().clamp(rgb[0], 0, 255);
	            g = new BasicImageOperations().clamp(rgb[1], 0, 255);
	            b = new BasicImageOperations().clamp(rgb[2], 0, 255);
	            imgTemp.setRGB(x, y, new Color(r,g,b).getRGB());
	        }
	    }
	    main.getImageLabel().setIcon(new ImageIcon(imgTemp));
	    main.getHistogram().drawHistogram(imgTemp);
	}	
	double[] XYZtoLAB(double X, double Y, double Z){
		double ref_X =  95.047;
		double ref_Y = 100.000;
		double ref_Z = 108.883;
		double var_X = X / ref_X;          //ref_X =  95.047   Observer= 2°, Illuminant= D65
		double var_Y = Y / ref_Y;          //ref_Y = 100.000
		double var_Z = Z / ref_Z;          //ref_Z = 108.883
		if ( var_X > 0.008856 )	{
			var_X = Math.pow(var_X, (double)1/3);
		}
		else {
			var_X = ( 7.787 * var_X ) + ( (double)16 / 116 );
		}
		
		if ( var_Y > 0.008856 ) {
			var_Y = Math.pow(var_Y, (double)1/3);
		}
		else{
			var_Y = ( 7.787 * var_Y ) + ( (double)16 / 116 );
		}
		
		if ( var_Z > 0.008856 ){
			var_Z = Math.pow(var_Z, (double)1/3);
		}
		else{
			var_Z = ( 7.787 * var_Z ) + ( (double)16 / 116 );
		}
		double CIEL = ( 116 * var_Y ) - 16;
		double CIEa = 500 * ( var_X - var_Y );
		double CIEb = 200 * ( var_Y - var_Z );
		return new double[]{CIEL, CIEa,CIEb};
	}
	
	double[] LABtoXYZ(double CIEL, double CIEa, double CIEb){
		double var_Y = ( CIEL + 16 ) / 116;
		double var_X = CIEa / 500 + var_Y;
		double var_Z = var_Y - CIEb / 200;

		if ( Math.pow(var_Y, 3) > 0.008856 ) {
			var_Y = Math.pow(var_Y, 3);
		}
		else {
			var_Y = ( var_Y - (double)16 / 116 ) / 7.787;
		}
		
		if ( Math.pow(var_X, 3) > 0.008856 ) {
			var_X = Math.pow(var_X, 3);
		}
		else {
			var_X = ( var_X - (double)16 / 116 ) / 7.787;
		}
		
		if ( Math.pow(var_Z, 3) > 0.008856 ) {
			var_Z = Math.pow(var_Z, 3);
		}
		else {
			var_Z = ( var_Z - (double)16 / 116 ) / 7.787;
		}
		
		double ref_X =  95.047;
		double ref_Y = 100.000;
		double ref_Z = 108.883;
		
		double X = ref_X * var_X;     //ref_X =  95.047     Observer= 2°, Illuminant= D65
		double Y = ref_Y * var_Y;     //ref_Y = 100.000
		double Z = ref_Z * var_Z;     //ref_Z = 108.883
		return new double[]{X, Y, Z};
	}
}
