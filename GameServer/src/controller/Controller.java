package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import network.ConstantList;
import network.Server;

public class Controller implements ICObserver {

	private Server server;
	
	public Controller() {
		int port = Integer.parseInt(JOptionPane.showInputDialog(ConstantList.GET_PORT));
		try {
			server = new Server(port);
			server.addObserver(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), ConstantList.ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void update() {
	}
}