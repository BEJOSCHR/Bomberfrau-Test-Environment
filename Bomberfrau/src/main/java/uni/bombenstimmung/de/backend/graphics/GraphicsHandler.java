/*
 * GraphicsHandler
 *
 * Version 1.0
 * Author: Benni
 *
 * Verwaltet die graphischen Ver�nderungen und Wechsel zwischen den Modulen
 */
package uni.bombenstimmung.de.backend.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import uni.bombenstimmung.de.backend.animation.AnimationHandler;
import uni.bombenstimmung.de.backend.console.ConsoleHandler;
import uni.bombenstimmung.de.backend.console.MessageType;
import uni.bombenstimmung.de.backend.graphics.subhandler.KeyHandler;
import uni.bombenstimmung.de.backend.graphics.subhandler.MouseHandler;
import uni.bombenstimmung.de.backend.graphics.subhandler.WindowHandler;
import uni.bombenstimmung.de.backend.images.ImageHandler;
import uni.bombenstimmung.de.backend.sounds.SoundHandler;
import uni.bombenstimmung.de.backend.sounds.SoundType;
import uni.bombenstimmung.de.main.BomberfrauMain;
import uni.bombenstimmung.de.menu.Menu;
import uni.bombenstimmung.de.menu.Settings;
import uni.bombenstimmung.de.lobby.LobbyCreate;
import uni.bombenstimmung.de.lobby.Player;

public class GraphicsHandler {

	private static int width = 0, height = 0;
	private static Label label;
	private static JFrame frame;
	private static DisplayType displayType = DisplayType.LOADINGSCREEN;
	private static boolean shuttingDown = false;
	
	/**
	 * Wird am anfang aufgerufen um sowohl den Frame als auch das Label zu erzeugen und zuzuordnen
	 */
	public static void initVisuals() {
		
		frame = new JFrame();
		
		frame.setLocationRelativeTo(null);
		//frame.setLocation(0, 0);
		frame.setLocation((Settings.getRes_width_max()-Settings.getRes_width())/2, (Settings.getRes_height_max()-Settings.getRes_height())/2);
		frame.setTitle("BomberFrau - "+BomberfrauMain.VERSION);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true); 
		frame.setVisible(true);
		
		frame.addKeyListener(new KeyHandler());
		frame.addMouseListener(new MouseHandler());
		frame.addMouseMotionListener(new MouseHandler());
		frame.addMouseWheelListener(new MouseHandler());
		frame.addWindowListener(new WindowHandler());
		
		try {
			frame.setIconImage(ImageIO.read(BomberfrauMain.class.getClassLoader().getResourceAsStream(ImageHandler.PATH+"Bomberman_Icon.png")));
		} catch (Exception error) {
			ConsoleHandler.print("Couldn't load window icon!", MessageType.BACKEND);
		}
		
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setSize(1920, 1080);
		frame.setSize(Settings.getRes_width(), Settings.getRes_height());
		//frame.setPreferredSize(frame.getSize());
		//frame.setMinimumSize(frame.getSize());
		//frame.setMaximumSize(frame.getSize());
				
		width = frame.getWidth();
		height = frame.getHeight();
		
		//TODO CALCULATE DIMENSIONS RELATIVE TO WIDTH AND HEIGHT
		
		label = new Label();
		label.requestFocus();

		frame.requestFocus();
		
		ConsoleHandler.print("Initialised Visuals!", MessageType.BACKEND);
		
	}
	
	
	//------------------------------------------------------------------------------------------------------------------
	//SWITCH TO SECTION
	
	/**
	 * Wird von der Main am Start aufgerufen und startet das Intro
	 */
	public static void switchToIntroFromLoadingscreen() {
		
		AnimationHandler.stopAllAnimations();
		
	    	SoundHandler.playSound(SoundType.INTRO, false);

		Menu.introTextAni();
		Menu.introAnimation();
		
		displayType = DisplayType.INTRO;
		ConsoleHandler.print("Switched to 'INTRO' from 'START'!", MessageType.MENU);
		
	}
	
	/**
	 * Wird nach dem Intro aufgerufen
	 */
	public static void switchToMenuFromIntro() {

		SoundHandler.stopAllSounds();
		AnimationHandler.stopAllAnimations();
		
	        Settings.setCreate_selected(true);

	        //Menu.titleShakeAni();
	        Menu.titlePulseAni();
	        
	        Menu.buildOptions();
		Menu.buildMenu();
		Menu.optionsComponentsActive(false);
		Menu.menuComponentsActive(true);
		
	    	SoundHandler.playSound(SoundType.MENU, true);
		displayType = DisplayType.MENU;

	        ConsoleHandler.print("Switched to 'MENU' from 'INTRO'!", MessageType.MENU);	
	}
	
	/**
	 * Wird beim Wechseln von Men� zu den Optionen aufgerufen
	 */
	public static void switchToOptionsFromMenu() {
		
		AnimationHandler.stopAllAnimations();
		Menu.menuComponentsActive(false);
		Menu.optionsComponentsActive(true);

		displayType = DisplayType.OPTIONS;
		ConsoleHandler.print("Switched to 'OPTIONS' from 'MENU'!", MessageType.MENU);
		
	}
	/**
	 * Wird bei R�ckkehr von den Optionen zum Men� aufgerufen
	 */
	public static void switchToMenuFromOptions() {
		
		AnimationHandler.stopAllAnimations();
		Menu.optionsComponentsActive(false);

	        //Menu.titleShakeAni();
	        Menu.titlePulseAni();
		Menu.buildMenu();

		displayType = DisplayType.MENU;
		ConsoleHandler.print("Switched to 'MENU' from 'OPTIONS'!", MessageType.MENU);
		
	}
	/**
	 * Wird aufgerufen wenn die Lobby verlassen wird
	 */
	public static void switchToMenuFromLobby() {
		
		AnimationHandler.stopAllAnimations();

	        //Menu.titleShakeAni();
	        Menu.titlePulseAni();
		Menu.menuComponentsActive(true);

		displayType = DisplayType.MENU;
		ConsoleHandler.print("Switched to 'MENU' from 'LOBBY'!", MessageType.BACKEND);
		
	}
	
	/**
	 * Wird aufgerufen wenn w�rend einem Spiel das Spiel verlassen wird bzw der Host das Spiel schlie�t
	 */
	public static void switchToMenuFromIngame() {
		
		AnimationHandler.stopAllAnimations();
		SoundHandler.stopAllSounds();

	        //Menu.titleShakeAni();
	        Menu.titlePulseAni();
		Menu.menuComponentsActive(true);
	    	SoundHandler.playSound(SoundType.MENU, true);
		displayType = DisplayType.MENU;
		ConsoleHandler.print("Switched to 'MENU' from 'INGAME'!", MessageType.BACKEND);
		
	}
	
	/**
	 * Wird aufgerufen wenn im Aftergame die Session verlassen wird
	 */
	public static void switchToMenuFromAftergame() {
		
		AnimationHandler.stopAllAnimations();
		SoundHandler.stopAllSounds();

	        //Menu.titleShakeAni();
	        Menu.titlePulseAni();
		Menu.menuComponentsActive(true);
	    	SoundHandler.playSound(SoundType.MENU, true);
		displayType = DisplayType.MENU;
		ConsoleHandler.print("Switched to 'MENU' from 'AFTERGAME'!", MessageType.BACKEND);
		
	}
	
	/**
	 * Wird aufgerufen wenn aus dem Menu ein Spiel erstellt wird oder einem beigetreten wird
	 */
	public static void switchToLobbyFromMenu() {
		
	    	//SoundHandler.reduceAllSounds();
		AnimationHandler.stopAllAnimations();
		Menu.menuComponentsActive(false);
		
		displayType = DisplayType.LOBBY;
		ConsoleHandler.print("Switched to 'LOBBY' from 'MENU'!", MessageType.BACKEND);

		LobbyCreate lobby = new LobbyCreate(new Player("SehrSehrSehr SehrSehrSehrLange"));
		lobby.addPlayer(new Player("3 0303030303030303030303030303", "127.0.0.1"));
		lobby.addPlayer(new Player("Son", "2.0.0.2"));
		lobby.addPlayer(new Player("Hos", "1.0.0.Jos"));

	}
	
	/**
	 * Wird aufgerufen wenn das Spiel aus der Lobby gestartet wird
	 */
	public static void switchToIngameFromLobby() {
		
		//SoundHandler.stopAllSounds();
		SoundHandler.reduceAllSounds();
		AnimationHandler.stopAllAnimations();
		
		displayType = DisplayType.INGAME;
		ConsoleHandler.print("Switched to 'INGAME' from 'LOBBY'!", MessageType.BACKEND);
		
	}
	
	/**
	 * Wird aufgerufen wenn ein Spiel beendet wird und man das Aftergame betreten soll
	 */
	public static void switchToAftergameFromIngame() {
		
		AnimationHandler.stopAllAnimations();
		SoundHandler.stopAllSounds();
		
		displayType = DisplayType.AFTERGAME;
		ConsoleHandler.print("Switched to 'AFTERGAME' from 'INGAME'!", MessageType.BACKEND);
		
	}
	//SWITCH TO SECTION
	//------------------------------------------------------------------------------------------------------------------
	
	/**
	 *  sch�nerer Font mit abgerundeten Zeichen
 	 */
	public static Font usedFont(int textSize) {

	    Float factor = (float)(Settings.getRes_height())/Settings.getRes_height_max();
	    Font font;
	    font = new Font("Arial", Font.BOLD, (int)(40*factor));
	    try {
        	    font = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\resources\\fonts\\vagrounded.ttf"));
                    font = font.deriveFont(10f*factor);
	    }
            catch (Exception e) {}
            font = font.deriveFont((10f+textSize)*factor);
	    return font;
	}
	
	/**
	 * Allgemeine methode um einen beliebigen text mit parametern relativ zu einem Punkt (x,y) mittig darzustellen
	 * @param g, das Graphics object
	 * @param color, die Textfarbe
	 * @param textSize, die Textgr��e
	 * @param text, der eigentliche Text
	 * @param x, die X-Koordinate (Links-Rechts-Verschiebung) zu der der Text mittig dargestellt wird
	 * @param y, die Y-Koordinate (Oben-Unten-Verschiebung) zu der der Text mittig dargestellt wird
	 */
	public static void drawCentralisedText(Graphics g, Color color, int textSize, String text, int x, int y) {
		
		g.setColor(color);   
        	g.setFont(usedFont(textSize));
		int width = g.getFontMetrics().stringWidth(text);
		int height = g.getFontMetrics().getHeight()*2/3;
		g.drawString(text, x-width/2, y+height/2);
		
	}

	
	public static void drawLeftText(Graphics g, Color color, int textSize, String text, int x, int y) {
		
		g.setColor(color);   
        	g.setFont(usedFont(textSize));
		int height = g.getFontMetrics().getHeight()*2/3;
		g.drawString(text, x, y+height/2);
		
	}
	
	public static void drawRightText(Graphics g, Color color, int textSize, String text, int x, int y) {
		
		g.setColor(color);   
        	g.setFont(usedFont(textSize));
		int height = g.getFontMetrics().getHeight()*2/3;
		g.drawString(text, x-g.getFontMetrics().stringWidth(text), y+height/2);
	}
	
	/**
	 * Der einzige saubere Weg dieses Programm zu stoppen (Stoppt alle Timer und schlie�t KONTROLLIERT alle Datenzug�nge bzw speichert setting etc).
	 * Wenn einmal aufgerufen werden weitere Aufrufe dieser Methode abgeblockt, so dass ein doppeltes runterfahren nicht m�glich ist!
	 */
	public static void shutdownProgram() {
		
		if(shuttingDown) { return; }
		
		ConsoleHandler.print("Stopping Bomberfrau ["+BomberfrauMain.VERSION+"]", MessageType.IMPORTANT);
		
		shuttingDown = true;
		
		ConsoleHandler.stopInputScanner();
		AnimationHandler.stopTickTimer();
		
		System.exit(0);
		
	}
	
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	public static JFrame getFrame() {
		return frame;
	}
	public static Label getLabel() {
		return label;
	}
	public static DisplayType getDisplayType() {
		return displayType;
	}
	
}
