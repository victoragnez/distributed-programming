package server;

import java.util.Comparator;

public class StoogeSort extends Sort {
	
	private Comparator<Integer> comp;

	public StoogeSort(){
		super();
	}
	
	public StoogeSort(Integer[] Vet){
		super(Vet);
	}
	
	public StoogeSort(Integer[] Vet, Boolean order){
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
		
		return sort(vet, 0, vet.length-1);
	}
	
	private Integer[] sort(Integer[] vet, int l, int r) {
		if(r-l+1 < 2)
			return vet;
		if(comp.compare(vet[r], vet[l]) < 0) {
			Integer aux = vet[l];
			vet[l] = vet[r];
			vet[r] = aux;
		}
		if(r-l+1 > 2) {
			int t = (r-l+1)/3;
			sort(vet, l, r-t);
			sort(vet, l+t, r);
			sort(vet, l, r-t);
		}
		return vet;
	}
}
