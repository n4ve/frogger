/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import logic.ConfigulableOption;
import logic.Frog;
import logic.GameLogic;
import logic.InputUtility;
import render.Gui;
import highscore.*;

public class GameScreen extends JComponent implements KeyListener, Runnable {
	private static BufferedImage bg;
	private static BufferedImage heart;
	private RenderManager renderManager;
	private GameLogic gameLogic;
	private static boolean gameRuning;
	static {
		bg = Gui.getImage("gamescreen.jpg");
		heart = Gui.getImage("Heart.png");
	}

	public GameScreen() {
		gameRuning = true;
		setPreferredSize(new Dimension(480, 640));
		setLayout(null);
		renderManager = new RenderManager();
		gameLogic = new GameLogic(renderManager);
		addKeyListener(this);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		// ----draw bg, heart and time----
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bg, null, 0, 0);
		g2.drawImage(heart, null, 20, 0);
		g2.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g2.drawString(" " + Gui.getTime().getMinute() + " \' "
				+ Gui.getTime().getSecond() + " \' "
				+ Gui.getTime().getCentiSec(), 240, 50);
		g2.drawString("x " + Frog.getLife(), 80, 50);
		requestFocus();

		// ------ draw all object ----
		renderManager.drawScreen(g2);
		// ------draw String pause ----
		if (GameLogic.isPause)
			g2.drawString("PAUSE", ConfigulableOption.SCREEN_WIDTH / 2 - 50,
					ConfigulableOption.SCREEN_HIGH / 2 + 50);

	}

	@Override
	public void run() {
		// ----run game----
		while (gameRuning) {
			// ----- if pause , set wait-----
			synchronized (Gui.getTime()) {
				if (GameLogic.isPause)
					try {
						Gui.getSound().pauseAllSound();
						Gui.getTime().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}

			try {

				Thread.sleep(5);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// ------update all----
			repaint();
			gameLogic.logicUpdate();
			renderManager.update();
			InputUtility.postUpdate();

		}

		// -----after game over-----
		setVisible(false);
		Gui.getSound().stopAllSound();

		// lose
		if (Frog.getLife() == 0) {
			Gui.getSound().play("gameover.wav", "single");

			Gui.gameOver("loose");

		}

		// win
		else {
			Gui.getSound().play("winner.wav", "single");
			Gui.gameOver("win");
			HighScoreManager hm = new HighScoreManager();
			ScoreComparator sc = new ScoreComparator();

			if (sc.compare(hm.getScores(ConfigulableOption.LEVEL).get(4),
					new Score("test", Gui.getTime())) == 1) {
				String name = JOptionPane.showInputDialog(null,
						"Enter your name",
						"Congratulations !! You get new record",
						JOptionPane.PLAIN_MESSAGE);
				if (name == null)
					name = "---";
				else if (name.equalsIgnoreCase(""))
					name = "---";
				else if (name.length() > 5)
					name = name.substring(0, 5);
				hm.addScore(name, Gui.getTime(), ConfigulableOption.LEVEL);
			}

		}

	}

	// set key listener
	@Override
	public void keyPressed(KeyEvent e) {
		InputUtility.setKeyTriggered(e.getKeyCode(),
				!InputUtility.getKeyPressed(e.getKeyCode()));
		InputUtility.setKeyPressed(e.getKeyCode(), true);
		if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)) {
			GameLogic.isPause = !gameLogic.isPause;
			if (!GameLogic.isPause)
				synchronized (Gui.getTime()) {
					Gui.getTime().notifyAll();
					Gui.getSound().resumeAllSound();
				}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		InputUtility.setKeyPressed(e.getKeyCode(), false);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void setGameRuning(boolean set) {
		gameRuning = set;
	}

}
