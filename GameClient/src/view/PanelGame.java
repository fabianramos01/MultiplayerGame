package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.ConstantList;
import model.Area;

public class PanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imagePlayer;
	private Area area;
	
	public PanelGame(Area area) {
		this.area = area;
		imagePlayer = new ImageIcon(ConstantList.PLAYER_IMG);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(imagePlayer.getImage(), area.getX(), area.getY(), this);
		repaint();
	}
}