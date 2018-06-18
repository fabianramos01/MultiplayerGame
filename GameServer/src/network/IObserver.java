package network;

public interface IObserver {

	void update(String name);
	
	void addPlayer(Connection connection);
	
	void removeConnection(Connection connection);
}