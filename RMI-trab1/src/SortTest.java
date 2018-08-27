import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SortTest {
	
	private Sort sort;
	private Integer[] vet;
	Comparator<Integer> comp;
	
	public SortTest(Sort kind, Integer[] Vet){
		sort = kind;
		vet = Vet;
		comp = Comparator.<Integer>naturalOrder();
	}
	
	@Parameters
	public static Collection<Object[]> tests(){
		Collection<Object[]> a = Arrays.asList(new Object[][]{});
		adiciona(a, InsertionSort.class, true, true, true, true, false);
		adiciona(a, MergeSort.class, true, true, true, true, true);
		adiciona(a, SelectionSort.class, true, true, true, true, false);
		adiciona(a, RandomSort.class, true, true, false, false, false);
		adiciona(a, BubbleSort.class, true, true, true, true, false);
		adiciona(a, HeapSort.class, true, true, true, true, true);
		adiciona(a, QuickSort.class, true, true, true, true, true);
		adiciona(a, RadixSort.class, true, true, true, true, true);
		adiciona(a, CountingSort.class, true, false, true, false, false);
		adiciona(a, BinaryTreeSort.class, true, true, true, true, true);
		adiciona(a, StoogeSort.class, true, true, true, true, false);
		return a;
	}

	private static void adiciona(Collection<Object[]> a, Class<? extends Sort> s, Integer[] vet) {
		try {
			a.add(new Object[]{s.getClass().getDeclaredConstructor(Integer[].class).newInstance(new Object[]{vet}), vet});
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	private static void adiciona(Collection<Object[]> a, Class<? extends Sort> s, boolean small, boolean bigElements, boolean bigArray, boolean bigTest, boolean giantTest) {
		Random rand = new Random();
		if(small) {
			adiciona(a, s, new Integer[]{6,-1,3,2});
			adiciona(a, s, new Integer[]{6,3,-1,2});
			adiciona(a, s, new Integer[]{6,-3,1,2});
			adiciona(a, s, new Integer[]{});
			adiciona(a, s, null);
			adiciona(a, s, new Integer[]{-6,-2,-8,-3});
			adiciona(a, s, new Integer[]{-1,3,2});
			adiciona(a, s, new Integer[]{6,3,6,3,1,3});
			adiciona(a, s, new Integer[]{-11,-17,-15,-928,-627});
			adiciona(a, s, new Integer[]{4});
			adiciona(a, s, new Integer[]{3,3});
			adiciona(a, s, new Integer[]{1,2});
			adiciona(a, s, new Integer[]{2,1});
			adiciona(a, s, new Integer[]{4,3,2,1});
			adiciona(a, s, new Integer[]{1,3,2,4});
		}
		if(bigElements) {
			adiciona(a, s, new Integer[]{60000000,-1000000,30000,2000000000});
			adiciona(a, s, new Integer[]{6000000,3,-100000,2000000});
			adiciona(a, s, new Integer[]{-100000000,300000000,2});
			adiciona(a, s, new Integer[]{600000000,3000000,600000000,300000000,-100000000,300000000});
			adiciona(a, s, new Integer[]{600000000,3000000,-600000000,300000000,100000000,300000000});
			adiciona(a, s, new Integer[]{400000000});
			adiciona(a, s, new Integer[]{300000000,-300000000});
			adiciona(a, s, new Integer[]{200000000, 2000000000});
			adiciona(a, s, new Integer[]{200000000, 1000000000});
		}
		if(bigArray) {
			for(int total = 0; total < 15; total++) {
				int n = rand.nextInt(100) + 200;
				Integer[] vet = new Integer[n];
				for(int i = 0; i < n; i++) {
					vet[i] = rand.nextInt(4*n) - 2*n;
				}
				adiciona(a, s, vet);
			}
		}
		if(bigTest) {
			for(int total = 0; total < 15; total++) {
				int n = rand.nextInt(100) + 200;
				Integer[] vet = new Integer[n];
				for(int i = 0; i < n; i++) {
					vet[i] = rand.nextInt();
				}
				adiciona(a, s, vet);
			}
		}
		if(giantTest) {
			int n = 200000;
			Integer[] vet = new Integer[n];
			for(int i = 0; i < n; i++) {
				vet[i] = rand.nextInt();
			}
			adiciona(a, s, vet);
			for(int i = 0; i < n; i++) {
				vet[i] = (i-n/2)*1000;
			}
			adiciona(a, s, vet);
			for(int i = 0; i < n; i++) {
				vet[i] = (n/2-i)*1000;
			}
			adiciona(a, s, vet);
		}
	}
	
	@Test(timeout = 3000)
	private void runTest() {
		if(vet == null) {
			if(sort.sort() != null)
				throw new RuntimeException("not null return for null array");
			return;
		}
		
		int n = vet.length;
		Integer [] aux = new Integer[n];
		for(int i = 0; i < n; i++) {
			aux[i] = vet[i];
		}
		vet = sort.sort();
		
		if(vet == null)
			throw new RuntimeException("null return for non-null array");
		
		if(vet.length != n)
			throw new RuntimeException("returned array size is different than original array size\noriginal size: " + n + "\nreturned size: " + vet.length);
		
		for(int i = 0; i < n - 1; i++) {
			if(comp.compare(vet[i], vet[i+1]) > 0) {
				String error = "Values not sorted: " + vet[i] + " " + vet[i+1];
				error += "\n";
				error += "Original Array:\n";
				for(int j = 0; j < n; j++)
					error += aux[j] + " ";
				error += "\n";
				error += "Returned Array:\n";
				for(int j = 0; j < n; j++)
					error += vet[j] + " ";
				throw new RuntimeException(error);
			}
		}
		Arrays.sort(aux, comp);
		for(int i = 0; i < n; i++) {
			if(aux[i].equals(vet[i]) == false) {
				throw new RuntimeException("Actual: " + vet[i] + ", expected: " + vet[i+1]);
			}
		}
	}
}
