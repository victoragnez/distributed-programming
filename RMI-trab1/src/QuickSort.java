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
		super.bigTests();
	}
	
	@Override
	protected Integer[] sort() {
		if(vet == null || vet.length < 2)
			return vet;
		return sort(vet,0, vet.length-1);
	}
	private Integer partition(Integer vet[], int lo, int hi) {
		Integer pivot = vet[hi];
		
		int i = lo;
		for (int j = lo; j < hi; j++){
			if(comp.compare(vet[j], pivot ) < 0 ) {
				Integer temp = vet[j];
				vet[j] = vet[i];
				vet[i] = temp;
				i++;
			}
		}
		
		Integer temp = vet[hi];
		vet[hi] = vet[i];
		vet[i] = temp;
		
		return i;
		
	}

	private Integer[] sort(Integer vet[], int lo, int hi) {
		if(vet == null || vet.length < 2)
			return vet;
		if (lo < hi) {
			Integer pivot = partition(vet, lo, hi);
			sort(vet,lo, pivot - 1);
			sort(vet, pivot + 1, hi);

			return vet;
		}
		return vet;

				
	}
}
