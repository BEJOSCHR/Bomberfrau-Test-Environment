/*
 * LanguageBlockHandler
 *
 * Version 1.0
 * Author: Benni
 *
 * Verwaltet alle SprachBl�cke (laden, ausgeben, ver�ndern...)
 */
package uni.bombenstimmung.de.backend.language;

import java.util.ArrayList;
import java.util.List;

import uni.bombenstimmung.de.backend.console.ConsoleHandler;
import uni.bombenstimmung.de.backend.console.MessageType;

public class LanguageHandler {

	private static List<LoadedLanguageBlock> languageBlocks = new ArrayList<LoadedLanguageBlock>();
	private static LanguageType activeLanguage = LanguageType.ENGLISH;
	
	/**
	 * Wird am Start aufgerufen und initialisiert alle SprachBl�cke
	 * @see LoadedLanguageBlock
	 */
	public static void initLLBs() {
		
		String[] intro_skip = {"<<  Click to skip  >>", "<< Klicken zum ueberspringen >>"};
		new LoadedLanguageBlock(LanguageBlockType.LB_INTRO_SKIP, intro_skip);

		String[] menu_txt1 = {"Create", "Erstellen"};
		new LoadedLanguageBlock(LanguageBlockType.LB_MENU_TXT1, menu_txt1);
		String[] menu_txt2 = {"Join", "Beitreten"};
		new LoadedLanguageBlock(LanguageBlockType.LB_MENU_TXT2, menu_txt2);
		String[] menu_info1 = {"max. 30 signs", "max. 30 Zeichen"};
		new LoadedLanguageBlock(LanguageBlockType.LB_MENU_INFO1, menu_info1);
		String[] menu_info2 = {"invalid IP", "ungueltige IP"};
		new LoadedLanguageBlock(LanguageBlockType.LB_MENU_INFO2, menu_info2);
		String[] menu_btn2 = {"OPTIONS", "OPTIONEN"};
		new LoadedLanguageBlock(LanguageBlockType.LB_MENU_BTN2, menu_btn2);
		String[] menu_btn3 = {"EXIT", "ENDE"};
		new LoadedLanguageBlock(LanguageBlockType.LB_MENU_BTN3, menu_btn3);
		
		String[] options_txt1 = {"Resolution:", "Aufloesung:"};
		new LoadedLanguageBlock(LanguageBlockType.LB_OPT_TXT1, options_txt1);
		String[] options_fullscreen = {"Fullscreen", "Vollbild"};
		new LoadedLanguageBlock(LanguageBlockType.LB_OPT_FULLSCREEN, options_fullscreen);
		String[] options_txt2 = {"Volume Music:", "Lautstaerke Musik:"};
		new LoadedLanguageBlock(LanguageBlockType.LB_OPT_TXT2, options_txt2);
		String[] options_txt3 = {"Volume Sound:", "Lautstaerke Sound:"};
		new LoadedLanguageBlock(LanguageBlockType.LB_OPT_TXT3, options_txt3);
		String[] options_txt4 = {"Controls:", "Steuerung:"};
		new LoadedLanguageBlock(LanguageBlockType.LB_OPT_TXT4, options_txt4);
		String[] options_txt5 = {"Bomb", "Bombe"};
		new LoadedLanguageBlock(LanguageBlockType.LB_OPT_TXT5, options_txt5);
		String[] options_txt6 = {"show FPS", "FPS anzeigen"};
		new LoadedLanguageBlock(LanguageBlockType.LB_OPT_TXT6, options_txt6);
		String[] options_txt7 = {"Language:", "Sprache:"};
		new LoadedLanguageBlock(LanguageBlockType.LB_OPT_TXT7, options_txt7);
		String[] options_btn = {"BACK", "ZURUECK"};
		new LoadedLanguageBlock(LanguageBlockType.LB_OPTIONS_BTN, options_btn);

		String[] key_up = {"Up", "Hoch"};
		new LoadedLanguageBlock(LanguageBlockType.LB_KEY_UP, key_up);
		String[] key_down = {"Down", "Runter"};
		new LoadedLanguageBlock(LanguageBlockType.LB_KEY_DOWN, key_down);
		String[] key_left = {"Left", "Links"};
		new LoadedLanguageBlock(LanguageBlockType.LB_KEY_LEFT, key_left);
		String[] key_right = {"Right", "Rechts"};
		new LoadedLanguageBlock(LanguageBlockType.LB_KEY_RIGHT, key_right);

		String[] bad_name = {"Please enter a name before starting ...", "Bitte vor dem Start einen Namen eingeben ..."};
		new LoadedLanguageBlock(LanguageBlockType.LB_MSG_BAD_NAME, bad_name);
		String[] bad_ip = {"Please enter an IP before starting ...", "Bitte vor dem Start eine IP eingeben ..."};
		new LoadedLanguageBlock(LanguageBlockType.LB_MSG_BAD_IP, bad_ip);
		String[] bad_res = {"resolution to high - switching to fullscreen.", "Die Aufloesung ist zu gross - Wechsel zu Vollbild."};
		new LoadedLanguageBlock(LanguageBlockType.LB_MSG_BAD_RESOLUTION, bad_res);
		
		String[] ready = {"READY?", "BEREIT?"};
		new LoadedLanguageBlock(LanguageBlockType.LB_LOBBY_READY, ready);
		
		String[] aftergame_Titel = {"A F T E R G A M E", "A F T E R G A M E"};
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_TITEL, aftergame_Titel);
		String[] aftergame_Ranking = {"",""};
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_SCORE_1, aftergame_Ranking);
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_SCORE_2, aftergame_Ranking);
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_SCORE_3, aftergame_Ranking);
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_SCORE_4, aftergame_Ranking);
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_NAME_1, aftergame_Ranking);
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_NAME_2, aftergame_Ranking);
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_NAME_3, aftergame_Ranking);
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_NAME_4, aftergame_Ranking);
		
		String[] aftergame_bt1 = {"Back to Menu", "Zur�ck zum Men�"};
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_BT1, aftergame_bt1);
		String[] aftergame_bt2 = {"Back to Lobby", "Zur�ck zur Lobby"};
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_BT2, aftergame_bt2);
		String[] aftergame_bt3 = {"EXIT", "ENDE"};
		new LoadedLanguageBlock(LanguageBlockType.LB_AFTERGAME_BT3, aftergame_bt3);
		
		ConsoleHandler.print("Initialised LanguageBlocks ("+languageBlocks.size()+")", MessageType.BACKEND);	
	}
	
	/**
	 * F�gt den LLB zur languageBlocks Liste hinzu (Wird eigentlich nur aus einem LLB aufgerufen)
	 * @param llb - Der {@link LoadedLanguageBlock} der hinzugef�gt werden soll
	 */
	public static void addLLB(LoadedLanguageBlock llb) {
		
		languageBlocks.add(llb);
		
	}
	
	/**
	 * Gibt den LLB zum angegebenen Type zur�ck (Wenn richtig geladen gibt es f�r jeden Type einen LLB)
	 * @param type - Der {@link LanguageBlockType} zu dem der gesuchte LLB geh�rt
	 * @return Den {@link LoadedLanguageBlock} der durch den Type identifiziert wird, falls keiner gefunden wird null
	 */
	public static LoadedLanguageBlock getLLB(LanguageBlockType type) {
		
		for(LoadedLanguageBlock llb : languageBlocks) {
			if(llb.getType() == type) {
				return llb;
			}
		}
		
		return null;
	}
	
	public static void setActiveLanguage(LanguageType activeLanguage) {
		LanguageHandler.activeLanguage = activeLanguage;
	}
	public static LanguageType getActiveLanguage() {
		return activeLanguage;
	}
}
