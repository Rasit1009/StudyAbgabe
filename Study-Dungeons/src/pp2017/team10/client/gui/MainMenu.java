package pp2017.team10.client.gui;
/*
 * 
 * Bitte starten sie Hier das Spiel.
 * 
 * 
 * 
 * 
 * @author <Tokmak, Mehmet, 5784093>
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class MainMenu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1506712517684413988L;
	JTextField chatEingabe;
	JLabel text1, text2, welcome, logo;
	private int FrameWidth = 1000;
	private int FrameHeight = 500;
	private String name = "Main Menu";
	private boolean visibility = true;
	private boolean resizability = true;
<<<<<<< HEAD
	JButton startButton, highscoreButton, storyButton, levelButton, exitButton;

	boolean startButtonClicked = false;
=======
	JButton startButton, highscoreButton, difficultyButton, exitButton;
>>>>>>> branch 'master' of https://github.com/Rasit1009/StudyAbgabe.git

	public MainMenu() {
		setVisible(visibility);
		setSize(FrameWidth, FrameHeight);
		setResizable(resizability);
		setTitle(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

<<<<<<< HEAD
		/*
		 * text1 = new JLabel("Study Dungeons");
		 * text1.setForeground(Color.YELLOW); text1.setBounds(208, 0, 653, 96);
		 * text1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 75));
		 * getContentPane().add(text1);
		 */
=======
		text1 = new JLabel("Study Dungeons");
		text1.setForeground(Color.YELLOW);
		text1.setBounds(208, 0, 653, 96);
		text1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 75));
		getContentPane().add(text1);
>>>>>>> branch 'master' of https://github.com/Rasit1009/StudyAbgabe.git

		text2 = new JLabel("Main Menu");
<<<<<<< HEAD
		text2.setHorizontalAlignment(SwingConstants.CENTER);
		text2.setForeground(Color.WHITE);
		text2.setBounds(94, 27, 785, 100);
=======
		text2.setForeground(Color.YELLOW);
		text2.setBounds(50, 100, 300, 55);
>>>>>>> branch 'master' of https://github.com/Rasit1009/StudyAbgabe.git
		// text.setBounds(x, y, width, height);
		Font schrift2 = new Font("Serif", Font.BOLD, 40);
<<<<<<< HEAD
		text2.setFont(new Font("18thCentury", Font.BOLD, 90));
=======
		text2.setFont(schrift2);
>>>>>>> branch 'master' of https://github.com/Rasit1009/StudyAbgabe.git
		getContentPane().add(text2);

<<<<<<< HEAD
		startButton = new JButton();
		startButton.setIcon(new ImageIcon(getClass().getResource("/StartIcon.jpg")));
		startButton.setBackground(Color.BLACK);
		startButton.setBounds(10, 150, 280, 35);
=======
		startButton = new JButton("Start");
		startButton.setFont(new Font("X-Files", Font.BOLD, 25));
		startButton.setBackground(Color.YELLOW);
		startButton.setBounds(35, 160, 400, 35);
>>>>>>> branch 'master' of https://github.com/Rasit1009/StudyAbgabe.git
		startButton.setEnabled(true);
		startButton.addActionListener(this);
		getContentPane().add(startButton);

		highscoreButton = new JButton();
		highscoreButton.setIcon(new ImageIcon(getClass().getResource("/HighscoreIcon.jpg")));
		highscoreButton.setBackground(Color.YELLOW);
<<<<<<< HEAD
		highscoreButton.setBounds(10, 220, 280, 35);
=======
		highscoreButton.setBounds(35, 220, 400, 35);
>>>>>>> branch 'master' of https://github.com/Rasit1009/StudyAbgabe.git
		highscoreButton.setEnabled(true);
		highscoreButton.addActionListener(this);
		getContentPane().add(highscoreButton);
		
		levelButton = new JButton();
//		levelButton.setIcon(new ImageIcon(getClass().getResource("/StoryIcon.jpg")));
		levelButton.setBackground(Color.YELLOW);
		levelButton.setBounds(10, 290, 280, 35);
		levelButton.setEnabled(true);
		levelButton.addActionListener(this);
		getContentPane().add(levelButton);

<<<<<<< HEAD
		storyButton = new JButton();
		storyButton.setIcon(new ImageIcon(getClass().getResource("/StoryIcon.jpg")));
		storyButton.setBackground(Color.YELLOW);
		storyButton.setBounds(10, 360, 280, 35);
		storyButton.setEnabled(true);
		storyButton.addActionListener(this);
		getContentPane().add(storyButton);
		

		exitButton = new JButton();
		exitButton.setBackground(Color.YELLOW);
		exitButton.setBounds(380, 380, 180, 60);
		exitButton.setIcon(new ImageIcon(getClass().getResource("/ExitIcon.jpg")));
=======
		difficultyButton = new JButton("Difficulty");
		difficultyButton.setFont(new Font("X-Files", Font.BOLD, 25));
		difficultyButton.setBackground(Color.YELLOW);
		difficultyButton.setBounds(35, 280, 400, 35);
		difficultyButton.setEnabled(true);
		difficultyButton.addActionListener(this);
		getContentPane().add(difficultyButton);

		exitButton = new JButton("EXIT");
		exitButton.setFont(new Font("X-Files", Font.BOLD, 40));
		exitButton.setBackground(new Color(128, 0, 0));
		exitButton.setBounds(260, 380, 180, 60);
>>>>>>> branch 'master' of https://github.com/Rasit1009/StudyAbgabe.git
		exitButton.setEnabled(true);
		exitButton.addActionListener(this);
		getContentPane().add(exitButton);

		welcome = new JLabel();
		welcome.setLayout(null);
		welcome.setIcon(new ImageIcon(getClass().getResource("src/MainmenuTest.jpg")));
		welcome.setBounds(0, 0, 1000, 500);
		getContentPane().add(welcome);

		HandlerClass handler = new HandlerClass();
		startButton.addMouseListener(handler);
		startButton.addMouseMotionListener(handler);
		highscoreButton.addMouseListener(handler);
		highscoreButton.addMouseMotionListener(handler);
		storyButton.addMouseListener(handler);
		storyButton.addMouseMotionListener(handler);
		levelButton.addMouseListener(handler);
		exitButton.addMouseListener(handler);
		exitButton.addMouseMotionListener(handler);

		repaint();

	}

	public static void main(String[] args) {

		new MainMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			new Login();
			this.dispose();
		} else if (e.getSource() == highscoreButton) {
			new Highscore();
		} else if (e.getSource() == difficultyButton) {
			// difficulty frame
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		}

	}

	private class HandlerClass implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == startButton) {
				startButton.setBounds(10, 150, 290, 45);
				startButton.setBackground(Color.BLACK);
			}
			if (e.getSource() == highscoreButton) {
				highscoreButton.setBounds(10, 220, 290, 45);
				highscoreButton.setBackground(Color.BLACK);
			}
			if (e.getSource() == storyButton) {
				storyButton.setBounds(10, 290, 290, 45);
				storyButton.setBackground(Color.BLACK);
			}
			if (e.getSource() == exitButton) {
				exitButton.setBackground(Color.BLACK);
				exitButton.setBounds(375, 375, 200, 70);
				exitButton.setIcon(new ImageIcon(getClass().getResource("/EnabledExitIcon.jpg")));
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == startButton) {
				startButton.setBounds(10, 150, 280, 35);
				startButton.setIcon(new ImageIcon(getClass().getResource("/StartIcon.jpg")));
			}
			if (e.getSource() == highscoreButton) {
				highscoreButton.setBounds(10, 220, 280, 35);
				highscoreButton.setBackground(Color.YELLOW);
				highscoreButton.setIcon(new ImageIcon(getClass().getResource("/HighscoreIcon.jpg")));
			}
			if (e.getSource() == storyButton) {
				storyButton.setBounds(10, 290, 280, 35);
				storyButton.setBackground(Color.YELLOW);
				storyButton.setIcon(new ImageIcon(getClass().getResource("/StoryIcon.jpg")));
			}
			if (e.getSource() == exitButton) {
				exitButton.setBackground(Color.YELLOW);
				exitButton.setIcon(new ImageIcon(getClass().getResource("/ExitIcon.jpg")));
				exitButton.setBounds(380, 380, 180, 60);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
