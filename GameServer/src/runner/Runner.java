package runner;

import java.io.IOException;

import javax.swing.JOptionPane;

import network.ConstantList;
import network.Server;

public class Runner {

	public static void main(String[] args) {
		int port = Integer.parseInt(JOptionPane.showInputDialog(ConstantList.GET_PORT));
		try {
			new Server(port);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantList.CONNECTION_ERROR, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
}