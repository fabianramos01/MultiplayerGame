package controller;

import javax.swing.ImageIcon;

public enum Command {

	COMMAND_LOGN_IN("COMMAND_LOGN_IN", "Ingresar" , ""),
	COMMAND_SIGN_IN("COMMAND_SIGN_IN", "Registrarse" , ""),
	COMMAND_SIGN_IN_PANEL("COMMAND_SIGN_IN_PANEL", "Registrarse" , "");
	
	private String command;
	private String title;
	private String pathImg;
	
	private Command(String command, String title, String pathImg) {
		this.command = command;
		this.title = title;
		this.pathImg = pathImg;
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getTitle() {
		return title;
	}
	
	public ImageIcon getImg() {
		return new ImageIcon(getClass().getResource(pathImg));
	}
}