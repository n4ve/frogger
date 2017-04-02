/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package logic;

public class InputUtility {
	private static boolean keyPressed[] = new boolean[256];
	private static boolean keyTriggered[] = new boolean[256];

	public static void setKeyPressed(int key, boolean pressed) {
		if (key < 0 || key >= 256)
			return;
		InputUtility.keyPressed[key] = pressed;
	}

	public static void setKeyTriggered(int key, boolean pressed) {
		if (key < 0 || key >= 256)
			return;
		InputUtility.keyTriggered[key] = pressed;
	}

	public static boolean getKeyPressed(int key) {
		if (key < 0 || key >= 256)
			return false;
		return keyPressed[key];
	}

	public static boolean getKeyTriggered(int key) {
		if (key < 0 || key >= 256)
			return false;
		return keyTriggered[key];
	}

	public static void reset() {
		for (int i = 0; i < keyPressed.length; i++) {
			keyPressed[i] = false;
			keyTriggered[i] = false;
		}
	}

	public static void postUpdate() {
		keyTriggered = new boolean[256];
	}
}
