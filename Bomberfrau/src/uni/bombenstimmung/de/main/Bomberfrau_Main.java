/**
 * 
 */
package uni.bombenstimmung.de.main;

/**
 * @author BEJOSCH
 *
 */
public class Bomberfrau_Main {

	/**
	 * The start of everything (in this program)
	 * @param args The arguments which are added to the start argument
	 */
	public static void main(String[] args) {
		
		System.out.println("Starting Bomberfrau, juhu!");
		
		//Test edit von Tim
		System.out.println("Läuft");
		
		System.out.println();
		System.out.println("Test: ");
		for(int i = 99 ; i > 0 ; i--) {
			if(i%9 == 0 && i != 0 && i != 99) {
				System.out.println();
			}
			System.out.print(doubleWriteNumber(i)+" ");
		}
		System.out.println();
		
	}

	//Beispiel wie ich mir eine Methode mit Documentation etc vorstellen w�rde (Ob deutsch oder english m�ssen wir uns noch einigen):
	
	/**
	 * Stellt jede nummer mit min. 2 Stellen dar
	 * @param number
	 * @return Die nummer aber immer in min 2 Stellen
	 */
	private static String doubleWriteNumber(int number) {
		if(number >= 10) {
			return ""+number;
		}else {
			return "0"+number;
		}
	}
	
}
