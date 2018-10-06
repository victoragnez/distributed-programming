package server;

import java.util.Comparator;

public class InsertionSort extends Sort {
	
	private Comparator<Integer> comp;
	
	public InsertionSort(){
		super();
	}
	
	public InsertionSort(Integer[] Vet){
		super(Vet);
	}
	
	public InsertionSort(Integer[] Vet, Boolean order){
		super(Vet, order);
	}
	
	@Override
	public Integer[] sort() {
		if(naturalOrder)
			comp = Comparator.<Integer>naturalOrder();
		else 
			comp = Comparator.<Integer>reverseOrder();
		
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
