package server;

import java.lang.reflect.Constructor;
import shared.Compute;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import javafx.util.Pair;

@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements Compute {
	protected Server() throws RemoteException { 
		super(); 
	}
	
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray) throws RemoteException {
		System.out.println("Executando a tarefa no servidor...");
		
		//Classe com o algoritmo de ordenacao
		Sort ordena;
		
		try {
			//Nome da classe com o algoritmo
			String className = "server." + sortType.toString() + "Sort";
			
			//Classe do Sort escolhido
			Class<?> classe = Class.forName(className);
			
			//Construtor a ser invocado
			Constructor<?> construtor = classe.getDeclaredConstructor(new Class[] {Integer[].class});
			
			//Instancia da classe
			ordena = (Sort)construtor.newInstance(new Object[]{inputArray});
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na instanciação do Sort\n" + e.getMessage());
		}
		
		//Para medir o tempo
		Long startTime = System.nanoTime();
		
		//Ordena
		Integer[] array = ordena.sort();
		
		//Retorna um Pair com o vetor ordenado e o tempo
		return new Pair<Integer[], Long>(array, System.nanoTime()-startTime);
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(Compute.port);
		Server compute = new Server();
		
		Naming.rebind("rmi://localhost:" + Compute.port + "/compute", compute);
		System.out.println("Servidor registrado no RMI Registry.");
	}
}
