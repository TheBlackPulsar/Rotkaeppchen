import java.util.Random;
import java.util.ArrayList;
public class Maerchenwelt {
	
	private int x;
	private int y;
	private VerwunschenerWald[][] karte;
	private Oma oma;
	private Wolf wolf;
	private Rotkaeppchen rotkaeppchen;
	Random r = new Random();
	ArrayList<Baum> arr = new ArrayList<Baum>();
	ArrayList<Gefahr> arr2 = new ArrayList<Gefahr>();

	
	
	public Maerchenwelt(int x, int y, int gefahrenZahl, int baumAnzahl) {											//Erzeugt den verwunschenen Wald
		
		if ( x < 10 || y < 10 )																						//IllegalArgumentException
		    throw new IllegalArgumentException( "Vergroessern sie den verwunschenen Wald" );						
		if ( gefahrenZahl+baumAnzahl>x*y-3 )
		    throw new IllegalArgumentException( "Reduzieren sie die Anzahl der Baeume und Gefahren" );
		
		this.x = x;
		this.y = y;
		karte = new VerwunschenerWald[x][y];
		Position rpos = new Position(0,0);																			//Platziert Rotkaeppchen auf der Karte
		rotkaeppchen = new Rotkaeppchen(rpos);
		karte[rpos.getX()][rpos.getY()]= rotkaeppchen;
		
		int oposx = 0;																								//Platziert Oma auf der Karte
		while(oposx == 0)
			oposx = r.nextInt(9);
		int oposy = 0;
		while(oposy == 0)
			oposy = r.nextInt(9);
		Position opos = new Position(x-oposx,y-oposy);
		oma = new Oma(opos);
		karte[opos.getX()][opos.getY()]= oma;
			
			boolean b = true;																						//Platziert Wolf auf der Karte und prueft andere Objekte
			while(b) {
				int wposx = r.nextInt(x);
				int wposy = r.nextInt(y);
				Position wpos = new Position(wposx,wposy);
				if (checkPersonen(wpos)) {
					wolf = new Wolf(wpos);
					karte[wpos.getX()][wpos.getY()]= wolf;
					b = false;
				}
			}
		
		Position bpos;																								//Platziert Baeume auf der Karte und prueft andere Objekte
		for(int i = 1; i <= baumAnzahl; i++) {
			boolean a = true;
			while(a) {
				int bposx = r.nextInt(x);
				int bposy = r.nextInt(y);
				bpos = new Position(bposx,bposy);
				if (checkBaum(bpos)) {
					Baum baum = new Baum(bpos);
					karte[bpos.getX()][bpos.getY()]= baum;
					arr.add(baum);
					a = false;
				}
			}
		}
		
		Position gpos;																								//Platziert Gefahren auf der Karte und prueft andere Objekte
		for(int i = 1; i <= gefahrenZahl; i++) {
			boolean a = true;
			while(a) {
				int gposx = r.nextInt(x);
				int gposy = r.nextInt(y);
				gpos = new Position(gposx,gposy);
				if (checkAll(gpos)) {
					Gefahr gefahr = new Gefahr(gpos);
					karte[gpos.getX()][gpos.getY()]= gefahr;
					arr2.add(gefahr);
					a = false;
				}
			}
		}
	}
	
	public boolean checkBaum(Position position) {																	//Prueft auf Objekte
		Position rpos = rotkaeppchen.getPosition();
		Position opos = oma.getPosition();
		if(rpos.getX()==position.getX()&&rpos.getY()==position.getY())
			return false;
		if(opos.getX()==position.getX()&&opos.getY()==position.getY())
			return false;
		for(Baum elem : arr) {
			Position bpos = elem.getPosition();
			if(bpos.getX()==position.getX()&&bpos.getY()==position.getY())
				return false;
		}
		return true;
		
	}
	
	public boolean checkPersonen(Position position) {																//Prueft auf Objekte
		Position rpos = rotkaeppchen.getPosition();
		Position opos = oma.getPosition();
		if(rpos.getX()==position.getX()&&rpos.getY()==position.getY())
			return false;
		if(opos.getX()==position.getX()&&opos.getY()==position.getY())
			return false;
		return true;
		
	}
	
	public boolean checkAll(Position position) {																	//Prueft auf Objekte
		Position rpos = rotkaeppchen.getPosition();
		Position opos = oma.getPosition();
		Position wpos = wolf.getPosition();
		if(rpos.getX()==position.getX()&&rpos.getY()==position.getY())
			return false;
		if(opos.getX()==position.getX()&&opos.getY()==position.getY())
			return false;
		if(wpos.getX()==position.getX()&&wpos.getY()==position.getY())
			
			return false;
		for(Baum elem : arr) {
			Position bpos = elem.getPosition();
			if(bpos.getX()==position.getX()&&bpos.getY()==position.getY())
				return false;
		}
		for(Gefahr elem2 : arr2) {
			Position gpos = elem2.getPosition();
			if(gpos.getX()==position.getX()&&gpos.getY()==position.getY())
				return false;
		}
		return true;
		
	}
	
	public VerwunschenerWald [] [] getKarte() {																		//Getter
		return karte;
	}
	
	public Rotkaeppchen getRotkaeppchen() {
		return rotkaeppchen;
	}
	
	public Oma getOma() {
		return oma;
	}
	
	public ArrayList<Position> wegFinden(Position ziel) {															//Laesst Rotkaeppchen einen weg zum angegebenen ziel finden und diesen ausgeben
		ArrayList<Position> weg = new ArrayList<Position>();
		Position rpos = rotkaeppchen.getPosition();
		Position npos;
		Position wpos = wolf.getPosition();
		for(int i=0; i<=500;i++) {																					//500 Runden für die Suche
				int richtung = r.nextInt(4);
				switch(richtung) {
				case 0:																								//Ueberpruefung Hindernisse und gehe eins nach oben 
					npos = new Position(rpos.getX(),rpos.getY()-1);
					if(npos.getY()<0) {
						break;
					}else {
						if(checkBaum(npos)) {
							rotkaeppchen.geheHoch();
						}else {
							if(npos.equals(ziel)){
								rotkaeppchen.geheHoch();
								return weg;
							}
							
						}
						
					}
					break;
				case 1:																								//Ueberpruefung Hindernisse und gehe eins nach links
					npos = new Position(rpos.getX()-1,rpos.getY());
					if(npos.getX()<0) {
						break;
					}else {
						if(checkBaum(npos)) {
							rotkaeppchen.geheLinks();
						}else {
							if(npos.equals(ziel)){
								rotkaeppchen.geheLinks();
								return weg;
							}
							
						}
						
					}
				
					break;
				case 2:																								//Ueberpruefung Hindernisse und gehe eins nach rechts
					npos = new Position(rpos.getX()+1,rpos.getY());
					if(npos.getX()>x) {
						break;
					}
					if(checkBaum(npos)) {
						rotkaeppchen.geheRechts();
					}
					if(npos.equals(ziel)){
						rotkaeppchen.geheRechts();
						return weg;
				
					}
					
					break;
				case 3:																								//Ueberpruefung Hindernisse und gehe eins nach unten
					npos = new Position(rpos.getX(),rpos.getY()+1);
					if(npos.getY()>y) {
						break;
					}
					if(checkBaum(npos)) {
						rotkaeppchen.geheRunter();
					}
					if(npos.equals(ziel)){
						rotkaeppchen.geheRunter();
						return weg;
					
					}
					
					break;
				}
				rpos = rotkaeppchen.getPosition();
				if(ziel.getX()==0 && ziel.getY()==0) {
					if(rpos.getX()==0 && rpos.getY()==0) {
						return weg;
					}
				}
				System.out.println(rpos.getX() + " "+ rpos.getY());													//Falls Wolf auf aktueller Position Gesundheit verringern
				weg.add(rpos);
				if(wpos.getX()==rpos.getX()&&wpos.getY()==rpos.getY()) {
					rotkaeppchen.gesundheitVerringern(wolf.schaden);
					if(!rotkaeppchen.istNochLebendig())																//Prueffen ob noch lebendig
						return weg;
					
				}
				
				for(Gefahr elem : arr2) {																			//Falls Gefahr auf aktueller Position Gesundheit verringern
					Position gpos = elem.getPosition();
					if(gpos.getX()==rpos.getX()&&gpos.getY()==rpos.getY()) {
						rotkaeppchen.gesundheitVerringern(elem.schaden);
						if(!rotkaeppchen.istNochLebendig())
							return weg;
					}
						
				}
				
			
		}
		return weg;
	}
	
	public  void printWald() {																									//Printet den Wald 
		
		//Rahmen: linke obere Ecke
		System.out.print("+");
		//Rahmen: erste Zeile
		for(int i = 0; i < x; i++) {
			System.out.print("-");
		}
		//Rahmen: rechte obere Ecke
		System.out.println("+");
		//Rahmen: linker Rand
		for(int j = 0; j < y; j++) {
			System.out.print("|");
		
			//Die eigentliche Karte
			for(int i = 0; i < x; i++) {
				if (karte [i] [j] != null ) {
					System.out.print(karte [i] [j].getName());
				}else {
					System.out.print(" ");
				}
			}
		
			//Rahmen: rechter Rand
			System.out.println("|");
		}
		//Rahmen: linke untere Ecke
		System.out.print("+");
		//Rahmen: letzte Zeile
		for(int i = 0; i < x; i++) {
			System.out.print("-");
		}
		//Rahmen: rechte untere Ecke
		System.out.println("+");
	}
	
	public void start() {
		
		printWald();
		Position opos = oma.getPosition();
		ArrayList <Position> weg = wegFinden(opos); 														//Ruft wegFinden mit Position Oma auf
		Position endpos = weg.get(weg.size()-1);
		if(!rotkaeppchen.istNochLebendig()) {																//Prueft ob Rotkaeppchen noch lebendig
			System.out.println("Rotkaeppchen ist nicht bei der Oma angekommen");							//Hat Oma nicht gefunden
			return;
		}
		if(endpos.getX()==opos.getX()&&endpos.getY()==opos.getY()) {
			rotkaeppchen.setGesundheit(100);																//Setzt Gesundheit wieder auf 100 wenn bei Oma
			System.out.println("Rotkaeppchen ist bei Oma angekommen");										//Hat Oma gefunden
			rotkaeppchen.sprechen(oma, 1);																	//Konversation
			oma.sprechen(rotkaeppchen, 2);
			rotkaeppchen.sprechen(oma, 3);
			Position start = new Position(0,0);																//Uebergibt startposition als ziel
			weg = wegFinden(start);
			if(!rotkaeppchen.istNochLebendig()) {
				System.out.println("Rotkaeppchen ist nicht wieder zu Hause angekommen");					//Ist gestorben
				return;
			}
			endpos = weg.get(weg.size()-1);
			System.out.println(endpos.getX() + " "+ endpos.getY());
			if(endpos.getX()==start.getX()&&endpos.getY()==start.getY()) {
				System.out.println("Rotkaeppchen ist wieder zu Hause angekommen");							//Hat start gefunden				
			} else {
				System.out.println("Rotkaeppchen hat sich auf dem Heimweg verlaufen");						//Hat start nicht gefunden
			}
		} else {
			System.out.println("Rotkaeppchen hat sich auf dem Weg zur Oma verlaufen");						//Hat Oma nicht gefunden
		}
		
		
		
	}
}
