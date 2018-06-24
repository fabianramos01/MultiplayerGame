package model;

import java.util.ArrayList;

import network.ConstantList;

public class Player {

	private String name;
	private int life;
	private int kills;
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
	
	public void addKill() {
		kills++;
	}
	
	public void lessLife(int asType) {
		if (asType == ConstantList.MAX_TYPE) {
			life-= ConstantList.LESS_LIFE_TH;
		} else if (asType == ConstantList.MIN_TYPE) {
			life-= ConstantList.LESS_LIFE_O;
		} else {
			life-= ConstantList.LESS_LIFE_T;
		}
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
	
	public int getKills() {
		return kills;
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