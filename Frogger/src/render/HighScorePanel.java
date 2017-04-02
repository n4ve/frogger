/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package render;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import highscore.*;

public class HighScorePanel extends JPanel {
	private static int level = 3;
	private static BufferedImage bg;
	private HighScoreManager hm;
	private String records;

	static {
		bg = Gui.getImage("HighScorePanel.jpg");
	}

	public HighScorePanel() {
		setPreferredSize(new Dimension(480, 640));
		hm = new HighScoreManager();
		setLayout(null);

		records = hm.getHighscoreString(level);

		JPanel leftButton = new JPanel();
		leftButton.setBounds(95, 510, 55, 50); // set location
		leftButton.setOpaque(false);
		leftButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				push("bg");
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				push("left");
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				level++;
				if (level >= 3)
					level = 3;
				records = hm.getHighscoreString(level);
				repaint();

			}
		});
		JPanel rightButton = new JPanel();
		rightButton.setBounds(350, 505, 55, 50); // set location
		rightButton.setOpaque(false);
		rightButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				push("bg");
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				push("right");
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				level--;
				if (level <= 1)
					level = 1;
				records = hm.getHighscoreString(level);
				repaint();
			}
		});
		JPanel backButton = new JPanel();
		backButton.setBounds(420, 590, 55, 40); // set location
		backButton.setOpaque(false);
		backButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				push("bg");
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				push("back");
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				Gui.goToPanel(new MainmenuPanel());
			}
		});
		add(leftButton);
		add(rightButton);
		add(backButton);

	}

	private void push(String p) {
		if (p.equalsIgnoreCase("left")) {
			bg = Gui.getImage("HighScorePanel_left.jpg");
		} else if (p.equalsIgnoreCase("right")) {
			bg = Gui.getImage("HighScorePanel_right.jpg");
		} else if (p.equalsIgnoreCase("back")) {
			bg = Gui.getImage("HighScorePanel_back.jpg");
		} else {
			bg = Gui.getImage("HighScorePanel.jpg");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bg, null, 0, 0);
		g2.setFont(new Font("Arial Black", Font.BOLD, 22));
		drawStringLine(g2, records, 80, 130);
	}

	void drawStringLine(Graphics2D g2, String text, int x, int y) {
		for (String line : text.split("\n"))
			g2.drawString(line, x, y += g2.getFontMetrics().getHeight());
	}
}
