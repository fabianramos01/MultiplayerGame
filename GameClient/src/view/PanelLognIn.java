package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Command;
import controller.ConstantList;

public class PanelLognIn extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField fieldName;
	private JPasswordField passwordField;
	
	public PanelLognIn(ActionListener listener) {
		setLayout(new BorderLayout());
		setOpaque(false);
		add(new JLabel(new ImageIcon(getClass().getResource(ConstantList.APP_ICON))), BorderLayout.NORTH);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.add(UtilityList.createJLabel(ConstantList.USER_NAME, ConstantList.AGENCY_FB, ConstantList.APP_COLOR));
		fieldName = new JTextField(ConstantList.TEXTFIELD_SIZE);
		fieldName.setFont(ConstantList.AGENCY_FB);
		fieldName.setHorizontalAlignment(JTextField.CENTER);
		panel.add(fieldName);
		panel.add(UtilityList.createJLabel(ConstantList.PASSWORD, ConstantList.AGENCY_FB, ConstantList.APP_COLOR));
		passwordField = new JPasswordField(ConstantList.TEXTFIELD_SIZE);
		passwordField.setFont(ConstantList.AGENCY_FB);
		passwordField.setHorizontalAlignment(JPasswordField.CENTER);
		panel.add(passwordField);
		panel.add(UtilityList.createJButtonText(Command.COMMAND_LOGN_IN.getCommand(), Command.COMMAND_LOGN_IN.getTitle(),
				Color.BLACK, ConstantList.APP_COLOR, ConstantList.AGENCY_FB, listener));
		panel.add(UtilityList.createJButtonText(Command.COMMAND_SIGN_IN_PANEL.getCommand(),
				Command.COMMAND_SIGN_IN_PANEL.getTitle(), Color.BLACK, ConstantList.APP_COLOR, ConstantList.AGENCY_FB,
				listener));
		add(panel, BorderLayout.CENTER);
	}

	public String[] getInfo() {
		String password = "";
		for (int i = 0; i < passwordField.getPassword().length; i++) {
			password += passwordField.getPassword()[i];
		}
		String[] info = { fieldName.getText(), password };
		return info;
	}
}