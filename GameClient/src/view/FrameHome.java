package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.ConstantList;

public class FrameHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private ActionListener listener;

	public FrameHome(ActionListener listener) {
		this.listener = listener;
		setTitle(ConstantList.APP_NAME);
		setLayout(new BorderLayout());
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.APP_ICON)).getImage());
		setSize(ConstantList.WIDTH_FRAME, ConstantList.HEIGHT_FRAME);
		init();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void init() {
		setJMenuBar(new MenuBarUser(listener));
	}

//	SwingUtilities.updateComponentTreeUI(this);		
}