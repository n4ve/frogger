/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package logic;

import render.IRenderable;

public abstract class Entity implements IRenderable {

	protected int x, y, z, width, high; // x,y are positions on up left corner
	protected boolean visible, destroyed;

	protected Entity(int x, int y, int width, int high, int z) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.high = high;
		this.z = z;
		visible = true;
		destroyed = false;
	}

	public boolean isSamePosition(Entity other) {
		if (other.x >= x + width || other.x + other.width <= x
				|| other.y >= y + high || other.y + other.high <= y)
			return false;
		return true;
	}

	public abstract void update();

	// Getter& Setter
	public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}



	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		return visible;

	}

	@Override
	public int getZ() {
		return z;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

}
