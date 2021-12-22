/*
 * PlayerHandler
 *
 * Version 0.4.1
 * 
 * Author: Christopher
 * 
 * Datum: 18.12.2021
 *
 * Verwaltet die erzeugten Player und deren Steuerung
 */

package uni.bombenstimmung.de.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import uni.bombenstimmung.de.backend.console.ConsoleHandler;
import uni.bombenstimmung.de.backend.console.MessageType;
import uni.bombenstimmung.de.backend.graphics.GraphicsHandler;

public class PlayerHandler {
    private static Player clientPlayer = new Player(0, "Bob", "localhost", true, 0,
	    					    new Point(1, 1));
    private static ArrayList<Player> opponentPlayers = new ArrayList<Player>();
    private static int opponentCount = 0;
    private static boolean playerMoving = false;
    private static boolean multiPress = false;
    private static ArrayList<Integer> inputBuffer = new ArrayList<Integer>();
    private static boolean debugKeys = true;
    
    public static Player getClientPlayer() {
	return clientPlayer;
    }
    
    public static ArrayList<Player> getOpponentPlayers() {
	return opponentPlayers;
    }
    
    public static int getOpponentCount() {
	return opponentCount;
    }
    
    /**
     * Setzt den Player, welcher auf der eigenen Programminstanz der zu steuernde Player ist.
     * @param id	ID des Players
     * @param name	Name des Player
     * @param ipAdress	IP-Adresse zugehoerig zu dem Player
     * @param host	Boolean, ob dieser Player der Host des Spiels ist
     * @param skin	Skin-ID des Players
     * @param pos	Position des Players
     */
    public static void setClientPlayer(int id, String name, String ipAdress, boolean host, int skin, Point pos) {
	clientPlayer = new Player(id, name, ipAdress, host, skin, pos);
    }
    
    /**
     * Fuegt einen Player hinzu, der aus Sicht des Benutzers sein Gegenspieler ist.
     * @param id	ID des Players
     * @param name	Name des Player
     * @param ipAdress	IP-Adresse zugehoerig zu dem Player
     * @param host	Boolean, ob dieser Player der Host des Spiels ist
     * @param skin	Skin-ID des Players
     * @param pos	Position des Players
     */
    public static void addOpponentPlayer(int id, String name, String ipAdress, boolean host, int skin, Point pos) {
	opponentPlayers.add(new Player(id, name, ipAdress, host, skin, pos));
	opponentCount++;
    }
    
    /**
     * Loescht die Gegenspieler-Liste vollstaendig.
     */
    public static void clearOpponentPlayers() {
	opponentPlayers.clear();
	opponentCount = 0;
    }
    
    public static void resetMovement() {
	playerMoving = false;
	multiPress = false;
	inputBuffer.clear();
    }
    
    /* Draw-Methode mit allen Player-bezogenen Grafiken. Wird vom GraphicsHandler aufgerufen. */
    public static void drawPlayers(Graphics g) {
	if (clientPlayer.getDead() == false) {
	    g.setColor(Color.red);
	    g.drawRect((int)(clientPlayer.getPosition().getX() - GraphicsHandler.getWidth()/44.5/2),
		    	(int)(clientPlayer.getPosition().getY() - GraphicsHandler.getHeight()/25/2),
		    	(int)(GraphicsHandler.getWidth()/44.5), (int)(GraphicsHandler.getHeight()/25));
	    g.fillRect((int)(clientPlayer.getPosition().getX() - GraphicsHandler.getWidth()/44.5/2),
		    	(int)(clientPlayer.getPosition().getY() - GraphicsHandler.getHeight()/25/2),
		    	(int)(GraphicsHandler.getWidth()/44.5), (int)(GraphicsHandler.getHeight()/25));
	} else {
	    g.setColor(Color.black);
	    g.drawRect((int)(clientPlayer.getPosition().getX() - GraphicsHandler.getWidth()/44.5/2),
		    	(int)(clientPlayer.getPosition().getY() - GraphicsHandler.getHeight()/25/2),
		    	(int)(GraphicsHandler.getWidth()/44.5), (int)(GraphicsHandler.getHeight()/25));
	    g.fillRect((int)(clientPlayer.getPosition().getX() - GraphicsHandler.getWidth()/44.5/2),
		    	(int)(clientPlayer.getPosition().getY() - GraphicsHandler.getHeight()/25/2),
		    	(int)(GraphicsHandler.getWidth()/44.5), (int)(GraphicsHandler.getHeight()/25));
	}
	for (Player i : opponentPlayers) {
	    if (i.getDead() == false) {
		g.setColor(Color.green);
		g.drawRect((int)(i.getPosition().getX() - GraphicsHandler.getWidth()/44.5/2),
			    (int)(i.getPosition().getY() - GraphicsHandler.getHeight()/25/2),
			    (int)(GraphicsHandler.getWidth()/44.5), (int)(GraphicsHandler.getHeight()/25));
		g.fillRect((int)(i.getPosition().getX() - GraphicsHandler.getWidth()/44.5/2),
			    (int)(i.getPosition().getY() - GraphicsHandler.getHeight()/25/2),
			    (int)(GraphicsHandler.getWidth()/44.5), (int)(GraphicsHandler.getHeight()/25));
	    } else {
		g.setColor(Color.black);
		g.drawRect((int)(i.getPosition().getX() - GraphicsHandler.getWidth()/44.5/2),
			    (int)(i.getPosition().getY() - GraphicsHandler.getHeight()/25/2),
			    (int)(GraphicsHandler.getWidth()/44.5), (int)(GraphicsHandler.getHeight()/25));
		g.fillRect((int)(i.getPosition().getX() - GraphicsHandler.getWidth()/44.5/2),
			    (int)(i.getPosition().getY() - GraphicsHandler.getHeight()/25/2),
			    (int)(GraphicsHandler.getWidth()/44.5), (int)(GraphicsHandler.getHeight()/25));
	    }
	    
	}
    }
    
    public static void handleKeyEventPressed(int keyCode) {
	if (clientPlayer.getDead() == false) {
	    if (playerMoving == false) {
		if (keyCode == clientPlayer.getCurrentButtonConfig().getUp()) {
		    ConsoleHandler.print("Client presses Button 'up'", MessageType.GAME);
		    clientPlayer.actionUp();
		    inputBuffer.add(keyCode);
		    playerMoving = true;
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getDown()) {
		    ConsoleHandler.print("Client presses Button 'down'", MessageType.GAME);
		    clientPlayer.actionDown();
		    inputBuffer.add(keyCode);
		    playerMoving = true;
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getLeft()) {
		    ConsoleHandler.print("Client presses Button 'left'", MessageType.GAME);
		    clientPlayer.actionLeft();
		    inputBuffer.add(keyCode);
		    playerMoving = true;
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getRight()) {
		    ConsoleHandler.print("Client presses Button 'right'", MessageType.GAME);
		    clientPlayer.actionRight();
		    inputBuffer.add(keyCode);
		    playerMoving = true;
		}
	    } else {
		if (keyCode == clientPlayer.getCurrentButtonConfig().getUp() && inputBuffer.contains(keyCode) == false) {
		    ConsoleHandler.print("Buffer 'up'", MessageType.GAME);
		    inputBuffer.add(keyCode);
		    multiPress = true;
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getDown() && inputBuffer.contains(keyCode) == false) {
		    ConsoleHandler.print("Buffer 'down'", MessageType.GAME);
		    inputBuffer.add(keyCode);
		    multiPress = true;
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getLeft() && inputBuffer.contains(keyCode) == false) {
		    ConsoleHandler.print("Buffer 'left'", MessageType.GAME);
		    inputBuffer.add(keyCode);
		    multiPress = true;
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getRight() && inputBuffer.contains(keyCode) == false) {
		    ConsoleHandler.print("Buffer 'right'", MessageType.GAME);
		    inputBuffer.add(keyCode);
		    multiPress = true;
		}
	    }
	}
    }
    
    @SuppressWarnings("removal")	/* Um Warnung von 'new Integer(keyCode)' auszublenden. */
    public static void handleKeyEventReleased(int keyCode) {
	if (clientPlayer.getDead() == false && playerMoving == true) {
	    if (multiPress == true) {
		if (keyCode == clientPlayer.getCurrentButtonConfig().getUp()) {
		    if (keyCode != inputBuffer.get(0)) {
			ConsoleHandler.print("Unbuffer 'up'", MessageType.GAME);
			inputBuffer.remove(new Integer(keyCode));
		    } else {
			ConsoleHandler.print("Client released Button 'up'", MessageType.GAME);
			if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getDown()) {
			    clientPlayer.actionDown();
			} else if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getLeft()) {
			    clientPlayer.actionLeft();
			} else if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getRight()) {
			    clientPlayer.actionRight();
			}
			inputBuffer.remove(0);
			Collections.swap(inputBuffer, 0, inputBuffer.size() - 1);
		    }
		    if (inputBuffer.size() == 1) {
			multiPress = false;
		    }
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getDown()) {
		    if (keyCode != inputBuffer.get(0)) {
			ConsoleHandler.print("Unbuffer 'down'", MessageType.GAME);
			inputBuffer.remove(new Integer(keyCode));
		    } else {
			ConsoleHandler.print("Client released Button 'down'", MessageType.GAME);
			if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getUp()) {
			    clientPlayer.actionUp();
			} else if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getLeft()) {
			    clientPlayer.actionLeft();
			} else if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getRight()) {
			    clientPlayer.actionRight();
			}
			inputBuffer.remove(0);
			Collections.swap(inputBuffer, 0, inputBuffer.size() - 1);
		    }
		    if (inputBuffer.size() == 1) {
			multiPress = false;
		    }
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getLeft()) {
		    if (keyCode != inputBuffer.get(0)) {
			ConsoleHandler.print("Unbuffer 'left'", MessageType.GAME);
			inputBuffer.remove(new Integer(keyCode));
		    } else {
			ConsoleHandler.print("Client released Button 'left'", MessageType.GAME);
			if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getUp()) {
			    clientPlayer.actionUp();
			} else if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getDown()) {
			    clientPlayer.actionDown();
			} else if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getRight()) {
			    clientPlayer.actionRight();
			}
			inputBuffer.remove(0);
			Collections.swap(inputBuffer, 0, inputBuffer.size() - 1);
		    }
		    if (inputBuffer.size() == 1) {
			multiPress = false;
		    }
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getRight()) {
		    if (keyCode != inputBuffer.get(0)) {
			ConsoleHandler.print("Unbuffer 'right'", MessageType.GAME);
			inputBuffer.remove(new Integer(keyCode));
		    } else {
			ConsoleHandler.print("Client released Button 'right'", MessageType.GAME);
			if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getUp()) {
			    clientPlayer.actionUp();
			} else if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getDown()) {
			    clientPlayer.actionDown();
			} else if (inputBuffer.get(inputBuffer.size() - 1) == clientPlayer.getCurrentButtonConfig().getLeft()) {
			    clientPlayer.actionLeft();
			}
			inputBuffer.remove(0);
			Collections.swap(inputBuffer, 0, inputBuffer.size() - 1);
		    }
		    if (inputBuffer.size() == 1) {
			multiPress = false;
		    }
		}
	    } else {
		if (keyCode == clientPlayer.getCurrentButtonConfig().getUp()) {
		    ConsoleHandler.print("Client released Button 'up'", MessageType.GAME);
		    clientPlayer.actionStop();
		    inputBuffer.clear();
		    playerMoving = false;
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getDown()) {
		    ConsoleHandler.print("Client released Button 'down'", MessageType.GAME);
		    clientPlayer.actionStop();
		    inputBuffer.clear();
		    playerMoving = false;
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getLeft()) {
		    ConsoleHandler.print("Client released Button 'left'", MessageType.GAME);
		    clientPlayer.actionStop();
		    inputBuffer.clear();
		    playerMoving = false;
		} else if (keyCode == clientPlayer.getCurrentButtonConfig().getRight()) {
		    ConsoleHandler.print("Client released Button 'right'", MessageType.GAME);
		    clientPlayer.actionStop();
		    inputBuffer.clear();
		    playerMoving = false;
		}
	    }
	}
	/* 
	 * Abfrage nach 'Bombe legen' geschieht unabhaengig von der Bewegungstastenabfrage
	 * (ergo kein InputBuffer noetig).
	 * Bombe wird erst bei Loslassen der Taste gelegt.
	 */
	if (clientPlayer.getDead() == false && keyCode == clientPlayer.getCurrentButtonConfig().getSetBomb()) {
	    clientPlayer.actionSetBomb();
	}
	/* Debug Tasten zum Testen von Funktionen. Koennen mit dem Boolean debugKey an-/abgeschaltet werden. */
	if (debugKeys) {
	    if (keyCode == KeyEvent.VK_O) {
		addOpponentPlayer(1, "Dave", "1.1.1.1", false, 1, new Point(1, 15));
		addOpponentPlayer(2, "Jenny", "2.2.2.2", false, 2, new Point(15, 15));
		addOpponentPlayer(3, "Christie", "3.3.3.3", false, 3, new Point(15, 1));
	    } else if (keyCode == KeyEvent.VK_L) {
		clearOpponentPlayers();
	    } else if (keyCode == KeyEvent.VK_I) {
		clientPlayer.increaseMaxBombs();
	    } else if (keyCode == KeyEvent.VK_K) {
		clientPlayer.decreaseMaxBombs();
	    } else if (keyCode == KeyEvent.VK_P) {
		if (clientPlayer.getDead() == false) {
		    clientPlayer.setDead(true);
		} else {
		    clientPlayer.setDead(false);
		}
		
	    }
	}
    }
    
    public static Player getClientplayer() {
	return clientPlayer;
    }
}
