package Algorytms;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Main.Main;

public class HSL {
	private BufferedImage img;	
	private Main main;
	private int currentHSL_H = 0;
	private int currentHSL_L = 0;
	private int currentHSL_S = 0;
	
	double H[][];
    double S[][];
    double L[][];
    
	public HSL(Main main){
		this.main = main;
		img = main.getImg();
	}
	
	public void setCurrentHSL_H(int currentHSL_H) {
		this.currentHSL_H = currentHSL_H;
		engineToHSL( currentHSL_H,currentHSL_S, currentHSL_L);
	}
	public void setCurrentHSL_L(int currentHSL_L) {
		this.currentHSL_L = currentHSL_L;
		engineToHSL( currentHSL_H,currentHSL_S, currentHSL_L);
	}
	public void setCurrentHSL_S(int currentHSL_S) {
		this.currentHSL_S = currentHSL_S;
		engineToHSL(currentHSL_H,currentHSL_S, currentHSL_L);
	}
	
	private  double[] rgb2hsv(double red, double grn, double blu){
		double hue, sat, val;
		double x, f, i;
		double result[] = new double[3];
		 
		x = Math.min(Math.min(red, grn), blu);
		val = Math.max(Math.max(red, grn), blu);
		if (Double.compare(x,val) == 0){
			hue = 0;
			sat = 0;
		}
		else {
			f = (Double.compare(red,x) == 0) ? grn-blu : ((Double.compare(grn,x) == 0) ? blu-red : red-grn);
			i = (Double.compare(red,x) == 0) ? 3 : ((Double.compare(grn,x) == 0) ? 5 : 1);
			hue = ((i-f/(val-x))*60)%360;
			sat = ((val-x)/val);
		}
		result[0] = hue;
		result[1] = sat;
		result[2] = val;
		return result;
	}
	
	private double[] hsv2rgb(double hue, double sat, double val) {
		double red = 0, grn = 0, blu = 0;
		double i, f, p, q, t;
		double result[] = new double[3];
		 
		if(Double.compare(val,0)==0) {
			red = 0;
			grn = 0;
			blu = 0;
		} else {
			hue/=60;
			i = Math.floor(hue);
			f = hue-i;
			p = val*(1-sat);
			q = val*(1-(sat*f));
			t = val*(1-(sat*(1-f)));
		if 		(Double.compare(i,0)==0) {red=val; grn=t; blu=p;}
		else if (Double.compare(i,1)==0) {red=q; grn=val; blu=p;}
		else if (Double.compare(i,2)==0) {red=p; grn=val; blu=t;}
		else if (Double.compare(i,3)==0) {red=p; grn=q; blu=val;}
		else if (Double.compare(i,4)==0) {red=t; grn=p; blu=val;}
		else if (Double.compare(i,5)==0) {red=val; grn=p; blu=q;}
		}
		result[0] = red;
		result[1] = grn;
		result[2] = blu;
		return result;
	}
	
	public void engineToHSL(int tempH, int tempS, int tempL){
		BufferedImage imgTemp = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB );
	    int nHeight = imgTemp.getHeight();
	    int nWidth = imgTemp.getWidth();
	     
	    for(int y = 0;y < nHeight; y++){
	        for(int x = 0;x < nWidth; ++x){
	        	Color color = new Color(img.getRGB(x, y));
	        	int red = color.getRed();;
	        	int green = color.getGreen();
	        	int blue = color.getBlue();
	        	// Convert RGB to HSB

	        	float[] hsb = Color.RGBtoHSB(red, green, blue, null);

	        	float hue = clamp((hsb[0] + tempH+240/360f),0f,1f);
	        	float saturation =  clamp((hsb[1] + tempS/100f),0f,1f);
	        	float brightness =  clamp((hsb[2] + tempL/100f),0f,1f);
	        	

//	        	System.out.println("RGB [" + red + "," + green + "," + blue + "] converted to HSB [" + hue + " "+hsb[0]+"," + saturation + " " +hsb[1]+"," + brightness + " " + hsb[2]+"]" );

	        	// Convert HSB to RGB value

	        	int rgb = Color.HSBtoRGB(hue, saturation, brightness);
	        	red = (rgb>>16)&0xFF;
	        	green = (rgb>>8)&0xFF;
	        	blue = rgb&0xFF;
//	        	System.out.println("r "+ red + " g "+green+" b "+blue);
//	        	System.out.println();
	        	
	        	imgTemp.setRGB(x, y, new Color((int)red,(int)green,(int)blue).getRGB());
	        }
	    }
	    main.getImageLabel().setIcon(new ImageIcon(imgTemp));    
	}
		 	
	public void W2engineToHSL(int tempH, int tempS, int tempL){
		BufferedImage imgTemp = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB );
	    int nHeight = imgTemp.getHeight();
	    int nWidth = imgTemp.getWidth();
	     
	    for(int y = 0;y < nHeight; y++){
	        for(int x = 0;x < nWidth; ++x){
	        	Color color = new Color(img.getRGB(x, y));
	        	double hue,sat,val;
	        	double red,grn,blu;
	        	double result[];
	        	 red = (double)color.getRed();
	             grn = (double)color.getGreen();
	             blu = (double)color.getBlue();
	        	result = rgb2hsv(red, grn, blu);
	        	
	        	hue = clamp((double)(result[0] + tempH),0.0,359.9);
	        	sat = clamp((double)(result[1] + tempS/100.0),0.0,1.0);
	        	val = clamp((double)(result[2] + tempL),0.0,255.0);
//	        	System.out.println("r "+ red + "g "+grn+"b "+blu);
    	//HSV do RGB
	        	result = hsv2rgb(hue, sat, val);
	        	
	        	red = result[0];
	        	grn = result[1];
	        	blu = result[2];
	        	
	        	red = clamp(result[0],0.0,255.0);
	        	grn = clamp(result[1],0.0,255.0);
	        	blu = clamp(result[2],0.0,255.0);
//	        	System.out.println("r "+ red + "g "+grn+"b "+blu);
	        	imgTemp.setRGB(x, y, new Color((int)red,(int)grn,(int)blu).getRGB());
	        }
	        
	    }
	    
	    main.getImageLabel().setIcon(new ImageIcon(imgTemp));
	}
	
	public void WengineToHSL(BufferedImage img, int tempH, int tempS, int tempL){
		BufferedImage imgTemp = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB );
	    int nHeight = imgTemp.getHeight();
	    int nWidth = imgTemp.getWidth();
	  	    
	    for(int y = 0;y < nHeight; y++){
	        for(int x = 0;x < nWidth; ++x){
	        	Color color = new Color(img.getRGB(x, y));
	        	double H = 0,S,L;
	            double r = (double)color.getRed()/255;
	            double g = (double)color.getGreen()/255;
	            double b = (double)color.getBlue()/255;
	            double MIN = Math.min(r, Math.min(g, b));
	            double MAX = Math.max(r, Math.max(g, b));
	            
	            double dM = MAX - MIN;
	            L = (MAX + MIN)/2;

	            if(Double.compare(L,0.0) == 0) S = 0;
	            else if(Double.compare(L,0.5) > 0) S = dM /(2 - 2*L);
	            else S = dM/2*L;
	            
	            if (Double.compare(MAX,MIN) == 0) H = 0;
	            else if(Double.compare(MAX,r) == 0 && Double.compare(g,b) >= 0) H = 60*(g - b)/dM;
	            else if(Double.compare(MAX,r) == 0 && Double.compare(g,b) < 0) 	H = (60*(g - b)/dM) + 360;
	            else if(Double.compare(MAX,g) == 0) 			H = (60*(b - r)/dM) + 120;
	            else if(Double.compare(MAX,b) == 0)			H = (60*(r - g)/dM) + 240;
	                     	
            	H += tempH;
	            S += (double)tempS/100;
	            L += (double)tempL/100;

	            if (Double.compare(H,0) < 0) H = 0;
	            if (Double.compare(S,0) < 0) S = 0;
	            if (Double.compare(L,0) < 0) L = 0;
	            if (Double.compare(H,360) > 0) H = 360;
	            if (Double.compare(S,1) > 0) S = 1;
	            if (Double.compare(L,1) > 0) L = 1;
            	
	            //Konwersja HSL do RGB
                if(Double.compare(S,0) == 0) r = g = b = L;
                else{
                    double Q = 0;
                    if(Double.compare(L,0.5) < 0) Q = L*(1 + S);
                    else if(Double.compare(L,0.5) >= 0) Q = L + S - (L * S);
                    
                    double P = 2.0*L - Q;
                    H = (double)H/360;

                    double Tr = H + 1.0/3.0;
                    double Tg = H;
                    double Tb = H - 1.0/3.0;

                    if(Double.compare(Tr,0) < 0) Tr += 1;
                    if(Double.compare(Tg,0) < 0) Tg += 1;
                    if(Double.compare(Tb,0) < 0) Tb += 1;
                    
                    if(Double.compare(Tr,1) > 0) Tr -= 1;
                    if(Double.compare(Tg,1) > 0) Tg -= 1;
                    if(Double.compare(Tb,1) > 0) Tb -= 1;

                    if	   (Double.compare(Tr,1.0/6.0) < 0) 										r = P + ((Q - P) * 6.0 * Tr);
                    else if(Double.compare(Tr,0.5)     < 0 	&& Double.compare(Tr,1.0/6.0) 	>= 0) 	r = Q;
                    else if(Double.compare(Tr,2.0/3.0) < 0 	&& Double.compare(Tr,0.5) 		>= 0) 	r = P + (Q - P) * 6.0 * (0.6667 - Tr);
                    else 																			r = P;

                    if		(Double.compare(Tg,1.0/6.0) < 0) 										g = P + ((Q - P) * 6.0 * Tg);
                    else if	(Double.compare(Tg,0.5)     < 0 && Double.compare(Tg,1.0/6.0) 	>= 0) 	g = Q;
                    else if	(Double.compare(Tg,2.0/3.0) < 0 && Double.compare(Tg,0.5) 		>= 0)	g = P + (Q - P) * 6.0 * (0.6667 - Tg);
                    else 																			g = P;

                    if		(Double.compare(Tb,1.0/6.0) < 0) 										b = P + (Q - P) * 6.0 * Tb;
                    else if	(Double.compare(Tb,0.5)     < 0 && Double.compare(Tb,1.0/6.0) 	>= 0) 	b = Q;
                    else if	(Double.compare(Tb,2.0/3.0) < 0 && Double.compare(Tb,0.5) 		>= 0) 	b = P + (Q - P) * 6.0 * (0.6667 - Tb);
                    else 																			b = P;

                    r *= 255;
                    g *= 255;
                    b *= 255;
                    r = clamp( r, 0, 255);
    	            g = clamp( g, 0, 255);
    	            b = clamp( b, 0, 255);
    	            //System.out.println(" r" + color.getRed() + " g" + color.getGreen() +" b" + color.getBlue());
    	        }
                
	            imgTemp.setRGB(x, y, new Color((int)r,(int)g,(int)b).getRGB());
    	    }
        }
	    main.getImageLabel().setIcon(new ImageIcon(imgTemp));
	}
	
	private double clamp(double d, double e, double f){
	    if(Double.compare(d,f) > 0) return f;
	    if(Double.compare(d,e) < 0) return e;
	    return d;
	}
	
	private float clamp(float d, float e, float f){
	    if(Float.compare(d,f) > 0) return f;
	    if(Float.compare(d,e) < 0) return e;
	    return d;
	}
}
