package model;

import java.util.ArrayList;

import controller.ConstantList;

public class ManagerGame {

	private Player player;
	private ArrayList<User> users;
	private ArrayList<Shoot> shoots;

	public ManagerGame(String name) {
		this.player = new Player(name,
				new Area((int) (Math.random() * (ConstantList.WIDTH_FRAME - ConstantList.IMG_SIZE)),
						ConstantList.HEIGHT_FRAME - ConstantList.IMG_SIZE - 70, 0, 0));
		users = new ArrayList<>();
		shoots = new ArrayList<>();
	}

	public void move(Direction direction) {
		player.move(direction);
	}

	public void loadUsers(ArrayList<User> players) {
		if (users.isEmpty()) {
			for (User user : players) {
				users.add(user);
			}
		} else {
			for (User user : players) {
				setInfo(user);
			}
		}
	}

	private void setInfo(User player) {
		for (User user : users) {
			if (user.getName().equals(player.getName())) {
				user.setPositionX(player.getPositionX());
				user.setPositionY(player.getPositionY());
				break;
			}
		}
	}

	public void loadShoots(ArrayList<Shoot> shootList) {
		for (int i = 0; i < shootList.size(); i++) {
			if (!setInfoShoot(shootList.get(i))) {
				shoots.add(shootList.get(i));
			}
		}
	}

	private boolean setInfoShoot(Shoot shoot) {
		for (Shoot actual : shoots) {
			if (actual.getId() == shoot.getId()) {
				actual.setX(shoot.getX());
				actual.setY(shoot.getY());
				return true;
			}
		}
		return false;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Shoot> getShoots() {
		return shoots;
	}

	public ArrayList<User> getUsers() {
		return users;
	}
}