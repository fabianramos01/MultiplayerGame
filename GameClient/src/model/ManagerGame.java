package model;

import java.util.ArrayList;

import controller.ConstantList;

public class ManagerGame {

	private Player player;
	private ArrayList<User> users;
	private ArrayList<Shoot> shoots;
	private ArrayList<Asteroid> asteroids;

	public ManagerGame(String name) {
		this.player = new Player(name,
				new Area((int) (Math.random() * (ConstantList.WIDTH_FRAME - ConstantList.IMG_SIZE)),
						ConstantList.HEIGHT_FRAME - ConstantList.IMG_SIZE - 70, 0, 0));
		users = new ArrayList<>();
		shoots = new ArrayList<>();
		asteroids = new ArrayList<>();
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
		for (int i = 0; i < users.size(); i++) {
			if (!userExist(users.get(i), players)) {
				users.remove(i);
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
	
	private boolean userExist(User user, ArrayList<User> userList) {
		for (User acUser : userList) {
			if (acUser.getName().equals(user.getName())) {
				return true;
			}
		}
		return false;
	}

	public void loadShoots(ArrayList<Shoot> shootList) {
		for (int i = 0; i < shootList.size(); i++) {
			if (!setInfoShoot(shootList.get(i))) {
				shoots.add(shootList.get(i));
			}
		}
		for (int i = 0; i < shoots.size(); i++) {
			if (!shootExist(shoots.get(i), shootList)) {
				shoots.remove(i);
			}
		}
	}
	
	private boolean shootExist(Shoot shoot, ArrayList<Shoot> shootList) {
		for (Shoot acShoot : shootList) {
			if (acShoot.getId() == shoot.getId()) {
				return true;
			}
		}
		return false;
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
	
	public void loadAsteroids(ArrayList<Asteroid> asteroidList) {
		for (int i = 0; i < asteroidList.size(); i++) {
			if (!setAsteroidInfo(asteroidList.get(i))) {
				asteroids.add(asteroidList.get(i));
			}
		}
		for (int i = 0; i < asteroids.size(); i++) {
			if (!asteroidExist(asteroids.get(i), asteroidList)) {
				asteroids.remove(i);
			}
		}
	}
	
	private boolean asteroidExist(Asteroid asteroid, ArrayList<Asteroid> asteroidList) {
		for (Asteroid acAsteroid : asteroidList) {
			if (acAsteroid.getId() == asteroid.getId()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean setAsteroidInfo(Asteroid asteroid) {
		for (Asteroid actual : asteroids) {
			if (actual.getId() == asteroid.getId()) {
				actual.setX(asteroid.getX());
				actual.setY(asteroid.getY());
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
	
	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}
}