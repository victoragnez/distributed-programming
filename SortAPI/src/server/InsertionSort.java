package server;

import java.util.Comparator;

public class InsertionSort extends Sort {
	
	private final Comparator<Integer> comp;
	
	public InsertionSort(Integer[] Vet, Boolean order){
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
			for(int j = i; j > 0 && comp.compare(vet[j], vet[j-1]) < 0; j--){
				Integer tmp = vet[j-1];
				vet[j-1] = vet[j];
				vet[j] = tmp;
			}
		}
		return vet;
	}
}
