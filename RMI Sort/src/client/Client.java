package client;

import shared.Compute;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import javafx.util.Pair;

public class Client {
	public static void main(String[] args) {

		
		boolean invalid;
		Scanner entrada = new Scanner(System.in);
		Integer n = 0;
		
		do {
			System.out.println("Digite o tamanho do vetor.");
			invalid = false;
			try {
				n = Integer.valueOf(entrada.next());
				if(n <= 0) {
					System.out.println("Valor inválido - digite um valor inteiro positivo.");
					invalid = true;
				}
			} catch(NumberFormatException e) {
				System.out.println("Valor inválido - digite um valor inteiro positivo.");
				invalid = true;
			}
		} while(invalid);
		
		Integer[] vet = new Integer[n];
		
		System.out.println("Digite o vetor.");
		
		do {
			invalid = false;
			int i = 0;
			try {
				//Leitura do vetor
				for(i = 0; i < n; i++)
					vet[i] = Integer.valueOf(entrada.next());
			} catch(NumberFormatException e) {
				invalid = true;
				System.out.println("Erro ao ler o elemento de índice " + i);
				System.out.println("O vetor deve ser composto apenas por valores inteiros de 32 bits");
				System.out.println("Digite o vetor novamente.");
			}
		} while(invalid);
		
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
		Pair<Integer[], Long> result = null;
		boolean repeat;
		
		int tryCount = 0; //Tentativas de comunicacao com o servidor
		final int maxTry = 5; //Numero maximo de tentativas
		
		do {
			repeat = false;
			tryCount++;
			
			try {
				result = Comunicate(type, order, vet);
				if(result == null) {
					System.out.println("Servidor retornou nulo.");
				}
			} catch (RemoteException e) {
				e.printStackTrace(System.out);
				
				System.out.println("Erro remoto");
				
				if(tryCount < maxTry) {
					System.out.println("Tentanto novamente em 3s...");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						System.out.println("Sleep interrompido");
					}
					
					repeat = true;
				}
				
			} catch (NotBoundException e) {
				e.printStackTrace(System.out);
				
				System.out.println("Objeto remoto não encontrado.");
				
				if(tryCount < maxTry) {
					System.out.println("Tentanto novamente em 3s...");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						System.out.println("Sleep interrompido");
					}
					
					repeat = true;
				}
				
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		} while(repeat);
		
		if(result != null) {
			
			if(result.getKey() == null) {
				System.out.println("Vetor retornado é nulo");
			} else {
				vet = result.getKey();
				
				System.out.println("Vetor retornado:");
				
				//Exibe o vetor
				for(Integer x : vet)
					System.out.print(x + " ");
				System.out.println();

				if(result.getValue() == null) {
					System.out.println("Tempo retornado é nulo");
				} else {
					System.out.println("Tempo gasto no servidor: " + String.format("%.5f", result.getValue() / 1e9) + "s" );
				}
			}
		}
		System.out.println("Encerrando cliente.");
	}
	
	static public Pair<Integer[], Long> Comunicate(Compute.SortType type, Compute.Order order, Integer[] vet) 
			throws MalformedURLException, RemoteException, NotBoundException {
		
		//Instancia o stub do cliente
		Compute stub = (Compute) Naming.lookup("rmi://localhost:" + Compute.port + "/compute");
		
		//Retorna o resultado da ordenacao
		return stub.sortArray(type, order, vet);
	}
	
}