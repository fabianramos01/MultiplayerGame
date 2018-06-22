package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Command;
import controller.ConstantList;

public class PanelSignIn extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField fieldName;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldC;

	public PanelSignIn(ActionListener listener) {
		setLayout(new GridLayout(7, 1));
		add(UtilityList.createJLabel(ConstantList.USER_NAME, ConstantList.AGENCY_FB, ConstantList.APP_COLOR));
		fieldName = new JTextField();
		fieldName.setFont(ConstantList.AGENCY_FB);
		fieldName.setHorizontalAlignment(JTextField.CENTER);
		add(fieldName);
		add(UtilityList.createJLabel(ConstantList.PASSWORD, ConstantList.AGENCY_FB, ConstantList.APP_COLOR));
		passwordField = new JPasswordField();
		passwordField.setFont(ConstantList.AGENCY_FB);
		passwordField.setHorizontalAlignment(JPasswordField.CENTER);
		add(passwordField);
		add(UtilityList.createJLabel(ConstantList.CONFIRM_PASSWORD, ConstantList.AGENCY_FB, ConstantList.APP_COLOR));
		passwordFieldC = new JPasswordField();
		passwordFieldC.setFont(ConstantList.AGENCY_FB);
		passwordFieldC.setHorizontalAlignment(JPasswordField.CENTER);
		add(passwordFieldC);
		add(UtilityList.createJButtonText(Command.COMMAND_SIGN_IN.getCommand(), Command.COMMAND_SIGN_IN.getTitle(),
				Color.BLACK, ConstantList.APP_COLOR, ConstantList.AGENCY_FB, listener));
	}
	
	public String[] getInfo() {
		String password = "";
		for (int i = 0; i < passwordField.getPassword().length; i++) {
			password+= passwordField.getPassword()[i];
		}
		String passwordC = "";
		for (int i = 0; i < passwordFieldC.getPassword().length; i++) {
			passwordC+= passwordFieldC.getPassword()[i];
		}
		String[] info = {fieldName.getText(), password, passwordC};
		return info;
	}
}