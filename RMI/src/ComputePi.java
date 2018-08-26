import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

public class ComputePi {
	public static void main(String[] args) throws RemoteException, 
		NotBoundException, MalformedURLException {
		
		Compute stub = (Compute) Naming.lookup("rmi://localhost:12346/compute");
		Pi tarefa = new Pi(Double.valueOf(args[0]));
		try {
			System.out.println("Cliente submetendo cálculo de Pi para execução...");
			double ret = stub.executeTask(tarefa);
			System.out.println("Resultado recebido pelo cliente: " + ret);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		Random rand = new Random();
		int n = rand.nextInt(21);;
		Fatorial fat = new Fatorial(n);
		try {
			System.out.println("Cliente submetendo cálculo do fatorial de " + n + " para execução...");
			long ret = stub.executeTask(fat);
			System.out.println("Resultado recebido pelo cliente: " + ret);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		int INT_MAX = (-1)^(1<<31);
		int a = rand.nextInt(INT_MAX);
		int b = rand.nextInt(INT_MAX);
		MDC mdc = new MDC(a, b);
		try {
			System.out.println("Cliente submetendo cálculo do MDC entre " + a + " e " + b + " para execução...");
			int ret = stub.executeTask(mdc);
			System.out.println("Resultado recebido pelo cliente: " + ret);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		n = rand.nextInt(100000);
		Primo primo = new Primo(n);
		try {
			System.out.println("Cliente submetendo cálculo número primo de índice " + n + " para execução...");
			int ret = stub.executeTask(primo);
			System.out.println("Resultado recebido pelo cliente: " + ret);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
