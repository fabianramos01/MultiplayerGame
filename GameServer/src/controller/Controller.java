package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import network.ConstantList;
import network.Server;

public class Controller implements ICObserver {

	public Controller() {
//		int port = Integer.parseInt(JOptionPane.showInputDialog(ConstantList.GET_PORT));
		try {
			new Server(2000);
//			Server server = new Server(port);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), ConstantList.ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void update() {
	}
}