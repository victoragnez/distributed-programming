package tests;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Comparator;

import shared.Compute.Order;
import shared.Compute.SortType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import client.Client;
import javafx.util.Pair;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class ComunicateTest {
	
	private SortType sort;
	private Integer[] vet;
	private Order order = Order.Decreasing;
	Comparator<Integer> comp;
	
	public ComunicateTest(SortType sortType, Integer[] Vet){
		sort = sortType;
		vet = Vet;
		if(order == Order.Increasing)
			comp = Comparator.<Integer>naturalOrder();
		else
			comp = Comparator.<Integer>reverseOrder();
	}
	
	@Parameters
	public static Collection<Object[]> tests(){
		return tests.TestGenerator.getTests();
	}

	@Test(timeout = 5000)
	public void runTest() throws MalformedURLException, 
		RemoteException, NotBoundException {
		
		Pair<Integer[], Long> retorno = Client.Comunicate(sort, order, vet);
		assertNotNull(retorno);
		
		Verify.verify(sort.toString(), vet, retorno.getKey(), comp);

	}
}
