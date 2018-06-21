package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import controller.ConstantList;
import controller.IObservable;
import controller.IObserver;
import model.MyThread;
import model.Player;
import model.User;
import persistence.FileManager;

public class Client extends MyThread implements IObservable{

	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	private IObserver iObserver;

	public Client(String ip, int port, Player player) throws IOException {
		super("", ConstantList.SLEEP);
		socket = new Socket(ip, port);
		System.out.println("Conexion iniciada");
		output = new DataOutputStream(socket.getOutputStream());
		input = new DataInputStream(socket.getInputStream());
		createPlayer(player);
		start();
	}

	private void createPlayer(Player player) throws IOException {
		output.writeUTF(Request.SIGN_IN.toString());
		output.writeUTF(player.getName());
		output.writeInt(player.getArea().getX());
		output.writeInt(player.getArea().getY());
		output.writeInt(player.getArea().getWidth());
		output.writeInt(player.getArea().getHeight());
	}

	private void responseManager(String response) throws IOException {
		switch (Response.valueOf(response)) {
		case PLAYERS_INFO:
			getUsersInfo();
			break;
		case START_GAME:
			iObserver.startGame();
			break;
		}
	}

	private void getUsersInfo() throws IOException {
		File file = new File(input.readUTF());
		byte[] fileArray = new byte[input.readInt()];
		input.readFully(fileArray);
		writeFile(file, fileArray);
		ArrayList<User> players = FileManager.loadUsers(file);
		file.delete();
		iObserver.loadUsers(players);
	}

	private void writeFile(File file, byte[] array) throws IOException {
		FileOutputStream fOutputStream = new FileOutputStream(file);
		fOutputStream.write(array);
		fOutputStream.close();
	}

	public void sendMove(int x, int y) {
		try {
			output.writeUTF(Request.MOVE_PLAYER.toString());
			output.writeInt(x);
			output.writeInt(y);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
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
			System.err.println(e.getMessage());
			stop();
		}
	}

	@Override
	public void addObserver(IObserver iObserver) {
		this.iObserver = iObserver;
	}
}