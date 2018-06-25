package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import controller.ConstantList;
import controller.IObservable;
import controller.IObserver;
import model.MyThread;
import model.Player;
import persistence.FileManager;

public class Client extends MyThread implements IObservable{

	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	private IObserver iObserver;
	

	public Client(String ip, int port) throws IOException {
		super("", ConstantList.SLEEP);
		socket = new Socket(ip, port);
		System.out.println("Conexion iniciada");
		output = new DataOutputStream(socket.getOutputStream());
		input = new DataInputStream(socket.getInputStream());
		start();
	}

	private void sendPlayer(Player player, String password) throws IOException {
		output.writeUTF(player.getName());
		output.writeUTF(password);
		output.writeInt(player.getArea().getX());
		output.writeInt(player.getArea().getY());
		output.writeInt(player.getArea().getWidth());
		output.writeInt(player.getArea().getHeight());
	}
	
	public void sendLognIn(Player player, String password) throws IOException {
		output.writeUTF(Request.LOG_IN.toString());
		sendPlayer(player, password);
	}
	
	public void sendSignIn(Player player, String password) throws IOException {
		output.writeUTF(Request.SIGN_IN.toString());
		sendPlayer(player, password);
	}

	private void responseManager(String response) throws IOException {
		switch (Response.valueOf(response)) {
		case PLAYERS_INFO:
			getUsersInfo();
			break;
		case START_GAME:
			iObserver.startGame();
			break;
		case SHOOTS_INFO:
			getShootsFile();
			break;
		case ASTEROIDS_INFO:
			getAsteroidsFile();
			break;
		case YOU_LOSE:
			iObserver.loseGame();
			break;
		case YOU_WIN:
			iObserver.winGame();
			break;
		case INCORRECT_USER:
			iObserver.incorrectUser();
			break;
		case CORRECT_USER:
			iObserver.correctUser();
			break;
		case LIFE:
			iObserver.setLife(input.readInt());
			break;
		}
	}

	private void getShootsFile() throws IOException {
		File file = new File(input.readUTF());
		readFile(file);
		iObserver.loadShoots(FileManager.loadShoots(file));
	}
	
	private void getAsteroidsFile() throws IOException {
		File file = new File(input.readUTF());
		readFile(file);
		iObserver.loadAsteroids(FileManager.loadAsteroids(file));
	}

	private void getUsersInfo() throws IOException {
		File file = new File(input.readUTF());
		readFile(file);
		iObserver.loadUsers(FileManager.loadUsers(file));
		file.delete();
	}
	
	private void readFile(File file) throws IOException {
		byte[] fileArray = new byte[input.readInt()];
		input.readFully(fileArray);
		FileOutputStream fOutputStream = new FileOutputStream(file);
		fOutputStream.write(fileArray);
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

	public void createShoot(int x, int y) {
		try {
			output.writeUTF(Request.CREATE_SHOOT.toString());
			output.writeInt(x);
			output.writeInt(y);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}