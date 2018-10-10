package server;

import java.util.Comparator;
import java.util.Random;

public class RandomSort extends Sort {
	
	private final Comparator<Integer> comp;

	public RandomSort(Integer[] Vet, Boolean order){
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
		
		while(true) {
			boolean sorted = true;
			for(int i = 0; i < vet.length - 1; i++) {
				if(comp.compare(vet[i+1], vet[i]) < 0) {
					sorted = false;
					break;
				}
			}
			if(sorted) {
				break;
			}
			Random rand = new Random();
			for(int i = 0; i < vet.length; i++) {
				int j = rand.nextInt(i+1);
				Integer aux = vet[i];
				vet[i] = vet[j];
				vet[j] = aux;
			}
		}
		return vet;
	}
}
