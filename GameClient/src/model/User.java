package model;

public class User {

	private String name;
	private int positionX;
	private int positionY;
	
	public User(String name, int positionX, int positionY) {
		super();
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public String getName() {
		return name;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
}