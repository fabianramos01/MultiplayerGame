package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.Direction;
import model.ManagerGame;
import model.Shoot;
import model.User;
import network.Client;
import view.FrameHome;

public class Controller implements ActionListener, KeyListener, IObserver {

	private Client client;
	private ManagerGame managerGame;
	private FrameHome frameHome;
	private Timer timer;

	public Controller() {
		frameHome = new FrameHome(this);
		connect();
	}

	private void startTimer() {
		timer = new Timer(ConstantList.SLEEP, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameHome.paintGame();
			}
		});
		timer.start();
	}

	// private void connect() {
	// String ip = JOptionPane.showInputDialog(ConstantList.GET_IP);
	// String port = JOptionPane.showInputDialog(ConstantList.GET_PORT);
	// if (!port.equals("")) {
	// newPlayer("", Integer.parseInt(port));
	// } else {
	// JOptionPane.showMessageDialog(null, ConstantList.PORT_ERROR,
	// ConstantList.ERROR, JOptionPane.ERROR_MESSAGE);
	// }
	// }

	private void connect() {
		newPlayer("", 2000);
	}

	private void newPlayer(String ip, int port) {
		try {
			String name = JOptionPane.showInputDialog(ConstantList.USER_NAME);
			managerGame = new ManagerGame(name, frameHome.getWidth(), frameHome.getHeight());
			client = new Client(ip, port, managerGame.getPlayer());
			client.addObserver(this);
			frameHome.showDialog();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantList.CONNECTION_ERROR, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Command.valueOf(e.getActionCommand())) {
		case COMMAND_LOG_ING:
			break;
		case COMMAND_SIGN_IN:
			break;
		}
	}

	private void movePlayer(int keycode) {
		if (keycode == KeyEvent.VK_UP) {
			managerGame.move(Direction.UP);
		} else if (keycode == KeyEvent.VK_DOWN) {
			managerGame.move(Direction.DOWN);
		} else if (keycode == KeyEvent.VK_RIGHT) {
			managerGame.move(Direction.RIGHT);
		} else if (keycode == KeyEvent.VK_LEFT) {
			managerGame.move(Direction.LEFT);
		} else if (keycode == KeyEvent.VK_SPACE) {
			client.createShoot(managerGame.getPlayer().getArea().getX(), managerGame.getPlayer().getArea().getY());
		}
		client.sendMove(managerGame.getPlayer().getArea().getX(), managerGame.getPlayer().getArea().getY());
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		movePlayer(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		movePlayer(arg0.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void startGame() {
		frameHome.init(managerGame.getPlayer(), managerGame.getUsers(), managerGame.getShoots());
		startTimer();
	}

	@Override
	public void loadUsers(ArrayList<User> users) {
		managerGame.loadUsers(users);
	}

	@Override
	public void loadShoots(ArrayList<Shoot> shoots) {
		managerGame.loadShoots(shoots);
	}
}