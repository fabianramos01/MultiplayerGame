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
import model.User;
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
		System.out.println(count);
		this.socket = socket;
		try {
			input = new DataInputStream(this.socket.getInputStream());
			output = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
		start();
	}

	private void managerRequest(String response) throws IOException {
		switch (Request.valueOf(response)) {
		case LOG_IN:
			break;
		case SIGN_IN:
			createPlayer();
			break;
		case MOVE_PLAYER:
			setPosition();
			break;
		default:
			break;
		}
	}

	private void setPosition() throws IOException {
		player.getArea().setX(input.readInt());
		player.getArea().setY(input.readInt());
	}

	private void createPlayer() throws IOException {
		player = new Player(input.readUTF(),
				new Area(input.readInt(), input.readInt(), input.readInt(), input.readInt()));
		advise();
	}
	
	public void sendPlayers(ArrayList<User> players) {
		try {
			output.writeUTF(Response.PLAYERS_INFO.toString());
			FileManager.saveFile(player.getName() + ConstantList.XML, players);
			File file = new File(player.getName() + ConstantList.XML);
			byte[] array = new byte[(int) file.length()];
			readFileBytes(file, array);
			output.writeUTF(file.getName());
			output.writeInt(array.length);
			output.write(array);
			file.delete();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void readFileBytes(File file, byte[] array) throws IOException {
		FileInputStream fInputStream = new FileInputStream(file);
		fInputStream.read(array);
		fInputStream.close();
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
			System.err.println(e.getMessage() + "-" + player.getName());
			stop();
			iObserver.removeConnection(this);
		}
	}

	private void advise() {
		iObserver.update(player.getName());
		iObserver.addConnection(this);
	}

	@Override
	public void addObserver(IObserver iObserver) {
		this.iObserver = iObserver;
	}

	@Override
	public void removeObserver(IObserver observer) {
		iObserver = null;
	}
	
	public Player getPlayer() {
		return player;
	}
}