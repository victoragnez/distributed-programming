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
		
		for(int i = 0; i < n; i++)
			vet[i] = Integer.valueOf(entrada.next());
		
		Compute.SortType type = null;
		
		while(true) {
			
			System.out.println("Digite o tipo de ordenação.");
			String readType = entrada.next().toUpperCase();
			
			for(Compute.SortType s : Compute.SortType.values())
				if(readType.equals(s.toString().toUpperCase())) 
					type = s;
			
			if(type != null)
				break;
			
			System.out.println("Ordenação inválida. Opções:");
			
			for(Compute.SortType s : Compute.SortType.values())
				System.out.println(s);
		}
		entrada.close();
		
		Compute stub = (Compute) Naming.lookup("rmi://localhost:" + Compute.port + "/compute");

		Pair<Integer[], Long> retorno = stub.sortArray(type, vet);
		vet = retorno.getKey();
		for(Object x : vet){
			System.out.print(x + " ");
		}
		System.out.println();
		System.out.println("Tempo gasto: " + String.format("%.5f", retorno.getValue() / 1e9) + "s" );
	}
}
