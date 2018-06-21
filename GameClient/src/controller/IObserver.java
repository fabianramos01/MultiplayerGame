package controller;

import java.util.ArrayList;

import model.Shoot;
import model.User;

public interface IObserver {

	void startGame();
	
	void loadUsers(ArrayList<User> users);
	
	void loadShoots(ArrayList<Shoot> shoots);
}