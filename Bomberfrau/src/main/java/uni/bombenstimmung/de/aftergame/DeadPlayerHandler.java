/*
 * DeadPlayerHandler.
 *
 * Version 1.0
 * Author: Alexej
 *
 * Verwalet die Punkte und die Plazierung der Player
 */
package uni.bombenstimmung.de.aftergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import uni.bombenstimmung.de.backend.console.ConsoleHandler;
import uni.bombenstimmung.de.backend.console.MessageType;
import uni.bombenstimmung.de.backend.language.LanguageBlockType;
import uni.bombenstimmung.de.backend.language.LanguageHandler;

public class DeadPlayerHandler {
	private static ArrayList<DeadPlayer> allPlayer = new ArrayList<DeadPlayer>();

	public static void generateDummyDeadPlayer() {
		addDeadPlayer(0, "A", 45);
		addDeadPlayer(1, "B", 978);
		addDeadPlayer(2, "C", 325);
		addDeadPlayer(3, "D", 84);
	}

    /**
     * Player Datensatz hinzuf�gen oder einen bestehenden Datensatz anpassen.
     * @param id	ID des Players
     * @param name	Name des Players
     * @param deathTime	Todeszeitpunkt des Players
     */
	public static void addDeadPlayer(int id, String name, int deathTime) {
		if (allPlayer.size() == id) {
			allPlayer.add(id, new DeadPlayer(id, name , deathTime));
			ConsoleHandler.print("new Player: " + id + " ,Name: "+ name + ", deathTime: " + deathTime, MessageType.AFTERGAME);
		}else if(allPlayer.size() > id) {
			allPlayer.get(id).updateDeathPlayer(id, name, deathTime);
			ConsoleHandler.print("updated Player: " + id + " ,Name: "+ name + ", deathTime: " + deathTime, MessageType.AFTERGAME);
		}
		else {
			ConsoleHandler.print("addDeadPlayer: id is not allowed!", MessageType.AFTERGAME);
		}
	}

    /**
     * Punkte f�r die Partie bestimmen und Plazierung anpassen.
     */
	public static void calculateScore() {
		ArrayList<DeadPlayer> Ranking = allPlayer;

		//Player sortieren nach deathTime
		Collections.sort(Ranking, new Comparator<DeadPlayer>() {
			public int compare(DeadPlayer p1, DeadPlayer p2) {
				return Integer.valueOf(p1.getDeathTime()).compareTo(p2.getDeathTime());
			}
		});

		//Punktevergabe f�r die besten drei Player
		for(int i = 0; i < Ranking.size(); i++) {
			switch(i) {
			case 0: Ranking.get(0).addScore((Ranking.size()-1)*100); break;
			case 1: Ranking.get(1).addScore((Ranking.size()-2)*100); break;
			case 2: Ranking.get(2).addScore((Ranking.size()-3)*100); break;
			}
		}

		//Player sortieren nach Score
		Collections.sort(Ranking, new Comparator<DeadPlayer>() {
			public int compare(DeadPlayer p1, DeadPlayer p2) {
				return Integer.valueOf(p2.getScore()).compareTo(p1.getScore());
			}
		});

		//Ranking zuweisen
		for(int i = 0; i < Ranking.size(); i++) {
			Ranking.get(i).setRanking(i+1);
		}

		//Ergebnisanzeige Aftergame
		for(int i = 0; i < Ranking.size(); i++) {
			String[] aftergame_Ranking = {Ranking.get(i).getRanking()+ ": " + Ranking.get(i).getName() + "    Score:" + Ranking.get(i).getScore(),Ranking.get(i).getRanking()+ ": " + Ranking.get(i).getName() + "    Score:" + Ranking.get(i).getScore()};

			switch(i) {
			case 0: LanguageHandler.getLLB(LanguageBlockType.LB_AFTERGAME_RANKING_1).setLanguageContent(aftergame_Ranking); break;
			case 1: LanguageHandler.getLLB(LanguageBlockType.LB_AFTERGAME_RANKING_2).setLanguageContent(aftergame_Ranking); break;
			case 2: LanguageHandler.getLLB(LanguageBlockType.LB_AFTERGAME_RANKING_3).setLanguageContent(aftergame_Ranking); break;
			case 3: LanguageHandler.getLLB(LanguageBlockType.LB_AFTERGAME_RANKING_4).setLanguageContent(aftergame_Ranking); break;
			}
		}

		//auf urspruengliche Sortierung zurueksetzen und AllPlayer updaten
		Collections.sort(Ranking, new Comparator<DeadPlayer>() {
			public int compare(DeadPlayer p1, DeadPlayer p2) {
				return Integer.valueOf(p1.getId()).compareTo(p2.getId());
			}
		});
		allPlayer=Ranking;

//		for(DeadPlayer test: allPlayer) {
//			ConsoleHandler.print("Player: " + test.getId() + " ,Name: "+ test.getName() + ", deathTime: " + test.getDeathTime(), MessageType.AFTERGAME);
//		}



	}

}