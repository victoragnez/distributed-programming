import java.util.Comparator;

public class QuickSort extends Sort {
	public QuickSort(){
		super();
	}
	
	public QuickSort(Integer[] Vet){
		super(Vet);
	}
	
	public QuickSort(Integer[] Vet, Comparator<Integer> Comp ){
		super(Vet, Comp);
	}
	
	public void test() {
		super.smallTests();
		super.bigElementsTests();
		super.bigArrayTests();
	}
	
	@Override
	protected Integer[] sort() {
		return sort(vet,0, vet.length-1);
	}

	private Integer[] sort(Integer vet[], int lo, int hi) {
		if(vet == null || vet.length < 2)
			return vet;
		int pivot_index = lo+(hi-lo)/2;
		Integer pivot = vet[pivot_index];
		int i = lo;
		int j = hi;

		while(i <= j){
			while(comp.compare(vet[i], pivot) < 0) 
				i++;
			
			while(comp.compare(vet[j], pivot) > 0) 
				j--;
			
			if(i <= j) {
				Integer temp = vet[i];
				vet[i] = vet[j];
				vet[j] = temp;
				
				i++;
				j--;
			}
			
			if(lo < j) 
				sort(vet,lo, pivot_index);

			if(hi > i)
				sort(vet,pivot_index + 1, hi);
		}
		
		return vet;


				
	}
}
