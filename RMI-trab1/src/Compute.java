import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Comparator;

import javafx.util.Pair;

public interface Compute extends Remote {
	
	public enum SortType {
		BinaryTree, Bubble, Counting, Heap, Insertion, 
		Merge, Quick, Radix, Random, Selection, Stooge
	};
	
	public final int port = 12347;
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray) throws RemoteException;
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray, Comparator<Integer> Comp) throws RemoteException;
}
