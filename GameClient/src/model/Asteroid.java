package model;

public class Asteroid{

	private int id;
	private int x;
	private int y;
	private int type;
	
	public Asteroid(int id, int x, int y, int type) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getId() {
		return id;
	}
	
	public int getType() {
		return type;
	}
}