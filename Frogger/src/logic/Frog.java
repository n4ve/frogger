/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package logic;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import render.Gui;

public class Frog extends Entity {
	private static int life = 5; // life of frog in game
	private boolean isOnWater; // is frog on the water
	private boolean isOnOther; // is frog on any entity
	private boolean isWin; // is frog win
	private int moveY = ConfigulableOption.SCREEN_HIGH / 10; // distance in one
																// move
																// (up,down)
	private int moveX = ConfigulableOption.SCREEN_WIDTH / 10; // (left,right)
	private int moveCount = 0; // counter of moving
	private boolean movingup, movingdown, movingleft, movingright; // is
																	// moving..
	private int direction = ConfigulableOption.NORTH; //
	private static BufferedImage imageUp;
	private static BufferedImage imageUpMoving;
	private static BufferedImage imageDown;
	private static BufferedImage imageDownMoving;
	private static BufferedImage imageLeft;
	private static BufferedImage imageLeftMoving;
	private static BufferedImage imageRight;
	private static BufferedImage imageRightMoving;
	private static BufferedImage imageWin;

	static {
		imageUp = Gui.getImage("frogtop.png");
		imageUpMoving = Gui.getImage("frogtopmov.png");
		imageDown = Gui.getImage("frogdown.png");
		imageDownMoving = Gui.getImage("frogdownmov.png");
		imageLeft = Gui.getImage("frogleft.png");
		imageLeftMoving = Gui.getImage("frogleftmov.png");
		imageRight = Gui.getImage("frogright.png");
		imageRightMoving = Gui.getImage("frogrightmov.png");
		imageWin = Gui.getImage("frogwin.png");

	}

	public Frog(int x, int y, int z) {
		super(x, y, imageUp.getWidth(), imageUp.getHeight(), z);
	}

	@Override
	public void update() {
		if (isWin)
			return;

		// --------isOnwater---------
		if (y < ConfigulableOption.SCREEN_HIGH / 2) {
			isOnWater = true;
		} else
			isOnWater = false;

		// --------moving-------
		if (movingup) {
			isOnWater = false;
			direction = ConfigulableOption.NORTH;
			if (moveCount < moveY) {
				if (y > 64) {
					y = y - 2;
				}
				moveCount = moveCount + 2;
				if (moveCount >= moveY) {
					moveCount = 0;
					movingup = false;
				}
			}

		}

		else if (movingdown) {
			isOnWater = false;
			direction = ConfigulableOption.SOUTH;
			if (moveCount < moveY) {
				if (y < 640 - 64) {
					y = y + 2;
				}
				moveCount = moveCount + 2;
				if (moveCount >= moveY) {
					moveCount = 0;
					movingdown = false;
				}
			}

		}

		else if (movingleft) {
			isOnWater = false;
			direction = ConfigulableOption.WEST;
			if (moveCount < moveX) {
				if (x > 0) {
					x = x - 2;
				}
				moveCount = moveCount + 2;
				if (moveCount >= moveX) {
					moveCount = 0;
					movingleft = false;
				}
			}

		}

		else if (movingright) {
			isOnWater = false;
			direction = ConfigulableOption.EAST;
			if (moveCount < moveX) {
				if (x < 480 - 48) {
					x = x + 2;
				}
				moveCount = moveCount + 2;
				if (moveCount >= moveX) {
					moveCount = 0;
					movingright = false;
				}
			}
			// -----set move-------
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_UP)
				&& InputUtility.getKeyTriggered(KeyEvent.VK_UP)) {
			movingup = true;
			Gui.getSound().play("jump.wav", "single");

		} else if (InputUtility.getKeyPressed(KeyEvent.VK_DOWN)
				&& InputUtility.getKeyTriggered(KeyEvent.VK_DOWN)) {
			movingdown = true;
			Gui.getSound().play("jump.wav", "single");

		} else if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)
				&& InputUtility.getKeyTriggered(KeyEvent.VK_LEFT)) {
			movingleft = true;
			Gui.getSound().play("jump.wav", "single");

		} else if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)
				&& InputUtility.getKeyTriggered(KeyEvent.VK_RIGHT)) {
			movingright = true;
			Gui.getSound().play("jump.wav", "single");

		}

		// check on goal
		if (y == 64)
			isWin = true;
	}

	@Override
	public void draw(Graphics2D g2) {
		// -------check win---------
		if (isWin) {
			g2.drawImage(imageWin, null, x + 10 - (x + 10) % 48, y - y % 64);

		} else
		// -------draw if moving-------
		if (movingup) {
			g2.drawImage(imageUp, null, x, y);
		} else if (movingdown) {
			g2.drawImage(imageDownMoving, null, x, y);
		} else if (movingleft) {
			g2.drawImage(imageLeftMoving, null, x, y);
		} else if (movingright) {
			g2.drawImage(imageRightMoving, null, x, y);

			// ---------draw not move
		} else if (direction == ConfigulableOption.NORTH)
			g2.drawImage(imageUpMoving, null, x, y);
		else if (direction == ConfigulableOption.SOUTH) {
			g2.drawImage(imageDown, null, x, y);
		} else if (direction == ConfigulableOption.EAST) {
			g2.drawImage(imageRight, null, x, y);
		} else if (direction == ConfigulableOption.WEST) {
			g2.drawImage(imageLeft, null, x, y);
		}

	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public boolean equals(Frog other) {
		if (other == null)
			return false;
		if (direction != other.direction)
			return false;
		if (isWin != other.isWin)
			return false;
		if (moveCount != other.moveCount)
			return false;
		if (moveX != other.moveX)
			return false;
		if (moveY != other.moveY)
			return false;
		if (movingdown != other.movingdown)
			return false;
		if (movingleft != other.movingleft)
			return false;
		if (movingright != other.movingright)
			return false;
		if (movingup != other.movingup)
			return false;
		return true;
	}

	public void moveOnWater(int v) {
		if (movingdown || movingleft || movingright || movingup)
			;
		else if ((v > 0 && x < ConfigulableOption.SCREEN_WIDTH - width)
				|| (v < 0 && x > 1))
			x = x + v;
	}

	public static int getLife() {
		return life;
	}

	public static void setLife() {
		life = 5;
	}

	public static void increaseLife() {
		life++;
	}

	public static void decreaseLife() {
		life--;
	}

	public boolean isOnWater() {
		return isOnWater;
	}

	public void setOnWater(boolean b) {
		isOnWater = b;
	}

	public void setOnOther(boolean isOnOther) {
		this.isOnOther = isOnOther;
	}

}
