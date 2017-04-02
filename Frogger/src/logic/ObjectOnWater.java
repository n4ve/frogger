/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package logic;

public abstract class ObjectOnWater extends Entity {
	protected int vCount = ConfigulableOption.LEVEL;
	protected int oldV = 0; // previous v

	public int getOldV() {
		return oldV;
	}

	public void setOldV(int oldV) {
		this.oldV = oldV;
	}

	protected ObjectOnWater(int x, int y, int width, int high, int z) {
		super(x, y, width, high, z);
	}

}
