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
import javax.swing.SwingConstants;

public class MainMenuB extends JFrame implements ActionListener {

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
	JButton startButton, highscoreButton, storyButton, exitButton;

	boolean startButtonClicked = false;

	public MainMenuB() {
		setVisible(visibility);
		setSize(FrameWidth, FrameHeight);
		setResizable(resizability);
		setTitle(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		/*
		 * text1 = new JLabel("Study Dungeons");
		 * text1.setForeground(Color.YELLOW); text1.setBounds(208, 0, 653, 96);
		 * text1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 75));
		 * getContentPane().add(text1);
		 */

		text2 = new JLabel("Main Menu");
		text2.setHorizontalAlignment(SwingConstants.CENTER);
		text2.setForeground(Color.WHITE);
		text2.setBounds(94, 27, 785, 100);
		// text.setBounds(x, y, width, height);
		Font schrift2 = new Font("Serif", Font.BOLD, 40);
		text2.setFont(new Font("18thCentury", Font.BOLD, 90));
		getContentPane().add(text2);

		startButton = new JButton();
		startButton.setIcon(new ImageIcon(getClass().getResource("/StartIcon.jpg")));
		startButton.setBackground(Color.BLACK);
		startButton.setBounds(10, 150, 280, 35);
		startButton.setEnabled(true);
		startButton.addActionListener(this);
		getContentPane().add(startButton);

		highscoreButton = new JButton();
		highscoreButton.setIcon(new ImageIcon(getClass().getResource("/HighscoreIcon.jpg")));
		highscoreButton.setBackground(Color.YELLOW);
		highscoreButton.setBounds(10, 220, 280, 35);
		highscoreButton.setEnabled(true);
		highscoreButton.addActionListener(this);
		getContentPane().add(highscoreButton);

		storyButton = new JButton();
		storyButton.setIcon(new ImageIcon(getClass().getResource("/StoryIcon.jpg")));
		storyButton.setBackground(Color.YELLOW);
		storyButton.setBounds(10, 290, 280, 35);
		storyButton.setEnabled(true);
		storyButton.addActionListener(this);
		getContentPane().add(storyButton);

		exitButton = new JButton();
		exitButton.setBackground(Color.YELLOW);
		exitButton.setBounds(380, 380, 180, 60);
		exitButton.setIcon(new ImageIcon(getClass().getResource("/ExitIcon.jpg")));
		exitButton.setEnabled(true);
		exitButton.addActionListener(this);
		getContentPane().add(exitButton);

		welcome = new JLabel();
		welcome.setLayout(null);
		welcome.setIcon(new ImageIcon(getClass().getResource("/StudyDungeonsMainIcon.png")));
		welcome.setBounds(0, 0, 1000, 461);
		getContentPane().add(welcome);

		HandlerClass handler = new HandlerClass();
		startButton.addMouseListener(handler);
		startButton.addMouseMotionListener(handler);
		highscoreButton.addMouseListener(handler);
		highscoreButton.addMouseMotionListener(handler);
		storyButton.addMouseListener(handler);
		storyButton.addMouseMotionListener(handler);
		exitButton.addMouseListener(handler);
		exitButton.addMouseMotionListener(handler);

		repaint();

	}

	public static void main(String[] args) {

		new MainMenuB();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			new Login();
			this.dispose();
		} else if (e.getSource() == highscoreButton) {
			new Highscore();
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
