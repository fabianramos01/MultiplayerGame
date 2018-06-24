package model;

import network.ConstantList;

public class Asteroid extends MyThread {

	public static int count = 0;
	private int id;
	private int type;
	private int x;
	private int y;
	private int size;
	private int life;
	
	public Asteroid() {
		super("", ConstantList.ASTEROID_SLEEP_I);
		this.type = (int) (Math.random() * ConstantList.MAX_TYPE + ConstantList.MIN_TYPE);
		life = type;
		id = count++;
		x = (int) (Math.random() * ConstantList.WIDTH_FRAME);
		setSize();
		start();
	}
	
	private void setSize() {
		if (type == ConstantList.MAX_TYPE) {
			size = ConstantList.AST_TH_SIZE;
		} else if (type == ConstantList.MIN_TYPE) {
			size = ConstantList.AST_O_SIZE;
		} else {
			size = ConstantList.AST_T_SIZE;
		}
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
	
	public int getSize() {
		return size;
	}
	
	public int getType() {
		return type;
	}
	
	public void shock() {
		life--;
		if (life == 0) {
			stop();
		}
	}

	@Override
	public void execute() {
		y += ConstantList.S_MOVE_UNIT;
		if (ConstantList.WIDTH_FRAME <= y) {
			stop();
		}
	}
}