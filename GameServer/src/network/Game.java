package network;

import java.io.File;
import java.util.ArrayList;

import model.MyThread;
import model.Player;
import model.Shoot;
import model.User;
import persistence.FileManager;

public class Game extends MyThread implements IObserver {

	public static int gameNum = 0;
	public int shootNum;
	private ArrayList<Connection> connections;
	private ArrayList<Shoot> shoots;
	private int sockets;

	public Game() {
		super(String.valueOf(gameNum++), ConstantList.GAME_SLEEP);
		connections = new ArrayList<>();
		shoots = new ArrayList<>();
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
		FileManager.saveShootFile(shootFile, shoots);
		for (Connection connection : connections) {
			sendUsers(connection);
			connection.sendShoots(shootFile);
		}
		shootFile.delete();
//		deleteShoot();
	}

	private void deleteShoot() {
		for (int i = 0; i < shoots.size(); i++) {
			if (shoots.get(i).isStop()) {
				shoots.remove(i);
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
		shoots.add(new Shoot(x, y));
	}
}