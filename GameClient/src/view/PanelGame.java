package view;

import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.ConstantList;
import model.Area;

public class PanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imagePlayer;
	private Area area;
	
	public PanelGame(KeyListener keyListener, Area area) {
		this.area = area;
		addKeyListener(keyListener);
		setFocusable(true);
		imagePlayer = new ImageIcon(getClass().getResource(ConstantList.PLAYER_IMG));
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(imagePlayer.getImage(), area.getX(), area.getY(), this);
		repaint();
	}
}