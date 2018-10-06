package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javafx.util.Pair;

public interface Compute extends Remote {
	
	public enum SortType {
		BinaryTree, Bubble, Counting, Heap, Insertion, 
		Merge, Quick, Radix, Random, Selection, Stooge
	};
	
	public enum Order {
		Increasing, Decreasing
	};
	
	public final int port = 12346;
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray) throws RemoteException;
	public Pair<Integer[], Long> sortArray(SortType sortType, Order order, Integer[] inputArray) throws RemoteException;
}
