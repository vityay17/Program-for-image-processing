package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Algorytms.Luv;
import Main.Main;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class LuvGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Luv luv;
	private JSlider slider_U;

	public LuvGUI(Main main) {
		luv = main.getLuv();
		setBackground(Color.WHITE);
		setResizable(false);
		setBounds(100, 100, 268, 188);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLub = new JLabel("LUV");
		lblLub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLub.setBounds(111, 11, 35, 20);
		contentPanel.add(lblLub);
		
		JLabel label = new JLabel("L");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label.setBounds(10, 35, 9, 23);
		contentPanel.add(label);
		
		JLabel lblU = new JLabel("U");
		lblU.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblU.setBounds(10, 62, 9, 23);
		contentPanel.add(lblU);
		
		JLabel lblV = new JLabel("V");
		lblV.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblV.setBounds(10, 89, 9, 23);
		contentPanel.add(lblV);
		
		JSlider slider_L = new JSlider();
		slider_L.setValue(0);
		slider_L.setMinimum(-100);
		slider_L.setBounds(23, 35, 200, 23);
		contentPanel.add(slider_L);
		
		slider_U = new JSlider();
		slider_U.setValue(0);
		slider_U.setMinorTickSpacing(25);
		slider_U.setMinimum(-335);
		slider_U.setMaximum(355);
		slider_U.setBounds(23, 62, 200, 23);
		contentPanel.add(slider_U);
		
		JSlider slider_V = new JSlider();
		slider_V.setValue(0);
		slider_V.setMinimum(-361);
		slider_V.setMaximum(361);
		slider_V.setBounds(23, 89, 200, 23);
		contentPanel.add(slider_V);
		
		JLabel lbl_valueV = new JLabel("0");
		lbl_valueV.setBounds(227, 89, 35, 23);
		contentPanel.add(lbl_valueV);
		
		JLabel lbl_valueU = new JLabel("0");
		lbl_valueU.setBounds(227, 62, 35, 23);
		contentPanel.add(lbl_valueU);
		
		JLabel lbl_valueL = new JLabel("0");
		lbl_valueL.setBounds(227, 35, 35, 23);
		contentPanel.add(lbl_valueL);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		slider_L.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int temp = slider_L.getValue();
				lbl_valueL.setText(Integer.toString(temp));
				luv.setL(temp);
			}
		});
		
		slider_U.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int temp = slider_U.getValue();
				lbl_valueU.setText(Integer.toString(temp));
				luv.setA(temp);
			}
		});
		slider_V.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int temp = slider_V.getValue();
				lbl_valueV.setText(Integer.toString(temp));
				luv.setB(temp);
			}
		});
	}
}
