package model;

import network.ConstantList;

public class Asteroid extends MyThread {

	public static int count = 0;
	private int id;
	private int x;
	private int y;
	
	public Asteroid() {
		super("", ConstantList.SHOOT_SLEEP);
		this.id = count++;
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

	@Override
	public void execute() {
		y += ConstantList.S_MOVE_UNIT;
		if (y <= ConstantList.WIDTH_FRAME) {
			stop();
		}
	}
}