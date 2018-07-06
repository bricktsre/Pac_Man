public enum DiagDirection {
	
	 UPLEFT(-1,-1), UPRIGHT(1,-1), DOWNRIGHT(1,1), DOWNLEFT(-1,1), NONE(0, 0);

	public int dx, dy;
	
	private DiagDirection(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public DiagDirection opposite(){
		switch(this){
		case UPLEFT:
			return DOWNRIGHT;
		case UPRIGHT:
			return DOWNLEFT;
		case DOWNLEFT:
			return UPRIGHT;
		case DOWNRIGHT:
			return UPLEFT;	
		default:
			return NONE;
		
		}		
	}
	
	public DiagDirection setDiag(int a, CardinalDirection b) {
		if(b.equals(CardinalDirection.UP)) {
			if(a>0)
				return UPLEFT;
			else
				return UPRIGHT;
		}else if(b.equals(CardinalDirection.RIGHT)) {
			if(a>0)
				return UPRIGHT;
			else
				return DOWNRIGHT;
		}
		else if(b.equals(CardinalDirection.DOWN)) {
			if(a>0)
				return DOWNLEFT;
			else
				return DOWNRIGHT;
		}
		else {
			if(a>0)
				return UPLEFT;
			else
				return DOWNLEFT;
		}
	}

}