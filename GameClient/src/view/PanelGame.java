package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.ConstantList;
import model.Area;
import model.Asteroid;
import model.Shoot;
import model.User;

public class PanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imagePlayer;
	private ImageIcon imageShoot;
	private ImageIcon background;
	private ImageIcon asteroidO;
	private ImageIcon asteroidT;
	private ImageIcon asteroidTh;
	private ArrayList<User> users;
	private ArrayList<Shoot> shoots;
	private ArrayList<Asteroid> asteroids;
	private Area area;

	public PanelGame(KeyListener keyListener, Area area, ArrayList<User> users, ArrayList<Shoot> shoots,
			ArrayList<Asteroid> asteroids) {
		this.area = area;
		this.users = users;
		this.shoots = shoots;
		this.asteroids = asteroids;
		addKeyListener(keyListener);
		setFocusable(true);
		ImageIcon image = new ImageIcon(getClass().getResource(ConstantList.PLAYER_IMG));
		imagePlayer = UtilityList.scaledImage(image, ConstantList.IMG_SIZE, ConstantList.IMG_SIZE);
		image = new ImageIcon(getClass().getResource(ConstantList.SHOOT_IMG));
		imageShoot = UtilityList.scaledImage(image, ConstantList.SHOOT_WIDHT, ConstantList.SHOOT_HEIGTH);
		image = new ImageIcon(getClass().getResource(ConstantList.ASTEROID_IMG));
		asteroidO = UtilityList.scaledImage(image, ConstantList.AST_O_SIZE, ConstantList.AST_O_SIZE);
		asteroidT = UtilityList.scaledImage(image, ConstantList.AST_T_SIZE, ConstantList.AST_T_SIZE);
		asteroidTh = UtilityList.scaledImage(image, ConstantList.AST_TH_SIZE, ConstantList.AST_TH_SIZE);
		background = new ImageIcon(getClass().getResource(ConstantList.BACKGROUND));
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
		for (int i = 0; i < shoots.size(); i++) {
			g.drawImage(imageShoot.getImage(), shoots.get(i).getX() + 60,
					shoots.get(i).getY() - ConstantList.SHOOT_HEIGTH, this);
		}
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).getType() == ConstantList.MAX_TYPE) {
				g.drawImage(asteroidO.getImage(), asteroids.get(i).getX(), asteroids.get(i).getY(), this);
			} else if (asteroids.get(i).getType() == ConstantList.MIN_TYPE) {
				g.drawImage(asteroidT.getImage(), asteroids.get(i).getX(), asteroids.get(i).getY(), this);
			} else {
				g.drawImage(asteroidTh.getImage(), asteroids.get(i).getX(), asteroids.get(i).getY(), this);
			}
		}
	}
}