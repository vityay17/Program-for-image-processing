package Luv;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import Algorytms.BasicImageOperations;
import Main.Main;

public class Luv {
	private BufferedImage img;
	private Main main;
	private int currentL = 0,
				currentU = 0,
				currentV = 0;
				
	public Luv(Main main) {
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
	            double[] LUV = XYZtoLUV(XYZ[0], XYZ[1], XYZ[2]);
	            LUV[0] += currentL;
	            LUV[1] += currentU;
	            LUV[2] += currentV;
	            XYZ = LUVtoXYZ(LUV[0], LUV[1], LUV[2]);
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
	private double[] XYZtoLUV(double X, double Y, double Z) {
		double var_U = ( 4 * X ) / ( X + ( 15 * Y ) + ( 3 * Z ) );
		double var_V = ( 9 * Y ) / ( X + ( 15 * Y ) + ( 3 * Z ) );
		double var_Y = Y / 100;
		
		if ( var_Y > 0.008856 ) var_Y = Math.pow(var_Y, (double)( 1/3 ));
		else                    var_Y = ( 7.787 * var_Y ) + (double)( 16 / 116 );

		double ref_X =  95.047;        //Observer= 2°, Illuminant= D65
		double ref_Y = 100.000;
		double ref_Z = 108.883;

		double ref_U = ( 4 * ref_X ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );
		double ref_V = ( 9 * ref_Y ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );

		double CIEL = ( 116 * var_Y ) - 16;
		double CIEu = 13 * CIEL * ( var_U - ref_U );
		double CIEv = 13 * CIEL * ( var_V - ref_V );
		
		return new double[]{CIEL,CIEu,CIEv};
	}
	private double[] LUVtoXYZ(double CIEL, double CIEu, double CIEv) {
		double var_Y = ( CIEL + 16 ) / 116;
		
		if ( Math.pow(var_Y,  3) > 0.008856 ) var_Y = Math.pow(var_Y,  3);
		else                      var_Y = ( var_Y - 16 / 116 ) / 7.787;

		double ref_X =  95.047;      //Observer= 2°, Illuminant= D65
		double ref_Y = 100.000;
		double ref_Z = 108.883;

		double ref_U = ( 4 * ref_X ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );
		double ref_V = ( 9 * ref_Y ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );

		double var_U = CIEu / ( 13 * CIEL ) + ref_U;
		double var_V = CIEv / ( 13 * CIEL ) + ref_V;

		double Y = var_Y * 100;
		double X =  - ( 9 * Y * var_U ) / ( ( var_U - 4 ) * var_V  - var_U * var_V );
		double Z = ( 9 * Y - ( 15 * var_V * Y ) - ( var_V * X ) ) / ( 3 * var_V );
		return new double[]{X,Y,Z};
	}
	
	
}
