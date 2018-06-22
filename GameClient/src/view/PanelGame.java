package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import controller.ConstantList;
import model.Area;
import model.Shoot;
import model.User;

public class PanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imagePlayer;
	private ImageIcon imageShoot;
	private ImageIcon background;
	private ArrayList<User> users;
	private ArrayList<Shoot> shoots;
	private Area area;

	public PanelGame(KeyListener keyListener, Area area, ArrayList<User> users, ArrayList<Shoot> shoots) {
		this.area = area;
		this.users = users;
		this.shoots = shoots;
		addKeyListener(keyListener);
		setFocusable(true);
		ImageIcon image = new ImageIcon(getClass().getResource(ConstantList.PLAYER_IMG));
		imagePlayer = UtilityList.scaledImage(image, ConstantList.IMG_SIZE, ConstantList.IMG_SIZE);
		image = new ImageIcon(getClass().getResource(ConstantList.SHOOT_IMG));
		imageShoot = UtilityList.scaledImage(image, ConstantList.SHOOT_WIDHT, ConstantList.SHOOT_HEIGTH);
		background = new ImageIcon(getClass().getResource(ConstantList.BACKGROUND));;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(ConstantList.AGENCY_FB);
		g.drawImage(background.getImage(), 0, 0, getSize().width, getSize().height, this);
		g.drawImage(imagePlayer.getImage(), area.getX(), area.getY(), this);
		g.setColor(Color.WHITE);
		for (User user : users) {
			g.drawImage(imagePlayer.getImage(), user.getPositionX(), user.getPositionY(), this);
			g.drawString(user.getName(), user.getPositionX() + 50, user.getPositionY());
		}
		for (Shoot shoot : shoots) {
			g.drawImage(imageShoot.getImage(), shoot.getX() + 100, shoot.getY() - ConstantList.SHOOT_HEIGTH, this);
		}
	}
}