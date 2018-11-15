import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ComputeEngine extends UnicastRemoteObject implements Compute {
	protected ComputeEngine() throws RemoteException { 
		super(); 
	}
	public <T> T executeTask(Task<T> task) throws RemoteException {
		System.out.println("Running new task...");
		return task.execute();
	}
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(12346);
		ComputeEngine compute = new ComputeEngine();
		
		Naming.rebind("rmi://localhost:12346/compute", compute);
		System.out.println("Server is ready");
	}
}
