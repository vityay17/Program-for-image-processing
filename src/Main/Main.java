package Main;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Algorytms.CMYK;
import Algorytms.ExpConGam;
import Algorytms.HSL;
import Algorytms.Histogram;
import Algorytms.SredniaWariacja;
import GUI.MainGUI;
import GUI.SplotGUI;
import Lab.Lab;
import Lab.LabGUI;
import Luv.Luv;
import Luv.LuvGUI;
import Splot.Maximum;
import Splot.Medianowy;
import Splot.Minimum;
import Splot.Splot;

public class Main {
	private BufferedImage img = null;
	private ExpConGam ecg = null;
	private CMYK cmyk;
	private HSL hsl;
	private SredniaWariacja sredniaWariacja;
	private Histogram histogram;
	private Splot splot;
	private Minimum minimum;
	private Maximum maximum;
	private Medianowy medianowy;
	private SplotGUI splotframe;
	private MainGUI mainGui;
	private Lab lab;
	private Luv luv;

	public Main () {
	}
	
	public MainGUI getMainGui() { return mainGui; }

	public void setMainGUI(MainGUI mainGui){
		this.mainGui = mainGui;
	}
	
	public JLabel getImageLabel() {
		return mainGui.getLbl_forImage();
	}
	public BufferedImage getImg() {
		return img;
	}
	public ExpConGam getExpConGam() {
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
	
	public  SredniaWariacja getSredniaWariacja() {
		return sredniaWariacja;
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
	public Lab getLab() {
		return lab;
	}
	public Luv getLuv() {
		return luv;
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
		histogram = new Histogram(this);
		sredniaWariacja = new SredniaWariacja(this);
		splot = new Splot(img);
		minimum = new Minimum(img);
		maximum = new Maximum(img);
		medianowy = new Medianowy(img);
	}
	
	public void resetAll() {
		// TODO Auto-generated method stub		
	}

	public void startLab() {
		lab = new Lab(this);
		Main main = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabGUI frame = new LabGUI(main);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public void startLuv() {
		luv = new Luv(this);
		Main main = this;
		try {
			LuvGUI dialog = new LuvGUI(main);
			dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LuvGUI frame = new LuvGUI(main);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	

}