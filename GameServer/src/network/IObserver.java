package network;

public interface IObserver {

	void addPlayer(Connection connection);
	
	void removeConnection(Connection connection);
}