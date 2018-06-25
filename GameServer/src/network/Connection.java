package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import model.Area;
import model.MyThread;
import model.Player;
import persistence.FileManager;

public class Connection extends MyThread implements IObservable {

	private static int count = 0;
	private IObserver iObserver;
	private DataInputStream input;
	private DataOutputStream output;
	private Socket socket;
	private Player player;

	public Connection(Socket socket) {
		super(String.valueOf(count++), ConstantList.GAME_SLEEP);
		this.socket = socket;
		try {
			input = new DataInputStream(this.socket.getInputStream());
			output = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			e.getStackTrace();
		}
		start();
	}

	private void managerRequest(String response) throws IOException {
		switch (Request.valueOf(response)) {
		case LOG_IN:
			logIn();
			break;
		case SIGN_IN:
			signIn();
			break;
		case MOVE_PLAYER:
			setPosition();
			break;
		case CREATE_SHOOT:
			createShoot();
			break;
		}
	}
	
	private void createShoot() throws IOException {
		if (iObserver != null) {
			iObserver.createShoot(input.readInt(), input.readInt());
		}
	}

	private void signIn() throws IOException {
		String password = createPlayer();
		iObserver.addPlayer(this, player.getName(), password);
	}

	private void logIn() throws IOException {
		String password = createPlayer();
		iObserver.addLognIn(this, player.getName(), password);
	}

	private void setPosition() throws IOException {
		player.getArea().setX(input.readInt());
		player.getArea().setY(input.readInt());
	}

	private String createPlayer() throws IOException {
		String name = input.readUTF();
		String password = input.readUTF();
		player = new Player(name, new Area(input.readInt(), input.readInt(), input.readInt(), input.readInt()));
		return password;
	}

	public void sendPlayers(ArrayList<Player> players) {
		try {
			output.writeUTF(Response.PLAYERS_INFO.toString());
			File file = new File(player.getName() + ConstantList.XML);
			FileManager.saveFile(file, players);
			sendFile(file);
			file.delete();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void sendPlayers(File file) {
		try {
			output.writeUTF(Response.PLAYERS_INFO.toString());
			sendFile(file);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void sendShoots(File shootFile) {
		try {
			output.writeUTF(Response.SHOOTS_INFO.toString());
			sendFile(shootFile);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void sendAsteroids(File asFile) {
		try {
			output.writeUTF(Response.ASTEROIDS_INFO.toString());
			sendFile(asFile);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void sendFile(File file) throws IOException {
		byte[] array = new byte[(int) file.length()];
		readFileBytes(file, array);
		output.writeUTF(player.getName() + file.getName());
		output.writeInt(array.length);
		output.write(array);
	}

	private void readFileBytes(File file, byte[] array) throws IOException {
		FileInputStream fInputStream = new FileInputStream(file);
		fInputStream.read(array);
		fInputStream.close();
	}

	public void startMessage() {
		try {
			output.writeUTF(Response.START_GAME.toString());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void incorrectUser() {
		try {
			output.writeUTF(Response.INCORRECT_USER.toString());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void correctUser() {
		try {
			output.writeUTF(Response.CORRECT_USER.toString());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	public void execute() {
		String request;
		try {
			request = input.readUTF();
			if (request != null) {
				managerRequest(request);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage() + "-" + (player != null ? player.getName() : "--"));
			removeConnection();
			stop();
		}
	}
	
	private void removeConnection() {
		if (iObserver != null) {
			iObserver.removeConnection(this);
		}
	}
	
	

	@Override
	public void addObserver(IObserver iObserver) {
		this.iObserver = iObserver;
	}

	@Override
	public void removeObserver() {
		iObserver = null;
	}

	public Player getPlayer() {
		return player;
	}

	public void loseMessage() {
		try {
			output.writeUTF(Response.YOU_LOSE.toString());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void winMessage() {
		try {
			output.writeUTF(Response.YOU_WIN.toString());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void sendLife() {
		try {
			output.writeUTF(Response.LIFE.toString());
			output.writeInt(player.getLife());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}