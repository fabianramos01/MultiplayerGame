package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.ConstantList;
import model.Player;

public class FrameHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private ActionListener listener;
	private PanelGame panelGame;

	public FrameHome(ActionListener listener) {
		this.listener = listener;
		setTitle(ConstantList.APP_NAME);
		setLayout(new BorderLayout());
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.APP_ICON)).getImage());
		setSize(ConstantList.WIDTH_FRAME, ConstantList.HEIGHT_FRAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public void init(Player player) {
		panelGame = new PanelGame(player.getArea());
		add(panelGame);
		setJMenuBar(new MenuBarUser(listener));
		setVisible(true);
	}

//	SwingUtilities.updateComponentTreeUI(this);		
}