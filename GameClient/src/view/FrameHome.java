package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ConstantList;
import controller.Controller;
import model.Asteroid;
import model.Player;
import model.Shoot;
import model.User;

public class FrameHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private Controller listener;
	private PanelGame panelGame;
	private PanelSignIn panelSignIn;
	private JDialog dialogLoad;

	public FrameHome(Controller listener) {
		this.listener = listener;
		setTitle(ConstantList.APP_NAME);
		setLayout(new BorderLayout());
		panelSignIn = new PanelSignIn(listener);
		add(panelSignIn);
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.APP_ICON)).getImage());
		setSize(ConstantList.WIDTH_FRAME_S, ConstantList.HEIGHT_FRAME_S);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void showDialog() {
		setVisible(false);
		dialogLoad = new JDialog();
		dialogLoad.setUndecorated(true);
		ImageIcon image = new ImageIcon(getClass().getResource(ConstantList.LOAD_IMG));
		dialogLoad.setSize(image.getIconWidth(), image.getIconHeight());
		dialogLoad.setLocationRelativeTo(this);
		JLabel label = new JLabel(image);
		dialogLoad.add(label);
		dialogLoad.setVisible(true);
	}

	public void init(Player player, ArrayList<User> users, ArrayList<Shoot> shoots, ArrayList<Asteroid> asteroids) {
		dialogLoad.setVisible(false);
		remove(panelSignIn);
		setResizable(true);
		setSize(ConstantList.WIDTH_FRAME, ConstantList.HEIGHT_FRAME);
		panelGame = new PanelGame(listener, player.getArea(), users, shoots, asteroids);
		add(panelGame, BorderLayout.CENTER);
		setJMenuBar(new MenuBarUser(listener));
		setResizable(false);
		setVisible(true);
	}

	public void paintGame() {
		panelGame.repaint();
		revalidate();
	}

	public String[] getInfo() {
		return panelSignIn.getInfo();
	}

	// SwingUtilities.updateComponentTreeUI(this);
}