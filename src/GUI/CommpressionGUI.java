package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.event.ChangeListener;

import Compression.Compression;
import Compression.Decompression;
import Main.Main;

import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class CommpressionGUI extends JFrame {

	private JPanel contentPane;
	static JLabel lbl_Image;

	public CommpressionGUI(Main main) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lbl_Image = new JLabel("");
		panel.add(lbl_Image);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnCreate = new JButton("create");

		panel_1.add(btnCreate);
		
		JButton btnLoad = new JButton("load");
		
		panel_1.add(btnLoad);
		
		JSlider slider_Quality = new JSlider();
		slider_Quality.setMaximum(99);
		slider_Quality.setMinimum(1);
		
		panel_1.add(slider_Quality);
		
		JLabel lbl_Quality = new JLabel("50");
		panel_1.add(lbl_Quality);
		
		JButton btnStart = new JButton("start");
		panel_1.add(btnStart);
		
		slider_Quality.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int temp = slider_Quality.getValue();
				lbl_Quality.setText(Integer.toString(temp));
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(main.isImgLoaded()){
					new Compression(main).calculate(slider_Quality.getValue());
					new Decompression();
				}
				else JOptionPane.showMessageDialog(null, "You must loaded image first.");
			}
		});
				
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(main.isImgLoaded()){
					JFileChooser fileopen = new JFileChooser();
					int ret = fileopen.showSaveDialog(null); 
					if (ret == JFileChooser.APPROVE_OPTION) {
					    String fileToSave = fileopen.getSelectedFile().getAbsolutePath();
					    new Compression(main, new File(fileToSave)).calculate(slider_Quality.getValue());
					}
				}
				else JOptionPane.showMessageDialog(null, "You must loaded image first.");
			}
		});
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser();
				int ret = fileopen.showSaveDialog(null); 
				if (ret == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileopen.getSelectedFile();
				    new Decompression(fileToSave);
				}
			}
		});
	}

	public static JLabel getLbl_Image() {
		return lbl_Image;
	}

}
