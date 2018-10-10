package server;

import java.util.Comparator;

public class SelectionSort extends Sort {
	
	private final Comparator<Integer> comp;

	public SelectionSort(Integer[] Vet, Boolean order){
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
		
		for(int i = 0; i < vet.length; i++){
			for(int j = i+1; j < vet.length; j++){
				if(comp.compare(vet[j], vet[i]) < 0){
					Integer tmp = vet[i];
					vet[i] = vet[j];
					vet[j] = tmp;
				}
			}
		}
		return vet;
	}
}
