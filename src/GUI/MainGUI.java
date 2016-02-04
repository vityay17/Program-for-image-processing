package GUI;
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

import Main.Main;

import java.awt.GridLayout;

public class MainGUI {
	private Main main;
	private JFrame frame;
	private SplotGUI splotGui;
	private JLabel lbl_forImage;
	private JPanel panelForHistogram;

	public MainGUI(Main main) {
		this.main = main;
		main.setMainGUI(this);
		initialize();
	}

	public JFrame getframe() {
		return frame;
	}

	public JLabel getLbl_forImage() {
		return lbl_forImage;
	}

	public JPanel getPanelForHistogram() {
		return panelForHistogram;
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
		
		JSlider slider_Exposure = new JSlider();
		slider_Exposure.setBounds(56, 7, 134, 23);
		panel_4.add(slider_Exposure);
		slider_Exposure.setValue(0);
		slider_Exposure.setMinimum(-100);
		
				
		JSlider slider_Contrast = new JSlider();
		slider_Contrast.setBounds(56, 34, 134, 23);
		panel_4.add(slider_Contrast);
		slider_Contrast.setValue(0);
		slider_Contrast.setMinimum(-100);
		
		JSlider slider_Gamma = new JSlider();
		slider_Gamma.setBounds(56, 61, 134, 23);
		panel_4.add(slider_Gamma);
		slider_Gamma.setValue(10);
		slider_Gamma.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblCurrentValueGamma.setText(Double.toString((double)slider_Gamma.getValue()/10));
				main.changeGamma(slider_Gamma.getValue());
			}
		});
				
		JButton btn_SplotOpen = new JButton("Splot");
		panel_5.add(btn_SplotOpen);
		btn_SplotOpen.setBounds(10, 11, 57, 23);
		
		JButton btn_ImageOpen = new JButton("open");
		panel_5.add(btn_ImageOpen);
		btn_ImageOpen.setBounds(158, 11, 57, 23);
		
		JButton btn_Reset_All = new JButton("reset all");
		panel_5.add(btn_Reset_All);
		btn_Reset_All.setBounds(77, 11, 71, 23);
		
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
		
		JSlider sliderCMYK_M = new JSlider();
		sliderCMYK_M.setBounds(22, 58, 200, 23);
		panel_1.add(sliderCMYK_M);
		sliderCMYK_M.setValue(0);
		sliderCMYK_M.setMinimum(-100);
		
		JSlider sliderCMYK_Y = new JSlider();
		sliderCMYK_Y.setBounds(22, 85, 200, 23);
		panel_1.add(sliderCMYK_Y);
		sliderCMYK_Y.setValue(0);
		sliderCMYK_Y.setMinimum(-100);
			
		JSlider sliderCMYK_K = new JSlider();
		sliderCMYK_K.setBounds(22, 112, 200, 23);
		panel_1.add(sliderCMYK_K);
		sliderCMYK_K.setValue(0);
		sliderCMYK_K.setMinimum(-100);
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
		
		JSlider sliderHSL_S = new JSlider();
		sliderHSL_S.setBounds(20, 58, 200, 23);
		sliderHSL_S.setMinimum(-100);
		sliderHSL_S.setMinorTickSpacing(25);
		sliderHSL_S.setValue(0);
		panel_2.add(sliderHSL_S);
		
		JSlider sliderHSL_L = new JSlider();
		sliderHSL_L.setBounds(20, 85, 200, 23);
		sliderHSL_L.setValue(0);
		sliderHSL_L.setMinimum(-100);
		panel_2.add(sliderHSL_L);
				
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
		
		lbl_forImage = new JLabel("");
		scrollPane.setViewportView(lbl_forImage);
	
		frame.getContentPane().add(panel_4);
		frame.getContentPane().add(panel_3);			
		frame.getContentPane().add(panel);
		frame.getContentPane().add(panel_2);
		frame.getContentPane().add(panel_1);
		frame.getContentPane().add(panel_5);
		
		panelForHistogram = new JPanel();
		panelForHistogram.setBounds(439, 11, 495, 250);
		frame.getContentPane().add(panelForHistogram);
		
		panelForHistogram.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnFurier = new JButton("Furier");
		btnFurier.setBounds(439, 270, 89, 23);
		frame.getContentPane().add(btnFurier);
		
		slider_Exposure.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblCurrentValueExposure.setText(Integer.toString(slider_Exposure.getValue()));
				main.changeExposure(slider_Exposure.getValue());
			}
		});
		slider_Contrast.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblCurrentValueContrast.setText(Integer.toString(slider_Contrast.getValue()));
				main.changeContrast(slider_Contrast.getValue());
			}
		});
			
		
		sliderHSL_H.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				labelHSL_H.setText(Integer.toString(sliderHSL_H.getValue()));
				main.changeHSL_H(sliderHSL_H.getValue());
			}
		});
		sliderHSL_S.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				labelHSL_S.setText(Integer.toString(sliderHSL_S.getValue()));
				main.changeHSL_S(sliderHSL_S.getValue());
			}
		});
		sliderHSL_L.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				labelHSL_L.setText(Integer.toString(sliderHSL_L.getValue()));
				main.changeHSL_L(sliderHSL_L.getValue());
			}
		});
		
		sliderCMYK_C.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				labelCurrentCMYK_C.setText(Integer.toString(sliderCMYK_C.getValue()));
				main.changeCMYK_C(sliderCMYK_C.getValue());
			}
		});
		sliderCMYK_M.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				labelCurrentCMYK_M.setText(Integer.toString(sliderCMYK_M.getValue()));
				main.changeCMYK_M(sliderCMYK_M.getValue());
			}
		});
		sliderCMYK_Y.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				labelCurrentCMYK_Y.setText(Integer.toString(sliderCMYK_Y.getValue()));
				main.changeCMYK_Y(sliderCMYK_Y.getValue());
			}
		});
		sliderCMYK_K.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				labelCurrentCMYK_K.setText(Integer.toString(sliderCMYK_K.getValue()));
				main.changeCMYK_K(sliderCMYK_K.getValue());
			}
		});
		
		btn_Reset_All.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				main.Show(lblImage);
			}
		});
		
		btn_ImageOpen.addActionListener(new ActionListener() {
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
		btn_SplotOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					splotGui = SplotGUI.getInstance(main);
					splotGui.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					splotGui.setVisible(true);
					main.setSplotFrame(splotGui);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnFurier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FurierGUI FurierImplementationGUIInstance = FurierGUI.getInstance(main);
					FurierImplementationGUIInstance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					FurierImplementationGUIInstance.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}
