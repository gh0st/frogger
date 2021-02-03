package a3;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
public class Sound {
	AudioClip myClip;
	public Sound(String fileName) {
		try {
			File file = new File(fileName);
			if (file.exists()) {
				myClip = Applet.newAudioClip(file.toURI().toURL());
			} else {
				throw new RuntimeException("Sound: file not found: "+fileName);
			}
		} catch (Exception e) {
			throw new RuntimeException("Sound: malformed URL: "+e);
		}
	}
	public void play() {
		myClip.play();
	}
	public void loop() {
		myClip.loop();
	}
	public void stop() {
		myClip.stop();
	}
}
