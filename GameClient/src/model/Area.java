package model;

import controller.ConstantList;

public class Area {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public Area(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void move(Direction direction) {
		switch (Direction.valueOf(direction.name())) {
		case UP:
			y -= ConstantList.MOVE_UNIT;
			break;
		case DOWN:
			y += ConstantList.MOVE_UNIT;
			break;
		case LEFT:
			x -= ConstantList.MOVE_UNIT;
			break;
		case RIGHT:
			x += ConstantList.MOVE_UNIT;
			break;
		}

	}
	
}