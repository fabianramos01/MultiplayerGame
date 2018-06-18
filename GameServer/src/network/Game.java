package network;

import java.util.ArrayList;

import model.MyThread;
import model.Player;
import model.User;

public class Game extends MyThread implements IObserver {

	public static int gameNum = 0;
	private ArrayList<Connection> connections;
	private int sockets;

	public Game() {
		super(String.valueOf(gameNum++), ConstantList.GAME_SLEEP);
		connections = new ArrayList<>();
	}

	public void addConnection(Connection connection) {
		System.out.println(connection.getPlayer().getName() + "- Game " + gameNum);
		connection.addObserver(this);
		connections.add(connection);
		if (connections.size() == ConstantList.PLAYER_LIM) {
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
		for (Connection connection : connections) {
			sendUsers(connection);
		}
	}

	@Override
	public void update(String name) {
		System.out.println(name);
	}

	@Override
	public void removeConnection(Connection connection) {
		connections.remove(connection);
	}

	@Override
	public void addPlayer(Connection connection) {
	}
}