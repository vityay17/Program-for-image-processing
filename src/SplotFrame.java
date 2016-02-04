import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SplotFrame extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SplotFrame instance = null;
	private Main main;
	
	private JTextField textFieldSizeOfMask;
	private JButton btnSplotRun;
	private JTextField textFieldSetToAll;
	private JButton btnSetToAll;
	private Histogram_01 histogram;
	private JTextField[][] textFieldGrid;
	private JScrollPane scrollPane;
	private JTextField textFieldFiGauss;
	private JTextField textFieldAGauss;
	private JTextField textFieldAUnsharp;
	
	FurierImplementationGUI FurierImplementationGUIInstance;
	 
	public void drawHistogram(BufferedImage image){
//		histogram.setImage(image);
	}
	public static SplotFrame getInstance(Main main){
		if(instance == null)
			instance = new SplotFrame(main);
		return instance;
	}
	

	
	private SplotFrame(Main main) {
		this.main = main;
		setBounds(100, 100, 996, 847);
		
		histogram = new Histogram_01();
//		drawHistogram(main.getImg());
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 768, 960, 30);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(226, 11, 744, 741);
		getContentPane().add(scrollPane);
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		textFieldSizeOfMask = new JTextField();
		textFieldSizeOfMask.setToolTipText("");
		textFieldSizeOfMask.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSizeOfMask.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSizeOfMask.setBounds(10, 11, 192, 30);
		textFieldSizeOfMask.addKeyListener(new KeyAdapter() {
			 @Override
		    public void keyReleased(KeyEvent event) {
				 try{
					panel.removeAll();
					int radius = Integer.valueOf(textFieldSizeOfMask.getText());
					int size = radius * 2 + 1;
					textFieldGrid = new JTextField[size][size];
					panel.setLayout(new GridLayout(size, size));
			    	for(int i = 0; i < size; i++){
			    		for(int j = 0; j < size; j++){
							JTextField tx = new JTextField();
							tx.setColumns(5);
							textFieldGrid[i][j] = tx;
							panel.add(tx);
			    		}
					}
			    	calculateGaussElements();
					panel.repaint();
					validate();
				} catch(NumberFormatException e){
				}
		    }
		});
		textFieldSizeOfMask.setColumns(10);
		
		btnSplotRun = new JButton("splot");
		btnSplotRun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSplotRun.setBounds(10, 51, 192, 30);
		btnSplotRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[][] maska = getArrayInt();
				BufferedImage img = main.getSplot().run(maska);
				main.getImageLabel().setIcon(new ImageIcon(img));
			}
		});

		
		textFieldSetToAll = new JTextField();
		textFieldSetToAll.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				setToAllField(textFieldSetToAll.getText());
			}
		});
			textFieldSetToAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textFieldSetToAll.setBounds(116, 92, 86, 30);
			textFieldSetToAll.setColumns(10);
		
		btnSetToAll = new JButton("set to all");
			btnSetToAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnSetToAll.setBounds(10, 92, 96, 30);
			btnSetToAll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setToAllField(textFieldSetToAll.getText());
				}
			});
		getContentPane().setLayout(null);
		getContentPane().add(btnSplotRun);
		getContentPane().add(textFieldSizeOfMask);
		getContentPane().add(btnSetToAll);
		getContentPane().add(textFieldSetToAll);
		getContentPane().add(progressBar);
		
		JLabel lblNewLabel = new JLabel("\u03C3:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 133, 21, 30);
		getContentPane().add(lblNewLabel);
		
		textFieldFiGauss = new JTextField();
		textFieldFiGauss.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldFiGauss.setColumns(10);
		textFieldFiGauss.setBounds(51, 133, 151, 30);
		textFieldFiGauss.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calculateGaussElements();
			}
		});
		getContentPane().add(textFieldFiGauss);
		
		textFieldAGauss = new JTextField();
		textFieldAGauss.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calculateGaussElements();
			}
		});
		textFieldAGauss.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldAGauss.setColumns(10);
		textFieldAGauss.setBounds(51, 174, 151, 30);
		getContentPane().add(textFieldAGauss);
		
		JButton btnGauss = new JButton("gauss");
		btnGauss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int r = Integer.valueOf(textFieldSizeOfMask.getText());
				int fi = Integer.valueOf(textFieldFiGauss.getText());
				int a = Integer.valueOf(textFieldAGauss.getText());
				
				int[][] maskaGauss = setGausMask( r, fi,  a);
				BufferedImage img = main.getSplot().run(maskaGauss);
				main.getImageLabel().setIcon(new ImageIcon(img));
			}
		});
		btnGauss.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGauss.setBounds(10, 215, 192, 30);
		getContentPane().add(btnGauss);
		
		textFieldAUnsharp = new JTextField();
		textFieldAUnsharp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldAUnsharp.setColumns(10);
		textFieldAUnsharp.setBounds(51, 256, 151, 30);
		getContentPane().add(textFieldAUnsharp);
		
		JButton btnUnsharp = new JButton("unsharp");
		btnUnsharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = Integer.valueOf(textFieldSizeOfMask.getText());
				int fi = Integer.valueOf(textFieldFiGauss.getText());
				int a = Integer.valueOf(textFieldAGauss.getText());
				int afi = Integer.valueOf(textFieldAUnsharp.getText());
				
				int[][] maskaGauss = setGausMask( r, fi,  a);
				BufferedImage img = main.getSplot().run(maskaGauss);
				BufferedImage imgTemp = main.getSplot().calculateUnsharpMask(img, afi);
				main.getImageLabel().setIcon(new ImageIcon(imgTemp));
			}
		});
		btnUnsharp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUnsharp.setBounds(10, 297, 192, 30);
		getContentPane().add(btnUnsharp);
		
		JLabel lblA = new JLabel("a:");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblA.setBounds(20, 174, 21, 30);
		getContentPane().add(lblA);
		
		JLabel labelAUnsharp = new JLabel("\u03B1:");
		labelAUnsharp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelAUnsharp.setBounds(20, 256, 21, 30);
		getContentPane().add(labelAUnsharp);
		
		JButton btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.getImageLabel().setIcon(new ImageIcon(main.getSplot().getOriginalImage()));
				main.getImageLabel().setIcon(new ImageIcon(main.getMinimum().getOriginalImage()));
				main.getImageLabel().setIcon(new ImageIcon(main.getMaximum().getOriginalImage()));
				main.getImageLabel().setIcon(new ImageIcon(main.getMedianowy().getOriginalImage()));
				setToAllField("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(10, 722, 192, 30);
		getContentPane().add(btnReset);							
		
		JButton btnMinimum = new JButton("minimum");
		btnMinimum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[][] maska = getArrayInt();
				BufferedImage img = main.getMinimum().run(maska);
				main.getImageLabel().setIcon(new ImageIcon(img));
			}
		});
		btnMinimum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMinimum.setBounds(10, 338, 192, 30);
		getContentPane().add(btnMinimum);
		
		JButton btnMaximum = new JButton("maximum");
		btnMaximum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[][] maska = getArrayInt();
				BufferedImage img = main.getMaximum().run(maska);
				main.getImageLabel().setIcon(new ImageIcon(img));
			}
		});
		btnMaximum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMaximum.setBounds(10, 379, 192, 30);
		getContentPane().add(btnMaximum);
		
		JButton btnMedianowy = new JButton("medianowy");
		btnMedianowy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[][] maska = getArrayInt();
				BufferedImage img = main.getMedianowy().run(maska);
				main.getImageLabel().setIcon(new ImageIcon(img));
			}
		});
		btnMedianowy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMedianowy.setBounds(10, 420, 192, 30);
		getContentPane().add(btnMedianowy);
		
		JButton btnFurier = new JButton("Furier");
		btnFurier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					FurierImplementationGUIInstance = FurierImplementationGUI.getInstance(main);
					FurierImplementationGUIInstance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					FurierImplementationGUIInstance.setVisible(true);
//					main.setSplotFrame(FurierImplementationGUIInstance);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnFurier.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFurier.setBounds(10, 522, 192, 30);
		getContentPane().add(btnFurier);
	}
	
	public int[][] setGausMask(int r,int fi, int a){
		int t = r; 
		int s = t;

		for(int i = -s;i <= s; i++){
	        for(int j = -t;j <= t; j++){
	        	if(i == 0 && j == 0){
	        		textFieldGrid[i +r][j+r].setText(String.valueOf(a));
	        	}else{
	        		double temp1 = -(Math.pow(i,2)+Math.pow(j,2));
	        		double temp2 = 2*Math.pow(fi,2);
	        		int result = (int)(a * Math.pow(Math.E,temp1/temp2));
		        	textFieldGrid[i+r][j+r].setText(String.valueOf(result));
	        	}
	        }
		}
		int[][] maska = getArrayInt();				
		return maska;
	}
	private int[][] getArrayInt(){
		int[][] maska = new int[textFieldGrid.length][textFieldGrid.length];
		for(int i = 0; i < textFieldGrid.length; i++){
    		for(int j = 0; j < textFieldGrid[i].length; j++){
    			try{
    				maska[i][j] = Integer.valueOf(textFieldGrid[i][j].getText());
    			} catch(NumberFormatException e){
    			}
    		}
		}
		return maska;
	}
	private void setToAllField(String s){
		if(textFieldGrid.length != 0){
			for(int i = 0; i < textFieldGrid.length; i++){
	    		for(int j = 0; j < textFieldGrid[i].length; j++){
	    			textFieldGrid[i][j].setText(s);
	    		}
			}
		}
	}
	private void calculateGaussElements(){
		 try{
				int r = Integer.valueOf(textFieldSizeOfMask.getText());
				int fi = Integer.valueOf(textFieldFiGauss.getText());
				int a = Integer.valueOf(textFieldAGauss.getText());
				
				int t = r; 
				int s = t;

				for(int i = -s;i <= s; i++){
			        for(int j = -t;j <= t; j++){
			        	if(i == 0 && j == 0){
			        		textFieldGrid[i +r][j+r].setText(String.valueOf(a));
			        	}else{
			        		double temp1 = -(Math.pow(i,2)+Math.pow(j,2));
			        		double temp2 = 2*Math.pow(fi,2);
			        		int result = (int)(a * Math.pow(Math.E,temp1/temp2));
				        	textFieldGrid[i+r][j+r].setText(String.valueOf(result));
			        	}
			        }
				}
			} catch(NumberFormatException e){
			}
	}
}
