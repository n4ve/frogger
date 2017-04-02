/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package render;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderManager {

	private List<IRenderable> entities;

	public RenderManager() {
		entities = new ArrayList<IRenderable>();
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, new Comparator<IRenderable>() {
			@Override
			public int compare(IRenderable o1, IRenderable o2) {
				if (o1.getZ() > o2.getZ())
					return 1;
				return -1;
			}
		});
	}

	// remove destroyed object
	public void update() {

		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}

	}

	// Will be called by JComponent object
	public void drawScreen(Graphics2D g2) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isVisible() && !entities.get(i).isDestroyed()) {
				entities.get(i).draw(g2);
			}
		}

	}
}
