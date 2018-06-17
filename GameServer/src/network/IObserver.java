package network;

public interface IObserver {

	void update(String name);
	
	void addConnection(Connection connection);
	
	void removeConnection(Connection connection);
}