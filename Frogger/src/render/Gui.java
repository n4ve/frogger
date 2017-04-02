/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package render;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Frog;
import logic.Time;

public class Gui {
	private static JFrame window;
	private static JPanel panel;
	private static Time time;
	private static WavPlayer sound;
	public Gui() {
		sound = new WavPlayer();
		window = new JFrame("Little Frogger");
		panel = new MainmenuPanel();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.add(panel);
		window.pack();
		window.setVisible(true);
		sound.play("bgmainmenu.wav", "Loop");
		time = new Time();
	}

	// ----change panel-----
	public static void goToPanel(JPanel p) {
		panel = p;
		window.add(panel);
		panel.setVisible(false);
		panel.setVisible(true);

	}

	// -----gameover ------
	public static void gameOver(String state) {

		if (state.equalsIgnoreCase("WIN")) {
			// /win
			goToPanel(new WinPanel());

		} else {
			// lose
			JPanel j = new GameOverPanel();
			goToPanel(j);

		}
	}

	// -----start game ------
	public static void runGame() {
		Frog.setLife();
		GameScreen g = new GameScreen();
		window.add(g);
		g.setVisible(true);
		Thread t = new Thread(g);
		t.start();
		sound.stopAllSound();
		sound.clearAll();
		sound.play("bg.wav", "loop");
		time = new Time();
		time.setWork(true);
		Thread t2 = new Thread(time);
		t2.start();

	}

	public static Time getTime() {
		return time;
	}

	public static void setTime(Time time) {
		Gui.time = time;
	}

	public static WavPlayer getSound() {
		return sound;
	}

	public static BufferedImage getImage(String s) {
		BufferedImage image;
		try {
			image = ImageIO.read(Frog.class.getClassLoader().getResource(
					"img/" + s));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
