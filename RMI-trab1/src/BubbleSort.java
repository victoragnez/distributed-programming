import java.util.Comparator;

public class BubbleSort extends Sort {
	public BubbleSort(){
		super();
	}
	
	public BubbleSort(Integer[] Vet){
		super(Vet);
	}
	
	public BubbleSort(Integer[] Vet, Comparator<Integer> Comp){
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
		
		int n = vet.length;
		boolean swapped;
		do {
			swapped = false;
			n--;
			for(int i = 0; i < n; i++) {
				if(comp.compare(vet[i],vet[i+1]) > 0) {
					Integer aux = vet[i];
					vet[i] = vet[i+1];
					vet[i+1] = aux;
					swapped = true;
				}
			}
		} while(swapped);
		return vet;
	}
}
