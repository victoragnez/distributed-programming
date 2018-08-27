import java.util.Comparator;

public class InsertionSort extends Sort {
	public InsertionSort(){
		super();
	}
	
	public InsertionSort(Integer[] Vet){
		super(Vet);
	}
	
	public InsertionSort(Integer[] Vet, Comparator<Integer> Comp){
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
		for(int i = 0; i < vet.length; i++){
			for(int j = i; j > 0 && comp.compare(vet[j],vet[j-1]) < 0; j--){
				Integer tmp = vet[j-1];
				vet[j-1] = vet[j];
				vet[j] = tmp;
			}
		}
		return vet;
	}
}
