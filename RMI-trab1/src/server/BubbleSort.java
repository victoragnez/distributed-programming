package server;

public class BubbleSort extends Sort {
	public BubbleSort(){
		super();
	}
	
	public BubbleSort(Integer[] Vet){
		super(Vet);
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
				if(vet[i+1] < vet[i]) {
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
