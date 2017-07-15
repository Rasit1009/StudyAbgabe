package pp2017.team10.client.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
	JLabel logo, user, pw, choice;
	JPanel panel;
	JTextField userInput;
	JPasswordField PWInput;
	JButton ok, cancel;
	JComboBox choice1;

	public Login() {

		super("Study Dungeons");
		this.setSize(300, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		panel = new JPanel();
		this.add(panel);
		placeLogin2Objects(panel);

		this.setVisible(true);
	}

	public void placeLogin2Objects(JPanel panel) {

		panel.setLayout(null);

		logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("/logo.jpg")));
		logo.setBounds(0, 0, 300, 120);
		panel.add(logo);

		user = new JLabel("User:");
		user.setBounds(10, 121, 100, 50);
		panel.add(user);

		pw = new JLabel("Password:");
		pw.setBounds(10, 171, 100, 50);
		panel.add(pw);

		userInput = new JTextField();
		userInput.setBounds(111, 121, 175, 50);
		panel.add(userInput);

		PWInput = new JPasswordField(25);
		PWInput.setBounds(111, 171, 175, 50);
		panel.add(PWInput);

		choice = new JLabel("Choose Fighter");
		choice.setBounds(10, 221, 100, 35);
		panel.add(choice);

		String[] choiceList = { "Burcu", "Tobuias", "Felix", "Rasit" };
		choice1 = new JComboBox(choiceList);
		choice1.setBounds(111, 221, 174, 30);
		panel.add(choice1);

		ok = new JButton("OK");
		ok.setBounds(20, 270, 120, 40);
		ok.addActionListener(this);
		panel.add(ok);

		cancel = new JButton("Cancel");
		cancel.setBounds(155, 270, 120, 40);
		cancel.addActionListener(this);
		panel.add(cancel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			System.exit(0);
		} else if (e.getSource() == ok) {
			new SDFrame();
			this.dispose();
		}

	}

	public static void main(String[] args) {

		new Login();
	}

}
