/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package render;

import java.awt.Graphics2D;

public interface IRenderable {
	public int getZ();

	public void draw(Graphics2D g2);

	public boolean isDestroyed();

	public boolean isVisible();
}
