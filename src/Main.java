import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {

	private BufferedImage img = null;
	private JLabel imageLabel;
	
	private ExpConGam ecg = null;
	private CMYK cmyk;
	private HSL hsl;
//	private Histogram histogram;
	private Histogram histogram;
	private JPanel panelHistogram;
	private Splot splot;
	private Minimum minimum;
	private Maximum maximum;
	private Medianowy medianowy;
	

	public Main (JLabel lbl) {
		imageLabel = lbl;
	}
	
	public JLabel getImageLabel() {
		return imageLabel;
	}
	public BufferedImage getImg() {
		return img;
	}
	public ExpConGam getEcg() {
		return ecg;
	}
	public CMYK getCmyk() {
		return cmyk;
	}
	public HSL getHsl() {
		return hsl;
	}
		
	public Histogram getHistogram() {
		return histogram;
	}

	public void setPanelHistogram(JPanel panelHistogram) {
		this.panelHistogram = panelHistogram;
		
	}

	public Splot getSplot() {
		return splot;
	}
	public Minimum getMinimum() {
		return minimum;
	}
	public Maximum getMaximum() {
		return maximum;
	}
	public Medianowy getMedianowy() {
		return medianowy;
	}

	public boolean openImage(){
		JFileChooser fileopen = new JFileChooser();
		int ret = fileopen.showDialog(null, "Открыть файл");                
		if (ret == JFileChooser.APPROVE_OPTION) {
		    File file = fileopen.getSelectedFile();
		    try {
				img = ImageIO.read(file);
				imageLabel.setIcon(new ImageIcon(img));
				ecg = new ExpConGam(imageLabel, img, this);
				cmyk =new CMYK(imageLabel, img, this);
				hsl = new HSL(imageLabel, img);
//				histogram = new Histogram();
//				histogram.setJPanel(panelHistogram);
				splot = new Splot(img);
				minimum = new Minimum(img);
				maximum = new Maximum(img);
				medianowy = new Medianowy(img);
				return true;
				}
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Something wrong. Try again.");
				}
		}
		return false;
		
	}
	
	public void drawHistogram(BufferedImage image){
//		histogram.setImage(image);
	}
		
	public void changeExposure(int k){ ecg.exposure(k);}
	public void changeContrast(int k){ecg.contrast(k); }
	public void changeGamma(int k){ecg.gamma(k);}
	
	public void changeCMYK_C(int k){cmyk.setCurrentCMYK_C(k);}
	public void changeCMYK_Y(int k){cmyk.setCurrentCMYK_Y(k);}
	public void changeCMYK_M(int k){cmyk.setCurrentCMYK_M(k);}
	public void changeCMYK_K(int k){cmyk.setCurrentCMYK_K(k);}
	
	public void changeHSL_H(int k){hsl.setCurrentHSL_H(k);}
	public void changeHSL_S(int k){hsl.setCurrentHSL_S(k);}
	public void changeHSL_L(int k){hsl.setCurrentHSL_L(k);}
	
	SplotFrame splotframe;
	public void setSplotFrame(SplotFrame splotframe){
		this.splotframe = splotframe;
	}
	public SplotFrame getSplotFrame() {
		return splotframe;
	}
	

}