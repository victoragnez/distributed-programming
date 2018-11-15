package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import shared.Compute;

public class Main {
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		//Criando registro para objetos remotos serem acessados pela porta Compute.port
		LocateRegistry.createRegistry(Compute.port);
		
		//Instancia a classe do servidor
		Server compute = new Server();
		
		String ip = "localhost"; //IP do servidor
		
		//(Re)binding
		Naming.rebind("rmi://" + ip + ":" + Compute.port + "/compute", compute);
		
		System.out.println("Servidor instanciado e registrado no RMI Registry.");
	}
}
