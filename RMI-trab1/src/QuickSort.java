import java.util.Comparator;
import java.util.Random;

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
	
	private Integer partition_random(Integer vet[], int lo, int hi ) {
		Random rand = new Random();
		int random_index = rand.nextInt((hi - lo) + 1) + lo;
		Integer temp = vet[random_index];
		vet[random_index] = vet[hi];
		vet[hi] = temp;
		return partition(vet, lo, hi);
	}
	
	private Integer[] sort(Integer vet[], int lo, int hi) {
		if(vet == null || vet.length < 2)
			return vet;
		if (lo < hi) {
			Integer pivot = partition_random(vet, lo, hi);
			sort(vet,lo, pivot - 1);
			sort(vet, pivot + 1, hi);

			return vet;
		}
		return vet;

				
	}
}
