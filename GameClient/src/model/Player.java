package model;

import java.util.ArrayList;

import controller.ConstantList;

public class Player {

	private String name;
	private int life;
	private int victories;
	private Area area;
	private ArrayList<String> achievements;
	private ArrayList<String> articles;
	
	public Player(String name, Area area) {
		this.name = name;
		this.area = area;
		this.life = ConstantList.LIFE;
		this.achievements = new ArrayList<>();
		this.articles = new ArrayList<>();
	}
	
	public void setVictories(int victories) {
		this.victories = victories;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	public void move(Direction direction) {
		 area.move(direction);
	}
	
	public String getName() {
		return name;
	}
	
	public int getLife() {
		return life;
	}
	
	public Area getArea() {
		return area;
	}
	
	public int getVictories() {
		return victories;
	}
	
	public void addAchievement(String achievement) {
		achievements.add(achievement);
	}
	
	public void addArticles(String article) {
		articles.add(article);
	}
	
	public ArrayList<String> getAchievement() {
		return achievements;
	}
	
	public ArrayList<String> getArticles() {
		return articles;
	}
}