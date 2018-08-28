package server;

import java.util.Random;

public class RandomSort extends Sort {
	public RandomSort(){
		super();
	}
	
	public RandomSort(Integer[] Vet){
		super(Vet);
	}
	
	@Override
	protected Integer[] sort() {
		if(vet == null || vet.length < 2)
			return vet;
		
		while(true) {
			boolean sorted = true;
			for(int i = 0; i < vet.length - 1; i++) {
				if(vet[i+1] < vet[i]) {
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
