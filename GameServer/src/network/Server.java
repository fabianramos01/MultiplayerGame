package network;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.MyThread;
import model.User;
import persistence.FileManager;

public class Server extends MyThread implements IObserver{

	private static final String SERVER = "Servidor";
	private static final int SLEEP = 1000;
	private ServerSocket serverSocket;
	private ArrayList<Connection> connections;
	private ArrayList<Game> games;
	private ArrayList<User> users;
	private Socket socket;

	public Server(int port) throws IOException {
		super(SERVER, SLEEP);
		loadUsers();
		serverSocket = new ServerSocket(port);
		games = new ArrayList<>();
		connections = new ArrayList<>();
		games.add(new Game());
		System.out.println("Server create at port " + port);
		start();
	}
	
	private void loadUsers() {
		File file = new File(ConstantList.USERS_FILE);
		if (file.exists()) {
			users = FileManager.getUsers(file);			
		} else {
			users = new ArrayList<>();
			FileManager.saveUsers(users);
		}
	}

	@Override
	public void execute() {
		try {
			socket = serverSocket.accept();
			System.out.println("New connection!" + socket.getInetAddress());
			Connection connection = new Connection(socket);
			connection.addObserver(this);
			connections.add(connection);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void addToGame(Connection connection) {
		connections.remove(connection);
		if (games.get(games.size()-1).getSize() < ConstantList.PLAYER_LIM) {
			games.get(games.size()-1).addConnection(connection);
		} else {
			Game game = new Game();
			game.addConnection(connection);
			games.add(game);
		}
	}
	
	private boolean userExist(String name, String password) {
		for (User user : users) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addPlayer(Connection connection, String name, String password) {
		users.add(new User(name, password));
		FileManager.saveUsers(users);
		addToGame(connection);
	}

	@Override
	public void removeConnection(Connection connection) {
	}

	@Override
	public void createShoot(int x, int y) {
	}

	@Override
	public void addLognIn(Connection connection, String name, String password) {
		if (userExist(name, password)) {
			connection.correctUser();
			addToGame(connection);
		} else {
			connection.incorrectUser();
		}
	}
}