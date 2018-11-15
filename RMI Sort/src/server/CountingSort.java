package server;

public class CountingSort extends Sort {
	
	Integer max_val, min_val;

	public CountingSort(){
		super();
	}
	
	public CountingSort(Integer[] Vet){
		super(Vet);
	}
	
	public CountingSort(Integer[] Vet, Boolean order){
		super(Vet, order);
	}
	
	private Integer getKey(Integer val) {
		if(naturalOrder)
			return val - min_val;
		else
			return max_val - val;
	}
	
	@Override
	public Integer[] sort() {
		if(vet == null || vet.length < 2)
			return vet;
		
		max_val = (1<<31);
		min_val = ((-1)^(1<<31));
		
		for(int i = 0; i < vet.length; i++) {
			if(vet[i] > max_val)
				max_val = vet[i];
			if(vet[i] < min_val)
				min_val = vet[i];
		}
		
		if( (long)max_val - (long)min_val + 1l > (long) ((-1)^(1<<31)))
			throw new RuntimeException("Faixa de valores maior que o limite de um int");
		
		Integer[] cnt = new Integer[(max_val - min_val + 1)];
		
		for(int i = 0; i < max_val - min_val + 1; i++)
			cnt[i] = 0;
		
		for(int i = 0; i < vet.length; i++)
			cnt[getKey(vet[i])]++;
		
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
			vet[cnt[getKey(aux[i])]] = aux[i];
			cnt[getKey(aux[i])]++;
		}
		return vet;
	}
}
