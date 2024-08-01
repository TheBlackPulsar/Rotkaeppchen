
public class Oma extends VerwunschenerWald implements Person{
	
	public Oma(Position position) {															//Ruft Position auf und uebergibt Namen Oma
		super(position);
		name = "Oma";
	}
	
	public String getName() {																//Uebergibt O als Namen
		
		return "O";
		
	}

	@Override
	public void sprechen(Person konversationspartner, int zaehler) {						//Ruft sprechen des Interface mit der jeweiligen Aussage auf
		switch(zaehler) {
			case 2:
				System.out.println("Oma: Hallo, "+ konversationspartner.getPartner());
				break;
			
			
		}
	}
	@Override
	public String getPartner() {															//Uebergibt Oma als Partner des Gespraech
		return name;
		
	}

}
