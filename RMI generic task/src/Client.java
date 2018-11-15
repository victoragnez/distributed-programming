import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

public class Client {
	//Receives as arg the desired EPS for Pi calculation.
	public static void main(String[] args) throws RemoteException, 
		NotBoundException, MalformedURLException {
		
		Double piEPS = 1e-5;
		Compute stub = (Compute) Naming.lookup("rmi://localhost:12346/compute");
		if(args.length > 0) {
			try {
				piEPS = Double.valueOf(args[0]);
			} catch(NumberFormatException e) { }
		}
		Pi piTask = new Pi(piEPS);
		try {
			System.out.println("Submit Pi calculation...");
			double ret = stub.executeTask(piTask);
			System.out.println("Result: " + ret);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		Random rand = new Random();
		int n = rand.nextInt(21);
		Fatorial fat = new Fatorial(n);
		try {
			System.out.println("Asked factorial of " + n + "...");
			long ret = stub.executeTask(fat);
			System.out.println("Result: " + ret);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		int INT_MAX = (-1)^(1<<31);
		int a = rand.nextInt(INT_MAX);
		int b = rand.nextInt(INT_MAX);
		GCD gcd = new GCD(a, b);
		try {
			System.out.println("Submit GCD of " + a + " and " + b + "...");
			int ret = stub.executeTask(gcd);
			System.out.println("Result: " + ret);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		n = rand.nextInt(100000);
		Primo primo = new Primo(n);
		try {
			System.out.println("Asked  " + n + "th prime number...");
			int ret = stub.executeTask(primo);
			System.out.println("Result: " + ret);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
