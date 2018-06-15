package model;

import java.util.ArrayList;

import controller.ConstantList;

public class Player {

	private String name;
	private int life;
	private int kills;
	private Area area;
	private ArrayList<String> achievement;
	private ArrayList<String> articles;
	
	public Player(String name, Area area, ArrayList<String> achievement, ArrayList<String> articles) {
		this.name = name;
		this.area = area;
		this.life = ConstantList.LIFE;
		this.achievement = achievement;
		this.articles = articles;
	}
	
	public void addKill() {
		kills++;
	}
	
	public void lessLife() {
		life-= ConstantList.LESS_LIFE;
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
	
	public int getKills() {
		return kills;
	}
	
	public ArrayList<String> getAchievement() {
		return achievement;
	}
	
	public ArrayList<String> getArticles() {
		return articles;
	}
}