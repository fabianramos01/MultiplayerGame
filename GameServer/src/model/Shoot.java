package model;

import network.ConstantList;

public class Shoot extends MyThread {

	private int id;
	private int x;
	private int y;
	
	public Shoot(int id, int x, int y) {
		super("", ConstantList.SHOOT_SLEEP);
		this.id = id;
		this.x = x;
		this.y = y;
		start();
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
		y -= ConstantList.MOVE_UNIT;
		if (y <= 0) {
			stop();
		}
	}
}