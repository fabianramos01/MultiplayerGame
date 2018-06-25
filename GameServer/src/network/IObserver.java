package network;

public interface IObserver {

	void removeConnection(Connection connection);
	
	void createShoot(int x, int y);

	void addPlayer(Connection connection, String name, String password);
	
	void addLognIn(Connection connection, String name, String password);
}