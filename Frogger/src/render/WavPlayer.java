/**
 * @author1 Korranat Naruenatthanaset (5630008021)
 * @author2 Navee Sratthatad (5630332221)
 * @version 25 Nov 2014
 * Project (1/2014) in 2110215 Prog Meth
 */
package render;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.*;

public class WavPlayer {

	private ArrayList<Clip> clip;
	private ArrayList<String> clipPlayingMode;
	private boolean isPause;

	public WavPlayer() {
		clip = new ArrayList<Clip>();
		clipPlayingMode = new ArrayList<String>();
		isPause = false;
	}

	public void play(String filename, String mode) {
		clearMemory();
		URL url = null;
		url = getClass().getClassLoader().getResource("sound/" + filename);
		AudioInputStream ais;
		try {
			ais = AudioSystem.getAudioInputStream(url);
			Clip tmp = AudioSystem.getClip();
			tmp.open(ais);
			if (mode.equalsIgnoreCase("LOOP"))
				tmp.loop(Clip.LOOP_CONTINUOUSLY);
			else
				tmp.loop(0);
			clip.add(tmp);
			clipPlayingMode.add(mode);
		} catch (UnsupportedAudioFileException e) {
		} catch (IOException e) {
		} catch (LineUnavailableException e) {
		}
	}

	public void pauseAllSound() {
		for (int i = 0; i < clip.size(); i++)
			clip.get(i).stop();
		isPause = true;
	}

	public void resumeAllSound() {
		for (int i = 0; i < clip.size(); i++) {
			if (clipPlayingMode.get(i).equals("LOOP"))
				clip.get(i).loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.get(i).loop(0);
		}
		isPause = false;
	}

	public boolean isPause() {
		return isPause;
	}

	public void clearAll() {
		clip = new ArrayList<Clip>();
		clipPlayingMode = new ArrayList<String>();
	}

	public void clearMemory() {
		for (int i = 0; i < clip.size(); i++) {
			if ((!clip.get(i).isActive() || !clip.get(i).isRunning())
					&& (clipPlayingMode.get(i).equals("once")
							|| clipPlayingMode.get(i).equals("Single") || clipPlayingMode
							.get(i).equalsIgnoreCase("LOOP"))) {
				clip.get(i).close();
				clip.remove(i);
				clipPlayingMode.remove(i);
			}
		}
	}

	public void stopAllSound() {
		for (int i = 0; i < clip.size(); i++)
			clip.get(i).stop();
	}
}
