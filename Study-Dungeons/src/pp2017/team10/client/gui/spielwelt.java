package pp2017.team10.client.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import pp2017.team10.client.comm.SendQueue;
import pp2017.team10.client.engine.ClientEngine;
import pp2017.team10.shared.Character;
import pp2017.team10.shared.ChatMessage;
import pp2017.team10.shared.ItemUsageMessage;
import pp2017.team10.shared.Login;
import pp2017.team10.shared.NewPlayer;
import pp2017.team10.shared.LevelMessage;

/**
 *
 * @author Computer
 */
public class spielwelt extends javax.swing.JFrame {

	private final int screenWidth;
	private final int screenHeight;
	private final Dimension itemPanelSize;
	private final Dimension worldPanelSize;
	private final Dimension mainPanelSize;
	private ArrayList<JPanel> jp = new ArrayList<JPanel>();
	private ArrayList<JLabel> jl = new ArrayList<JLabel>();
	public boolean showChat = true;
	private int playerPosX;
	private int playerPosY;
	public int world[][] = new int[50][50];
	public JLabel playerOnField = new JLabel();
	private int delay = 8;
	public JLabel playerOnMinimap = new JLabel();
	private JLayeredPane jlp = new JLayeredPane();
	private JPanel minimapPanel;
	// public ClientEngine ceg = new ClientEngine();
	public SendQueue send;
	public String receiver;
	public String recipient;
	public ArrayList<JLabel> monsterList = new ArrayList<JLabel>();
	public ArrayList<JLabel> playerList = new ArrayList<JLabel>();
	public ArrayList<JLabel> itemList = new ArrayList<JLabel>();
	private JLayeredPane minimap;
	public ArrayList<JLabel> playerMiniMapList = new ArrayList<JLabel>();
	public MainMenuB main;

	public static spielwelt spiel;

	/**
	 * Creates new form spielwelt
	 * 
	 * @throws IOException
	 */

	public static synchronized spielwelt getSpielwelt() throws IOException {
		if (spiel == null) {
			spiel = new spielwelt();
		}
		return spiel;
	}

	private ImageIcon getImage(String name) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(getClass().getResource(name));
		} catch (Exception e) {
			System.out.println(e);
		}
		Image dimg = bi.getScaledInstance(screenWidth / 50, screenWidth / 50, Image.SCALE_SMOOTH);
		ImageIcon img = new ImageIcon(dimg);
		return img;
	}

	public void addMonster(int monsterTyp, int monsterPosX, int monsterPosY) {
		JLabel monster = new JLabel();
		monster.setSize(screenWidth / 50, screenWidth / 50);
		switch (monsterTyp) {
		case 0: {
			monster.setIcon(getImage("wachmann1.png"));
		}
		case 1: {
			monster.setIcon(getImage("wachmann2.png"));
		}
		case 2: {
			monster.setIcon(getImage("wachmann.png"));
		}
		}
		monster.setLocation((monsterPosX * (screenWidth / 50)), (monsterPosY * (screenWidth / 50)));
		monsterList.add(monster);
		jlp.add(monster, 3);

	}

	public void moveMonster(int monsterID, int posXNeu, int posYNeu) {
		JLabel monster = monsterList.get(monsterID);
		ActionListener taskPerformer = new ActionListener() {
			boolean run = true;

			public void actionPerformed(ActionEvent evt) {
				int posX = (monsterList.get(monsterID).getLocation().x / screenWidth);
				int posY = (monsterList.get(monsterID).getLocation().y / screenWidth);
				if (run == false) {
					((Timer) evt.getSource()).stop();
				}
				if (posXNeu != posX)
					posX++;
				else if (posYNeu != posY)
					posY++;
				else
					run = false;
				monster.setLocation(posX, posY);
			}
		};
		new Timer(delay, taskPerformer).start();
	}

	public void addPlayer(int playerID, int posX, int posY) {
		JLabel player = new JLabel();
		player.setLocation(posX * (screenWidth / 50), posY * (screenWidth / 50));
		player.setSize(screenWidth / 50, screenWidth / 50);
		player.setIcon(getImage("run0.png"));
		jlp.add(player, 3);
		playerList.add(player);

		playerOnMinimap.setBackground(Color.red);
		playerOnMinimap.setLocation(posX * 4, posY * 4);
		playerOnMinimap.setSize(4, 4);
		playerOnMinimap.setOpaque(true);
		minimap.add(playerOnMinimap, 0);

	}

	public void movePlayer(int playerID, int posXNeu, int posYNeu) {
		JLabel player = playerList.get(playerID);
		ActionListener taskPerformer = new ActionListener() {
			boolean run = true;

			public void actionPerformed(ActionEvent evt) {
				int posX = (playerList.get(playerID).getLocation().x / screenWidth);
				int posY = (playerList.get(playerID).getLocation().y / screenWidth);
				if (run == false) {
					((Timer) evt.getSource()).stop();
				}
				if (posXNeu != posX)
					posX++;
				else if (posYNeu != posY)
					posY++;
				else
					run = false;
				player.setLocation(posX, posY);
			}
		};
		new Timer(delay, taskPerformer).start();
	}

	public void setItemCount(int itemID, int count) {

		switch (itemID) {
		case 0: {
			int countBlue = Integer.parseInt(bluePotionCount.getText()) + count;
			greenPotionCount.setText(Integer.toString(countBlue));
			break;
		}
		case 1: {
			int countGreen = Integer.parseInt(greenPotionCount.getText()) + count;
			greenPotionCount.setText(Integer.toString(countGreen));
			break;
		}
		case 2: {
			int countRed = Integer.parseInt(redPotionCount.getText()) + count;
			greenPotionCount.setText(Integer.toString(countRed));
			break;
		}
		}
	}

	public void setChat(String message) {
		chatText.append(chatInput.getText() + "\n");
	}

	public void setHealth(int leben) {
		healthBar.setValue(leben);
	}

	public void addItemMap(int itemID, int posX, int posY) {
		JLabel item = new JLabel();
		item.setSize(screenWidth / 50, screenWidth / 50);
		item.setLocation(posX * (screenWidth / 50), posY * (screenWidth / 50));
		switch (itemID) {
		case 0: {
			item.setIcon(getImage("bluePotion.png"));
		}
		case 1: {
			item.setIcon(getImage("greenPotion.png"));
		}
		case 2: {
			item.setIcon(getImage("redPotion.png"));
		}
		}
		itemList.add(item);
		jlp.add(item, 4);
	}

	public void deleteMonster(int monsterID) {
		JLabel monster = monsterList.get(monsterID);
		jlp.remove(monster);
	}

	public void resetMonster() {
		monsterList.clear();
	}

	public void resetItem() {
		itemList.clear();
	}

	public void resetPlayer() {
		playerList.clear();
	}

	public void deleteItemMap(int itemIDMap) {
		JLabel item = itemList.get(itemIDMap);
		jlp.remove(item);
		itemList.remove(itemIDMap);
	}

	public void setWorld(int[][] map) {

		// for (int k = 0; k < 50; k++) {
		// for (int j = 0; j < 50; j++) {
		// System.out.print(map[k][j] + ", ");
		// }
		// System.out.println();
		// }

		minimapPanel = new JPanel();
		minimapPanel.setSize(200, 200);
		minimapPanel.setLocation(screenWidth - 220, 20);
		minimap = new JLayeredPane();
		minimap.setLocation(0, 0);
		minimap.setSize(200, 200);
		minimapPanel.setLayout(null);
		minimapPanel.setOpaque(false);
		minimap.setOpaque(true);
		minimapPanel.add(minimap);
		JPanel minimapBackground;

		for (int j = 0; j < 50; j++) {
			for (int i = 0; i < 50; i++) {
				minimapBackground = new JPanel();
				minimapBackground.setSize(4, 4);
				switch (map[i][j]) {
				case 2:
					minimapBackground.setBackground(Color.YELLOW);
					break;
				case 4:
					minimapBackground.setBackground(Color.black);
				}
				minimap.add(minimapBackground, 1);
				minimapBackground.setLocation(i * 4, j * 4);
			}
		}

		jlp.add(minimapPanel, 2);

		JLabel groundImageLabel = new JLabel();

		System.out.println("geht");

		for (int j = 0; j < 50; j++) {
			for (int i = 0; i < 50; i++) {
				groundImageLabel = new JLabel();
				groundImageLabel.setSize(screenWidth / 50, screenWidth / 50);
				groundImageLabel.setOpaque(false);
				switch (map[i][j]) {
				// case 0:
				// groundImageLabel.setIcon(getImage("Ground.png"));
				// break;
				// case 1:
				// groundImageLabel.setIcon(getImage("wall.png"));
				// break;
				case 2:
					groundImageLabel.setIcon(getImage("wallwiso.png"));
					break;
				case 3:
					groundImageLabel.setIcon(getImage("wallwiso.png"));
					break;
				case 4:
					groundImageLabel.setIcon(getImage("groundwiso.png"));
					break;
				case 5:
					groundImageLabel.setIcon(getImage("groundwiso.png"));
					break;
				case 7:
					groundImageLabel.setIcon(getImage("groundwiso.png"));
					break;
				case 8:
					// Column
					break;
				case 9:
					groundImageLabel.setIcon(getImage("keygold.png"));
				case 100:
					groundImageLabel.setIcon(getImage("spieler.png"));
					// groundImageLabel.setIcon(getImage("spieler.png"));
					break;
				case 150:
					groundImageLabel.setIcon(getImage("wachmann.png"));
					break;
				}

				jlp.add(groundImageLabel, 1);
				groundImageLabel.setLocation(i * screenWidth / 50, j * screenWidth / 50);
			}

		}

		// for (int k = 0; k < 50; k++) {
		// for (int j = 0; j < 50; j++) {
		// System.out.print(world[k][j] + ", ");
		// }
		// System.out.println();
		// }

	}

	public void resetWorld() {
		jlp.removeAll();
	}

	public void deletePlayer(int playerID) {
		JLabel player = playerList.get(playerID);
		jlp.remove(player);
		playerList.remove(playerID);
	}
	// public void moveOwnPlayer(String direction, int posX, int posY) throws
	// InterruptedException {
	// if (direction.equals("right") && world[posX][posY] != 1) {
	// ActionListener taskPerformer = new ActionListener() {
	// int count = 0;
	//
	// public void actionPerformed(ActionEvent evt) {
	// if (count > ((screenWidth / 50) % 1000) - 2) {// we did the
	// // task 10
	// // times
	// ((Timer) evt.getSource()).stop();
	// }
	// if (playerPosX < screenWidth - (screenWidth / 50))
	// playerPosX++;
	// playerOnField.setLocation(playerPosX, playerPosY);
	// count++;
	// }
	// };
	// new Timer(delay, taskPerformer).start();
	// }
	// if (direction.equals("up") && world[posX][posY] != 1) {
	// ActionListener taskPerformer = new ActionListener() {
	// int count = 0;
	//
	// public void actionPerformed(ActionEvent evt) {
	// if (count > ((screenWidth / 50) % 1000) - 2) {// we did the
	// // task 10
	// // times
	// ((Timer) evt.getSource()).stop();
	// }
	// if (playerPosY > 0)
	// playerPosY--;
	// playerOnField.setLocation(playerPosX, playerPosY);
	// count++;
	// }
	// };
	// new Timer(delay, taskPerformer).start();
	// }
	// if (direction.equals("left") && world[posX][posY] != 1) {
	// ActionListener taskPerformer = new ActionListener() {
	// int count = 0;
	//
	// public void actionPerformed(ActionEvent evt) {
	//
	// if (count > ((screenWidth / 50) % 1000) - 2) {// we did the
	// // task 10
	// // times
	// ((Timer) evt.getSource()).stop();
	// }
	// if (playerPosX > 0)
	// playerPosX--;
	// playerOnField.setLocation(playerPosX, playerPosY);
	// count++;
	// }
	// };
	// new Timer(delay, taskPerformer).start();
	// }
	// if (direction.equals("down") && world[posX][posY] != 1) {
	// ActionListener taskPerformer = new ActionListener() {
	// int count = 0;
	//
	// public void actionPerformed(ActionEvent evt) {
	// if (count > ((screenWidth / 50) % 1000) - 2) {// we did the
	// // task 10
	// // times
	// ((Timer) evt.getSource()).stop();
	// }
	// if (playerPosY < (screenWidth - (screenWidth / 50)))
	// playerPosY++;
	// playerOnField.setLocation(playerPosX, playerPosY);
	// count++;
	// }
	// };
	// new Timer(delay, taskPerformer).start();
	// }
	//
	// System.out.println(ceg.bSendQueue.isEmpty());
	//
	// }

	public void movePlayerMinimap(int posX, int posY) {
		int playerX = posX * 4;
		int playerY = posY * 4;
		playerOnMinimap.setLocation(playerX, playerY);
	}

	public KeyListener kl = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int playerX = playerPosX / (screenWidth / 50);
			int playerY = playerPosY / (screenWidth / 50);

			System.out.println(playerPosX);
			System.out.println(playerPosY);
			try {
				if (e.getKeyCode() == 37 && playerPosX != 0 && --playerX >= 0 && world[playerX][playerY] != 1) {
					movePlayerMinimap(playerX, playerY);
					// ceg.consistency(world, "left", ++playerX, playerY);
					ClientEngine.getEngine().consistency(world, "left", ++playerX, ++playerY);
				} else if (e.getKeyCode() == 38 && playerPosY != 0 && --playerY >= 0 && world[playerX][playerY] != 1) {
					movePlayerMinimap(playerX, playerY);
					ClientEngine.getEngine().consistency(world, "up", playerX, ++playerY);
				} else if (e.getKeyCode() == 39 && playerPosX != screenWidth && ++playerX < 50
						&& world[playerX][playerY] != 1) {
					movePlayerMinimap(playerX, playerY);
					ClientEngine.getEngine().consistency(world, "right", --playerX, playerY);
				} else if (e.getKeyCode() == 40 && playerPosY != screenWidth && ++playerY < 50
						&& world[playerX][playerY] != 1) {
					movePlayerMinimap(playerX, playerY);
					ClientEngine.getEngine().consistency(world, "down", playerX, --playerY);
				}

			} catch (InterruptedException ex) {
				Logger.getLogger(spielwelt.class.getName()).log(Level.SEVERE, null, ex);
			}
			playerOnField.setOpaque(false);
			jlp.repaint();
			showChat();
			if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
				chatInput.setEnabled(true);
				chatInput.requestFocus();
			}
			if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
				if (chatButton.getText() == "Chat verbergen") {
					chatWindow.setVisible(false);
					chatButton.setText("Chat anzeigen");
					showChat = false;

				} else {
					chatButton.setText("Chat verbergen");
					chatWindow.setVisible(true);
				}
			}
			if (e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {// minimap
																	// anzeigen/schließen
				if (minimapPanel.isVisible())
					minimapPanel.setVisible(false);
				else
					minimapPanel.setVisible(true);

			}
			if (e.getKeyChar() == 'v' || e.getKeyChar() == 'V') { // Item
																	// aufheben
				ClientEngine.getEngine().itemAvailable(playerX, playerY, world);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	};

	public spielwelt() throws IOException {

		setVisible(false);
		new LoginB();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int screen = (int) screenSize.getHeight() - 100;
		screenHeight = (screen - screen % 50);
		screenWidth = screenHeight - 100;
		itemPanelSize = new Dimension(screenWidth, 100);
		worldPanelSize = new Dimension(screenWidth, screenWidth);
		mainPanelSize = new Dimension(screenWidth, screenHeight);
		initComponents();

		chatButton.setSize(screenWidth / 5, 37);
		chatButton.setLocation(0, 0);

		BufferedImage bi = null;
		try {
			bi = ImageIO.read(getClass().getResource("bluePotion.png"));
		} catch (Exception e) {
			System.out.println(e);
		}
		Image dimg = bi.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon img = new ImageIcon(dimg);
		bluePotionLabel.setIcon(img);
		bluePotionLabel.setText("");

		bi = null;
		try {
			bi = ImageIO.read(getClass().getResource("greenPotion.png"));
		} catch (Exception e) {
			System.out.println(e);
		}
		dimg = bi.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		img = new ImageIcon(dimg);

		greenPotionLabel.setIcon(img);
		greenPotionLabel.setText("");

		bi = null;
		try {
			bi = ImageIO.read(getClass().getResource("redPotion.png"));
		} catch (Exception e) {
			System.out.println(e);
		}
		dimg = bi.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		img = new ImageIcon(dimg);

		redPotionLabel.setIcon(img);
		redPotionLabel.setText("");
		redPotionCount.setText("0");
		greenPotionCount.setText("0");
		bluePotionCount.setText("0");

		chatWindow.setOpaque(false);
		chatWindow.addKeyListener(kl);
		chatText.setEditable(false);
		chatText.setLineWrap(true);
		chatText.addKeyListener(kl);
		chatInput.setEnabled(false);
		chatInput.addActionListener(new tHandler());

		playerOnMinimap.setBackground(Color.red);
		playerOnMinimap.setLocation(0, 0);
		playerOnMinimap.setSize(4, 4);
		// playerOnMinimap.setOpaque(true);
		// minimap.add(playerOnMinimap, 0);
		playerOnMinimap.setOpaque(true);
		// minimap.add(playerOnMinimap, 0);

		this.setSize(screenWidth + 6, screenHeight + 28);
		this.setLocationRelativeTo(null);

		ImageIcon playerIcon = getImage("spieler.png");
		// ImageIcon groundIcon = getImage("green.png");
		// ImageIcon wallIcon = getImage("wall4.png");
		// ImageIcon itemIcon = getImage("greenPotion.png");
		ImageIcon groundIcon = getImage("groundwiso.png");
		ImageIcon wallIcon = getImage("wallwiso.png");
		// ImageIcon wallIcon = getImage("wall4.png");
		ImageIcon itemIcon = getImage("greenPotion.png");
		ImageIcon keyIcon = getImage("keygold.png");
		ImageIcon monsterIcon = getImage("wachmann.png");

		playerOnField.setSize(screenWidth / 50, screenWidth / 50);
		playerOnField.setIcon(playerIcon);

		jlp.setSize(screenWidth, screenWidth);
		worldPanel.add(jlp);

		playerPosX = 0;
		playerPosY = 0;
		playerOnField.setLocation(playerPosX, playerPosY);

		chatButton.addKeyListener(kl);
		mainPanel.setSize(screenWidth, screenHeight);
		mainPanel.setPreferredSize(mainPanelSize);
		worldPanel.setLocation(screenWidth, screenWidth);
		worldPanel.setPreferredSize(worldPanelSize);
		mainItemPanel.setSize(screenWidth, 100);
		mainItemPanel.setPreferredSize(itemPanelSize);

		healthBar.setMaximum(100);
		// pw[1] = 'c';
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		mainPanel = new javax.swing.JPanel();
		worldPanel = new javax.swing.JPanel();
		chatWindow = new javax.swing.JPanel();
		chatScroll = new javax.swing.JScrollPane();
		chatText = new javax.swing.JTextArea();
		chatInput = new javax.swing.JTextField();
		mainItemPanel = new javax.swing.JPanel();
		chatButton = new javax.swing.JButton();
		healthBar = new javax.swing.JProgressBar();
		itemPanel = new javax.swing.JPanel();
		itemLabel = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		bluePotionLabel = new javax.swing.JLabel();
		redPotionLabel = new javax.swing.JLabel();
		greenPotionLabel = new javax.swing.JLabel();
		bluePotionCount = new javax.swing.JLabel();
		greenPotionCount = new javax.swing.JLabel();
		redPotionCount = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Study Dungeons");
		setMinimumSize(new java.awt.Dimension(700, 800));
		setName("mainFrame"); // NOI18N
		setPreferredSize(new java.awt.Dimension(700, 800));
		setResizable(false);
		setSize(new java.awt.Dimension(700, 800));

		mainPanel.setBackground(new java.awt.Color(102, 255, 51));
		mainPanel.setName("mainPanel"); // NOI18N

		worldPanel.setBackground(new java.awt.Color(255, 153, 153));
		worldPanel.setName("worldPanel"); // NOI18N

		chatScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		chatText.setColumns(20);
		chatText.setRows(5);
		chatScroll.setViewportView(chatText);

		chatInput.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				chatInputMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout chatWindowLayout = new javax.swing.GroupLayout(chatWindow);
		chatWindow.setLayout(chatWindowLayout);
		chatWindowLayout
				.setHorizontalGroup(chatWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(chatWindowLayout.createSequentialGroup()
								.addGroup(chatWindowLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(chatScroll, javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(chatInput, javax.swing.GroupLayout.Alignment.LEADING))
								.addGap(0, 0, Short.MAX_VALUE)));
		chatWindowLayout
				.setVerticalGroup(chatWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(chatWindowLayout.createSequentialGroup()
								.addComponent(chatScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(chatInput, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 17, Short.MAX_VALUE)));

		javax.swing.GroupLayout worldPanelLayout = new javax.swing.GroupLayout(worldPanel);
		worldPanel.setLayout(worldPanelLayout);
		worldPanelLayout
				.setHorizontalGroup(worldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(worldPanelLayout.createSequentialGroup().addContainerGap()
								.addComponent(chatWindow, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		worldPanelLayout.setVerticalGroup(
				worldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING, worldPanelLayout.createSequentialGroup()
								.addContainerGap(532, Short.MAX_VALUE)
								.addComponent(chatWindow, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		mainItemPanel.setBackground(new java.awt.Color(0, 153, 255));
		mainItemPanel.setName("mainItemPanel"); // NOI18N
		mainItemPanel.setPreferredSize(new java.awt.Dimension(600, 100));

		chatButton.setFont(new java.awt.Font("Raven Song", 1, 14)); // NOI18N
		chatButton.setText("Chat verbergen");
		chatButton.setName("chatButton"); // NOI18N
		chatButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chatButtonActionPerformed(evt);
			}
		});

		healthBar.setBackground(new java.awt.Color(255, 51, 51));
		healthBar.setForeground(new java.awt.Color(51, 255, 0));
		healthBar.setName("healthBar"); // NOI18N

		itemPanel.setName("itemPanel"); // NOI18N
		bluePotionLabel.setText("jLabel3");
		bluePotionLabel.setPreferredSize(new java.awt.Dimension(25, 25));

		redPotionLabel.setText("jLabel3");
		redPotionLabel.setPreferredSize(new java.awt.Dimension(25, 25));

		greenPotionLabel.setText("jLabel3");
		greenPotionLabel.setPreferredSize(new java.awt.Dimension(25, 25));

		bluePotionCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		bluePotionCount.setText("j");

		greenPotionCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		greenPotionCount.setText("j");

		redPotionCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		redPotionCount.setText("j");

		javax.swing.GroupLayout itemPanelLayout = new javax.swing.GroupLayout(itemPanel);
		itemPanel.setLayout(itemPanelLayout);
		itemPanelLayout.setHorizontalGroup(itemPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(itemPanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(bluePotionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 34,
										Short.MAX_VALUE)
								.addComponent(bluePotionCount, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(greenPotionCount, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(greenPotionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 34,
										Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(redPotionLabel, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 34,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(redPotionCount, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 34,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		itemPanelLayout.setVerticalGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(itemPanelLayout.createSequentialGroup().addGroup(itemPanelLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(redPotionLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(greenPotionLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(bluePotionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
								javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(redPotionCount).addComponent(greenPotionCount)
								.addComponent(bluePotionCount))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jLabel1.setFont(new java.awt.Font("Raven Song", 1, 14)); // NOI18N
		jLabel1.setText("Elixiere");
		jLabel1.setName("itemLabel"); // NOI18N

		jLabel2.setFont(new java.awt.Font("Raven Song", 1, 18)); // NOI18N
		jLabel2.setText("Lebenspunkte");

		javax.swing.GroupLayout mainItemPanelLayout = new javax.swing.GroupLayout(mainItemPanel);
		mainItemPanel.setLayout(mainItemPanelLayout);
		mainItemPanelLayout.setHorizontalGroup(mainItemPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(mainItemPanelLayout.createSequentialGroup().addContainerGap()
						.addComponent(chatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(75, 75, 75)
						.addGroup(mainItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(healthBar, javax.swing.GroupLayout.PREFERRED_SIZE, 270,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(mainItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(mainItemPanelLayout.createSequentialGroup()
										.addComponent(itemPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(mainItemPanelLayout.createSequentialGroup().addComponent(jLabel1).addGap(66,
										66, 66)))));
		mainItemPanelLayout.setVerticalGroup(mainItemPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(mainItemPanelLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(healthBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(30, 30, 30))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						mainItemPanelLayout.createSequentialGroup().addComponent(jLabel1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(itemPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(22, 22, 22))
				.addGroup(mainItemPanelLayout.createSequentialGroup().addContainerGap()
						.addComponent(chatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout
				.setHorizontalGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(mainItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
						.addComponent(worldPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup()
						.addComponent(worldPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(mainItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));

		pack();
	}// </editor-fold>

	private void chatButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:

		if (chatButton.getText() == "Chat verbergen") {
			chatWindow.setVisible(false);
			chatButton.setText("Chat anzeigen");
			showChat = false;

		} else {
			chatButton.setText("Chat verbergen");
			chatWindow.setVisible(true);
		}
	}

	private void chatInputMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		chatInput.setEnabled(true);
		chatInput.requestFocus();
	}

	private void showChat() {
		if (showChat == true) {
			chatWindow.setVisible(false);
			chatWindow.setVisible(true);
			// Msg.setVisible(false);
			// Msg.setVisible(true);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(spielwelt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(spielwelt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(spielwelt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(spielwelt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new spielwelt().setVisible(true);
				} catch (IOException ex) {
					Logger.getLogger(spielwelt.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public class tHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setChat(chatInput.getText());
			chatInput.setText("");

			// ChatMessage chat = new ChatMessage(chatInput.getText(), receiver,
			// recipient);
			// ceg.addQueue(chat);
			chatInput.setEnabled(false);

		}

	}

	// public ClientEngine returnEngine(ClientEngine ceg) {
	// ceg = this.ceg;
	// return ceg;
	// }

	// Variables declaration - do not modify
	private javax.swing.JButton chatButton;
	private javax.swing.JTextField chatInput;
	private javax.swing.JScrollPane chatScroll;
	private javax.swing.JTextArea chatText;
	private javax.swing.JPanel chatWindow;
	private javax.swing.JProgressBar healthBar;
	private javax.swing.JLabel itemLabel;
	private javax.swing.JPanel itemPanel;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel mainItemPanel;
	private javax.swing.JPanel mainPanel;
	private javax.swing.JPanel worldPanel;
	private javax.swing.JLabel bluePotionCount;
	private javax.swing.JLabel bluePotionLabel;
	private javax.swing.JLabel greenPotionCount;
	private javax.swing.JLabel greenPotionLabel;
	private javax.swing.JLabel healthLabel;
	private javax.swing.JLabel redPotionCount;
	private javax.swing.JLabel redPotionLabel;
	// End of variables declaration
}
