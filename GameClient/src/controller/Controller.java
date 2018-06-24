package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.Asteroid;
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
		connect();
	}

	private void startTimer() {
		timer = new Timer(100, new ActionListener() {

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
		try {
			client = new Client("", 2000);
			frameHome = new FrameHome(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantList.CONNECTION_ERROR, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void newPlayer() {
		String[] info = frameHome.getInfo();
		if (!areEmptyFields(info)) {
			validatePassword(info);
		} else {
			JOptionPane.showMessageDialog(null, ConstantList.EMPTY_FIELDS, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void validatePassword(String[] info) {
		try {
			if (info[1].equals(info[2])) {
				managerGame = new ManagerGame(info[0]);
				client.sendPlayer(managerGame.getPlayer(), info[1]);
				client.addObserver(this);
				frameHome.showDialog();
			} else {
				JOptionPane.showMessageDialog(null, ConstantList.WRONG_PASSWORD, ConstantList.ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private boolean areEmptyFields(String[] info) {
		for (String string : info) {
			if (string.equals("")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Command.valueOf(e.getActionCommand())) {
		case COMMAND_LOG_ING:
			break;
		case COMMAND_SIGN_IN:
			newPlayer();
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
		}
		client.sendMove(managerGame.getPlayer().getArea().getX(), managerGame.getPlayer().getArea().getY());
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		movePlayer(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			client.createShoot(managerGame.getPlayer().getArea().getX(), managerGame.getPlayer().getArea().getY());
		} else {
			movePlayer(arg0.getKeyCode());
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void startGame() {
		frameHome.init(managerGame.getPlayer(), managerGame.getUsers(), managerGame.getShoots(),
				managerGame.getAsteroids());
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

	@Override
	public void loadAsteroids(ArrayList<Asteroid> asteroids) {
		managerGame.loadAsteroids(asteroids);
	}

	@Override
	public void loseGame() {
		// TODO Auto-generated method stub
	}

	@Override
	public void winGame() {
		// TODO Auto-generated method stub
	}
}