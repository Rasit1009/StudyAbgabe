package pp2017.team10.client.gui;

import javax.swing.*;

import pp2017.team10.client.engine.ClientEngineGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginBB extends JFrame implements ActionListener {
	JLabel logo, user, pw, choice;
	JPanel panel;
	JTextField userInput;
	JPasswordField PWInput;
	JButton signInButton, signUpButton, cancel;
	JComboBox choice1;

	public LoginBB() {

		super("Login to Study");
		this.setSize(301, 427);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
//		this.setUndecorated(true);
		this.setLocationRelativeTo(null);

		panel = new JPanel();
		getContentPane().add(panel);
		placeLogin2Objects(panel);

		this.setVisible(true);
	}

	public void placeLogin2Objects(JPanel panel) {

		panel.setLayout(null);

		signUpButton = new JButton();
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		signUpButton.setBounds(170, 300, 120, 40);
		signUpButton.setIcon(new ImageIcon(getClass().getResource("/SignUpIcon.jpg")));
		signUpButton.setBorder(BorderFactory.createEmptyBorder());
		panel.add(signUpButton);

		signInButton = new JButton();
		signInButton.setBounds(20, 300, 120, 40);
		signInButton.setIcon(new ImageIcon(getClass().getResource("/SignInIcon.jpg")));
		signInButton.setBorder(BorderFactory.createEmptyBorder());
		signInButton.addActionListener(this);
		panel.add(signInButton);

		user = new JLabel("User:");
		user.setHorizontalAlignment(SwingConstants.TRAILING);
		user.setFont(new Font("Tahoma", Font.BOLD, 18));
		user.setBounds(10, 121, 100, 50);
		panel.add(user);

		pw = new JLabel("Password:");
		pw.setHorizontalAlignment(SwingConstants.TRAILING);
		pw.setFont(new Font("Tahoma", Font.BOLD, 18));
		pw.setBounds(10, 171, 100, 50);
		panel.add(pw);

		userInput = new JTextField();
		userInput.setBounds(111, 121, 175, 50);
		panel.add(userInput);

		PWInput = new JPasswordField(25);
		PWInput.setBounds(111, 171, 175, 50);
		panel.add(PWInput);

		choice = new JLabel("Choose Fighter:");
		choice.setFont(new Font("Tahoma", Font.BOLD, 12));
		choice.setBounds(10, 221, 100, 35);
		panel.add(choice);

		String[] choiceList = { "Anna", "Burcu", "Felix", "Rasit" };
		choice1 = new JComboBox(choiceList);
		choice1.setBounds(111, 221, 174, 30);
		panel.add(choice1);

		cancel = new JButton();
		cancel.setBounds(216, 354, 70, 40);
		cancel.setIcon(new ImageIcon(getClass().getResource("/CancelIcon.jpg")));
		cancel.setBorder(BorderFactory.createEmptyBorder());
		cancel.addActionListener(this);
		panel.add(cancel);

		logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("/LoginFrameIcon2.jpg")));
		logo.setBounds(0, 0, 300, 405);
		panel.add(logo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			System.exit(0);
		} else if (e.getSource() == signInButton) {
			// if(/*successfullLogin()==*/ true){
			try {
				ClientEngineGUI c = new ClientEngineGUI();
				c.startUp();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		}

	}

	private boolean successfullLogin() {
		// TODO Auto-generated method stub
		return true;
	}

	public static void main(String[] args) {

		new LoginBB();
	}
}
