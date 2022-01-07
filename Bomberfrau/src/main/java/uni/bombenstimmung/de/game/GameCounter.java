/*
 * GameCounter
 *
 * Version 1.0
 * Author: Dennis
 *
 * Malt und verwaltet den Counter und den Ring of Death
 */

package uni.bombenstimmung.de.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.Timer;

import uni.bombenstimmung.de.backend.graphics.GraphicsHandler;

public class GameCounter implements ActionListener{
    
    private static int gametime = 60;
    private static int ringOfDeathNumber = 0;
    private Timer gameTimer;
    
    public GameCounter() {
	gameTimer = new Timer(1000,this);
    }
    
    public void actionPerformed(ActionEvent e) {
	    if(Game.getGameOver() == true) {
		gameTimer.stop();
		gametime = 0;
	    }else {
		if (gametime == 0 && (ringOfDeathNumber < 6)) {
		    ringOfDeathNumber++;
			Game.ringOfDeath(ringOfDeathNumber);
			gametime = 10;
		}else {
		    GameCounter.gametime--;
		}
	    }
    }
    
    public static int getGameTime() {
	return gametime;
    }
    
    public void startCounter() {
	gameTimer.start();
    }
    
    public void stopCounter() {
	gameTimer.stop();
    }
    
    public void setGameTime(int t) {
	gametime = t;
    }
    
    public static void setRingOfDeathNumber(int up) {
	ringOfDeathNumber = up;
    }
    
    public static int getRingOfDeathNumber() {
	return ringOfDeathNumber;
    }
    
    public static void drawCounter(Graphics g, int w, int h) {
	GraphicsHandler.drawCentralisedText(g, Color.RED, 30, "Time left: " + gametime, w, h+200);
	GraphicsHandler.drawCentralisedText(g, Color.RED, 30, "R of D: " + ringOfDeathNumber, w, h+300);
    }
    
    public static void drawCounterBackground(Graphics g) {
	int xOffset = GraphicsHandler.getWidth()-(GameData.FIELD_DIMENSION*GameData.MAP_DIMENSION);
	int yStart = GameData.MAP_SIDE_BORDER;
	int xStart = (GameData.FIELD_DIMENSION*GameData.MAP_DIMENSION)+(xOffset/2);
	
	int rectWidth = xOffset/2 - 50;
	
	g.setColor(Color.BLACK);
	g.fillRect(xStart+25, yStart+150, rectWidth, 200);
    }
}