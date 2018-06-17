package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.MyThread;

public class Server extends MyThread{

	private static final String SERVER = "Servidor";
	private static final int SLEEP = 1000;
	private ServerSocket serverSocket;
	private ArrayList<Game> games;
	private Socket socket;

	public Server(int port) throws IOException {
		super(SERVER, SLEEP);
		serverSocket = new ServerSocket(port);
		games = new ArrayList<>();
		games.add(new Game());
		System.out.println("Server create at port " + port);
		start();
	}

	@Override
	public void execute() {
		try {
			socket = serverSocket.accept();
			System.out.println("New connection!" + socket.getInetAddress());
			addToGame(socket);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void addToGame(Socket socket) {
		if (games.get(games.size()-1).getSize() < ConstantList.PLAYER_LIM) {
			games.get(games.size()-1).addConnection(socket);
		} else {
			Game game = new Game();
			game.addConnection(socket);
			games.add(game);
		}
	}
}