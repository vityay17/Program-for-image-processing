package Main;

import java.awt.EventQueue;

import GUI.MainGUI;

public class StartHere {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI(new Main());
					window.getframe().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

}
