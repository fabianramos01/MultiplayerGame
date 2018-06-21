package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ConstantList;
import controller.Controller;
import model.Player;
import model.Shoot;
import model.User;

public class FrameHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private Controller listener;
	private PanelGame panelGame;
	private JDialog dialogLoad;

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
	
	public void showDialog() {
		dialogLoad = new JDialog();
		dialogLoad.setUndecorated(true);
		ImageIcon image = new ImageIcon(getClass().getResource(ConstantList.LOAD_IMG));
		dialogLoad.setSize(image.getIconWidth(), image.getIconHeight());
		dialogLoad.setLocationRelativeTo(this);
		JLabel label = new JLabel(image);
		dialogLoad.add(label);
		dialogLoad.setVisible(true);
	}

	public void init(Player player, ArrayList<User> users, ArrayList<Shoot> shoots) {
		dialogLoad.setVisible(false);
		panelGame = new PanelGame(listener, player.getArea(), users, shoots);
		add(panelGame, BorderLayout.CENTER);
		setJMenuBar(new MenuBarUser(listener));
		setVisible(true);
	}
	
	public void paintGame() {
		panelGame.repaint();
		revalidate();
	}

//	SwingUtilities.updateComponentTreeUI(this);		
}