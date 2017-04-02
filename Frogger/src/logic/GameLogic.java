/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package logic;

import java.util.ArrayList;
import java.util.List;

import render.GameScreen;
import render.Gui;
import render.RenderManager;

public class GameLogic {
	private RenderManager renderableContainer;
	private List<Entity> gameObjectContainer = new ArrayList<Entity>();
	private Frog frog;
	public static boolean isPause;
	public static int winCount = 5;

	public GameLogic(RenderManager renderableContainer) {
		this.renderableContainer = renderableContainer;
		gameObjectContainer = new ArrayList<Entity>();
		frog = new Frog(216, 576, Integer.MAX_VALUE);
		addNewObject(frog);
		addNewObject(new CarBlue(0, 512, 100));
		addNewObject(new CarBlue(240, 512, 100));
		addNewObject(new CarPolice(120, 448, 100));
		addNewObject(new CarPolice(360, 448, 100));
		addNewObject(new CarRed(180, 384, 100));
		addNewObject(new CarRed(420, 384, 100));
		addNewObject(new Log4(0, 128, 100));
		addNewObject(new Log4(288, 128, 100));
		addNewObject(new Log3(100, 256, 100));
		addNewObject(new Log3(340, 256, 100));
		addNewObject(new Turtle(200, 192, 100));
		addNewObject(new Turtle(500, 192, 100));
		addNewObject(new Block(4, 64, 40, 32, 30));
		addNewObject(new Block(100, 64, 40, 32, 30));
		addNewObject(new Block(197, 64, 40, 32, 30));
		addNewObject(new Block(293, 64, 40, 32, 30));
		addNewObject(new Block(389, 64, 40, 32, 30));

	}

	protected void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		renderableContainer.add(entity);
	}

	public void logicUpdate() {
		if (isPause)
			return;
		// -------all update----
		for (int i = 0; i < gameObjectContainer.size(); i++) {
			if (gameObjectContainer.get(i).isVisible()
					&& !gameObjectContainer.get(i).isDestroyed()) {
				gameObjectContainer.get(i).update();
			}
		}
		// set if win
		if (frog.isWin()) {
			winCount--;
			Gui.getSound().play("goal.wav", "single");
		}
		// set game over
		if (winCount == 0)
			GameScreen.setGameRuning(false);
		// -------check frog issameposition?
		for (int i = 0; i < gameObjectContainer.size(); i++) {
			if (gameObjectContainer.get(i).visible
					&& gameObjectContainer.get(i).isSamePosition(frog)) {
				// check frog car
				if (gameObjectContainer.get(i) instanceof Car) {
					Gui.getSound().play("carimpact.wav", "single");
					newFrog();
				} else if (gameObjectContainer.get(i) instanceof Block) { // block
					Gui.getSound().play("roar.wav", "single");
					newFrog();
				} // Check frog on frogwin
				else if (gameObjectContainer.get(i) instanceof Frog) {
					Frog newFrog = (Frog) gameObjectContainer.get(i);
					if (!frog.equals(newFrog)) {
						Gui.getSound().play("onfrog.wav", "single");
						newFrog();

					}
				}
				// check on objectinwater
				else if (gameObjectContainer.get(i) instanceof ObjectOnWater) {
					ObjectOnWater o = (ObjectOnWater) gameObjectContainer
							.get(i);
					frog.setOnWater(false);
					frog.moveOnWater(o.getOldV());
				}
				// set gameover
				if (Frog.getLife() <= 0) {
					GameScreen.setGameRuning(false);
					Gui.getTime().setWork(false);
				}

			}

		}
		// ----- if on water
		if (frog.isOnWater()) {
			Gui.getSound().play("waterbubble.wav", "single");
			newFrog();
		}

		// ------ if frog win
		if (frog.isWin()) {
			frog = new Frog(216, 576, Integer.MAX_VALUE);
			addNewObject(frog);
		}
	}

	// ------ set new frog and decrease life ------
	private void newFrog() {
		frog.setDestroyed(true);
		frog.setVisible(false);
		frog = new Frog(216, 576, Integer.MAX_VALUE);
		addNewObject(frog);
		Frog.decreaseLife();
	}
}
