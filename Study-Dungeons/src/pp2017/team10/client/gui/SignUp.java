package pp2017.team10.client.gui;

import javax.swing.*;

import pp2017.team10.client.engine.ClientEngineGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignUp extends JFrame implements ActionListener {
	JLabel logo, user, passwordLabel1, choice;
	JPanel panel;
	JTextField userInput;
	JPasswordField passwortField1;
	JButton signUpButton, cancel;
	JComboBox choice1;
	private JPasswordField passwordField2;
	private JLabel passwordLabel2;

	public SignUp() {

		super("Create an Account");
		this.setSize(305, 425);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		panel = new JPanel();
		getContentPane().add(panel);
		placeLogin2Objects(panel);

		this.setVisible(true);
	}

	public void placeLogin2Objects(JPanel panel) {

		panel.setLayout(null);

		passwordLabel2 = new JLabel("Repeat Password:");
		passwordLabel2.setForeground(Color.WHITE);
		passwordLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel2.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel2.setBounds(0, 209, 120, 50);
		panel.add(passwordLabel2);

		passwordField2 = new JPasswordField(25);
		passwordField2.setBounds(120, 210, 175, 50);
		panel.add(passwordField2);

		signUpButton = new JButton();
		signUpButton.setBounds(10, 341, 120, 40);
		signUpButton.setBorder(BorderFactory.createEmptyBorder());
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		signUpButton.setIcon(new ImageIcon(getClass().getResource("/SignUpIcon.jpg")));
		panel.add(signUpButton);

		user = new JLabel("Enter Username:");
		user.setForeground(Color.WHITE);
		user.setHorizontalAlignment(SwingConstants.TRAILING);
		user.setFont(new Font("Tahoma", Font.BOLD, 12));
		user.setBounds(10, 97, 100, 50);
		panel.add(user);

		passwordLabel1 = new JLabel("Set Password:");
		passwordLabel1.setForeground(Color.WHITE);
		passwordLabel1.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordLabel1.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel1.setBounds(10, 160, 100, 50);
		panel.add(passwordLabel1);

		userInput = new JTextField();
		userInput.setBounds(120, 98, 175, 50);
		panel.add(userInput);

		passwortField1 = new JPasswordField(25);
		passwortField1.setBounds(120, 163, 175, 50);
		panel.add(passwortField1);

		String[] choiceList = { "Anna", "Burcu", "Felix", "Rasit" };
		choice1 = new JComboBox(choiceList);
		choice1.setBounds(120, 270, 174, 30);
		panel.add(choice1);

		cancel = new JButton();
		cancel.setBounds(205, 341, 70, 40);
		cancel.setIcon(new ImageIcon(getClass().getResource("/CancelIcon.jpg")));
		cancel.setBorder(BorderFactory.createEmptyBorder());
		cancel.addActionListener(this);
		panel.add(cancel);

		choice = new JLabel("Choose Fighter:");
		choice.setForeground(Color.WHITE);
		choice.setFont(new Font("Tahoma", Font.BOLD, 12));
		choice.setBounds(10, 270, 100, 35);
		panel.add(choice);

		logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("/LoginFrameIcon2.jpg")));
		logo.setBounds(0, 0, 299, 399);
		panel.add(logo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			System.exit(0);
		} else if (e.getSource() == signUpButton) {
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

		new SignUp();
	}
}
