import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;

public class MainWindow {
	Main main = null;
	private JFrame frame;
	private SplotFrame dialog;
	


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	
	public MainWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 960, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(718, 281, 224, 139);
		panel.setLayout(null);
		
		JLabel labelSredniaWartoscIntensywnosciRed = new JLabel("0");
		labelSredniaWartoscIntensywnosciRed.setBounds(83, 11, 49, 23);
		panel.add(labelSredniaWartoscIntensywnosciRed);
		labelSredniaWartoscIntensywnosciRed.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel lblRed = new JLabel("Red");
		lblRed.setBounds(10, 11, 20, 23);
		panel.add(lblRed);
		lblRed.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel label = new JLabel("h = ");
		label.setBounds(50, 11, 21, 23);
		panel.add(label);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel labelWariacijIntensywnosciRed = new JLabel("0");
		labelWariacijIntensywnosciRed.setBounds(179, 11, 60, 23);
		panel.add(labelWariacijIntensywnosciRed);
		labelWariacijIntensywnosciRed.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel label_4 = new JLabel("\u03C32 = ");
		label_4.setBounds(142, 11, 27, 23);
		panel.add(label_4);
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel lblGreen = new JLabel("Green");
		lblGreen.setBounds(10, 45, 31, 23);
		panel.add(lblGreen);
		lblGreen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel labelSredniaWartoscIntensywnosciGreen = new JLabel("0");
		labelSredniaWartoscIntensywnosciGreen.setBounds(83, 45, 49, 23);
		panel.add(labelSredniaWartoscIntensywnosciGreen);
		labelSredniaWartoscIntensywnosciGreen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel lblH_1 = new JLabel("h = ");
		lblH_1.setBounds(50, 45, 21, 23);
		panel.add(lblH_1);
		lblH_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel label_1 = new JLabel("\u03C32 = ");
		label_1.setBounds(142, 45, 27, 23);
		panel.add(label_1);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel labelWariacijIntensywnosciGreen = new JLabel("0");
		labelWariacijIntensywnosciGreen.setBounds(179, 45, 60, 23);
		panel.add(labelWariacijIntensywnosciGreen);
		labelWariacijIntensywnosciGreen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel lblBlue = new JLabel("Blue");
		lblBlue.setBounds(10, 78, 23, 23);
		panel.add(lblBlue);
		lblBlue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel label_2 = new JLabel("h = ");
		label_2.setBounds(50, 78, 21, 23);
		panel.add(label_2);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel labelSredniaWartoscIntensywnosciBlue = new JLabel("0");
		labelSredniaWartoscIntensywnosciBlue.setBounds(83, 78, 49, 23);
		panel.add(labelSredniaWartoscIntensywnosciBlue);
		labelSredniaWartoscIntensywnosciBlue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel labelWariacijIntensywnosciBlue = new JLabel("0");
		labelWariacijIntensywnosciBlue.setBounds(179, 78, 60, 23);
		panel.add(labelWariacijIntensywnosciBlue);
		labelWariacijIntensywnosciBlue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel label_5 = new JLabel("\u03C32 = ");
		label_5.setBounds(142, 78, 27, 23);
		panel.add(label_5);
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel lblGray = new JLabel("Gray");
		lblGray.setBounds(10, 112, 24, 23);
		panel.add(lblGray);
		lblGray.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel labelSredniaWartoscIntensywnosciGray = new JLabel("0");
		labelSredniaWartoscIntensywnosciGray.setBounds(83, 112, 49, 23);
		panel.add(labelSredniaWartoscIntensywnosciGray);
		labelSredniaWartoscIntensywnosciGray.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel label_3 = new JLabel("h = ");
		label_3.setBounds(50, 112, 21, 23);
		panel.add(label_3);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel label_6 = new JLabel("\u03C32 = ");
		label_6.setBounds(142, 112, 27, 23);
		panel.add(label_6);
		label_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel labelWariacijIntensywnosciGray = new JLabel("0");
		labelWariacijIntensywnosciGray.setBounds(179, 112, 60, 23);
		panel.add(labelWariacijIntensywnosciGray);
		labelWariacijIntensywnosciGray.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(439, 416, 269, 145);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(439, 294, 269, 111);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(1013, 316, 255, 550);
		panel_3.setLayout(null);
		panel_3.setLayout(new MigLayout("", "[231px]", "[134.00][158.00px][164px][164px]"));
		panel_3.setLayout(new MigLayout("", "[241.00px]", "[131.00px][131.00][131.00][131.00]"));
		
		JLabel labelHistogramR = new JLabel("New label");
		panel_3.add(labelHistogramR, "cell 0 0,grow");
		
		JLabel labelHistogramG = new JLabel("New label");
		panel_3.add(labelHistogramG, "cell 0 1,grow");
		
		JLabel labelHistogramB = new JLabel("New label");
		panel_3.add(labelHistogramB, "cell 0 2,grow");
		
		JLabel labelHistogramGray = new JLabel("New label");
		panel_3.add(labelHistogramGray, "cell 0 3,grow");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(718, 431, 224, 86);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBounds(718, 528, 205, 33);
				panel_4.setLayout(null);
								
				JLabel lblExposure = new JLabel("Exposure");
				lblExposure.setBounds(7, 7, 45, 23);
				panel_4.add(lblExposure);
				
				JLabel lblContrast = new JLabel("Contrast");
				lblContrast.setBounds(7, 34, 45, 23);
				panel_4.add(lblContrast);
				
				JLabel lblGamma = new JLabel("Gamma");
				lblGamma.setBounds(7, 61, 45, 23);
				panel_4.add(lblGamma);
				
				JLabel lblCurrentValueExposure = new JLabel("0");
				lblCurrentValueExposure.setBounds(194, 7, 43, 23);
				panel_4.add(lblCurrentValueExposure);
				
				JLabel lblCurrentValueContrast = new JLabel("0");
				lblCurrentValueContrast.setBounds(194, 34, 43, 23);
				panel_4.add(lblCurrentValueContrast);
				
				JLabel lblCurrentValueGamma = new JLabel("0");
				lblCurrentValueGamma.setBounds(194, 61, 43, 23);
				panel_4.add(lblCurrentValueGamma);
				
				JSlider sliderExposure = new JSlider();
				sliderExposure.setBounds(56, 7, 134, 23);
				panel_4.add(sliderExposure);
				sliderExposure.setValue(0);
				sliderExposure.setMinimum(-100);
				sliderExposure.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						lblCurrentValueExposure.setText(Integer.toString(sliderExposure.getValue()));
						main.changeExposure(sliderExposure.getValue());
					}
				});
				
				JSlider sliderContrast = new JSlider();
				sliderContrast.setBounds(56, 34, 134, 23);
				panel_4.add(sliderContrast);
				sliderContrast.setValue(0);
				sliderContrast.setMinimum(-100);
				sliderContrast.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						lblCurrentValueContrast.setText(Integer.toString(sliderContrast.getValue()));
						main.changeContrast(sliderContrast.getValue());
					}
				});
				
				JSlider sliderGamma = new JSlider();
				sliderGamma.setBounds(56, 61, 134, 23);
				panel_4.add(sliderGamma);
				sliderGamma.setValue(10);
				sliderGamma.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						lblCurrentValueGamma.setText(Double.toString((double)sliderGamma.getValue()/10));
						main.changeGamma(sliderGamma.getValue());
					}
				});
				
			JButton btnNewButton_1 = new JButton("Splot");
			panel_5.add(btnNewButton_1);
			btnNewButton_1.setBounds(10, 11, 57, 23);
			
			JButton btnOpen = new JButton("open");
			panel_5.add(btnOpen);
			btnOpen.setBounds(158, 11, 57, 23);
			
			JButton btnNewButton = new JButton("reset all");
			panel_5.add(btnNewButton);
			btnNewButton.setBounds(77, 11, 71, 23);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
//				main.Show(lblImage);
				}
			});
			
			btnOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(main.openImage() == true){
//						main.getHistogram().setLabels(labelHistogramR, labelHistogramG, labelHistogramB, labelHistogramGray);
//						main.getHistogram().setLabelsSredniaWartoscIntensywnosci(labelSredniaWartoscIntensywnosciRed, 
//								labelSredniaWartoscIntensywnosciGreen, labelSredniaWartoscIntensywnosciBlue, 
//								labelSredniaWartoscIntensywnosciGray, labelWariacijIntensywnosciRed, 
//								labelWariacijIntensywnosciGreen, labelWariacijIntensywnosciBlue, labelWariacijIntensywnosciGray);
//						main.getHistogram().calculateHistogram(main.getImg());
					}
				}
			});
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						dialog = SplotFrame.getInstance(main);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						main.setSplotFrame(dialog);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			frame.getContentPane().setLayout(null);
			panel_1.setLayout(null);
			panel_1.setLayout(null);
			
			JLabel lblCmyk = new JLabel("CMYK");
			lblCmyk.setBounds(103, 7, 38, 20);
			panel_1.add(lblCmyk);
			lblCmyk.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			
			JLabel labelCurrentCMYK_C = new JLabel("0");
			labelCurrentCMYK_C.setBounds(226, 31, 35, 23);
			panel_1.add(labelCurrentCMYK_C);
			
			JLabel labelCurrentCMYK_M = new JLabel("0");
			labelCurrentCMYK_M.setBounds(226, 58, 35, 23);
			panel_1.add(labelCurrentCMYK_M);
			
			JLabel labelCurrentCMYK_Y = new JLabel("0");
			labelCurrentCMYK_Y.setBounds(226, 85, 35, 23);
			panel_1.add(labelCurrentCMYK_Y);
			
			JLabel labelCurrentCMYK_K = new JLabel("0");
			labelCurrentCMYK_K.setBounds(226, 112, 35, 23);
			panel_1.add(labelCurrentCMYK_K);
			
			JLabel lblC = new JLabel("C");
			lblC.setBounds(7, 31, 11, 23);
			panel_1.add(lblC);
			lblC.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			
			JLabel lblM = new JLabel("M");
			lblM.setBounds(7, 58, 11, 23);
			panel_1.add(lblM);
			lblM.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			
			JLabel lblY = new JLabel("Y");
			lblY.setBounds(7, 85, 11, 23);
			panel_1.add(lblY);
			lblY.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			
			JLabel lblK = new JLabel("K");
			lblK.setBounds(7, 112, 11, 23);
			panel_1.add(lblK);
			lblK.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			
			JSlider sliderCMYK_C = new JSlider();
			sliderCMYK_C.setBounds(22, 31, 200, 23);
			panel_1.add(sliderCMYK_C);
			sliderCMYK_C.setValue(0);
			sliderCMYK_C.setMinimum(-100);
			sliderCMYK_C.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					labelCurrentCMYK_C.setText(Integer.toString(sliderCMYK_C.getValue()));
					main.changeCMYK_C(sliderCMYK_C.getValue());
				}
			});
			
			JSlider sliderCMYK_M = new JSlider();
			sliderCMYK_M.setBounds(22, 58, 200, 23);
			panel_1.add(sliderCMYK_M);
			sliderCMYK_M.setValue(0);
			sliderCMYK_M.setMinimum(-100);
			sliderCMYK_M.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					labelCurrentCMYK_M.setText(Integer.toString(sliderCMYK_M.getValue()));
					main.changeCMYK_M(sliderCMYK_M.getValue());
				}
			});
			
			JSlider sliderCMYK_Y = new JSlider();
			sliderCMYK_Y.setBounds(22, 85, 200, 23);
			panel_1.add(sliderCMYK_Y);
			sliderCMYK_Y.setValue(0);
			sliderCMYK_Y.setMinimum(-100);
			sliderCMYK_Y.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					labelCurrentCMYK_Y.setText(Integer.toString(sliderCMYK_Y.getValue()));
					main.changeCMYK_Y(sliderCMYK_Y.getValue());
				}
			});
			
			JSlider sliderCMYK_K = new JSlider();
			sliderCMYK_K.setBounds(22, 112, 200, 23);
			panel_1.add(sliderCMYK_K);
			sliderCMYK_K.setValue(0);
			sliderCMYK_K.setMinimum(-100);
			sliderCMYK_K.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					labelCurrentCMYK_K.setText(Integer.toString(sliderCMYK_K.getValue()));
					main.changeCMYK_K(sliderCMYK_K.getValue());
				}
			});
			panel_2.setLayout(null);
			panel_2.setLayout(null);
			
			JLabel lblHsl = new JLabel("HSL");
			lblHsl.setBounds(108, 7, 24, 20);
			lblHsl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			panel_2.add(lblHsl);
			
			JLabel labelHSL_H = new JLabel("0");
			labelHSL_H.setBounds(224, 31, 35, 23);
			panel_2.add(labelHSL_H);
			
			JLabel labelHSL_S = new JLabel("0");
			labelHSL_S.setBounds(224, 58, 35, 23);
			panel_2.add(labelHSL_S);
			
			JLabel labelHSL_L = new JLabel("0");
			labelHSL_L.setBounds(224, 85, 35, 23);
			panel_2.add(labelHSL_L);
			
			JSlider sliderHSL_H = new JSlider();
			sliderHSL_H.setBounds(20, 31, 200, 23);
			sliderHSL_H.setMaximum(180);
			sliderHSL_H.setValue(0);
			sliderHSL_H.setMinimum(-180);
			panel_2.add(sliderHSL_H);
			sliderHSL_H.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					labelHSL_H.setText(Integer.toString(sliderHSL_H.getValue()));
					main.changeHSL_H(sliderHSL_H.getValue());
				}
			});
			
			JSlider sliderHSL_S = new JSlider();
			sliderHSL_S.setBounds(20, 58, 200, 23);
			sliderHSL_S.setMinimum(-100);
			sliderHSL_S.setMinorTickSpacing(25);
			sliderHSL_S.setValue(0);
			panel_2.add(sliderHSL_S);
			sliderHSL_S.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					labelHSL_S.setText(Integer.toString(sliderHSL_S.getValue()));
					main.changeHSL_S(sliderHSL_S.getValue());
				}
			});
			
			JSlider sliderHSL_L = new JSlider();
			sliderHSL_L.setBounds(20, 85, 200, 23);
			sliderHSL_L.setValue(0);
			sliderHSL_L.setMinimum(-100);
			panel_2.add(sliderHSL_L);
			sliderHSL_L.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					labelHSL_L.setText(Integer.toString(sliderHSL_L.getValue()));
					main.changeHSL_L(sliderHSL_L.getValue());
				}
			});
			
			JLabel lblH = new JLabel("H");
			lblH.setBounds(7, 31, 9, 23);
			lblH.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			panel_2.add(lblH);
			
			JLabel lblS = new JLabel("S");
			lblS.setBounds(7, 58, 9, 23);
			lblS.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			panel_2.add(lblS);
			
			JLabel lblL = new JLabel("L");
			lblL.setBounds(7, 85, 9, 23);
			lblL.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			panel_2.add(lblL);
			frame.getContentPane().setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(7, 11, 422, 546);
			frame.getContentPane().add(scrollPane);
			
			JLabel lblImage = new JLabel("");
			scrollPane.setViewportView(lblImage);
			
			main = new Main(lblImage);
			frame.getContentPane().add(panel_4);
			frame.getContentPane().add(panel_3);			
			frame.getContentPane().add(panel);
			frame.getContentPane().add(panel_2);
			frame.getContentPane().add(panel_1);
			frame.getContentPane().add(panel_5);
			
			JPanel panelHistogram = new JPanel();
			panelHistogram.setBounds(439, 11, 495, 250);
			frame.getContentPane().add(panelHistogram);
			
			main.setPanelHistogram(panelHistogram);
			panelHistogram.setLayout(new GridLayout(1, 0, 0, 0));
			
			JButton btnFurier = new JButton("Furier");
			btnFurier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						FurierImplementationGUI FurierImplementationGUIInstance = FurierImplementationGUI.getInstance(main);
						FurierImplementationGUIInstance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						FurierImplementationGUIInstance.setVisible(true);
//						main.setSplotFrame(FurierImplementationGUIInstance);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			btnFurier.setBounds(439, 270, 89, 23);
			frame.getContentPane().add(btnFurier);
		
			
			
	}
}
