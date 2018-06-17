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
import model.Area;
import model.Direction;
import model.MyThread;
import model.Player;
import model.User;
import persistence.FileManager;

public class ManagerPlayer extends MyThread implements IObservable {

	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	private Player player;
	private ArrayList<User> users;
	private IObserver iObserver;

	public ManagerPlayer(String ip, int port, String name, int width, int height) throws IOException {
		super("", ConstantList.SLEEP);
		socket = new Socket(ip, port);
		System.out.println("Conexion iniciada");
		output = new DataOutputStream(socket.getOutputStream());
		input = new DataInputStream(socket.getInputStream());
		createPlayer(name, width, height);
		users = new ArrayList<>();
		start();
	}

	private void createPlayer(String name, int width, int height) throws IOException {
		player = new Player(name, new Area((int) (Math.random() * (width - ConstantList.IMG_SIZE)),
				(int) (height - ConstantList.IMG_SIZE), width, height));
		output.writeUTF(Request.SIGN_IN.toString());
		output.writeUTF(player.getName());
		output.writeInt(player.getArea().getX());
		output.writeInt(player.getArea().getY());
		output.writeInt(width);
		output.writeInt(height);
	}

	private void responseManager(String response) throws IOException {
		switch (Response.valueOf(response)) {
		case PLAYERS_INFO:
			getUsersInfo();
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
		loadUsers(players);
	}

	public void loadUsers(ArrayList<User> players) {
		if (users.isEmpty()) {
			for (User user : players) {
				users.add(user);
			}
		} else {
			for (User user : players) {
				setInfo(user);
			}
		}
	}

	private void setInfo(User player) {
		for (User user : users) {
			if (user.getName().equals(player.getName())) {
				user.setPositionX(player.getPositionX());
				user.setPositionY(player.getPositionY());
				break;
			}
		}
	}

	private void writeFile(File file, byte[] array) throws IOException {
		FileOutputStream fOutputStream = new FileOutputStream(file);
		fOutputStream.write(array);
		fOutputStream.close();
	}

	public void move(Direction direction) {
		player.move(direction);
		try {
			output.writeUTF(Request.MOVE_PLAYER.toString());
			output.writeInt(player.getArea().getX());
			output.writeInt(player.getArea().getY());
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

	public Player getPlayer() {
		return player;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	@Override
	public void addObserver(IObserver iObserver) {
		this.iObserver = iObserver;

	}
}