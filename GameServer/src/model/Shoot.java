package model;

import network.ConstantList;

public class Shoot extends MyThread {

	public static int count = 0;
	private int id;
	private int x;
	private int y;
	
	public Shoot(int x, int y) {
		super("", ConstantList.SHOOT_SLEEP);
		this.id = count++;
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