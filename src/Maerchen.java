
public class Maerchen {

	public static void main(String[] args) {
		
		int x = 40;																					//Breite der Karte
		int y = 10;																					//Hoehe der Karte
		int gefahrenZahl = 0;																		//Anzahl der Gefahren
		int baumAnzahl = 20;																		//Anzahl der Baeume
		
		Maerchenwelt maerchenwelt= new Maerchenwelt(x, y, gefahrenZahl, baumAnzahl);				//Aufrufen der Maerchenwelt Methode mit den angegebenen Parametern
		
		maerchenwelt.start();																		//Aufrufen der Methode start
		

	}

}
