import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Comparator;

import javafx.util.Pair;

public interface Compute extends Remote {
	
	public enum SortType {
		QUICK, MERGE, RADIX, RANDOM, HEAP, INSERTION, COUNTING,
		BUBBLE, BINARYTREE, SELECTION, STOOGE
	};
	
	
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray) throws RemoteException;
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray, Comparator<Integer> Comp) throws RemoteException;
}
