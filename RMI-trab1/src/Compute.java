import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Comparator;

import javafx.util.Pair;

public interface Compute extends Remote {
	public Pair<Integer[], Long> sortArray(Integer sortType, Integer[] inputArray) throws RemoteException;
	public Pair<Integer[], Long> sortArray(Integer sortType, Integer[] inputArray, Comparator<Integer> Comp) throws RemoteException;
}
