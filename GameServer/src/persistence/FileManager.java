package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.Asteroid;
import model.Player;
import model.Shoot;
import model.User;
import network.ConstantList;

public class FileManager {

	public static void saveFile(File file, ArrayList<Player> players) {
		Element root = new Element(ConstantList.PLAYERS);
		Document doc = new Document(root);
		for (Player actual : players) {
			Element player = new Element(ConstantList.PLAYER);
			Element name = new Element(ConstantList.NAME).setText(actual.getName());
			Element positionX = new Element(ConstantList.X).setText(String.valueOf(actual.getArea().getX()));
			Element positionY = new Element(ConstantList.Y).setText(String.valueOf(actual.getArea().getY()));
			player.addContent(name);
			player.addContent(positionX);
			player.addContent(positionY);
			root.addContent(player);
		}
		try {
			FileWriter fileWriter = new FileWriter(file);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void saveShootFile(File file, ArrayList<Shoot> shoots) {
		Element root = new Element(ConstantList.PLAYERS);
		Document doc = new Document(root);
		for (Shoot shoot : shoots) {
			Element element = new Element(ConstantList.SHOOT);
			Element name = new Element(ConstantList.ID).setText(String.valueOf(shoot.getId()));
			Element positionX = new Element(ConstantList.X).setText(String.valueOf(shoot.getX()));
			Element positionY = new Element(ConstantList.Y).setText(String.valueOf(shoot.getY()));
			element.addContent(name);
			element.addContent(positionX);
			element.addContent(positionY);
			root.addContent(element);
		}
		try {
			FileWriter fileWriter = new FileWriter(file);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void saveAsteroidFile(File file, ArrayList<Asteroid> asteroids) {
		Element root = new Element(ConstantList.PLAYERS);
		Document doc = new Document(root);
		for (Asteroid asteroid : asteroids) {
			Element element = new Element(ConstantList.ASTEROID);
			Element name = new Element(ConstantList.ID).setText(String.valueOf(asteroid.getId()));
			Element positionX = new Element(ConstantList.X).setText(String.valueOf(asteroid.getX()));
			Element positionY = new Element(ConstantList.Y).setText(String.valueOf(asteroid.getY()));
			Element type = new Element(ConstantList.TYPE).setText(String.valueOf(asteroid.getType()));
			element.addContent(name);
			element.addContent(positionX);
			element.addContent(positionY);
			element.addContent(type);
			root.addContent(element);
		}
		try {
			FileWriter fileWriter = new FileWriter(file);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void saveUsers(ArrayList<User> users) {
		Element root = new Element(ConstantList.PLAYERS);
		Document doc = new Document(root);
		for (User actual : users) {
			Element player = new Element(ConstantList.PLAYER);
			Element name = new Element(ConstantList.NAME).setText(actual.getName());
			Element password = new Element(ConstantList.PASSWORD).setText(actual.getPassword());
			player.addContent(name);
			player.addContent(password);
			root.addContent(player);
		}
		try {
			FileWriter fileWriter = new FileWriter(new File(ConstantList.USERS_FILE));
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static ArrayList<User> getUsers(File file) {
		ArrayList<User> users = new ArrayList<User>();
		SAXBuilder builder = new SAXBuilder();
	    try {
	        Document document = (Document) builder.build(file);
	        Element rootNode = (Element) ((org.jdom2.Document) document).getRootElement();
	        List<Element> userFileList = ((org.jdom2.Element) rootNode).getChildren(ConstantList.PLAYER);
	        for (Element player : userFileList) {
	        	String name = player.getChildTextTrim(ConstantList.NAME);
	        	String password = player.getChildTextTrim(ConstantList.PASSWORD);
	            users.add(new User(name, password));
	        }
	    }catch (IOException io) {
	        System.err.println(io.getMessage());
	    }catch (JDOMException jdomex) {
	        System.err.println(jdomex.getMessage());
	    }
		return users;
	}
}