package uni.bombenstimmung.de.game;

public class GameData {

	public static final int MAP_DIMENSION = 17; 
	public static final int MAP_SIDE_BORDER = 17;
	public static int FIELD_DIMENSION;
	public static final String MAP_1 = "0,0,BO:0,1,BO:0,2,BO:0,3,BO:0,4,BO:0,5,BO:0,6,BO:0,7,BO:0,8,BO:0,9,BO:0,10,BO:0,11,BO:0,12,BO:0,13,BO:0,14,BO:0,15,BO:0,16,BO:"
					+ "1,0,BO:1,1,EM:1,2,EM:1,3,BL:1,4,EM:1,5,EM:1,6,EM:1,7,BO:1,8,EM:1,9,EM:1,10,BL:1,11,EM:1,12,EM:1,13,BO:1,14,BL:1,15,EM:1,16,EM:"
					+ "2,0,BO:2,1,EM:2,2,BO:2,3,BO:2,4,BO:2,5,BO:2,6,BO:2,7,BO:2,8,BO:2,9,BO:2,10,BO:2,11,BO:2,12,BO:2,13,BO:2,14,BO:2,15,BO:2,16,BO:"
					+ "3,0,BO:3,1,EM:3,2,EM:3,3,BL:3,4,EM:3,5,EM:3,6,EM:3,7,BO:3,8,EM:3,9,EM:3,10,BL:3,11,EM:3,12,EM:3,13,BO:3,14,BL:3,15,EM:3,16,EM:"
					+ "4,0,BO:4,1,BO:4,2,BO:4,3,BO:4,4,BO:4,5,BO:4,6,BO:4,7,BO:4,8,BO:4,9,BO:4,10,BO:4,11,BO:4,12,BO:4,13,BO:4,14,BO:4,15,BO:4,16,BO:"
					+ "5,0,BO:5,1,EM:5,2,EM:5,3,BL:5,4,EM:5,5,EM:5,6,EM:5,7,BO:5,8,EM:5,9,EM:5,10,BL:5,11,EM:5,12,EM:5,13,BO:5,14,BL:5,15,EM:5,16,EM:"
					+ "6,0,BO:6,1,BO:6,2,BO:6,3,BO:6,4,BO:6,5,BO:6,6,BO:6,7,BO:6,8,BO:6,9,BO:6,10,BO:6,11,BO:6,12,BO:6,13,BO:6,14,BO:6,15,BO:6,16,BO:"
					+ "7,0,BO:7,1,EM:7,2,EM:7,3,BL:7,4,EM:7,5,EM:7,6,EM:7,7,BO:7,8,EM:7,9,EM:7,10,BL:7,11,EM:7,12,EM:7,13,BO:7,14,BL:7,15,EM:7,16,EM:"
					+ "8,0,BO:8,1,BO:8,2,BO:8,3,BO:8,4,BO:8,5,BO:8,6,BO:8,7,BO:8,8,BO:8,9,BO:8,10,BO:8,11,BO:8,12,BO:8,13,BO:8,14,BO:8,15,BO:8,16,BO:"
					+ "9,0,BO:9,1,EM:9,2,EM:9,3,BL:9,4,EM:9,5,EM:9,6,WA:9,7,BO:9,8,EM:9,9,EM:9,10,BL:9,11,EM:9,12,EM:9,13,BO:9,14,BL:9,15,EM:9,16,EM:"
					+ "10,0,BO:10,1,BO:10,2,BO:10,3,BO:10,4,BO:10,5,BO:10,6,BO:10,7,BO:10,8,BO:10,9,BO:10,10,BO:10,11,BO:10,12,BO:10,13,BO:10,14,BO:10,15,BO:10,16,BO:"
					+ "11,0,BO:11,1,EM:11,2,EM:11,3,BL:11,4,EM:11,5,EM:11,6,WA:11,7,BO:11,8,EM:11,9,EM:11,10,BL:11,11,EM:11,12,EM:11,13,BO:11,14,BL:11,15,EM:11,16,EM:"
					+ "12,0,BO:12,1,BO:12,2,BO:12,3,BO:12,4,BO:12,5,BO:12,6,BO:12,7,BO:12,8,BO:12,9,BO:12,10,BO:12,11,BO:12,12,BO:12,13,BO:12,14,BO:12,15,BO:12,16,BO:"
					+ "13,0,BO:13,1,EM:13,2,EM:13,3,BL:13,4,EM:13,5,EM:13,6,WA:13,7,BO:13,8,EM:13,9,EM:13,10,BL:13,11,EM:13,12,EM:13,13,BO:13,14,BL:13,15,EM:13,16,EM:"
					+ "14,0,BO:14,1,BO:14,2,BO:14,3,BO:14,4,BO:14,5,BO:14,6,BO:14,7,BO:14,8,BO:14,9,BO:14,10,WA:14,11,BO:14,12,BO:14,13,BO:14,14,BO:14,15,BO:14,16,BO:"
					+ "15,0,BO:15,1,EM:15,2,EM:15,3,BL:15,4,EM:15,5,EM:15,6,EM:15,7,BO:15,8,EM:15,9,EM:15,10,BL:15,11,EM:15,12,EM:15,13,BO:15,14,BL:15,15,EM:15,16,EM:"
					+ "16,0,BO:16,1,BO:16,2,BO:16,3,BO:16,4,BO:16,5,BO:16,6,BO:16,7,BO:16,8,BO:16,9,BO:16,10,BO:16,11,BO:16,12,BO:16,13,BO:16,14,BO:16,15,BO:16,16,BO:"
					;	
	public static final String MAP_2 = "0,0,BO:0,1,BO:0,2,BO:0,3,BO:0,4,BO:0,5,BO:0,6,BO:0,7,BO:0,8,BO:0,9,BO:0,10,BO:0,11,BO:0,12,BO:0,13,BO:0,14,BO:0,15,BO:0,16,BO:"
			+ "1,0,BO:1,1,YG:1,2,YG:1,3,WA:1,4,WA:1,5,WA:1,6,WA:1,7,WA:1,8,WA:1,9,WA:1,10,WA:1,11,WA:1,12,WA:1,13,WA:1,14,YG:1,15,YG:1,16,BO:"
			+ "2,0,BO:2,1,YG:2,2,BL:2,3,WA:2,4,BL:2,5,WA:2,6,BL:2,7,WA:2,8,BL:2,9,WA:2,10,BL:2,11,WA:2,12,BL:2,13,WA:2,14,BL:2,15,YG:2,16,BO:"
			+ "3,0,BO:3,1,WA:3,2,WA:3,3,WA:3,4,WA:3,5,WA:3,6,WA:3,7,WA:3,8,WA:3,9,WA:3,10,WA:3,11,WA:3,12,WA:3,13,WA:3,14,WA:3,15,WA:3,16,BO:"
			+ "4,0,BO:4,1,WA:4,2,BL:4,3,WA:4,4,BL:4,5,WA:4,6,BL:4,7,WA:4,8,BL:4,9,WA:4,10,BL:4,11,WA:4,12,BL:4,13,WA:4,14,BL:4,15,WA:4,16,BO:"
			+ "5,0,BO:5,1,WA:5,2,WA:5,3,WA:5,4,WA:5,5,WA:5,6,WA:5,7,WA:5,8,WA:5,9,WA:5,10,WA:5,11,WA:5,12,WA:5,13,WA:5,14,WA:5,15,WA:5,16,BO:"
			+ "6,0,BO:6,1,WA:6,2,BL:6,3,WA:6,4,BL:6,5,WA:6,6,BL:6,7,WA:6,8,BL:6,9,WA:6,10,BL:6,11,WA:6,12,BL:6,13,WA:6,14,BL:6,15,WA:6,16,BO:"
			+ "7,0,BO:7,1,WA:7,2,WA:7,3,WA:7,4,WA:7,5,WA:7,6,WA:7,7,WA:7,8,WA:7,9,WA:7,10,WA:7,11,WA:7,12,WA:7,13,WA:7,14,WA:7,15,WA:7,16,BO:"
			+ "8,0,BO:8,1,WA:8,2,BL:8,3,WA:8,4,BL:8,5,WA:8,6,BL:8,7,WA:8,8,BL:8,9,WA:8,10,BL:8,11,WA:8,12,BL:8,13,WA:8,14,BL:8,15,WA:8,16,BO:"
			+ "9,0,BO:9,1,WA:9,2,WA:9,3,WA:9,4,WA:9,5,WA:9,6,WA:9,7,WA:9,8,WA:9,9,WA:9,10,WA:9,11,WA:9,12,WA:9,13,WA:9,14,WA:9,15,WA:9,16,BO:"
			+ "10,0,BO:10,1,WA:10,2,BL:10,3,WA:10,4,BL:10,5,WA:10,6,BL:10,7,WA:10,8,BL:10,9,WA:10,10,BL:10,11,WA:10,12,BL:10,13,WA:10,14,BL:10,15,WA:10,16,BO:"
			+ "11,0,BO:11,1,WA:11,2,WA:11,3,WA:11,4,WA:11,5,WA:11,6,WA:11,7,WA:11,8,WA:11,9,WA:11,10,WA:11,11,WA:11,12,WA:11,13,WA:11,14,WA:11,15,WA:11,16,BO:"
			+ "12,0,BO:12,1,WA:12,2,BL:12,3,WA:12,4,BL:12,5,WA:12,6,BL:12,7,WA:12,8,BL:12,9,WA:12,10,BL:12,11,WA:12,12,BL:12,13,WA:12,14,BL:12,15,WA:12,16,BO:"
			+ "13,0,BO:13,1,WA:13,2,WA:13,3,WA:13,4,WA:13,5,WA:13,6,WA:13,7,WA:13,8,WA:13,9,WA:13,10,WA:13,11,WA:13,12,WA:13,13,WA:13,14,WA:13,15,WA:13,16,BO:"
			+ "14,0,BO:14,1,YG:14,2,BL:14,3,WA:14,4,BL:14,5,WA:14,6,BL:14,7,WA:14,8,BL:14,9,WA:14,10,BL:14,11,WA:14,12,BL:14,13,WA:14,14,BL:14,15,YG:14,16,BO:"
			+ "15,0,BO:15,1,YG:15,2,YG:15,3,WA:15,4,WA:15,5,WA:15,6,WA:15,7,WA:15,8,WA:15,9,WA:15,10,WA:15,11,WA:15,12,WA:15,13,WA:15,14,YG:15,15,YG:15,16,BO:"
			+ "16,0,BO:16,1,BO:16,2,BO:16,3,BO:16,4,BO:16,5,BO:16,6,BO:16,7,BO:16,8,BO:16,9,BO:16,10,BO:16,11,BO:16,12,BO:16,13,BO:16,14,BO:16,15,BO:16,16,BO:";
}
