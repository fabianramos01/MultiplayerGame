package view;

import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.Command;

public class MenuBarUser extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	private JMenu userOption;
	private JMenuItem newUser;
	
	public MenuBarUser(ActionListener listener) {
		userOption = new JMenu(".");
		newUser = new JMenuItem(Command.COMMAND_LOG_ING.getTitle());
		newUser.addActionListener(listener);
		newUser.setActionCommand(Command.COMMAND_LOG_ING.getCommand());
		newUser.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		userOption.add(newUser);
		add(userOption);
	}
}