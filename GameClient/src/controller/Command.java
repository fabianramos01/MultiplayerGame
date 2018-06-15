package controller;

import javax.swing.ImageIcon;

public enum Command {

	COMMAND_LOG_ING("COMMAND_LOG_ING", "Ingresar" , ""),
	COMMAND_SIGN_IN("COMMAND_SIGN_IN", "Registrarse" , "");
	
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