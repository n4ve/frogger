/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package highscore;

import java.util.*;
import java.io.*;

import logic.Time;

public class HighScoreManager {
	// An arraylist of the type "score" we will use to work with the scores
	// inside the class

	private ArrayList<Score> easy;
	private ArrayList<Score> normal;
	private ArrayList<Score> hard;

	// The name of the file where the highscores will be saved

	private static final String HIGHSCORE_FILE_EASY = "easy.dat";
	private static final String HIGHSCORE_FILE_NORMAL = "normal.dat";
	private static final String HIGHSCORE_FILE_HARD = "hard.dat";

	// Initialising an in and outputStream for working with the file
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public ArrayList<Score> getScores(int level) {
		loadScoreFile(level);
		sort(level);
		if (level == 3) {
			return easy;
		} else if (level == 2) {
			return normal;
		} else
			return hard;
	}

	public HighScoreManager() {
		// initialising the scores-arraylist
		easy = new ArrayList<Score>();
		normal = new ArrayList<Score>();
		hard = new ArrayList<Score>();
	}

	private void sort(int level) {
		ScoreComparator comparator = new ScoreComparator();

		if (level == 3) {
			Collections.sort(easy, comparator);
		} else if (level == 2) {
			Collections.sort(normal, comparator);
		} else
			Collections.sort(hard, comparator);
	}

	public void addScore(String name, Time time, int level) {
		loadScoreFile(level);
		if (level == 3) {
			easy.add(new Score(name, time));
		} else if (level == 2) {
			normal.add(new Score(name, time));
		} else {
			hard.add(new Score(name, time));
		}
		updateScoreFile(level);
	}

	public void loadScoreFile(int level) {
		try {
			if (level == 3) {
				inputStream = new ObjectInputStream(new FileInputStream(
						HIGHSCORE_FILE_EASY));
				easy = (ArrayList<Score>) inputStream.readObject();
			} else if (level == 2) {
				inputStream = new ObjectInputStream(new FileInputStream(
						HIGHSCORE_FILE_NORMAL));
				normal = (ArrayList<Score>) inputStream.readObject();
			} else {
				inputStream = new ObjectInputStream(new FileInputStream(
						HIGHSCORE_FILE_HARD));
				hard = (ArrayList<Score>) inputStream.readObject();
			}

		} catch (FileNotFoundException e) {
			if (level == 3) {
				easy.add(new Score("John", new Time(98, 3, 10)));
				easy.add(new Score("Mark", new Time(62, 5, 3)));
				easy.add(new Score("Henry", new Time(34, 2, 2)));
				easy.add(new Score("David", new Time(90, 13, 1)));
				easy.add(new Score("Cony", new Time(13, 15, 1)));
			} else if (level == 2) {
				normal.add(new Score("Sophia", new Time(98, 5, 10)));
				normal.add(new Score("Eric", new Time(62, 7, 3)));
				normal.add(new Score("Edward", new Time(34, 17, 15)));
				normal.add(new Score("May", new Time(90, 13, 22)));
				normal.add(new Score("Eve", new Time(13, 15, 10)));
			} else {
				hard.add(new Score("Yegar", new Time(98, 19, 10)));
				hard.add(new Score("Mark", new Time(62, 12, 18)));
				hard.add(new Score("Henry", new Time(34, 11, 23)));
				hard.add(new Score("Paul", new Time(90, 13, 15)));
				hard.add(new Score("Christ", new Time(13, 15, 12)));
			}
			try {

				if (level == 3) {
					outputStream = new ObjectOutputStream(new FileOutputStream(
							HIGHSCORE_FILE_EASY));
					outputStream.writeObject(easy);
				} else if (level == 2) {
					outputStream = new ObjectOutputStream(new FileOutputStream(
							HIGHSCORE_FILE_NORMAL));
					outputStream.writeObject(normal);
				} else {
					outputStream = new ObjectOutputStream(new FileOutputStream(
							HIGHSCORE_FILE_HARD));
					outputStream.writeObject(hard);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (IOException e) {
			System.out.println("[Laad] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
	}

	public void updateScoreFile(int level) {
		try {
			if (level == 3) {
				outputStream = new ObjectOutputStream(new FileOutputStream(
						HIGHSCORE_FILE_EASY));
				outputStream.writeObject(easy);
			} else if (level == 2) {
				outputStream = new ObjectOutputStream(new FileOutputStream(
						HIGHSCORE_FILE_NORMAL));
				outputStream.writeObject(normal);
			} else {
				outputStream = new ObjectOutputStream(new FileOutputStream(
						HIGHSCORE_FILE_HARD));
				outputStream.writeObject(hard);
			}

		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage()
					+ ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	public String getHighscoreString(int level) {
		String mode = "            ";
		if (level == 3) {
			mode += "Easy";
		} else if (level == 2) {
			mode += "Normal";
		} else {
			mode += "Hard";
		}

		String highscoreString = "";
		int max = 5;

		ArrayList<Score> scores;
		scores = getScores(level);

		int i = 0;
		int x = scores.size();
		if (x > max) {
			x = max;
		}
		highscoreString += mode + "\n" + "\n";
		while (i < x) {
			highscoreString += (i + 1) + "    " + scores.get(i).getName()
					+ "    " + scores.get(i).getMinute() + " \" "
					+ scores.get(i).getSecond() + " \" "
					+ scores.get(i).getCentiSec() + " \n";
			i++;
		}

		return highscoreString;
	}

}
