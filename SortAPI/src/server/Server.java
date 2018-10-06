package server;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.util.Pair;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import shared.Compute.*;

@Path("api")
public class Server {

	public Server() { 
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sortArray/{array}")
	public Pair<Integer[], Long> sortArray(/*SortType sortType, Order order, */@PathParam("array") Integer[] inputArray) throws RemoteException {
		
		SortType sortType = SortType.Merge;
		Order order = Order.Increasing;
		
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
//		return Response.status(200).entity(new Pair<Integer[], Long>(array, totalTime)).build();
	}
	
	/*public Pair<Integer[], Long> sortArray(SortType sortType, Integer[] inputArray) throws RemoteException {
		return sortArray(sortType, Order.Decreasing, inputArray);
	}*/
}
