package tests;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Comparator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import server.Sort;
import shared.Compute.*;

@RunWith(Parameterized.class)
public class SortTest {
	
	private Sort sort;
	private Integer[] vet;
	Comparator<Integer> comp;
	SortType sortType;
	Order order = Order.Decreasing;
	
	public SortTest(SortType SortType, Integer[] Vet) throws ClassNotFoundException, 
		NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, 
		IllegalArgumentException, InvocationTargetException{
		
		sortType = SortType;
		vet = Vet;
		
		if(order == Order.Increasing)
			comp = Comparator.<Integer>naturalOrder();
		else
			comp = Comparator.<Integer>reverseOrder();
		
		Constructor<?> construtor = Class.forName("server." + sortType.toString() + "Sort").
				getDeclaredConstructor(new Class[] {Integer[].class, Boolean.class});

		sort = (Sort)construtor.newInstance(new Object[]{Vet, order == Order.Increasing});
	}
	
	@Parameters
	public static Collection<Object[]> tests(){
		return TestGenerator.getTests();
	}

	@Test(timeout = 5000)
	public void runTest() {
		Verify.verify(sortType.toString(), vet, sort.sort(), comp);
	}
}
