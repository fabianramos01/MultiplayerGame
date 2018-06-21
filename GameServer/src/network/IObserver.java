package network;

public interface IObserver {

	void addPlayer(Connection connection);
	
	void removeConnection(Connection connection);
	
	void createShoot(int x, int y);
}