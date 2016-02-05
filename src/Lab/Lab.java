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
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		Lab lab = new Lab(new Main());
	    while(true){
	    	double r, g, b;
	    	System.out.println("r");
	    	r = scan.nextDouble();
	    	System.out.println("g");
	    	g = scan.nextDouble();
	    	System.out.println("b");
	    	b = scan.nextDouble();
	    	double[] XYZ = lab.XYZtoLAB(r,g,b);
	    	System.out.println("X "+ XYZ[0] + " Y "+ XYZ[1]+" Z "+XYZ[2]);
	    }
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
	            double[] XYZ = RGBtoXYZ(r, g, b);
	            double[] LAB = XYZtoLAB(XYZ[0], XYZ[1], XYZ[2]);
	            LAB[0] += currentL;
	            LAB[1] += currentA;
	            LAB[2] += currentB;
	            XYZ = LABtoXYZ(LAB[0], LAB[1], LAB[2]);
	            int[] rgb = XYZtoRGB(XYZ[0], XYZ[1], XYZ[2]);
	            r = new BasicImageOperations().clamp(rgb[0], 0, 255);
	            g = new BasicImageOperations().clamp(rgb[1], 0, 255);
	            b = new BasicImageOperations().clamp(rgb[2], 0, 255);
	            imgTemp.setRGB(x, y, new Color(r,g,b).getRGB());
	        }
	    }
	    main.getImageLabel().setIcon(new ImageIcon(imgTemp));
	    main.getHistogram().drawHistogram(imgTemp);
	}
	double[] RGBtoXYZ(int R, int G, int B){
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
	
	int[] XYZtoRGB(double X, double Y, double Z){
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
