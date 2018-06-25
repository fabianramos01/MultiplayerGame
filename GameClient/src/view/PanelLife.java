package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.ConstantList;

public class PanelLife extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int life;
	
	public PanelLife() {
		life = ConstantList.LIFE;
		repaint();
	}
	
	public void setLife(int life) {
		this.life = life;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.fillRect(5, 30, life*110/ConstantList.LIFE, 110);
		g.setColor(Color.BLACK);
		g.drawRect(5, 30, ConstantList.LIFE, 110);
		g.drawString(String.valueOf(life), 5, 15);
	}
}
