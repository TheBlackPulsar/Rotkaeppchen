
public abstract class VerwunschenerWald {
	
	protected Position position;
	protected int schaden = 0;
	protected String name;
	
	public VerwunschenerWald(Position position) {						//Ruft Position auf
		this.position=position;
	}

	public Position getPosition() {										//Getter
		
		return position;
		
	}
	
	public int getSchaden() {
		
		return schaden;
		
	}
	
	public abstract String getName();									//Uebergibt Namen 
}
