/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package logic;

public abstract class Car extends Entity {
	protected int vCount = ConfigulableOption.LEVEL; // counter of moving

	protected Car(int x, int y, int width, int hight, int z) {
		super(x, y, width, hight, z);
	}

}
