import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
	public <T> T executeTask(Task<T> task) throws RemoteException;
}
