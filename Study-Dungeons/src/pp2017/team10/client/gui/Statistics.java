package pp2017.team10.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Statistics extends JFrame implements ActionListener {

	JPanel statspanel;
	JLabel monster, items, muenzen;
	JButton backButton;

	public Statistics() {
		super("Statistics");
		this.setSize(300, 230);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		statspanel = new JPanel();
		this.add(statspanel);
		placeLogin4Objects(statspanel);

		this.setVisible(true);
	}

	public void placeLogin4Objects(JPanel statspanel) {
		statspanel.setLayout(null);

		monster = new JLabel("Monster: 4 killed");
		monster.setHorizontalAlignment(JLabel.CENTER);
		monster.setBounds(0, 0, 300, 40);
		statspanel.add(monster);

		items = new JLabel("Items: 5 collected");
		items.setHorizontalAlignment(JLabel.CENTER);
		items.setBounds(0, 40, 300, 40);
		statspanel.add(items);

		muenzen = new JLabel("Coins: 43 collected");
		muenzen.setHorizontalAlignment(JLabel.CENTER);
		muenzen.setBounds(0, 80, 300, 40);
		statspanel.add(muenzen);

		backButton = new JButton("back");
		backButton.setLayout(null);
		backButton.setBounds(115, 160, 70, 25);
		backButton.addActionListener(this);
		statspanel.add(backButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			this.dispose();
		}

	}

	public static void main(String[] args) {
		new Statistics();

	}

}
