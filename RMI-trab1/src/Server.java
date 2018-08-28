import java.lang.reflect.InvocationTargetException;
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
		String className = sortType.toString() + "Sort";
		try {
			ordena = (Sort)Class.forName(className).getDeclaredConstructor(new Class[] {Integer[].class, Comparator.class}).newInstance(new Object[]{inputArray, Comp});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na instanciação do Sort");
		}
		Long startTime = System.nanoTime();
		Integer[] array = ordena.sort();
		return new Pair<Integer[], Long>(array, System.nanoTime()-startTime);
	}
	
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray) throws RemoteException {
		return sortArray(sortType, inputArray, Comparator.<Integer>naturalOrder());
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(Compute.port);
		Server compute = new Server();
		
		Naming.rebind("rmi://localhost:" + Compute.port + "/compute", compute);
		System.out.println("Servidor registrado no RMI Registry.");
	}
}
