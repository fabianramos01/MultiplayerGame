package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import model.MyThread;

public class Connection extends MyThread implements IObservable {

	private static int count = 0;
	private ArrayList<IObserver> observers;
	private DataInputStream input;
	private DataOutputStream output;
	private Socket socket;

	public Connection(Socket socket) {
		super(String.valueOf(count++), 1000);
		System.out.println(count);
		this.socket = socket;
		observers = new ArrayList<>();
		try {
			input = new DataInputStream(this.socket.getInputStream());
			output = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
		start();
	}

	public void sendMessage(String message) {
	}
	
	private void managerRequest(String request) throws IOException {
		switch (Request.valueOf(request)) {
		case LOG_IN:
			break;
		case SIGN_IN:
			break;
		default:
			break;
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
			System.err.println(e.getMessage());
			stop();
		}
	}
	
	private void advise() {
		for (IObserver iObserver : observers) {
		}
	}

	@Override
	public void addObserver(IObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		observers.remove(observer);
	}
}