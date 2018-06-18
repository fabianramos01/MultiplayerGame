package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.Direction;
import network.ManagerPlayer;
import view.FrameHome;

public class Controller implements ActionListener, KeyListener, IObserver {

	private ManagerPlayer managerPlayer;
	private FrameHome frameHome;

	public Controller() {
		frameHome = new FrameHome(this);
		connect();
	}
	
	private void startTimer() {
		Timer timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frameHome.paintUsers();
			}
		});
		timer.start();
	}

	private void connect() {
		frameHome.setVisible(false);
		String ip = JOptionPane.showInputDialog(ConstantList.GET_IP);
		String port = JOptionPane.showInputDialog(ConstantList.GET_PORT);
		if (!port.equals("")) {
			newPlayer(ip, Integer.parseInt(port));
		} else {
			JOptionPane.showMessageDialog(null, ConstantList.PORT_ERROR, ConstantList.ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void newPlayer(String ip, int port) {
		try {
			String name = JOptionPane.showInputDialog(ConstantList.USER_NAME);
			managerPlayer = new ManagerPlayer(ip, port, name, frameHome.getWidth(), frameHome.getHeight());
			managerPlayer.addObserver(this);
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
		default:
			break;
		}
	}

	private void movePlayer(int keycode) {
		if (keycode == KeyEvent.VK_UP) {
			managerPlayer.move(Direction.UP);
		} else if (keycode == KeyEvent.VK_DOWN) {
			managerPlayer.move(Direction.DOWN);
		} else if (keycode == KeyEvent.VK_RIGHT) {
			managerPlayer.move(Direction.RIGHT);
		} else if (keycode == KeyEvent.VK_LEFT) {
			managerPlayer.move(Direction.LEFT);
		}
		frameHome.paintUsers();
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
		frameHome.init(managerPlayer.getPlayer(), managerPlayer.getUsers());
		startTimer();
	}
}