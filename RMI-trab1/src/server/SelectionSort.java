package server;

public class SelectionSort extends Sort {
	public SelectionSort(){
		super();
	}
	
	public SelectionSort(Integer[] Vet){
		super(Vet);
	}
	
	@Override
	protected Integer[] sort() {
		if(vet == null || vet.length < 2)
			return vet;
		for(int i = 0; i < vet.length; i++){
			for(int j = i+1; j < vet.length; j++){
				if(vet[j] < vet[i]){
					Integer tmp = vet[i];
					vet[i] = vet[j];
					vet[j] = tmp;
				}
			}
		}
		return vet;
	}
}
