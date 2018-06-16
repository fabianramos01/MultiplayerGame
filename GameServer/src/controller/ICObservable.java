package controller;

public interface ICObservable {

	void addObserver(ICObserver icObserver);
	
	void removeObserver();
}