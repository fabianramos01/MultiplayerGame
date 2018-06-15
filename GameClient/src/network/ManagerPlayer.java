package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import controller.ConstantList;
import model.Area;
import model.Direction;
import model.MyThread;
import model.Player;

public class ManagerPlayer extends MyThread {

	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	private Player player;

	public ManagerPlayer(String ip, int port) throws IOException {
		super("", ConstantList.SLEEP);
		System.out.println("Conexion iniciada");
		socket = new Socket(ip, port);
		output = new DataOutputStream(socket.getOutputStream());
		input = new DataInputStream(socket.getInputStream());
		player = new Player("", new Area(0, 0, 1000, 1000), null, null);
		start();
	}

	private void responseManager(String response) throws IOException {
		switch (Request.valueOf(response)) {
		case LOG_IN:
			break;
		case SIGN_IN:
			break;
		}
	}
	
	public void logIn(String userName, String password) throws IOException {
		output.writeUTF(Request.LOG_IN.toString());
		output.writeUTF(userName);
		output.writeUTF(password);
		if (input.readUTF().equals(Request.LOG_IN.toString())) {
			if (input.readBoolean()) {
				
			}
		}
	}
	
	public void move(Direction direction) {
		player.move(direction);
	}
	
	@Override
	public void execute() {
		String response;
		try {
			response = input.readUTF();
			if (response != null) {
				responseManager(response);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			stop();
		}
	}
	
	public Player getPlayer() {
		return player;
	}
}