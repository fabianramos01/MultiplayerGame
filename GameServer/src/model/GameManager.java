package model;

import java.util.ArrayList;

import network.ConstantList;

public class GameManager extends MyThread {

	private ArrayList<Player> players;
	private ArrayList<Shoot> shoots;
	private ArrayList<Asteroid> asteroids;
	
	public GameManager() {
		super("", ConstantList.MANAGER_SLEEP);
		players = new ArrayList<>();
		shoots = new ArrayList<>();
		asteroids = new ArrayList<>();
	}
	
	public void addShoot(int x, int y) {
		shoots.add(new Shoot(x, y));
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	private void deleteShoot() {
		for (int i = 0; i < shoots.size(); i++) {
			if (shoots.get(i).isStop()) {
				shoots.remove(i);
			}
		}
	}

	@Override
	public void execute() {
		deleteShoot();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public ArrayList<Shoot> getShoots() {
		return shoots;
	}
	
	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}
}