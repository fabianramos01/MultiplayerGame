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
	private LabelBack labelBack;
	private JDialog dialogLoad;
	private PanelLife panelLife;

	public FrameHome(Controller listener) {
		this.listener = listener;
		setTitle(ConstantList.APP_NAME);
		setLayout(new BorderLayout());
		labelBack = new LabelBack(listener);
		add(labelBack);
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.APP_ICON)).getImage());
		setSize(ConstantList.WIDTH_FRAME_L, ConstantList.HEIGHT_FRAME_L);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void panelSignIn() {
		setResizable(true);
		setSize(ConstantList.WIDTH_FRAME_S, ConstantList.HEIGHT_FRAME_S);
		labelBack.signIn();
		setResizable(false);
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
		remove(labelBack);
		setResizable(true);
		setSize(ConstantList.WIDTH_FRAME, ConstantList.HEIGHT_FRAME);
		panelLife = new PanelLife();
		add(panelLife, BorderLayout.NORTH);
		panelGame = new PanelGame(listener, player.getArea(), users, shoots, asteroids);
		add(panelGame, BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);
	}
	
	public void setLife(int life) {
		panelLife.setLife(life);

	}

	public void paintGame() {
		panelGame.repaint();
	}
	
	public void loseMessage() {
		setMessage(ConstantList.LOSE_IMG);
	}
	
	public void winMessage() {
		setMessage(ConstantList.WIN_IMG);
	}
	
	private void setMessage(String imagePath) {
		JDialog dialog = new JDialog(this);
		ImageIcon image = new ImageIcon(getClass().getResource(imagePath));
		dialog.setSize(image.getIconWidth(), image.getIconHeight());
		dialog.setLocationRelativeTo(this);
		dialog.add(new JLabel(image));
		dialog.setResizable(false);
		dialog.setVisible(true);
	}

	public String[] signInInfo() {
		return labelBack.getInfoSignIn();
	}
	
	public String[] lognInInfo() {
		return labelBack.getInfoLognIn();
	}

	// SwingUtilities.updateComponentTreeUI(this);
}