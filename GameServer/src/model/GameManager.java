package model;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

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

	private void deleteAsteroid() {
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).isStop()) {
				asteroids.remove(i);
			}
		}
	}
	
	public void timerAsteroid() {
		Timer timer = new Timer(ConstantList.CREATE_AS_TIME, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				asteroids.add(new Asteroid());
			}
		});
		timer.start();
	}

	@Override
	public void execute() {
		collisionShoot();
		collisionPlayer();
		deleteShoot();
		deleteAsteroid();
	}

	private void collisionPlayer() {
		for (Player player : players) {
			for (int j = 0; j < asteroids.size(); j++) {
				if (validateCollision(getAsteroidRectangle(asteroids.get(j)), getPlayerRectangle(player))) {
					asteroids.get(j).stop();
					player.lessLife(asteroids.get(j).getType());
				}
			}
		}
	}

	private void collisionShoot() {
		for (int i = 0; i < asteroids.size(); i++) {
			for (int j = 0; j < shoots.size(); j++) {
				if (validateCollision(getAsteroidRectangle(asteroids.get(i)), getShootRectangle(shoots.get(j)))) {
					asteroids.get(i).shock();
					shoots.remove(j);
				}
			}
		}
	}

	private Rectangle getShootRectangle(Shoot shoot) {
		return new Rectangle(shoot.getX(), shoot.getY(), ConstantList.SHOOT_WIDHT, ConstantList.SHOOT_HEIGTH);
	}

	private Rectangle getPlayerRectangle(Player player) {
		return new Rectangle(player.getArea().getX(), player.getArea().getY(), ConstantList.PLAYER_SIZE,
				ConstantList.PLAYER_SIZE);
	}

	private Rectangle getAsteroidRectangle(Asteroid asteroid) {
		return new Rectangle(asteroid.getX(), asteroid.getY(), asteroid.getSize(), asteroid.getSize());
	}

	private boolean validateCollision(Rectangle rectangle1, Rectangle rectangle2) {
		if (rectangle1.intersects(rectangle2)) {
			return true;
		}
		return false;
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