package persistence;

import java.io.FileWriter;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.Shoot;
import model.User;
import network.ConstantList;

public class FileManager {

	public static void saveFile(String path, ArrayList<User> users) {
		Element root = new Element(ConstantList.PLAYERS);
		Document doc = new Document(root);
		for (User user : users) {
			Element player = new Element(ConstantList.PLAYER);
			Element name = new Element(ConstantList.NAME).setText(user.getName());
			Element positionX = new Element(ConstantList.X).setText(String.valueOf(user.getPositionX()));
			Element positionY = new Element(ConstantList.Y).setText(String.valueOf(user.getPositionY()));
			player.addContent(name);
			player.addContent(positionX);
			player.addContent(positionY);
			root.addContent(player);
		}
		try {
			FileWriter fileWriter = new FileWriter(path);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void saveShootFile(String path, ArrayList<Shoot> shoots) {
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
			FileWriter fileWriter = new FileWriter(path);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}