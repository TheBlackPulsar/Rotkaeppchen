
public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y) {										//Uebergabe der Verschiedenen Objekt Positionen
		
		this.x = x;
		this.y = y;
	}
	
	public int getX() {													//Getter
		
		return this.x;
		
	}
	
	public int getY() {
		
		return this.y;
		
	}
	
	public void setX(int x) {											//Setter
		
		this.x = x;
		
	}
	
	public void setY(int y) {
		
		this.y = y;
		
	}
	@Override
	public boolean equals (Object o) {									//Override zum Positionsvergleich zweier Objekte
		Position p = (Position)o;
		if(p instanceof Position) {
			if(this.x ==p.getX()&& this.y == p.getY()) {
			return true;
			}
			
			
		}
		return false;
	}

}

