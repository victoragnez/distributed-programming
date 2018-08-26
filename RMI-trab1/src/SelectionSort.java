import java.util.Comparator;

public class SelectionSort extends Sort {
	public SelectionSort(){
		super();
	}
	
	public SelectionSort(Integer[] Vet){
		super(Vet);
	}
	
	public SelectionSort(Integer[] Vet, Comparator<Integer> Comp){
		super(Vet, Comp);
	}
	
	public void test() {
		super.smallTests();
		super.bigElementsTests();
		super.bigArrayTests();
	}
	
	@Override
	protected Integer[] sort() {
		return sort(vet);
	}
	
	private Integer[] sort(Integer vet[]){
		if(vet == null || vet.length < 2)
			return vet;
		for(int i = 0; i < vet.length; i++){
			for(int j = i+1; j < vet.length; j++){
				if(comp.compare(vet[j],vet[i]) < 0){
					Integer tmp = vet[i];
					vet[i] = vet[j];
					vet[j] = tmp;
				}
			}
		}
		return vet;
	}
}
