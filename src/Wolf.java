
public class Wolf extends VerwunschenerWald {
	
	public Wolf(Position position) {							//Ruft Position auf und uebergibt schaden
		super (position);
		schaden = 5;
	}
	
	public String getName() {									//Uebergibt W als Namen
		
		return "W";
		
	}

}
