package client;

import shared.Compute;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import javafx.util.Pair;

public class Client {
	public static void main(String[] args) throws RemoteException, 
		NotBoundException, MalformedURLException {

		System.out.println("Digite o tamanho do vetor.");
		
		Scanner entrada = new Scanner(System.in);
		Integer n = Integer.valueOf(entrada.next());
		
		Integer[] vet = new Integer[n];
		
		System.out.println("Digite o vetor.");
		
		//Leitura do vetor
		for(int i = 0; i < n; i++)
			vet[i] = Integer.valueOf(entrada.next());
		
		Compute.SortType type = null;
		
		//Laco ate que seja informado um algoritmo valido
		while(true) {
			
			System.out.println("Digite o tipo de ordenação.");
			String readType = entrada.next().toUpperCase();
			
			//Verifica uppercase/lowercase
			for(Compute.SortType s : Compute.SortType.values())
				if(readType.equals(s.toString().toUpperCase())) 
					type = s;
			
			if(type != null)
				break;
			
			System.out.println("Ordenação inválida. Opções:");
			
			//Mostra as opcoes
			for(Compute.SortType s : Compute.SortType.values())
				System.out.println(s);
		}
		
		System.out.println("Digite 0 para ordem crescente (ou não-decrescente) ou outro valor para decrescente (ou não-crescente).");
		
		String ord = entrada.next();
		
		entrada.close();
		
		Compute.Order order = (ord.equals("0") ? Compute.Order.Increasing : Compute.Order.Decreasing );
		
		//Recebe o vetor ordenado e o tempo em nano-segundos
		Pair<Integer[], Long> result = Comunicate(type, order, vet);
		
		vet = result.getKey();
		
		//Exibe o vetor
		for(Integer x : vet)
			System.out.print(x + " ");
		
		System.out.println();
		System.out.println("Tempo gasto: " + String.format("%.5f", result.getValue() / 1e9) + "s" );
	}
	
	static public Pair<Integer[], Long> Comunicate(Compute.SortType type, Compute.Order order, Integer[] vet) throws MalformedURLException,
		RemoteException, NotBoundException {
		
		//Instancia o stub do cliente
		Compute stub = (Compute) Naming.lookup("rmi://localhost:" + Compute.port + "/compute");
		
		//Retorna o resultado da ordenacao
		return stub.sortArray(type, order, vet);
	}
	
}