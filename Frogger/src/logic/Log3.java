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

public class Log3 extends ObjectOnWater {
	private static BufferedImage image;
	static {
		image = Gui.getImage("log3.png");
	}

	protected Log3(int x, int y, int z) {
		super(x, y, image.getWidth() - 48, image.getHeight(), z);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, null, x - 24, y);
	}

	@Override
	public void update() {
		if (vCount != 0) {
			vCount--;
			setOldV(0);
			return;
		} else {
			vCount = ConfigulableOption.LEVEL;
		}
		if (x >= 480 + 24)
			x = -width - 24;
		x = x + 2;
		setOldV(2);
	}

}
