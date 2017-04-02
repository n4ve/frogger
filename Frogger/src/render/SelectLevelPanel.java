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

import logic.ConfigulableOption;
import logic.GameLogic;

public class SelectLevelPanel extends JPanel {
	private static BufferedImage bg;

	static {
		// background
		bg = Gui.getImage("selectlevel.jpg");
	}

	public SelectLevelPanel() {
		setPreferredSize(new Dimension(800, 600));
		setLayout(null);
		// add easy button
		JPanel easyButton = new JPanel();
		easyButton.setBounds(151, 87, 189, 85); // set location
		easyButton.setOpaque(false);
		easyButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				push("manu");
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				push("easy");
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				ConfigulableOption.LEVEL = 3;
				GameLogic.winCount = 5;
				Gui.runGame();
			}
		});

		// add normal button
		JPanel normalButton = new JPanel();
		normalButton.setBounds(151, 214, 189, 86); // set location
		normalButton.setOpaque(false);
		normalButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				push("menu");
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				push("normal");
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				ConfigulableOption.LEVEL = 2;
				GameLogic.winCount = 5;

				Gui.runGame();

			}
		});
		// add hard button
		JPanel hardButton = new JPanel();
		hardButton.setBounds(151, 352, 189, 84); // set location
		hardButton.setOpaque(false);
		hardButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				push("menu");
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				push("hard");
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				ConfigulableOption.LEVEL = 1;
				GameLogic.winCount = 5;
				Gui.runGame();
			}
		});
		JPanel backButton = new JPanel();
		backButton.setBounds(420, 590, 55, 40); // set location
		backButton.setOpaque(false);
		backButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				push("menu");
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				push("back");
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Gui.goToPanel(new MainmenuPanel());
			}
		});
		// add all button
		add(easyButton);
		add(normalButton);
		add(hardButton);
		add(backButton);
	}

	private void push(String p) {
		if (p.equalsIgnoreCase("easy")) {
			// change bg main menu
			bg = Gui.getImage("selectlevel_easy.jpg");
		} else if (p.equalsIgnoreCase("normal")) {
			// change bg main menu
			bg = Gui.getImage("selectlevel_normal.jpg");
		} else if (p.equalsIgnoreCase("hard")) {
			// change bg main menu
			bg = Gui.getImage("selectlevel_hard.jpg");
		} else if (p.equalsIgnoreCase("back")) {
			// change bg main menu
			bg = Gui.getImage("selectlevel_back.jpg");
		} else {
			// change bg main menu
			bg = Gui.getImage("selectlevel.jpg");
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
