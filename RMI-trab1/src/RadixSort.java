import java.util.Comparator;

public class RadixSort extends Sort {
	public RadixSort(){
		super();
	}
	
	public RadixSort(Integer[] Vet){
		super(Vet);
	}
	
	public RadixSort(Integer[] Vet, Comparator<Integer> Comp){
		super(Vet, Comp);
	}
	
	public void test() {
		super.smallTests();
		super.bigElementsTests();
		super.bigArrayTests();
		super.bigTests();
	}
	
	@Override
	protected Integer[] sort() {
		Integer max = getMax(vet);
		return sort(vet, max);
	}
	
	private Integer getMax(Integer[] vet) {

		if(vet == null || vet.length == 0)
			return null;
		
		int m = vet[0];
		for (int  i = 1; i < vet.length; i++) {
			m = vet[i] > m ? m : vet[i]; 
		}
		return m;
	}

	private Integer[] sort(Integer vet[],Integer max){
		if(vet == null || vet.length < 2)
			return vet;
		for(int  i = 1; max/i > 0; i *= 10) {
			Integer out[]   = new Integer[vet.length];
			for(int j = 0; j < vet.length; j++) 
				out[j] = 0;
			
			Integer count_digit[] = new Integer[10];
			
			for(int j = 0; j < 10; j++) 
				count_digit[j] = 0;
			
			for(int j = 0; j < vet.length; j++) 
				count_digit[vet[j]%i]++;
			
			for(int j = 1; j < 10 ; j++) {
				count_digit[j] += count_digit[j -1];
			}
			
			for (int j = vet.length - 1; j >= 0; j--){
				 
	            out[count_digit[ (vet[j]/i)%10 ] - 1] = vet[j];
	            count_digit[ (vet[j]/i)%10 ]--;
	        }
			 
	        for (int j = 0; j < vet.length; j++)
	            vet[j] = out[j];
			
			
		}
		
		return vet;
	}
}
