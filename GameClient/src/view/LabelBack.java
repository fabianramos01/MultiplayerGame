package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.ConstantList;

public class LabelBack extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	private ActionListener listener;
	private PanelLognIn panelLognIn;
	private PanelSignIn panelSignIn;

	public LabelBack(ActionListener listener) {
		this.listener = listener;
		setIcon(new ImageIcon(getClass().getResource(ConstantList.BACKGROUND)));
		setLayout(new BorderLayout());
		panelLognIn = new PanelLognIn(listener);
		add(panelLognIn, BorderLayout.CENTER);
	}
	
	public void signIn() {
		remove(panelLognIn);
		panelSignIn = new PanelSignIn(listener);
		add(panelSignIn, BorderLayout.CENTER);
		revalidate();
	}
	
	public String[] getInfoLognIn() {
		return panelLognIn.getInfo();
	}
	
	public String[] getInfoSignIn() {
		return panelSignIn.getInfo();
	}
}