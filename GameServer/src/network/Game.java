package network;

import java.io.File;
import java.util.ArrayList;

import model.GameManager;
import model.MyThread;
import model.Player;
import persistence.FileManager;

public class Game extends MyThread implements IObserver {

	public static int gameNum = 0;
	public int shootNum;
	private ArrayList<Connection> connections;
	private GameManager gameManager;
	private int sockets;

	public Game() {
		super(String.valueOf(gameNum++), ConstantList.GAME_SLEEP);
		connections = new ArrayList<>();
		gameManager = new GameManager();
	}

	public void addConnection(Connection connection) {
		System.out.println(connection.getPlayer().getName() + " - Game " + getText());
		connection.addObserver(this);
		connections.add(connection);
		gameManager.addPlayer(connection.getPlayer());
		if (connections.size() == ConstantList.PLAYER_LIM) {
			for (Connection actual : connections) {
				sendUsers(actual);
				actual.startMessage();
			}
			start();
			gameManager.start();
			gameManager.timerAsteroid();
		}
		sockets++;
	}

	public int getSize() {
		return sockets;
	}

	private void sendUsers(Connection actual) {
		ArrayList<Player> list = new ArrayList<>();
		for (Connection connection : connections) {
			if (connection != actual) {
				list.add(connection.getPlayer());
			}
		}
		actual.sendPlayers(list);
	}

	@Override
	public void execute() {
		File shootFile = new File(ConstantList.SHOOT + ConstantList.XML);
		FileManager.saveShootFile(shootFile, gameManager.getShoots());
		File asteroidFile = new File(ConstantList.ASTEROID + ConstantList.XML);
		FileManager.saveAsteroidFile(asteroidFile, gameManager.getAsteroids());
		Connection connection;
		for (int i = 0; i < connections.size(); i++) {
			connection = connections.get(i);
			connection.sendLife();
			sendUsers(connection);
			if (!gameManager.getShoots().isEmpty()) {
				connection.sendShoots(shootFile);
			}
			if (!gameManager.getAsteroids().isEmpty()) {
				connection.sendAsteroids(asteroidFile);
			}
		}
		playerAlive();
	}
	
	private void playerAlive() {
		for (int i = 0; i < connections.size(); i++) {
			if (connections.get(i).getPlayer().getLife() <= 0) {
				connections.get(i).loseMessage();
				removeConnection(connections.get(i));
			}
		}
	}

	@Override
	public void removeConnection(Connection connection) {
		connection.removeObserver();
		connections.remove(connection);
		if (connections.size() == 1) {
			stop();
			connections.get(0).winMessage();
			gameManager.stopGame();
		}
	}

	@Override
	public void createShoot(int x, int y) {
		gameManager.addShoot(x, y);
	}

	@Override
	public void addPlayer(Connection connection, String name, String password) {
	}

	@Override
	public void addLognIn(Connection connection, String name, String password) {
	}
}