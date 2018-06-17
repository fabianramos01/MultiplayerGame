package view;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.ConstantList;
import model.Area;
import model.User;

public class PanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imagePlayer;
	private ArrayList<User> users;
	private Area area;

	public PanelGame(KeyListener keyListener, Area area, ArrayList<User> users) {
		this.area = area;
		this.users = users;
		addKeyListener(keyListener);
		setFocusable(true);
		ImageIcon image = new ImageIcon(getClass().getResource(ConstantList.PLAYER_IMG));
		imagePlayer = UtilityList.scaledImage(image, ConstantList.IMG_SIZE, ConstantList.IMG_SIZE);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(ConstantList.AGENCY_FB);
		g.drawImage(imagePlayer.getImage(), area.getX(), area.getY(), this);
		for (User user : users) {
			g.drawImage(imagePlayer.getImage(), user.getPositionX(), user.getPositionY(), this);
			g.drawString(user.getName(), user.getPositionX()+50, user.getPositionY());
		}
	}
}