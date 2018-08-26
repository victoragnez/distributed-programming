import java.util.Comparator;
import java.util.Random;

public abstract class Sort{
	protected Integer[] vet;
	protected Comparator<Integer> comp;
	protected abstract Integer[] sort();
	protected abstract void test();
	
	public Sort(){}
	
	public Sort(Integer[] Vet){
		this(Vet, Comparator.<Integer>naturalOrder());
	}
	
	public Sort(Integer[] Vet, Comparator<Integer> Comp){
		vet = Vet;
		comp = Comp;
	}
	
	
	public Integer[] execute(){
		return sort();
	}
	
	protected void smallTests() {
		comp = Comparator.<Integer>naturalOrder();
		vet = new Integer[]{6,-1,3,2};
		runTest();
		vet = new Integer[]{6,3,-1,2};
		runTest();
		vet = new Integer[]{};
		runTest();
		vet = null;
		runTest();
		vet = new Integer[] {-1,3,2};
		runTest();
		vet = new Integer[]{6,3,6,3,1,3};
		runTest();
		vet = new Integer[]{4};
		runTest();
		vet = new Integer[]{3,3};
		runTest();
		vet = new Integer[]{1,2};
		runTest();
		vet = new Integer[]{2,1};
		runTest();
		vet = new Integer[]{4,3,2,1};
		runTest();
		vet = new Integer[]{1,3,2,4};
		runTest();
	}
	
	protected void bigElementsTests() {
		comp = Comparator.<Integer>naturalOrder();
		vet = new Integer[]{60000000,-1000000,30000,2000000000};
		runTest();
		vet = new Integer[]{6000000,3,-100000,2000000};
		runTest();
		vet = new Integer[] {-100000000,300000000,2};
		runTest();
		vet = new Integer[]{600000000,3000000,600000000,300000000,-100000000,300000000};
		runTest();
		vet = new Integer[]{400000000};
		runTest();
		vet = new Integer[]{300000000,-300000000};
		runTest();
		vet = new Integer[]{200000000, 2000000000};
		runTest();
		vet = new Integer[]{2000000000,1000000000};
		runTest();
	}
	
	protected void bigArrayTests() {
		comp = Comparator.<Integer>naturalOrder();
		for(int total = 0; total < 10; total++) {
			Random rand = new Random();
			int n = rand.nextInt(1000) + 1000;
			vet = new Integer[n];
			for(int i = 0; i < n; i++) {
				vet[i] = rand.nextInt(2*n);
			}
			runTest();
		}
	}
	
	protected void bigTests() {
		comp = Comparator.<Integer>naturalOrder();
		for(int total = 0; total < 10; total++) {
			Random rand = new Random();
			int n = rand.nextInt(1000) + 1000;
			vet = new Integer[n];
			for(int i = 0; i < n; i++) {
				vet[i] = rand.nextInt();
			}
			runTest();
		}
	}
	
	private void runTest() {
		if(vet == null) {
			assert(sort() == null);
			return;
		}
		int n = vet.length;
		vet = sort();
		assert(vet.length == n);
		for(int i = 0; i < n - 1; i++)
			assert(comp.compare(vet[i], vet[i+1]) <= 0);
	}
}

