public enum Direction {

	UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0),  NONE(0, 0);
	
	public int dx,dy;
	
	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	//Reutns the direction opposite of the direction calling the method
	public Direction opposite() {
		switch(this) {
			case UP: return Direction.DOWN;
			case RIGHT: return Direction.LEFT;
			case DOWN: return Direction.UP;
			case LEFT: return Direction.RIGHT;
			default: break;
		}
		return Direction.NONE;
	}
}
