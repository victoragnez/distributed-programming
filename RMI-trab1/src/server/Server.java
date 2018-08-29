package server;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.util.Pair;
import shared.Compute;

@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements Compute {
	protected Server() throws RemoteException { 
		super(); 
	}
	
	public Pair<Integer[], Long> sortArray(SortType sortType, Order order, Integer[] inputArray) throws RemoteException {
		if(sortType == null) {
			System.out.println("sortType nulo");
			throw new NullPointerException("Null sortType");
		}
		
		System.out.println("Executando a tarefa no servidor...");
		
		//Classe com o algoritmo de ordenacao
		Sort ordena;
		
		try {
			//Nome da classe com o algoritmo
			String className = "server." + sortType.toString() + "Sort";
			
			//Classe do Sort escolhido
			Class<?> classe = Class.forName(className);
			
			//Construtor a ser invocado
			Constructor<?> construtor = classe.getDeclaredConstructor(new Class[] {Integer[].class, Boolean.class});
			
			//Instancia da classe
			ordena = (Sort)construtor.newInstance(new Object[]{inputArray, order == Order.Increasing});
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na instanciação do Sort\n" + e.getMessage());
		}
		
		//Instante inicial
		Long startTime = System.nanoTime();
		
		//Ordena
		Integer[] array = ordena.sort();
		
		//Calcula o tempo total
		Long totalTime = System.nanoTime()-startTime;
		
		System.out.println("Tempo gasto: " + String.format("%.5f", totalTime / 1e9) + "s" );
		
		//Retorna um Pair com o vetor ordenado e o tempo
		return new Pair<Integer[], Long>(array, totalTime);
	}
	
	public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray) throws RemoteException {
		return sortArray(sortType, Order.Decreasing, inputArray);
	}
}
