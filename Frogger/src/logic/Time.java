/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package logic;

public class Time implements Runnable {
	private int centiSec;
	private int second;
	private int minute;
	private static boolean work;

	public Time() {
	}

	public Time(int centiSec, int second, int minute) {
		this.centiSec = centiSec;
		this.second = second;
		this.minute = minute;
		work = false;
	}

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

	public void resetT() { // ไว้ reset เวลาเริ่มเกมใหม่
		this.centiSec = 0;
		this.second = 0;
		this.minute = 0;
	}

	public boolean isWork() {
		return work;
	}

	public void setWork(boolean work) {
		this.work = work;
	}

	public void run() {
		while (true) {
			while (GameLogic.isPause) {
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			// ถ้า work=true จะนับเวลาขึ้น 1 หน่วย
			try {
				if (work) {
					centiSec++;
					if (centiSec >= 100) {
						centiSec = 0;
						second++;
						if (second >= 60) {
							second = 0;
							minute++;
						}
					}
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
