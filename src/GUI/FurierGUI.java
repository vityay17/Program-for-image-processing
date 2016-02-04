package GUI;

import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import Algorytms.FurierImpl;
import Main.Main;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class FurierGUI extends JDialog {
	private Main main;
	private FurierImpl furierImpl;
	private static final long serialVersionUID = 1L;

	private static FurierGUI instance = null;
	public static FurierGUI getInstance(Main main){
		if(instance == null)
			instance = new FurierGUI(main);
		return instance;
	}

	private FurierGUI(Main main) {
		this.main = main;
		furierImpl = new FurierImpl(main.getImg());
		
		setBounds(100, 100, 766, 669);
		
		getContentPane().setLayout(null);
		
		
		
		
		JButton btnSpektrum = new JButton("spektrum");
	
		btnSpektrum.setBounds(10, 137, 118, 52);
		getContentPane().add(btnSpektrum);
		
		JButton btnFaza = new JButton("faza");
		
		btnFaza.setBounds(10, 200, 118, 52);
		getContentPane().add(btnFaza);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(138, 11, 602, 609);
		getContentPane().add(scrollPane);
		
		JLabel lbl_Furier = new JLabel("");
		scrollPane.setViewportView(lbl_Furier);
		

		JButton btnNewButton = new JButton("czesc rzeczywista");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage imgTemp = furierImpl.getRealisImage();
				lbl_Furier.setIcon(new ImageIcon(imgTemp)); 
				repaint();
			}
		});
		btnNewButton.setBounds(10, 11, 118, 52);
		getContentPane().add(btnNewButton);
		
		JButton btnCzescUrojona = new JButton("czesc urojona");
		btnCzescUrojona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage imgTemp = furierImpl.getImaginalisImage();
				lbl_Furier.setIcon(new ImageIcon(imgTemp)); 
				repaint();
			}
		});
		btnCzescUrojona.setBounds(10, 74, 118, 52);
		getContentPane().add(btnCzescUrojona);
		
		btnSpektrum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage imgTemp = furierImpl.getSpektrumImage();
				lbl_Furier.setIcon(new ImageIcon(imgTemp)); 
				repaint();
			}
		});
		btnFaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage imgTemp = furierImpl.getFazaImage();
				lbl_Furier.setIcon(new ImageIcon(imgTemp)); 
				repaint();
			}
		});
	}

}
