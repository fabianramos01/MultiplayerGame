package persistence;

import java.io.FileWriter;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
}