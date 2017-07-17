package pp2017.team10.client.gui;


import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Controls extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controls frame = new Controls();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Controls() {
		
		super("Study Dungeons Controls");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel controlsBackground = new JLabel("");
		controlsBackground.setBounds(0, 0, 584, 261);
		controlsBackground.setIcon(new ImageIcon(getClass().getResource("/Controls.jpg")));
		contentPane.add(controlsBackground);
	}
}
