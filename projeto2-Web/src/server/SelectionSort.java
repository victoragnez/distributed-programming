package server;

import java.util.Comparator;

public class SelectionSort extends Sort {
	
	private Comparator<Integer> comp;

	public SelectionSort(){
		super();
	}
	
	public SelectionSort(Integer[] Vet){
		super(Vet);
	}
	
	public SelectionSort(Integer[] Vet, Boolean order){
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
