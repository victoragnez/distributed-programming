package service;

import java.lang.reflect.Constructor;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import server.Sort;
import shared.*;

@Path("/api")
public class API implements Compute {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHTML() {
		String resource = "<h1> Hi there! </h1>";
		return resource;
	}
	
	@GET
	@Path("/sortArray")
	@Produces(MediaType.APPLICATION_JSON)
	public OutputModelInterface sortArray(@QueryParam("sortType") final String sortType,
			@QueryParam("list") final String list,
			@QueryParam("isIncreasing") final Boolean isIncreasing){
		
		String[] lista = (list == null) ? (new String[0]) : list.split(",");
		Integer[] array = new Integer[lista.length];
		Sort ordena;
		try {
			//Nome da classe com o algoritmo
			String className = "server." + (sortType == null ? "Merge" : sortType) + "Sort";
			
			//Classe do Sort escolhido
			Class<?> classe = Class.forName(className);
			
			//Construtor a ser invocado
			Constructor<?> construtor = classe.getDeclaredConstructor(
					new Class[] {Integer[].class, Boolean.class});
			
			//Instancia da classe
			ordena = (Sort)construtor.newInstance(new Object[]{array, 
					(isIncreasing == null) || isIncreasing});
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na instanciação do Sort\n" + e.getMessage());
		}
		
		for(int i = 0; i < lista.length ; i++) {
	        array[i] = Integer.parseInt(lista[i]);
		}
		//Instante inicial
		Long startTime = System.nanoTime();
		
		//Ordena
		Integer[] arrayOutput = ordena.sort();
		
		//Calcula o tempo total
		Long totalTime = System.nanoTime()-startTime;

		OutputModel out = new OutputModel();
		
		out.setArray(arrayOutput);
		out.setTime(totalTime);
		
		return out;
	}

	@POST
	@Path("/sortArrayJSON")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OutputModelInterface sortArrayJSON(InputModelInterface in) {
		
		if(in.getIsIncreasing() == null) {
			in.setIsIncreasing(true);
		}
		
		if(in.getSortType() == null) {
			in.setSortType("Merge");
		}
		
		Sort ordena;
		try {
			//Nome da classe com o algoritmo
			String className = "server." + in.getSortType() + "Sort";
			
			//Classe do Sort escolhido
			Class<?> classe = Class.forName(className);
			
			//Construtor a ser invocado
			Constructor<?> construtor = classe.getDeclaredConstructor(
					new Class[] {Integer[].class, Boolean.class});
			
			//Instancia da classe
			ordena = (Sort)construtor.newInstance(new Object[]{in.getArray(), in.getIsIncreasing()});
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na instanciação do Sort\n" + e.getMessage());
		}
		
		//Instante inicial
		Long startTime = System.nanoTime();
		
		//Ordena
		Integer[] arrayOutput = ordena.sort();
		
		//Calcula o tempo total
		Long totalTime = System.nanoTime()-startTime;

		OutputModel out = new OutputModel();
		out.setArray(arrayOutput);
		out.setTime(totalTime);
		
		return out;
	}
}
