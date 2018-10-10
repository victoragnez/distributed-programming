package server;

import java.util.Comparator;

public class BubbleSort extends Sort {

	private final Comparator<Integer> comp;
	
	public BubbleSort(Integer[] Vet, Boolean order){
		super(Vet, order);
		
		if(naturalOrder)
			comp = Comparator.<Integer>naturalOrder();
		else 
			comp = Comparator.<Integer>reverseOrder();
	}
	
	@Override
	public Integer[] sort() {
		
		if(vet == null || vet.length < 2)
			return vet;
		
		int n = vet.length;
		boolean swapped;
		do {
			swapped = false;
			n--;
			for(int i = 0; i < n; i++) {
				if(comp.compare(vet[i+1], vet[i]) < 0) {
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
