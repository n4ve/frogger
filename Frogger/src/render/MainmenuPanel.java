/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package render;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class MainmenuPanel extends JPanel {
	private static BufferedImage bg;
	static {
		bg = Gui.getImage("mainmenu.jpg");
	}

	public MainmenuPanel() {
		setPreferredSize(new Dimension(480, 640));
		setLayout(null);
		// add button
		// start button
		JPanel startButton = new JPanel();
		startButton.setBounds(207, 273, 92, 58); // set location
		startButton.setOpaque(false);
		startButton.addMouseListener(new MouseListener() {

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
				push("menu");
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				push("start");
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				Gui.goToPanel(new SelectLevelPanel());
			}
		});

		// highScore button
		JPanel highScoreButton = new JPanel();
		highScoreButton.setBounds(206, 376, 92, 57); // set location
		highScoreButton.setOpaque(false);
		highScoreButton.addMouseListener(new MouseListener() {

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
				push("menu");
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				push("highscore");
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				Gui.goToPanel(new HighScorePanel());
			}
		});

		add(startButton);
		add(highScoreButton);

	}

	private void push(String p) {
		if (p.equalsIgnoreCase("start")) {
			// change bg main menu
			bg = Gui.getImage("mainmenu_start.jpg");
		} else if (p.equalsIgnoreCase("highscore")) {
			// change bg main menu
			bg = Gui.getImage("mainmenu_highscore.jpg");
		} else {
			// change bg main menu
			bg = Gui.getImage("mainmenu.jpg");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bg, null, 0, 0);
	}
}
