
public class Gefahr extends VerwunschenerWald {
	
	public Gefahr(Position position) {						//Ruft Position auf und uebergibt schaden
		super(position);
		schaden = 2;
	}
	
	public String getName() {								//Uebergibt G als Namen
		
		return "G";
		
	}

}
