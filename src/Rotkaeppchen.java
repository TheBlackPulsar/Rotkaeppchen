
public class Rotkaeppchen extends VerwunschenerWald implements Person{
	
	private int gesundheit = 100;
	
	public Rotkaeppchen(Position position) {								//Ruft Position auf und uebergibt Namen Rotkaeppchen
		super(position);
		name="Rotkaeppchen";
	}

	public void geheHoch() {												// Methoden zur Bewegung
		int a=position.getY();
		position.setY(a-1);
	}
	
	public void geheRunter() {
		int a=position.getY();
		position.setY(a+1);	
	}
	
	public void geheLinks() {
		int a=position.getX();
		position.setX(a-1);
	}
	
	public void geheRechts() {
		int a=position.getX();
		position.setX(a+1);
	}
	
	public void gesundheitVerringern(int wert) {							//Verringert Gesundheit um angegebenen Wert
		gesundheit -= wert;
		if (gesundheit <= 0)
			gesundheit = 0;
	}
	
	public boolean istNochLebendig() {										//Prueft ob Rotkaeppchen noch lebendig ist
		if(gesundheit == 0)
			return false;
		return true;
	}
	
	public String getName() {												//Uebergibt R als Namen
		
		return "R";
		
	}

	@Override
	public void sprechen(Person konversationspartner, int zaehler) {						//Ruft sprechen des Interface mit der jeweiligen Aussage auf
		switch(zaehler) {
			case 1:
				System.out.println("Rotkaeppchen: Hallo, "+ konversationspartner.getPartner());
				break;
			case 3:
				System.out.println("Rotkaeppchen: Tschuess, "+ konversationspartner.getPartner());
				break;
		}
	}

	@Override
	public String getPartner() {															//Uebergibt Rotkaeppchen als Partner des Gespraech
	
		return name;
	}
	
	public void setGesundheit(int gesundheit) {												//Setter fuer Gesundheit
		
		this.gesundheit = gesundheit;
	}
}
