/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package highscore;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
	public int compare(Score score1, Score score2) {

		int cs1 = score1.getCentiSec();
		int cs2 = score2.getCentiSec();
		int s1 = score1.getSecond();
		int s2 = score2.getSecond();
		int m1 = score1.getMinute();
		int m2 = score2.getMinute();

		if (m1 > m2) {
			return 1;
		} else if (m1 < m2) {
			return -1;
		} else if (m1 == m2) {
			if (s1 > s2) {
				return 1;
			} else if (s1 < s2) {
				return -1;
			} else if (s1 == s2) {
				if (cs1 > cs2) {
					return 1;
				} else if (cs1 < cs2) {
					return -1;
				}

			}

		}

		return 0;
	}
}