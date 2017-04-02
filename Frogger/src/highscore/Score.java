/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package highscore;

import java.io.Serializable;

import logic.Time;

public class Score implements Serializable {

	private int centiSec;
	private int second;
	private int minute;
	private String name;

	public int getCentiSec() {
		return centiSec;
	}

	public void setCentiSec(int centiSec) {
		this.centiSec = centiSec;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Score(String name, int centiSec, int second, int minute) {

		this.name = name;
		this.centiSec = centiSec;
		this.second = second;
		this.minute = minute;

	}

	public Score(String name, Time time) {

		this.name = name;
		this.centiSec = time.getCentiSec();
		this.second = time.getSecond();
		this.minute = time.getMinute();

	}

}
