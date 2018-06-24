package network;

import java.io.File;
import java.util.ArrayList;

import model.GameManager;
import model.MyThread;
import model.Player;
import model.Shoot;
import model.User;
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
		if (connections.size() == ConstantList.PLAYER_LIM) {
			for (Connection actual : connections) {
				sendUsers(actual);
				actual.startMessage();
			}
			start();
		}
		sockets++;
	}

	public int getSize() {
		return sockets;
	}

	private void sendUsers(Connection actual) {
		ArrayList<User> list = new ArrayList<>();
		for (Connection connection : connections) {
			if (connection != actual) {
				Player player = connection.getPlayer();
				list.add(new User(player.getName(), player.getArea().getX(), player.getArea().getY()));
			}
		}
		actual.sendPlayers(list);
	}

	@Override
	public void execute() {
		File shootFile = new File(ConstantList.SHOOT + ConstantList.XML);
		FileManager.saveShootFile(shootFile, gameManager.getShoots());
		for (Connection connection : connections) {
			sendUsers(connection);
			if (!gameManager.getShoots().isEmpty()) {
				connection.sendShoots(shootFile);
			}
		}
	}

	

	@Override
	public void removeConnection(Connection connection) {
		connections.remove(connection);
	}

	@Override
	public void addPlayer(Connection connection) {
	}

	@Override
	public void createShoot(int x, int y) {
		gameManager.addShoot(x, y);
	}
}