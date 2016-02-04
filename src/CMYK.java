

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CMYK {
	private BufferedImage img;
	private JLabel imageLabel;
	private Main main;
	private int currentCMYK_C = 0;
	private int currentCMYK_M = 0;
	private int currentCMYK_Y = 0;
	private int currentCMYK_K = 0;
	
	public CMYK(JLabel lbl, BufferedImage imgTemp, Main main){
		img = (BufferedImage)imgTemp;
		imageLabel = lbl;
		this.main = main;
	}

	public void setCurrentCMYK_C(int currentCMYK_C) {
		this.currentCMYK_C = currentCMYK_C;
		CMYKEngine(currentCMYK_C, currentCMYK_M, currentCMYK_Y, currentCMYK_K);
	}

	public void setCurrentCMYK_M(int currentCMYK_M) {
		this.currentCMYK_M = currentCMYK_M;
		CMYKEngine(currentCMYK_C, currentCMYK_M, currentCMYK_Y, currentCMYK_K);
	}

	public void setCurrentCMYK_Y(int currentCMYK_Y) {
		this.currentCMYK_Y = currentCMYK_Y;
		CMYKEngine(currentCMYK_C, currentCMYK_M, currentCMYK_Y, currentCMYK_K);
	}

	public void setCurrentCMYK_K(int currentCMYK_K) {
		this.currentCMYK_K = currentCMYK_K;
		CMYKEngine(currentCMYK_C, currentCMYK_M, currentCMYK_Y, currentCMYK_K);
	}
	
	private void CMYKEngine(int currentCMYK_C, int currentCMYK_M, int currentCMYK_Y, int currentCMYK_K){
		BufferedImage imgTemp = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB );
	    int nHeight = imgTemp.getHeight();
	    int nWidth = imgTemp.getWidth();
	    for(int y = 0;y < nHeight; y++){
	        for(int x = 0;x < nWidth; ++x){
	        	Color color = new Color(img.getRGB(x, y));
	            int r = color.getRed();
	            int g = color.getGreen();
	            int b = color.getBlue();
	            
	            double c = 1.0 - (double)r/255.0;
	            double m = 1.0 - (double)g/255.0;
	            double y1 = 1.0 - (double)b/255.0;

	            double K = 1.0;

	            if(K >= c) K = c;
	            if(K >= m) K = m;
	            if(K >= y1) K = y1;


	            double C = (c - K)/(1.0 - K);
	            double M = (m - K)/(1.0 - K);
	            double Y = (y1 - K)/(1.0 - K);

	            C = C + (double)currentCMYK_C/100.0;
	            M = M + (double)currentCMYK_M/100.0;
	            Y = Y + (double)currentCMYK_Y/100.0;
	            K = K + (double)currentCMYK_K/100.0;

	            if (C < 0) C = 0;
	            if (M < 0) M = 0;
	            if (Y < 0) Y = 0;
	            if (K < 0) K = 0;
	            if (C > 100) C = 100;
	            if (M > 100) M = 100;
	            if (Y > 100) Y = 100;
	            if (K > 100) K = 100;

	            c = C * (1.0 - K) + K;
	            m = M * (1.0 - K) + K;
	            y1 = Y * (1.0 - K) + K;

	            r = (int) ((1-c)*255);
	            g = (int) ((1-m)*255);
	            b = (int) ((1-y1)*255);
	            
	            r = clamp(r, 0, 255);
	            g = clamp(g, 0, 255);
	            b = clamp(b, 0, 255);
	            
	            imgTemp.setRGB(x, y, new Color(r,g,b).getRGB());
	        }
	    }
	    imageLabel.setIcon(new ImageIcon(imgTemp));
//	    main.getHistogram().calculateHistogram(imgTemp);
//	    main.getSplotFrame().drawHistogram(imgTemp);
	}
	
	private int clamp(int v, int min, int max){
	    if(v>max) return max;
	    if(v<min) return min;
	    return v;
	}
}
