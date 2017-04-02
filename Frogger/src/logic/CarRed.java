/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import render.Gui;

public class CarRed extends Car {
	private static BufferedImage image;
	static {
		image = Gui.getImage("carred.png");
	}

	protected CarRed(int x, int y, int z) {
		super(x, y, image.getWidth(), image.getHeight(), z);

	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, null, x, y);
	}

	@Override
	public void update() {
		// move right
		if (vCount != 0) {
			vCount--;
			return;
		} else {
			vCount = ConfigulableOption.LEVEL;
		}
		if (x >= ConfigulableOption.SCREEN_WIDTH)
			x = -width;
		x = x + 2;
	}

}
