package pp2017.team10.client.gui;

/*
 * Dies ist der Study-Dungeons Frame , kurz SDFrame. Also das Spielfenster.
 * 
 * 
 * 
 * 
 * @author <Tokmak, Mehmet, 5784093>
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SDFrame extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4423673542395151953L;
	JPanel charPic, chatswitch, lifebar, xpbar, minimap, chat, inventar, lifebarbig, msg, stats, inventory, potion,
			logoutbutton;
	JButton chatSwitch, stats1, inventory1, logoutButton, item1, item2, item3, item4, item5, item6, item7, item8, item9,
			item10, item11, item12, item13, item14, item15, item16;
	JProgressBar lifeBar, xpBar, lifeBarBig, potion1;
	JLabel miniMap, inventar1, inventar0, charPic1;
	JTextArea chat1;
	JTextField Msg;
	JScrollPane scroll;
	JMenuBar menuBar;
	JMenu mnOptions, mnHelp;
	JMenuItem mntmRestart, mntmHighscore, mntmExit, mntmCheatcodes, mntmControls;
	public boolean gameOver;

	public SDFrame() {

		super("Study Dungeons");
		this.setSize(800, 660);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		JPanel placementPanel = new JPanel();
		placementPanel.setLayout(null);
		placementPanel.setSize(800, 600);
		placementPanel.setBackground(Color.gray);
		this.add(placementPanel);
		placeGameObjects(placementPanel);

		this.addKeyListener(this);
		this.setVisible(true);
	}

	// ein Grundgerüst für die Steuerung der Spielfigur
	public void keyPressed(KeyEvent e) {
		gameOver = false;
		if (!gameOver) {
			if (e.getKeyCode() == KeyEvent.VK_UP)
				System.out.println("Spieler geht nach oben");
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				System.out.println("Spieler geht nach unten");
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				System.out.println("Spieler geht nach rechts");
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				System.out.println("Spieler geht nach links");
			if (e.getKeyCode() == KeyEvent.VK_SPACE)
				System.out.println("Spieler nimmt Item auf");

		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void placeGameObjects(JPanel placementPanel) {

		placementPanel.setLayout(null);

		charPic = new JPanel();
		charPic.setLayout(null);
		charPic.setBounds(0, 0, 80, 80);
		charPic1 = new JLabel();
		charPic1.setLayout(null);
		charPic1.setIcon(new ImageIcon(getClass().getResource("/bogenschuetze.jpg")));
		charPic1.setBounds(0, 0, 80, 80);
		charPic.add(charPic1);
		placementPanel.add(charPic);

		lifebar = new JPanel();
		lifebar.setLayout(null);
		lifebar.setSize(70, 20);
		lifebar.setBounds(80, 0, 70, 20);
		lifeBar = new JProgressBar(0, 200);
		lifeBar.setForeground(Color.red);
		lifeBar.setBounds(0, 0, 70, 20);
		lifeBar.setValue(200);
		lifeBar.setStringPainted(true);

		for (int i = 132; i >= lifeBar.getMaximum(); i--) {
			lifeBar.setValue(i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lifeBar.setOpaque(false);
		lifebar.add(lifeBar);
		lifebar.setOpaque(false);
		lifebar.setToolTipText("Life Points");
		placementPanel.add(lifebar);

		xpbar = new JPanel();
		xpbar.setLayout(null);
		xpbar.setSize(70, 20);
		xpbar.setBounds(80, 30, 70, 20);
		xpbar.setOpaque(false);
		xpBar = new JProgressBar(0, 300);
		xpBar.setForeground(Color.magenta);
		xpBar.setBounds(0, 0, 70, 20);
		xpBar.setValue(150);
		xpBar.setStringPainted(true);

		for (int j = 259; j >= xpBar.getMaximum(); j--) {
			xpBar.setValue(j);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		xpBar.setOpaque(false);
		xpbar.add(xpBar);
		xpbar.setOpaque(false);
		xpbar.setToolTipText("Experience");
		placementPanel.add(xpbar);

		minimap = new JPanel();
		minimap.setSize(185, 185);
		minimap.setBounds(615, 0, 185, 185);
		minimap.setBackground(Color.GRAY);

		miniMap = new JLabel("Minimap");
		miniMap.setBounds(0, 0, 185, 185);
		miniMap.setOpaque(true);
		miniMap.setBackground(Color.CYAN);
		minimap.add(miniMap);
		// dwsfcasfcsac

		GameField gf2 = new GameField();
		gf2.setBounds(0, 0, 185, 185);
		minimap.add(gf2);
		placementPanel.add(minimap);

		chat = new JPanel();
		chat.setLayout(null);
		chat.setSize(150, 100);
		chat.setBounds(50, 350, 150, 100);
		chat.setOpaque(true);
		chat.setVisible(true);
		chat1 = new JTextArea(12, 20);
		scroll = new JScrollPane(chat1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(0, 0, 150, 100);
		chat1.setOpaque(true);
		chat1.setLineWrap(true);
		chat1.setEditable(false);
		chat1.setVisible(true);
		scroll.setVisible(true);
		chat1.addKeyListener(this);
		chat.add(scroll);
		placementPanel.add(chat);

		msg = new JPanel();
		msg.setLayout(null);
		msg.setSize(150, 30);
		msg.setBounds(50, 450, 150, 30);
		msg.setOpaque(false);
		msg.setVisible(true);
		Msg = new JTextField(30);
		Msg.setBounds(0, 0, 150, 30);
		Msg.setOpaque(true);
		Msg.addKeyListener(this);
		msg.add(Msg);
		Msg.addActionListener(new tHandler());
		placementPanel.add(msg);

		inventar = new JPanel();
		inventar.setLayout(null);
		inventar.setSize(185, 230);
		inventar.setBounds(615, 185, 185, 230);
		inventar.setBackground(Color.BLUE);
		inventar.setVisible(true);
		inventar0 = new JLabel("Inventory");
		inventar0.setHorizontalAlignment(JLabel.CENTER);
		inventar0.setBounds(0, 0, 185, 45);
		inventar0.setOpaque(true);
		inventar0.setBackground(Color.YELLOW);
		inventar.add(inventar0);
		inventar1 = new JLabel();
		inventar1.setLayout(null);
		inventar1.setBounds(0, 45, 185, 185);
		inventar1.setOpaque(true);
		inventar1.setBackground(Color.BLUE);
		item1 = new JButton();
		item1.setSize(40, 40);
		item1.setVisible(true);
		item1.setBounds(5, 5, 40, 40);
		item1.setIcon(new ImageIcon(getClass().getResource("/sword1.jpg")));
		item1.addActionListener(new bHandler());
		item1.addKeyListener(this);
		inventar1.add(item1);

		item2 = new JButton();
		item2.setSize(40, 40);
		item2.setVisible(true);
		item2.setBounds(50, 5, 40, 40);
		item2.setIcon(new ImageIcon(getClass().getResource("/sword2.jpg")));
		item2.addActionListener(new bHandler());
		item2.addKeyListener(this);
		inventar1.add(item2);

		item3 = new JButton();
		item3.setSize(40, 40);
		item3.setVisible(true);
		item3.setBounds(95, 5, 40, 40);
		item3.setIcon(new ImageIcon(getClass().getResource("/sword3.jpg")));
		item3.addActionListener(new bHandler());
		item3.addKeyListener(this);
		inventar1.add(item3);
		item4 = new JButton();
		item4.setSize(40, 40);
		item4.setVisible(true);
		item4.setBounds(140, 5, 40, 40);
		item4.setIcon(new ImageIcon(getClass().getResource("/sword4.jpg")));
		item4.addActionListener(new bHandler());
		item4.addKeyListener(this);
		inventar1.add(item4);
		item5 = new JButton();
		item5.setSize(40, 40);
		item5.setVisible(true);
		item5.setBounds(5, 50, 40, 40);
		item5.setIcon(new ImageIcon(getClass().getResource("/bow1.jpg")));
		item5.addActionListener(new bHandler());
		item5.addKeyListener(this);
		inventar1.add(item5);
		item6 = new JButton();
		item6.setSize(40, 40);
		item6.setVisible(true);
		item6.setBounds(50, 50, 40, 40);
		item6.setIcon(new ImageIcon(getClass().getResource("/bow2.jpg")));
		item6.addActionListener(new bHandler());
		item6.addKeyListener(this);
		inventar1.add(item6);
		item7 = new JButton();
		item7.setSize(40, 40);
		item7.setVisible(true);
		item7.setBounds(95, 50, 40, 40);
		item7.setIcon(new ImageIcon(getClass().getResource("/bow3.jpg")));
		item7.addActionListener(new bHandler());
		item7.addKeyListener(this);
		inventar1.add(item7);
		item8 = new JButton();
		item8.setSize(40, 40);
		item8.setVisible(true);
		item8.setBounds(140, 50, 40, 40);
		item8.setIcon(new ImageIcon(getClass().getResource("/bow4.jpg")));
		item8.addActionListener(new bHandler());
		item8.addKeyListener(this);
		inventar1.add(item8);
		item9 = new JButton();
		item9.setSize(40, 40);
		item9.setVisible(true);
		item9.setBounds(5, 95, 40, 40);
		item9.setIcon(new ImageIcon(getClass().getResource("/glove1.jpg")));
		item9.addActionListener(new bHandler());
		item9.addKeyListener(this);
		inventar1.add(item9);
		item10 = new JButton();
		item10.setSize(40, 40);
		item10.setVisible(true);
		item10.setBounds(50, 95, 40, 40);
		item10.setIcon(new ImageIcon(getClass().getResource("/glove2.jpg")));
		item10.addActionListener(new bHandler());
		item10.addKeyListener(this);
		inventar1.add(item10);
		item11 = new JButton();
		item11.setSize(40, 40);
		item11.setVisible(true);
		item11.setBounds(95, 95, 40, 40);
		item11.setIcon(new ImageIcon(getClass().getResource("/glove3.jpg")));
		item11.addActionListener(new bHandler());
		item11.addKeyListener(this);
		inventar1.add(item11);
		item12 = new JButton();
		item12.setSize(40, 40);
		item12.setVisible(true);
		item12.setBounds(140, 95, 40, 40);
		item12.setIcon(new ImageIcon(getClass().getResource("/glove4.jpg")));
		item12.addActionListener(new bHandler());
		item12.addKeyListener(this);
		inventar1.add(item12);
		item13 = new JButton();
		item13.setSize(40, 40);
		item13.setVisible(true);
		item13.setBounds(5, 140, 40, 40);
		item13.setIcon(new ImageIcon(getClass().getResource("/helmet1.jpg")));
		item13.addActionListener(new bHandler());
		item13.addKeyListener(this);
		inventar1.add(item13);
		item14 = new JButton();
		item14.setSize(40, 40);
		item14.setVisible(true);
		item14.setBounds(50, 140, 40, 40);
		item14.setIcon(new ImageIcon(getClass().getResource("/helmet2.jpg")));
		item14.addActionListener(new bHandler());
		item14.addKeyListener(this);
		inventar1.add(item14);
		item15 = new JButton();
		item15.setSize(40, 40);
		item15.setVisible(true);
		item15.setBounds(95, 140, 40, 40);
		item15.setIcon(new ImageIcon(getClass().getResource("/shoe1.jpg")));
		item15.addActionListener(new bHandler());
		item15.addKeyListener(this);
		inventar1.add(item15);
		item16 = new JButton();
		item16.setSize(40, 40);
		item16.setVisible(true);
		item16.setBounds(140, 140, 40, 40);
		item16.setIcon(new ImageIcon(getClass().getResource("/shoe2.jpg")));
		item16.addActionListener(new bHandler());
		item16.addKeyListener(this);
		inventar1.add(item16);
		inventar.add(inventar1);
		placementPanel.add(inventar);

		chatswitch = new JPanel();
		chatswitch.setLayout(null);
		chatswitch.setSize(100, 30);
		chatswitch.setBounds(35, 530, 100, 30);
		chatSwitch = new JButton("Chat on/off");
		chatSwitch.setBounds(0, 0, 100, 30);
		chatSwitch.addActionListener(new bHandler());
		chatSwitch.addKeyListener(this);
		chatswitch.add(chatSwitch);
		chatswitch.setOpaque(false);
		placementPanel.add(chatswitch);

		lifebarbig = new JPanel();
		lifebarbig.setLayout(null);
		lifebarbig.setSize(120, 120);
		lifebarbig.setBounds(150, 480, 120, 120);
		lifeBarBig = new JProgressBar(JProgressBar.VERTICAL, 0, 200);
		lifeBarBig.setBounds(0, 0, 120, 120);
		lifeBarBig.setValue(200);
		lifeBarBig.setStringPainted(true);
		lifeBarBig.setForeground(Color.red);

		/*
		 * for (int k = 132; k >= lifeBarBig.getMaximum(); k--) {
		 * lifeBarBig.setValue(k); try { Thread.sleep(10); } catch
		 * (InterruptedException e) { e.printStackTrace(); } }
		 */
		lifeBarBig.setOpaque(false);
		lifebarbig.add(lifeBarBig);
		lifebarbig.setOpaque(false);
		lifebarbig.setToolTipText("Life Points");
		placementPanel.add(lifebarbig);

		stats = new JPanel();
		stats.setLayout(null);
		stats.setSize(130, 60);
		stats.setBounds(270, 540, 130, 60);
		stats1 = new JButton("Stats");
		stats1.setBounds(0, 0, 130, 60);
		stats1.addActionListener(new bHandler());
		stats.add(stats1);
		stats.setOpaque(false);
		placementPanel.add(stats);

		inventory = new JPanel();
		inventory.setLayout(null);
		inventory.setSize(130, 60);
		inventory.setBounds(400, 540, 130, 60);
		inventory1 = new JButton("Inventory on/off");
		inventory1.setBounds(0, 0, 130, 60);
		inventory1.addActionListener(new bHandler());
		inventory.add(inventory1);
		inventory.setOpaque(false);
		placementPanel.add(inventory);

		potion = new JPanel();
		potion.setLayout(null);
		potion.setSize(120, 120);
		potion.setBounds(530, 480, 120, 120);
		potion1 = new JProgressBar(JProgressBar.VERTICAL, 0, 400);
		potion1.setBounds(0, 0, 120, 120);
		potion1.setValue(350);
		potion1.setStringPainted(true);
		potion1.setForeground(Color.GREEN);

		for (int k = 309; k >= lifeBarBig.getMaximum(); k--) {
			lifeBarBig.setValue(k);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		potion1.setOpaque(false);
		potion.add(potion1);
		potion.setOpaque(false);
		potion.setToolTipText("Potion");
		placementPanel.add(potion);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		mntmRestart = new JMenuItem("Restart");
		mnOptions.add(mntmRestart);

		mntmHighscore = new JMenuItem("Highscore");
		mnOptions.add(mntmHighscore);

		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new bHandler());
		mnOptions.add(mntmExit);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmCheatcodes = new JMenuItem("CheatCodes");
		mnHelp.add(mntmCheatcodes);

		mntmControls = new JMenuItem("Controls");
		mnHelp.add(mntmControls);
		this.setVisible(true);

		repaint();

	}

	public class tHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			chat1.append(Msg.getText() + "\n");
			Msg.setText("");

		}

	}

	public class bHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mntmExit) {
				new Logout();
			}
			if (e.getSource() == inventory1) {
				if (inventar.isVisible() == true) {
					inventar.setVisible(false);
				} else if (inventar.isVisible() == false) {
					inventar.setVisible(true);
				}
			}
			if (e.getSource() == chatSwitch) {
				if (chat.isVisible() == true) {
					chat.setVisible(false);
					msg.setVisible(false);
				} else if (chat.isVisible() == false) {
					chat.setVisible(true);
					msg.setVisible(true);
				}

			}
			if (e.getSource() == stats1) {
				new Statistics();
			}
			if (e.getSource() == item1) {
				System.out.println("Sie haben auf Item 1 gedrueckt!");
			}
			if (e.getSource() == item2) {
				System.out.println("Sie haben auf Item 2 gedrueckt!");
			}
			if (e.getSource() == item3) {
				System.out.println("Sie haben auf Item 3 gedrueckt!");
			}
			if (e.getSource() == item4) {
				System.out.println("Sie haben auf Item 4 gedrueckt!");
			}
			if (e.getSource() == item5) {
				System.out.println("Sie haben auf Item 5 gedrueckt!");
			}
			if (e.getSource() == item6) {
				System.out.println("Sie haben auf Item 6 gedrueckt!");
			}
			if (e.getSource() == item7) {
				System.out.println("Sie haben auf Item 7 gedrueckt!");
			}
			if (e.getSource() == item8) {
				System.out.println("Sie haben auf Item 8 gedrueckt!");
			}
			if (e.getSource() == item9) {
				System.out.println("Sie haben auf Item 9 gedrueckt!");
			}
			if (e.getSource() == item10) {
				System.out.println("Sie haben auf Item 10 gedrueckt!");
			}
			if (e.getSource() == item11) {
				System.out.println("Sie haben auf Item 11 gedrueckt!");
			}
			if (e.getSource() == item12) {
				System.out.println("Sie haben auf Item 12 gedrueckt!");
			}
			if (e.getSource() == item13) {
				System.out.println("Sie haben auf Item 13 gedrueckt!");
			}
			if (e.getSource() == item14) {
				System.out.println("Sie haben auf Item 14 gedrueckt!");
			}
			if (e.getSource() == item15) {
				System.out.println("Sie haben auf Item 15 gedrueckt!");
			}
			if (e.getSource() == item16) {
				System.out.println("Sie haben auf Item 16 gedrueckt!");
			}
		}
		// public void repaintGamefield(){
		// gf.repaint();
		// }
	}

	public static void main(String[] args) {

		new SDFrame();

	}

}
