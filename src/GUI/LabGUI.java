package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.event.ChangeListener;

import Algorytms.Lab;
import Main.Main;

import javax.swing.event.ChangeEvent;

public class LabGUI extends JFrame {

	private Lab lab;
	private JPanel contentPane;
	

	public LabGUI(Main main) {
		setResizable(false);
		lab = main.getLab();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 273, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLab = new JLabel("LAB");
		lblLab.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLab.setBounds(111, 11, 24, 20);
		contentPane.add(lblLab);
		
		JLabel lblL = new JLabel("L");
		lblL.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblL.setBounds(10, 35, 9, 23);
		contentPane.add(lblL);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblA.setBounds(10, 62, 9, 23);
		contentPane.add(lblA);
		
		JLabel lblB = new JLabel("B");
		lblB.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblB.setBounds(10, 89, 9, 23);
		contentPane.add(lblB);
		
		JSlider slider_L = new JSlider();		
		slider_L.setValue(0);
		slider_L.setMinimum(-100);
		slider_L.setBounds(23, 35, 200, 23);
		contentPane.add(slider_L);
		
		JSlider slider_A = new JSlider();
		slider_A.setMaximum(256);
		slider_A.setValue(0);
		slider_A.setMinorTickSpacing(25);
		slider_A.setMinimum(-256);
		slider_A.setBounds(23, 62, 200, 23);
		contentPane.add(slider_A);
	
		JSlider slider_B = new JSlider();
		slider_B.setMaximum(256);
		slider_B.setValue(0);
		slider_B.setMinimum(-256);
		slider_B.setBounds(23, 89, 200, 23);
		contentPane.add(slider_B);
				
		JLabel lbl_valueA = new JLabel("0");
		lbl_valueA.setBounds(227, 62, 35, 23);
		contentPane.add(lbl_valueA);
		
		JLabel lbl_valueL = new JLabel("0");
		lbl_valueL.setBounds(227, 35, 35, 23);
		contentPane.add(lbl_valueL);
		
		JLabel lbl_valueB = new JLabel("0");
		lbl_valueB.setBounds(227, 89, 35, 23);
		contentPane.add(lbl_valueB);
		
		slider_L.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int temp = slider_L.getValue();
				lbl_valueL.setText(Integer.toString(temp));
				lab.setL(temp);
			}
		});
		slider_A.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int temp = slider_A.getValue();
				lbl_valueA.setText(Integer.toString(temp));
				lab.setA(temp);
			}
		});
		slider_B.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int temp = slider_B.getValue();
				lbl_valueB.setText(Integer.toString(temp));
				lab.setB(temp);
			}
		});
	}
}
