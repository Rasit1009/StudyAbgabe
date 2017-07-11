package pp2017.team10.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Logout extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7291962828086897504L;
	JPanel logpanel;
	JLabel logtext;
	JButton logout, notlogout;

	public Logout() {
		super("Logout?");
		this.setSize(250, 130);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		logpanel = new JPanel();
		this.add(logpanel);
		placeLogin3Objects(logpanel);

		this.setVisible(true);
	}

	public void placeLogin3Objects(JPanel logpanel) {
		logpanel.setLayout(null);

		logtext = new JLabel("Would you like to Logout?");
		logtext.setHorizontalAlignment(JLabel.CENTER);
		logtext.setBounds(0, 0, 250, 40);
		logpanel.add(logtext);

		logout = new JButton("Yes");
		logout.setLayout(null);
		logout.setBounds(20, 50, 80, 30);
		logout.addActionListener(this);
		logpanel.add(logout);

		notlogout = new JButton("Study On!");
		notlogout.setLayout(null);
		notlogout.setBounds(110, 50, 120, 30);
		notlogout.addActionListener(this);
		logpanel.add(notlogout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logout) {
			System.exit(0);
		} else if (e.getSource() == notlogout) {
			this.dispose();
		}

	}

	public static void main(String[] args) {
		new Logout();

	}

}
