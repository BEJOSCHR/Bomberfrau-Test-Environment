/*
 * Player
 *
 * Version 0.4.1
 * 
 * Author: Christopher
 * 
 * Datum: 28.12.2021
 *
 * Diese Klasse enthaelt Funktionen und Variablen, die mit einem Player direkt zusammenhaengen.
 * Darunter fallen Informationen ueber den aktuellen Zustand des Players, sowie Aktionen,
 * welche der Player bei Aufrufen direkt ausfuehrt.
 */

package uni.bombenstimmung.de.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;

import javax.swing.Timer;

import uni.bombenstimmung.de.backend.console.ConsoleHandler;
import uni.bombenstimmung.de.backend.console.MessageType;
import uni.bombenstimmung.de.backend.graphics.GraphicsHandler;

public class Player extends Entity implements ActionListener{

    private int id;
    private String name;
    private String ipAdress;
    private boolean host;
    private int skin;
    private int movementSpeed;
    private int maxBombs;
    private int placedBombs;
    private int bombRadius;
    private PlayerButtonConfig currentButtonConfig;
    private boolean dead;
    private Field currentField;
    private Timer t;
    /* Genauere Positionsdaten und Velocity-Daten des Players. */
    private double realPosX;
    private double realPosY;
    private double velX;
    private double velY;
    private double hitbox;
    private int direction;
    private double speedFactor;
    
    public Player(int id, String name, String ipAdress, boolean host, int skin, Point pos) {
	/* Offset-Variablen fuer Berechnung*/
	int xOffset = GraphicsHandler.getWidth()-(GameData.FIELD_DIMENSION*GameData.MAP_DIMENSION);
	int yOffset = GameData.MAP_SIDE_BORDER;
	
	this.id = id;
	this.name = name;
	this.ipAdress = ipAdress;
	this.host = host;
	this.skin = skin;
	this.movementSpeed = 1;
	this.maxBombs = 1;
	this.placedBombs = 0;
	this.bombRadius = 3;
	this.realPosX = (pos.getX()*GameData.FIELD_DIMENSION)+(xOffset/2)+(GameData.FIELD_DIMENSION/2);
	this.realPosY = (pos.getY()*GameData.FIELD_DIMENSION)+(yOffset/2)+(GameData.FIELD_DIMENSION/2);
	
	/* Variablen der Oberklasse Entity. Werden als Bildschirmkoordinaten behandelt. */
	super.xPosition = (int)this.realPosX;
	super.yPosition = (int)this.realPosY;
	
	this.velX = 0.0;
	this.velY = 0.0;
	this.currentButtonConfig = new PlayerButtonConfig();
	this.dead = false;
	this.currentField = Game.getFieldFromCoord(xPosition, yPosition);
	this.hitbox = (double)GraphicsHandler.getHeight()/54;
	this.direction = 0;
	this.speedFactor = 540.0;
	this.t = new Timer(8, this);
	this.t.start();
	ConsoleHandler.print("Created Player. ID: " + id + ", Name: " + name + ", Pos(" + currentField.xPosition +
				", " + currentField.yPosition +")", MessageType.GAME);
    }
    
    /* Zustaendig fuer das kontinuierliche Aktualisieren der Player-Position inklusive Kollisionsabfrage. */
    
    public void actionPerformed(ActionEvent e) {
	boolean block = false;
	Field tempField;
	/* 
	 * tempField stellt die Hitbox dar. Es wird das vorliegende Field in Bewegungsrichtung bestimmt
	 * und fuer die Abfrage, ob das Field betretbar ist, weiterverwendet.
	 */
	switch (this.direction) {
	case 0:		// ohne Bewegung
	    tempField = Game.getFieldFromCoord(super.xPosition, super.yPosition);
	    break;
	case 1:		// hoch
	    block = this.isPlayerHittingCorner();
	    tempField = Game.getFieldFromCoord(super.xPosition, (int)(super.yPosition - this.hitbox));
	    break;
	case 2:		// runter
	    block = this.isPlayerHittingCorner();
	    tempField = Game.getFieldFromCoord(super.xPosition, (int)(super.yPosition + this.hitbox));
	    break;
	case 3:		// links
	    block = this.isPlayerHittingCorner();
	    tempField = Game.getFieldFromCoord((int)(super.xPosition - this.hitbox), super.yPosition);
	    break;
	case 4:		// rechts
	    block = this.isPlayerHittingCorner();
	    tempField = Game.getFieldFromCoord((int)(super.xPosition + this.hitbox), super.yPosition);
	    break;
	default:
	    tempField = null;
	    ConsoleHandler.print("Invalid Direction ID!", MessageType.GAME);
	}
	if (block == false && (tempField.getContent() != FieldContent.WALL && tempField.getContent() != FieldContent.BLOCK
		&& tempField.getContent() != FieldContent.BORDER)) {
	    this.realPosX += this.velX;
	    this.realPosY += this.velY;
	    
	}
	
	super.xPosition = (int)this.realPosX;
	super.yPosition = (int)this.realPosY;
	this.currentField = Game.getFieldFromCoord(xPosition, yPosition);
	
	/* Abfrage, ob sich Player in Explosion befindet. Falls ja, dann tot. */
	if (this.currentField.getContent() == FieldContent.EXPLOSION1 || this.currentField.getContent() == FieldContent.EXPLOSION2 ||
	    this.currentField.getContent() == FieldContent.EXPLOSION2_NS || this.currentField.getContent() == FieldContent.EXPLOSION3_N ||
	    this.currentField.getContent() == FieldContent.EXPLOSION3_S || this.currentField.getContent() == FieldContent.EXPLOSION3_W ||
	    this.currentField.getContent() == FieldContent.EXPLOSION3_O) {
	    this.setDead(true);
	}
	/* Abfrage, ob Player ueber Upgrade laeuft. Falls ja, aufsammeln und passende Upgrade-Methode ausfuehren.*/
	if (this.currentField.getContent() == FieldContent.UPGRADE_ITEM_BOMB) {
	    this.increaseMaxBombs();
	    Game.changeFieldContent(FieldContent.EMPTY, this.currentField.xPosition, this.currentField.yPosition);
	}
	if (this.currentField.getContent() == FieldContent.UPGRADE_ITEM_FIRE) {
	    this.increaseBombRadius();
	    Game.changeFieldContent(FieldContent.EMPTY, this.currentField.xPosition, this.currentField.yPosition);
	}
	if (this.currentField.getContent() == FieldContent.UPGRADE_ITEM_SHOE) {
	    this.increaseMovementSpeed();
	    Game.changeFieldContent(FieldContent.EMPTY, this.currentField.xPosition, this.currentField.yPosition);
	}
    }
    
    public void setId(int id) {
	this.id = id;
    }
    
    public void setMovementSpeed(int movementSpeed) {
	this.movementSpeed = movementSpeed;
    }
    
    public void setMaxBombs(int maxBombs) {
	this.maxBombs = maxBombs;
    }
    
    public void setSkin(int skin) {
	this.skin = skin;
    }
    
    public void setDead(boolean dead) {
	/* Bewegung zuruecksetzen im Todesfall. */
	if (dead == true) {
	    this.actionStop();
	    PlayerHandler.resetMovement();
	}
	this.dead = dead;
    }
    
    public int getId() {
	return id;
    }
    
    public String getIpAdress() {
	return ipAdress;
    }
    
    public boolean getHost() {
	return host;
    }
    
    public int getSkin() {
	return skin;
    }
    
    public int getMovementSpeed() {
	return movementSpeed;
    }
    
    public int getMaxBombs() {
	return maxBombs;
    }
    
    public String getName() {
	return name;
    }
    
    public PlayerButtonConfig getCurrentButtonConfig() {
	return currentButtonConfig;
    }
    
    public boolean getDead() {
	return dead;
    }
    
    public Field getCurrentField() {
	return currentField;
    }
    
    public double getVelX() {
	return this.velX;
    }
    
    public double getVelY() {
	return this.velY;
    }
    
    public double getRealPosX() {
	return realPosX;
    }
    
    public double getRealPosY() {
	return realPosY;
    }
    
    /*
     * Es folgen Methoden zu Bewegungsaktionen. Hier werden jeweils die Velocities passend zur Aktion
     * angepasst. Der Velocity-Wert wird in actionPerformed kontinuierlich addiert.
     * =====================================================================================
     */
    
    public void actionUp() {
	this.direction = 1;
	this.velX = 0;
	this.velY = -((double)GraphicsHandler.getHeight() / this.speedFactor); 
    }
    
    public void actionDown() {
	this.direction = 2;
	this.velX = 0;
	this.velY = (double)GraphicsHandler.getHeight() / this.speedFactor;
    }
    
    public void actionLeft() {
	this.direction = 3;
	this.velX = -((double)GraphicsHandler.getHeight() / this.speedFactor);
	this.velY = 0;
    }
    
    public void actionRight() {
	this.direction = 4;
	this.velX = (double)GraphicsHandler.getHeight() / this.speedFactor;
	this.velY = 0;
    }
    
    public void actionStop() {
	this.direction = 0;
	this.velX = 0;
	this.velY = 0;
    }
    
    /* ========= Ende des Blocks fuer Bewegungsmethoden. =========== */
    
    /**
     * Platzieren der Bombe. Fragt ab, ob die aktuelle Position EMPTY ist.
     * Falls ja, dann aendert sich der FieldContent zu BOMB und ein Bomb-Objekt
     * wird in die static ArrayList 'placedBombs' hinzugefuegt.
     */
    public void actionSetBomb() {
	Field temp = Game.getFieldFromCoord(xPosition, yPosition);
	if (placedBombs < maxBombs && temp.getContent() == FieldContent.EMPTY) {
	    Game.changeFieldContent(FieldContent.BOMB, temp.xPosition, temp.yPosition);
	    Game.addBomb(this.bombRadius, 3, this.id);
	    placedBombs++;
	    ConsoleHandler.print("Player ID: " + this.id + " placed Bomb at Pos(" + temp.xPosition + ", "
		    			+ temp.yPosition + ")", MessageType.GAME);
	}
    }
    
    public void increaseMaxBombs() {
	this.maxBombs++;
	ConsoleHandler.print("Player ID: " + id + ": New MaxBombs: " + this.maxBombs, MessageType.GAME);
    }
    
    public void decreaseMaxBombs() {
	if (this.maxBombs > 1) {
	    this.maxBombs--;
	}
	ConsoleHandler.print("Player ID: " + id + ": New MaxBombs: " + this.maxBombs, MessageType.GAME);
    }
    
    public void decreasePlacedBombs() {
	if (placedBombs > 0) {
	    placedBombs--;
	}
    }
    
    public void increaseBombRadius() {
	if (this.bombRadius < GameData.MAP_DIMENSION) {
	    this.bombRadius++;
	}
	ConsoleHandler.print("Player ID: " + id + ": New Bomb Radius: " + this.bombRadius, MessageType.GAME);
    }
    
    /* 
     * Bei Erhoehen der Laufgeschwindigkeit wird der SpeedFactor erhoeht.
     * Dieser wird dann fuer die Berechnung der Velocity bei den Action-Methoden herangeholt.
     * Auch wird die Action-Methode der passenden Richtung einmal aufgerufen,
     * um den neuen Velocity Wert zu berechnen.
     */
    public void increaseMovementSpeed() {
	if (this.movementSpeed < 5) {
	    this.movementSpeed++;
	    
	    switch (movementSpeed) {
	    case 1:
		this.speedFactor = 540.0;
		break;
	    case 2:
		this.speedFactor = 360.0;
		break;
	    case 3:
		this.speedFactor = 270.0;
		break;
	    case 4:
		this.speedFactor = 216.0;
		break;
	    case 5:
		this.speedFactor = 180.0;
	    }
	    
	    switch (this.direction) {
	    case 0:
		this.actionStop();
		break;
	    case 1:
		this.actionUp();
		break;
	    case 2:
		this.actionDown();
		break;
	    case 3:
		this.actionLeft();
		break;
	    case 4:
		this.actionRight();
	    }
	}
	ConsoleHandler.print("Player ID: " + id + ": New Movement Speed: " + this.movementSpeed, MessageType.GAME);
    }
    
    /* Gleiche Prozedur wie bei increaseMovementSpeed, nur wird die Geschwindigkeit stattdessen verringert. */
    public void decreaseMovementSpeed() {
	if (this.movementSpeed > 1) {
	    this.movementSpeed--;
	    
	    switch (movementSpeed) {
	    case 1:
		this.speedFactor = 540.0;
		break;
	    case 2:
		this.speedFactor = 360.0;
		break;
	    case 3:
		this.speedFactor = 270.0;
		break;
	    case 4:
		this.speedFactor = 216.0;
		break;
	    case 5:
		this.speedFactor = 180.0;
	    }
	    
	    switch (this.direction) {
	    case 0:
		this.actionStop();
		break;
	    case 1:
		this.actionUp();
		break;
	    case 2:
		this.actionDown();
		break;
	    case 3:
		this.actionLeft();
		break;
	    case 4:
		this.actionRight();
	    }
	}
	ConsoleHandler.print("Player ID: " + id + ": New Movement Speed: " + this.movementSpeed, MessageType.GAME);
    }
    
    public boolean isPlayerHittingCorner() {
	boolean block = false;
	Field tempField_l = null;
	Field tempField_fl = null;
	Field tempField_r = null;
	Field tempField_fr = null;
	double cornerHitbox = this.hitbox * 0.9;
	
	switch (this.direction) {
	case 1:		// hoch
	    
	    tempField_l = Game.getFieldFromCoord((int)(super.xPosition - cornerHitbox), super.yPosition);
	    tempField_fl = Game.getFieldFromCoord((int)(super.xPosition - cornerHitbox), (int)(super.yPosition - cornerHitbox));
	    tempField_r = Game.getFieldFromCoord((int)(super.xPosition + cornerHitbox), super.yPosition);
	    tempField_fr = Game.getFieldFromCoord((int)(super.xPosition + cornerHitbox), (int)(super.yPosition - cornerHitbox));
	    
	    if ( (tempField_l.getContent() != FieldContent.WALL && tempField_l.getContent() != FieldContent.BLOCK
			&& tempField_l.getContent() != FieldContent.BORDER) && (tempField_fl.getContent() == FieldContent.WALL
			|| tempField_fl.getContent() == FieldContent.BLOCK || tempField_fl.getContent() == FieldContent.BORDER) ) {
		this.realPosX += (this.hitbox * 0.1);
		block = true;
	    }
	    
	    if ( (tempField_r.getContent() != FieldContent.WALL && tempField_r.getContent() != FieldContent.BLOCK
			&& tempField_r.getContent() != FieldContent.BORDER) && (tempField_fr.getContent() == FieldContent.WALL
			|| tempField_fr.getContent() == FieldContent.BLOCK || tempField_fr.getContent() == FieldContent.BORDER) ) {
		this.realPosX -= (this.hitbox * 0.1);
		block = true;
	    }
	    
	    break;
	case 2:		// runter
	    
	    tempField_l = Game.getFieldFromCoord((int)(super.xPosition + cornerHitbox), super.yPosition);
	    tempField_fl = Game.getFieldFromCoord((int)(super.xPosition + cornerHitbox), (int)(super.yPosition + cornerHitbox));
	    tempField_r = Game.getFieldFromCoord((int)(super.xPosition - cornerHitbox), super.yPosition);
	    tempField_fr = Game.getFieldFromCoord((int)(super.xPosition - cornerHitbox), (int)(super.yPosition + cornerHitbox));
	    
	    if ( (tempField_l.getContent() != FieldContent.WALL && tempField_l.getContent() != FieldContent.BLOCK
			&& tempField_l.getContent() != FieldContent.BORDER) && (tempField_fl.getContent() == FieldContent.WALL
			|| tempField_fl.getContent() == FieldContent.BLOCK || tempField_fl.getContent() == FieldContent.BORDER) ) {
		this.realPosX -= (this.hitbox * 0.1);
		block = true;
	    }
	    
	    if ( (tempField_r.getContent() != FieldContent.WALL && tempField_r.getContent() != FieldContent.BLOCK
			&& tempField_r.getContent() != FieldContent.BORDER) && (tempField_fr.getContent() == FieldContent.WALL
			|| tempField_fr.getContent() == FieldContent.BLOCK || tempField_fr.getContent() == FieldContent.BORDER) ) {
		this.realPosX += (this.hitbox * 0.1);
		block = true;
	    }
	    
	    break;
	case 3:		// links
	    
	    tempField_l = Game.getFieldFromCoord(super.xPosition, (int)(super.yPosition + cornerHitbox));
	    tempField_fl = Game.getFieldFromCoord((int)(super.xPosition - cornerHitbox), (int)(super.yPosition + cornerHitbox));
	    tempField_r = Game.getFieldFromCoord(super.xPosition, (int)(super.yPosition - cornerHitbox));
	    tempField_fr = Game.getFieldFromCoord((int)(super.xPosition - cornerHitbox), (int)(super.yPosition - cornerHitbox));
	    
	    if ( (tempField_l.getContent() != FieldContent.WALL && tempField_l.getContent() != FieldContent.BLOCK
			&& tempField_l.getContent() != FieldContent.BORDER) && (tempField_fl.getContent() == FieldContent.WALL
			|| tempField_fl.getContent() == FieldContent.BLOCK || tempField_fl.getContent() == FieldContent.BORDER) ) {
		this.realPosY -= (this.hitbox * 0.1);
		block = true;
	    }
	    
	    if ( (tempField_r.getContent() != FieldContent.WALL && tempField_r.getContent() != FieldContent.BLOCK
			&& tempField_r.getContent() != FieldContent.BORDER) && (tempField_fr.getContent() == FieldContent.WALL
			|| tempField_fr.getContent() == FieldContent.BLOCK || tempField_fr.getContent() == FieldContent.BORDER) ) {
		this.realPosY += (this.hitbox * 0.1);
		block = true;
	    }
	    
	    break;
	case 4:		// rechts
	    
	    tempField_l = Game.getFieldFromCoord(super.xPosition, (int)(super.yPosition - cornerHitbox));
	    tempField_fl = Game.getFieldFromCoord((int)(super.xPosition + cornerHitbox), (int)(super.yPosition - cornerHitbox));
	    tempField_r = Game.getFieldFromCoord(super.xPosition, (int)(super.yPosition + cornerHitbox));
	    tempField_fr = Game.getFieldFromCoord((int)(super.xPosition + cornerHitbox), (int)(super.yPosition + cornerHitbox));
	    
	    if ( (tempField_l.getContent() != FieldContent.WALL && tempField_l.getContent() != FieldContent.BLOCK
			&& tempField_l.getContent() != FieldContent.BORDER) && (tempField_fl.getContent() == FieldContent.WALL
			|| tempField_fl.getContent() == FieldContent.BLOCK || tempField_fl.getContent() == FieldContent.BORDER) ) {
		this.realPosY += (this.hitbox * 0.1);
		block = true;
	    }
	    
	    if ( (tempField_r.getContent() != FieldContent.WALL && tempField_r.getContent() != FieldContent.BLOCK
			&& tempField_r.getContent() != FieldContent.BORDER) && (tempField_fr.getContent() == FieldContent.WALL
			|| tempField_fr.getContent() == FieldContent.BLOCK || tempField_fr.getContent() == FieldContent.BORDER) ) {
		this.realPosY -= (this.hitbox * 0.1);
		block = true;
	    }
	    
	}
	
	/*if ( ( (tempField_l.getContent() != FieldContent.WALL && tempField_l.getContent() != FieldContent.BLOCK
		&& tempField_l.getContent() != FieldContent.BORDER) && (tempField_fl.getContent() == FieldContent.WALL
		|| tempField_fl.getContent() == FieldContent.BLOCK || tempField_fl.getContent() == FieldContent.BORDER) )
		||
		( (tempField_r.getContent() != FieldContent.WALL && tempField_r.getContent() != FieldContent.BLOCK
		&& tempField_r.getContent() != FieldContent.BORDER) && (tempField_fr.getContent() == FieldContent.WALL
		|| tempField_fr.getContent() == FieldContent.BLOCK || tempField_fr.getContent() == FieldContent.BORDER) ) ) {
		block = true;
	    }*/
	
	return block;
    }
    
}