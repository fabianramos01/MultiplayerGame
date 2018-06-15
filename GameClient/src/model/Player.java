package model;

import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<String> achievement;
	private ArrayList<String> articles;
	
	public Player(String name, ArrayList<String> achievement, ArrayList<String> articles) {
		super();
		this.name = name;
		this.achievement = achievement;
		this.articles = articles;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getAchievement() {
		return achievement;
	}
	
	public ArrayList<String> getArticles() {
		return articles;
	}
}