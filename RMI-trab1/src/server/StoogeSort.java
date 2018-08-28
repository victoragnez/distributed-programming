package server;

public class StoogeSort extends Sort {
	public StoogeSort(){
		super();
	}
	
	public StoogeSort(Integer[] Vet){
		super(Vet);
	}
	
	@Override
	protected Integer[] sort() {
		if(vet == null)
			return null;
		return sort(vet, 0, vet.length-1);
	}
	
	private Integer[] sort(Integer[] vet, int l, int r) {
		if(r-l+1 < 2)
			return vet;
		if(vet[r] < vet[l]) {
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
