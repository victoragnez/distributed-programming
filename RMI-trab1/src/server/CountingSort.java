package server;

public class CountingSort extends Sort {
	public CountingSort(){
		super();
	}
	
	public CountingSort(Integer[] Vet){
		super(Vet);
	}
	
	@Override
	protected Integer[] sort() {
		if(vet == null || vet.length < 2)
			return vet;
		
		long max_val = (long) (1<<31), min_val = (long) ((-1)^(1<<31));
		for(int i = 0; i < vet.length; i++) {
			if(vet[i] > max_val)
				max_val = vet[i];
			if(vet[i] < min_val)
				min_val = vet[i];
		}
		
		if(max_val - min_val + 1 > (long) ((-1)^(1<<31)))
			throw new RuntimeException("Faixa de valores maior que o limite de um int");
		
		Integer[] cnt = new Integer[(int) (max_val - min_val + 1)];
		
		for(int i = 0; i < max_val - min_val + 1; i++)
			cnt[i] = 0;
		
		for(int i = 0; i < vet.length; i++)
			cnt[vet[i]-(int)min_val]++;
		
		int totalCount = 0;
		for(int i = 0; i < max_val - min_val + 1; i++) {
			int oldCount = cnt[i];
			cnt[i] = totalCount;
			totalCount += oldCount;
		}
		
		Integer[] aux = new Integer[vet.length];
		
		for(int i = 0; i < vet.length; i++)
			aux[i] = vet[i];
		
		for(int i = 0; i < vet.length; i++) {
			vet[cnt[aux[i]-(int)min_val]] = aux[i];
			cnt[aux[i]-(int)min_val]++;
		}
		return vet;
	}
}
