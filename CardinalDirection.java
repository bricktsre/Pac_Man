public enum CardinalDirection {
	
	UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0),  NONE(0, 0);

	public int dx, dy;
	
	private CardinalDirection(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public CardinalDirection opposite(){
		switch(this){
		case UP:
			return DOWN;
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		default:
			return NONE;
		
		}		
	}
	
	public int directionToConsiderCardinal() {
		switch(this) {
		case UP:
			return 0;
		case DOWN:
			return 0;
		case LEFT:
			return 1;
		case RIGHT:
			return 1;
		default:
			return -1;
		}
	}
	
	public int directionToConsiderDiagonal() {
		switch(this) {
		case UP:
			return 1;
		case DOWN:
			return 1;
		case LEFT:
			return 0;
		case RIGHT:
			return 0;
		default:
			return -1;
		}
	}
	
	public String toString() {
		switch(this){
		case UP:
			return "UP";
		case DOWN:
			return "DOWN";
		case LEFT:
			return "LEFT";
		case RIGHT:
			return "RIGHT";
		default:
			return "";
		
		}
	}
}