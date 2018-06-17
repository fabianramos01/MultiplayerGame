package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.ConstantList;
import controller.Controller;
import model.Player;
import model.User;

public class FrameHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private Controller listener;
	private PanelGame panelGame;

	public FrameHome(Controller listener) {
		this.listener = listener;
		setTitle(ConstantList.APP_NAME);
		setLayout(new BorderLayout());
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.APP_ICON)).getImage());
		setSize(ConstantList.WIDTH_FRAME, ConstantList.HEIGHT_FRAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public void init(Player player, ArrayList<User> users) {
		panelGame = new PanelGame(listener, player.getArea(), users);
		add(panelGame, BorderLayout.CENTER);
		setJMenuBar(new MenuBarUser(listener));
		setVisible(true);
	}
	
	public void paintUsers() {
		panelGame.repaint();
	}

//	SwingUtilities.updateComponentTreeUI(this);		
}