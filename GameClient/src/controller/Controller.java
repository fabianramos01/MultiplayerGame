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

public class Controller implements ActionListener, KeyListener {

	private ManagerPlayer managerPlayer;
	private FrameHome frameHome;
	private Timer timer;

	public Controller() {
		frameHome = new FrameHome(this);
		connect();
		startTimer();
	}

	private void startTimer() {
		timer = new Timer(ConstantList.TIMER_TIME, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (managerPlayer != null) {
				}
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
			JOptionPane.showMessageDialog(null, ConstantList.PORT_ERROR, ConstantList.ERROR,
					JOptionPane.ERROR_MESSAGE);			
		}	
		frameHome.setVisible(true);
	}
	
	private void newPlayer(String ip, int port) {
		try {
			managerPlayer = new ManagerPlayer(ip, port);
			frameHome.init(managerPlayer.getPlayer());
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

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			managerPlayer.move(Direction.UP);
		} else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {			
			managerPlayer.move(Direction.DOWN);
		} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			managerPlayer.move(Direction.RIGHT);
		} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			managerPlayer.move(Direction.LEFT);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}