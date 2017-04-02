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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logic.InputUtility;

public class WinPanel extends JPanel {

	public WinPanel() {

		setPreferredSize(new Dimension(480, 640));
		setLayout(null);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Gui.getSound().stopAllSound();
				setVisible(false);
				Gui.getSound().play("bgmainmenu.wav", "Loop");
				Gui.goToPanel(new MainmenuPanel());
				InputUtility.reset();
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 480, 640);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Arial Black", Font.BOLD, 30));
		g2.drawString("You Win", 160, 300);

	}
}
