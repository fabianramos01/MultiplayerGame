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
			client.addObserver(this);
			frameHome = new FrameHome(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantList.CONNECTION_ERROR, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void newPlayer() {
		String[] info = frameHome.signInInfo();
		if (!areEmptyFields(info)) {
			validatePassword(info);
		} else {
			JOptionPane.showMessageDialog(frameHome, ConstantList.EMPTY_FIELDS, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void validatePassword(String[] info) {
		try {
			if (info[1].equals(info[2])) {
				managerGame = new ManagerGame(info[0]);
				client.sendSignIn(managerGame.getPlayer(), info[1]);
				frameHome.showDialog();
			} else {
				JOptionPane.showMessageDialog(frameHome, ConstantList.WRONG_PASSWORD, ConstantList.ERROR,
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

	private void lognIn() {
		String[] info = frameHome.lognInInfo();
		try {
			if (!areEmptyFields(info)) {
				managerGame = new ManagerGame(info[0]);
				client.sendLognIn(managerGame.getPlayer(), info[1]);
			} else {
				JOptionPane.showMessageDialog(frameHome, ConstantList.EMPTY_FIELDS, ConstantList.ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Command.valueOf(e.getActionCommand())) {
		case COMMAND_LOGN_IN:
			lognIn();
			break;
		case COMMAND_SIGN_IN:
			newPlayer();
			break;
		case COMMAND_SIGN_IN_PANEL:
			frameHome.panelSignIn();
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
		timer.stop();
		frameHome.loseMessage();
	}

	@Override
	public void winGame() {
		timer.stop();
		frameHome.winMessage();
	}

	@Override
	public void correctUser() {
		frameHome.showDialog();
	}

	@Override
	public void incorrectUser() {
		JOptionPane.showMessageDialog(frameHome, ConstantList.INVALID_USER, ConstantList.ERROR,
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void setLife(int life) {
		frameHome.setLife(life);
	}
}