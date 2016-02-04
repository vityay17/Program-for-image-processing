package Main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Algorytms.CMYK;
import Algorytms.ExpConGam;
import Algorytms.HSL;
import Algorytms.Histogram;
import GUI.MainGUI;
import GUI.SplotGUI;
import Splot.Maximum;
import Splot.Medianowy;
import Splot.Minimum;
import Splot.Splot;

public class Main {
	private BufferedImage img = null;
	private ExpConGam ecg = null;
	private CMYK cmyk;
	private HSL hsl;
//	private Histogram histogram;
	private Histogram histogram;
//	private JPanel panelHistogram;
	private Splot splot;
	private Minimum minimum;
	private Maximum maximum;
	private Medianowy medianowy;
	private SplotGUI splotframe;
	private MainGUI mainGui;
	
	public Main () {
		openImage();
	}
	public void setMainGUI(MainGUI mainGui){
		this.mainGui = mainGui;
	}
	
	public JLabel getImageLabel() {
		return mainGui.getLbl_forImage();
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

	public JPanel getPanelHistogram() {
		return mainGui.getPanelForHistogram();
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
		    	JLabel lbl_forImage = mainGui.getLbl_forImage();
				img = ImageIO.read(file);
				lbl_forImage.setIcon(new ImageIcon(img));
				initAllClasses(img);
				return true;
				}
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Something wrong. Try again.");
					return false;
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
	
	
	public void setSplotFrame(SplotGUI splotframe){
		this.splotframe = splotframe;
	}
	public SplotGUI getSplotFrame() {
		return splotframe;
	}
	
	void initAllClasses(BufferedImage img){
		ecg = new ExpConGam(this);
		cmyk =new CMYK(this);
		hsl = new HSL(this);
//		histogram = new Histogram();
//		histogram.setJPanel(panelHistogram);
		splot = new Splot(img);
		minimum = new Minimum(img);
		maximum = new Maximum(img);
		medianowy = new Medianowy(img);
	}
	

}