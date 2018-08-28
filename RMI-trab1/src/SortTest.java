import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class SortTest {
	
	private InsertionSort insertion;
	private MergeSort merge;
	private SelectionSort selection;
	private RandomSort random;
	private BubbleSort bubble;
	private HeapSort heap;
	private QuickSort quick;
	private RadixSort radix;
	private CountingSort counting;
	private BinaryTreeSort binaryTree;
	private StoogeSort stooge;
	private static Comparator comp;
	private Integer expected[];
	
	public SortTest(Integer actual[], Integer expected[]){
		this.expected = expected;
		insertion = new InsertionSort(actual);
		merge = new MergeSort(actual);
		selection = new SelectionSort(actual);
		random = new RandomSort(actual);
		bubble = new BubbleSort(actual);
		heap = new HeapSort(actual);
		quick = new QuickSort(actual);
		radix = new RadixSort(actual);
		counting = new CountingSort(actual);
		binaryTree = new BinaryTreeSort(actual);
		stooge = new StoogeSort(actual);
		comp = Comparator.<Integer>naturalOrder();
	}
	
	@Parameterized.Parameters
	public static Collection input() {
		Integer a[] = new Integer[] {6,-1,3,2};
		Arrays.sort(a, comp);
		Integer b[] = new Integer[]{6,3,-1,2};
		Arrays.sort(b, comp);
		Integer c[] = new Integer[]{6,-3,1,2};
		Arrays.sort(c, comp);
		Integer d[] = new Integer[]{};
		Arrays.sort(d, comp);
		Integer e[] = null;
		Integer f[] = new Integer[]{-6,-2,-8,-3};
		Arrays.sort(f, comp);
		Integer g[] = new Integer[] {-1,3,2};
		Arrays.sort(g, comp);
		Integer h[] = new Integer[]{6,3,6,3,1,3};
		Arrays.sort(h, comp);
		Integer i[] = new Integer[]{-11,-17,-15,-928,-627};
		Arrays.sort(i, comp);
		Integer j[] = new Integer[]{4};
		Arrays.sort(j, comp);
		Integer k[] = new Integer[]{3,3};
		Arrays.sort(k, comp);
		Integer l[] = new Integer[]{1,2};
		Arrays.sort(l, comp);
		Integer m[] = new Integer[]{2,1};
		Arrays.sort(m, comp);
		Integer n[] = new Integer[]{4,3,2,1};
		Arrays.sort(n, comp);
		Integer o[] = new Integer[]{1,3,2,4};
		Arrays.sort(o, comp);
		
		return Arrays.asList(new Integer[][][] {
			{{6,-1,3,2}, a},{{6,3,-1,2}, b}, {{6,-3,1,2},c}, {{},d},
			{null, e}, {{-6,-2,-8,-3}, f}, {{-1,3,2}, g}, {{6,3,6,3,1,3}, h},
			{{-11,-17,-15,-928,-627}, i} , {{4}, j}, {{3,3}, k},{{1,2}, l},
			{{2,1}, m}, {{4,3,2,1}, n}, {{1,3,2,4}, o}
			
		}) ;
	}
	
	@Test
	public void smallTestHeap() throws Exception {
		assertThat(heap.sort(), is(expected));
	}
	
	@Test
	public void smallTestQuick() throws Exception {
		assertThat(quick.sort(), is(expected));
	}
	
	@Test
	public void smallTestRadix() throws Exception {
		assertThat(radix.sort(), is(expected));
	}
}
