import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Comparator;

import javafx.util.Pair;

@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements Compute {
	protected Server() throws RemoteException { 
		super(); 
	}
	
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray, Comparator<Integer> Comp) throws RemoteException {
		System.out.println("Executando a tarefa no servidor...");
		Sort ordena;
		switch(sortType) {
		case INSERTION:
			ordena = new InsertionSort(inputArray, Comp);
			break;
		case MERGE:
			ordena = new MergeSort(inputArray, Comp);
			break;
		case SELECTION:
			ordena = new SelectionSort(inputArray, Comp);
			break;
		case RANDOM:
			ordena = new RandomSort(inputArray, Comp);
			break;
		case BUBBLE:
			ordena = new BubbleSort(inputArray, Comp);
			break;
		case HEAP:
			ordena = new HeapSort(inputArray, Comp);
			break;
		case QUICK:
			ordena = new QuickSort(inputArray, Comp);
			break;
		case RADIX:
			ordena = new RadixSort(inputArray, Comp);
			break;
		case COUNTING:
			ordena = new CountingSort(inputArray, Comp);
			break;
		case BINARYTREE:
			ordena = new BinaryTreeSort(inputArray, Comp);
			break;
		case STOOGE:
			ordena = new StoogeSort(inputArray, Comp);
			break;
		default:
			throw new RuntimeException("Invalid sortType");
		}
		Long startTime = System.nanoTime();
		Integer[] array = ordena.sort();
		return new Pair<Integer[], Long>(array, System.nanoTime()-startTime);
	}
	
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray) throws RemoteException {
		return sortArray(sortType, inputArray, Comparator.<Integer>naturalOrder());
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(12346);
		Server compute = new Server();
		
		Naming.rebind("rmi://localhost:12346/compute", compute);
		System.out.println("Servidor registrado no RMI Registry.");
	}
}
