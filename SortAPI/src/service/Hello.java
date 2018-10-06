package service;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javafx.util.Pair;
import server.MergeSort;
import server.Sort;
import shared.Compute.Order;
import shared.Compute.SortType;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Application;

import shared.Compute.SortType;
import shared.Compute.Order;

@Path("/hello")
public class Hello {

	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayHello() {
		String resource = "<? xml=version'1.0' ?" + "<hello> Hi Me</hello> ";
		return resource;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHTML() {
		String resource = "<h1> Hi Me</h1>";
		return resource;
	}

	@GET
	@Path("/celsiusToFahrenheit")
	@Produces(MediaType.TEXT_PLAIN)
	public Double celsiusToFahrenheit(@QueryParam("temperatura") Double temperatura) {
		return temperatura * 9.0 / 5.0 + 32;
	}
	
	// receive List of Strings
	@GET
	@Path("/sortArray")
	@Produces(MediaType.TEXT_PLAIN)
	public String receiveListOfStrings(@QueryParam("list") final String list){
		String[] lista = list.split(",");
		Integer[] array = new Integer[lista.length];
		for(int i = 0; i < lista.length ; i++) {
			try{
		        array[i] = Integer.parseInt(lista[i]);
		    }
		    catch (NumberFormatException nfe)   
		    {
		        array[i] = null;
		    }
		}
		Sort ordena = new MergeSort(array, true);
		Integer[] arrayOutput = ordena.sort();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arrayOutput.length ; i++) {
		  builder.append(arrayOutput[i]);
		  if(i != arrayOutput.length -1)
			  builder.append(',');
		}
		return builder.toString();
	}

}
