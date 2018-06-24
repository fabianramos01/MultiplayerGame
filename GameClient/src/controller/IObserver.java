package controller;

import java.util.ArrayList;

import model.Asteroid;
import model.Shoot;
import model.User;

public interface IObserver {

	void startGame();

	void loseGame();
	
	void winGame();
	
	void loadUsers(ArrayList<User> users);
	
	void loadShoots(ArrayList<Shoot> shoots);

	void loadAsteroids(ArrayList<Asteroid> asteroids);
}