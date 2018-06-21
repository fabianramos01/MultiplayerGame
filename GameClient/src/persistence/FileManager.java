package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import controller.ConstantList;
import model.Shoot;
import model.User;

public class FileManager {

	public static ArrayList<User> loadUsers(File file) {
		ArrayList<User> users = new ArrayList<User>();
		SAXBuilder builder = new SAXBuilder();
	    try {
	        Document document = (Document) builder.build(file);
	        Element rootNode = (Element) ((org.jdom2.Document) document).getRootElement();
	        List<Element> userFileList = ((org.jdom2.Element) rootNode).getChildren(ConstantList.PLAYER);
	        User user;
	        for (Element player : userFileList) {
	        	String name = player.getChildTextTrim(ConstantList.NAME);
	        	int x = Integer.parseInt(player.getChildTextTrim(ConstantList.X));
	        	int y = Integer.parseInt(player.getChildTextTrim(ConstantList.Y));
	        	user = new User(name, x, y);
	            users.add(user);
	        }
	    }catch (IOException io) {
	        System.err.println(io.getMessage());
	    }catch (JDOMException jdomex) {
	        System.err.println(jdomex.getMessage());
	    }
		return users;
	}
	
	public static ArrayList<Shoot> loadShoots(File file) {
		ArrayList<Shoot> shoots = new ArrayList<>();
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = (Document) builder.build(file);
			Element rootNode = (Element) ((org.jdom2.Document) document).getRootElement();
			List<Element> userFileList = ((org.jdom2.Element) rootNode).getChildren(ConstantList.SHOOT);
			Shoot shoot;
			for (Element element : userFileList) {
				int id = Integer.parseInt(element.getChildTextTrim(ConstantList.ID));
				int x = Integer.parseInt(element.getChildTextTrim(ConstantList.X));
				int y = Integer.parseInt(element.getChildTextTrim(ConstantList.Y));
				shoot = new Shoot(id, x, y);
				shoots.add(shoot);
			}
		}catch (IOException io) {
			System.err.println(io.getMessage());
		}catch (JDOMException jdomex) {
			System.err.println(jdomex.getMessage());
		}
		return shoots;
	}
}