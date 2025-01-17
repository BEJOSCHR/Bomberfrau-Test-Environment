/*
 * SoundHandler
 *
 * Version 1.0
 * Author: Benni
 *
 * Verwaltet alle Sounds (laden, ausgeben, ver�ndern...)
 */
package uni.bombenstimmung.de.backend.sounds;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import uni.bombenstimmung.de.backend.animation.Animation;
import uni.bombenstimmung.de.backend.animation.AnimationData;
import uni.bombenstimmung.de.backend.console.ConsoleHandler;
import uni.bombenstimmung.de.backend.console.MessageType;
import uni.bombenstimmung.de.menu.Menu;
import uni.bombenstimmung.de.menu.Settings;

public class SoundHandler {

    public static final String PATH = "sounds/";
    private static List<LoadedSound> sounds = new ArrayList<LoadedSound>();
    public static Clip lastPlayedClip;

    /**
     * Wird am Start aufgerufen und l�d alle Sounds
     */
    public static void initSounds() {

	// TODO ADD SOUND TO LOAD HERE
	// EXAMPLE: new LoadedSound("test123.wav", SoundType.SOUND_MENU_XXX,
	// SoundCategory.SOUND_EFFECT, 0.02D);

	new LoadedSound("menu/logo_opener.wav", SoundType.INTRO, SoundCategory.MENU_MUSIC, 0.2D);
	new LoadedSound("menu/menu.wav", SoundType.MENU, SoundCategory.MENU_MUSIC, 0.2D);
	new LoadedSound("menu/sound.wav", SoundType.OPTIONS, SoundCategory.MENU_SOUND, 0.2D);

	new LoadedSound("ingame/MAP1.wav", SoundType.MAP1, SoundCategory.INGAME_MUSIC, 0.2D);
	new LoadedSound("ingame/MAP2.wav", SoundType.MAP2, SoundCategory.INGAME_MUSIC, 0.2D);
	new LoadedSound("ingame/MAP3.wav", SoundType.MAP3, SoundCategory.INGAME_MUSIC, 0.2D);
	new LoadedSound("ingame/fuse.wav", SoundType.FUSE, SoundCategory.INGAME_SOUNDS, 0.2D);
	new LoadedSound("ingame/explosion.wav", SoundType.EXPLOSION, SoundCategory.INGAME_SOUNDS, 0.1D);
	new LoadedSound("ingame/item.wav", SoundType.ITEM, SoundCategory.INGAME_SOUNDS, 0.2D);
	new LoadedSound("ingame/wall.wav", SoundType.WALL, SoundCategory.INGAME_SOUNDS, 0.2D);
	new LoadedSound("ingame/dying.wav", SoundType.DYING, SoundCategory.INGAME_SOUNDS, 0.2D);
	new LoadedSound("ingame/countdown.wav", SoundType.COUNTDOWN, SoundCategory.INGAME_SOUNDS, 0.2D);

	new LoadedSound("aftergame/aftergame.wav", SoundType.AFTERGAME, SoundCategory.AFTERGAME_MUSIC, 0.08);

	ConsoleHandler.print("Loaded sounds (" + sounds.size() + ")", MessageType.BACKEND);

    }

//	/**
//	 * Spielt den Sound ab, der zum �bergebenene Type geh�rt
//	 * @param type - Der {@link SoundType} der den Sound identifziert
//	 * @param loop - Clip in Schleife wiederholen oder nicht
//	 */
//	public static void playSound_alt(SoundType type, boolean loop) {
//		
//		LoadedSound sound = getSound(type);
//		Clip clip = sound.getClip();
//		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//		float value = 20f * (float) Math.log10(sound.getVolume());
//		if(value < -70) { value = -70; } //MINIMUM VOLUME
//		else if(value > 6) { value = 6; } //MAXIMUM VOLUME
//		gainControl.setValue(value);
//		clip.setFramePosition(0);
//		if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
//		clip.start();
//		lastPlayedClip = clip;	
//	}

    /**
     * Spielt den Sound ab, der zum �bergebenene Type geh�rt
     * 
     * @param type - Der {@link SoundType} der den Sound identifziert
     * @param loop - Clip in Schleife wiederholen oder nicht
     */
    public static void playSound(SoundType type, boolean loop) {
	float vol = -80F;

	LoadedSound sound = getSound(type);
	Clip clip = sound.getClip();
	FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	if (sound.getCategory() == SoundCategory.MENU_MUSIC)
	    vol = Menu.volumeIntToFloat(Settings.getIniVolMusic());
	if (sound.getCategory() == SoundCategory.MENU_SOUND)
	    vol = Menu.volumeIntToFloat(Settings.getIniVolSound());
	if (sound.getCategory() == SoundCategory.INGAME_MUSIC)
	    vol = Menu.volumeIntToFloat(Settings.getIniVolMusic());
	if (sound.getCategory() == SoundCategory.INGAME_SOUNDS)
	    vol = Menu.volumeIntToFloat(Settings.getIniVolSound());
	if (sound.getCategory() == SoundCategory.AFTERGAME_MUSIC)
	    vol = Menu.volumeIntToFloat(Settings.getIniVolMusic());
	if ((vol > -80F) || (type == SoundType.MENU)) {
	    gainControl.setValue(vol);
	    // ConsoleHandler.print("playing sound '" + type + "' with Volume " + vol,
	    // MessageType.BACKEND);
	    clip.setFramePosition(0);
	    if (loop)
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	    clip.start();
	}
    }

    /**
     * Spielt den Sound ab, der zum �bergebenene Type geh�rt
     * 
     * @param type  - Der {@link SoundType} der den Sound identifziert
     * @param loop  - Clip in Schleife wiederholen oder nicht
     * @param value - Lautst�rke [-70F bis 6F]
     */
    public static void playSound(SoundType type, boolean loop, float value) {

	LoadedSound sound = getSound(type);
	Clip clip = sound.getClip();
	FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	if (value < -70) {
	    value = -70;
	} // MINIMUM VOLUME
	else if (value > 6) {
	    value = 6;
	} // MAXIMUM VOLUME
	gainControl.setValue(value);
	clip.setFramePosition(0);
	if (loop)
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	clip.start();
	lastPlayedClip = clip;
    }

    /**
     * Stopt den Sound, der zum �bergebenene Type geh�rt
     * 
     * @param type - Der {@link SoundType} der den Sound identifziert
     */
    public static void stopSound(SoundType type) {
	LoadedSound sound = getSound(type);
	Clip clip = sound.getClip();
	clip.stop();
	ConsoleHandler.print("stoppe Sound " + type, MessageType.BACKEND);
    }

    /**
     * Stoppt alle Sounds die gerade laufen
     */
    public static void stopAllSounds() {
	for (LoadedSound sounds : sounds) {
	    sounds.getClip().stop();
	}
    }

    /**
     * Reduziert die Lautstaerke des gerade laufenden Clips kontinuierlich bis zur
     * Stille.
     * 
     * @param type    Der SoundType, bei welchem die Lautstaerke reduziert werden
     *                soll.
     * @param seconds Die Dauer der Lautstaerkenreduktion.
     * @param delay   Bestimmt, ob der weitere Programmverlauf verzoegert werden
     *                soll, bis die Reduktion beendet ist.
     */
    /*
     * TODO: Es gibt wohl einen Bug in Java, wo das Aendern der Lautstaerke gefuehlt
     * eine halbe oder ganze Sekunde spaeter registriert wird, obwohl der Volume
     * Wert richtig durchgehen aktualisiert wird. Es wird empfohlen, beim Aufrufen
     * der Methode die Dauer auf mind. 3 Sekunden zu setzen, damit das nicht so sehr
     * auffaellt.
     */
    public static void reducePlayingSound(SoundType type, int seconds, boolean delay) {
	Clip clip = getSound(type).getClip();
	FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	float vol = volume.getValue();
	float factor = (vol + 60f) / (float) (seconds * 100);
	if (delay) {
	    AnimationData.vol = vol;
	    int timer = 0;
	    while (timer < (seconds * 100)) {
		AnimationData.vol -= factor;
		if (AnimationData.vol < -60f) {
		    AnimationData.vol = -60f;
		}
		volume.setValue(AnimationData.vol);
		Menu.sleep(10);
		timer++;
	    }
	    clip.stop();
	} else {
	    new Animation(1, seconds * 100) {
		@Override
		public void initValues() {
		    AnimationData.vol = vol;
		}

		@Override
		public void changeValues() {
		    AnimationData.vol -= factor;
		    if (AnimationData.vol < -60f) {
			AnimationData.vol = -60f;
		    }
		    volume.setValue(AnimationData.vol);
		}

		@Override
		public void finaliseValues() {
		    clip.stop();
		}
	    };
	}
    }

    /**
     * Passt den Sound bei allen {@link LoadedSound} an, die zu dieser Category
     * geh�ren
     * 
     * @param category-      Die Category die ver�ndert werden soll
     * @param volumeModifier - Der modifier [0.02D hei�t also das das volume um
     *                       diesen wert erh�ht wird, -0.02D das er geringer wird]
     * @see SoundCategory, {@link LoadedSound}
     */
    public static void changeCategoryVolume(SoundCategory category, double volumeModifier) {

	for (LoadedSound sound : getSoundCategory(category)) {
	    sound.setCategoryVolume(sound.getCategoryVolume() + volumeModifier);
	}

    }

    /**
     * Passt den Sound bei allen {@link LoadedSound} an, die zu dieser Category
     * geh�ren
     * 
     * @param category- Die Category die ver�ndert werden soll
     * @param volume    - Die gew�nschte Lautst�rke
     * @see SoundCategory, {@link LoadedSound}
     */
    public static void setCategoryVolume(SoundCategory category, double volume) {

	for (LoadedSound sound : getSoundCategory(category)) {
	    sound.setCategoryVolume(volume);
	}

    }

    /**
     * Added den angegebenen Sound zur sounds Liste (Wird eigentlich nur aus den
     * LoadedSounds aufgerufen)
     * 
     * @param sound - Der zu addende Sound
     * @see LoadedSounds
     */
    public static void addSound(LoadedSound sound) {

	sounds.add(sound);

    }

    /**
     * Gibt den Sound zum zugeh�rigen Type zur�ck (Wenn richtig geladen gibt es
     * f�r jeden Type einen Sound)
     * 
     * @param type - Der Type der gesucht wird
     * @see SoundType
     * @return Der {@link LoadedSound} der zum Type geh�rt, wenn keiner gefunden
     *         wird dann null
     */
    public static LoadedSound getSound(SoundType type) {

	for (LoadedSound sound : sounds) {
	    if (sound.getType() == type) {
		return sound;
	    }
	}

	return null;
    }

    /**
     * Gibt die Sounds zur zugeh�rigen Category zur�ck (Wenn richtig geladen
     * gibt es f�r jede Category einen Sound)
     * 
     * @param category - Die Category die gesucht wird
     * @see SoundCategory
     * @return Liste von {@link LoadedSound} die zur Category geh�ren, wenn keiner
     *         gefunden wird dann eine Leere Liste
     */
    public static List<LoadedSound> getSoundCategory(SoundCategory category) {

	List<LoadedSound> output = new ArrayList<LoadedSound>();
	for (LoadedSound sound : sounds) {
	    if (sound.getCategory() == category) {
		output.add(sound);
	    }
	}

	return output;
    }

}
