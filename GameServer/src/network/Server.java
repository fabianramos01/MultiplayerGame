package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.MyThread;

public class Server extends MyThread implements IObserver{

	private static final String SERVER = "Servidor";
	private static final int SLEEP = 1000;
	private ServerSocket serverSocket;
	private ArrayList<Connection> connections;
	private Socket socket;

	public Server(int port) throws IOException {
		super(SERVER, SLEEP);
		serverSocket = new ServerSocket(port);
		connections = new ArrayList<>();
		System.out.println("Server create at port " + port);
		start();
	}

	@Override
	public void execute() {
		try {
			socket = serverSocket.accept();
			System.out.println("New connection!");
			Connection connection = new Connection(socket);
			connection.addObserver(this);
			connections.add(connection);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void update(String name, String message) {
		for (Connection connection : connections) {
			if (!connection.getText().equals(name)) {
				connection.sendMessage(message);
			}
		}
	}
}