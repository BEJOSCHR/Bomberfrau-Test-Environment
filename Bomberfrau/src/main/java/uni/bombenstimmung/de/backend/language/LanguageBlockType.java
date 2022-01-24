/*
 * LanguageBlockType
 *
 * Version 1.0
 * Author: Benni
 *
 * Dient zur Identifizierung jedes LanguageBlock Objektes �ber den LanguageBlockHandler
 */
package uni.bombenstimmung.de.backend.language;

public enum LanguageBlockType {

	LB_LOADINGSCREEN_TITLE,
	LB_LOADINGSCREEN_CONTINUE,
	LB_LOADINGSCREEN_AUTHOR,
	LB_LOADINGSCREEN_VERSION,
	
	LB_INTRO_SKIP,
	LB_MENU_TXT1, LB_MENU_TXT2, LB_MENU_INFO1, LB_MENU_INFO2, LB_MENU_INFO3, LB_MENU_BTN2, LB_MENU_BTN3,
	LB_OPT_TXT1, LB_OPT_TXT2, LB_OPT_TXT3, LB_OPT_TXT4, LB_OPT_TXT5, LB_OPT_TXT6, LB_OPT_TXT7, LB_OPTIONS_BTN,
	LB_OPT_INFO1, LB_OPT_INFO2,
	LB_KEY_UP, LB_KEY_DOWN, LB_KEY_LEFT, LB_KEY_RIGHT,
	LB_MSG_BAD_NAME, LB_MSG_BAD_IP, LB_MSG_BAD_RESOLUTION,
	LB_OPT_FULLSCREEN,
	
	LB_LOBBY_READY,
	LB_LOBBY_IP,
	LB_LOBBY_FULL,
	
	LB_INGAME_GO, LB_INGAME_PLAYER, LB_INGAME_TIME, LB_INGAME_ROD1, LB_INGAME_ROD2, LB_INGAME_ROD3,
	LB_INGAME_GAMEOVER,
	
	LB_AFTERGAME_TITEL,
	LB_AFTERGAME_NAME_1, LB_AFTERGAME_NAME_2, LB_AFTERGAME_NAME_3, LB_AFTERGAME_NAME_4,
	LB_AFTERGAME_SCORE_1, LB_AFTERGAME_SCORE_2, LB_AFTERGAME_SCORE_3, LB_AFTERGAME_SCORE_4,
	LB_AFTERGAME_BT1, LB_AFTERGAME_BT2, LB_AFTERGAME_BT3;
	

	
}
