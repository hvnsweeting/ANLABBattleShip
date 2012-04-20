package com.familug.hvn;

public class Coordinate {
	int x;
	int y;

	public Coordinate(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public Coordinate(String string, String string2) {
		this.y = Integer.parseInt(string);
		this.x = Integer.parseInt(string2);
	}
	
	public Coordinate(String y_x) {
		String[] tokens = y_x.split("[ ]");
		this.y = Integer.parseInt(tokens[0]);
		this.x = Integer.parseInt(tokens[1]);
	}
	
	

	@Override
	public String toString() {
		return y + " " + x;
	}
	
	@Override
	public boolean equals(Object obj) {
		Coordinate other = (Coordinate) obj;
		if(other.x == this.x && other.y == this.y)
			return true;
		else
			return false;
	}

}
