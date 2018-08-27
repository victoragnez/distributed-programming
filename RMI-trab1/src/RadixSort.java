import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
		Integer min = getMin(vet);
		return sort(vet, max, min);
	}
	
	private Integer getMax(Integer[] vet) {

		if(vet == null || vet.length == 0)
			return null;
		
		int m = vet[0];
		for (int  i = 1; i < vet.length; i++) {
			m = vet[i] < m ? m : vet[i]; 
		}
		return m;
	}
	
	private Integer getMin(Integer[] vet) {

		if(vet == null || vet.length == 0)
			return null;
		
		int m = vet[0];
		for (int  i = 1; i < vet.length; i++) {
			m = vet[i] > m ? m : vet[i]; 
		}
		return m;
	}

	private Integer[] sort(Integer vet[],Integer max,Integer min){
		if(vet == null || vet.length < 2)
			return vet;
		
		List<Integer> negatives = new ArrayList<Integer>();
		List<Integer> positives = new ArrayList<Integer>();
		
		for(int  i = 0; i < vet.length; i++) {
			if(vet[i] < 0)
				negatives.add(vet[i]);
			else
				positives.add(vet[i]);
		}
		
		for(int  i = 1; Math.abs(min)/i > 0; i *= 10) {
			Integer out[]   = new Integer[negatives.size()];
			for(int j = 0; j < vet.length; j++) 
				out[j] = 0;
			
			Integer count_digit[] = new Integer[10];
			
			for(int j = 0; j < 10; j++) 
				count_digit[j] = 0;
			
			for(int j = 0; j < negatives.size(); j++) 
				count_digit[ negatives.get(j) %i]++;
			
			for(int j = 1; j < 10 ; j++) {
				count_digit[j] += count_digit[j -1];
			}
			
			for (int j = negatives.size() - 1; j >= 0; j--){
				 
	            out[count_digit[ (negatives.get(j)/i)%10 ] - 1] = negatives.get(j);
	            count_digit[ (negatives.get(j)/i)%10 ]--;
	        }
			 
	        for (int j = 0; j < negatives.size(); j++)
	            vet[j] = out[negatives.size() -1 -j];
			
			
		}
		
		for(int  i = 1; Math.abs(max)/i > 0; i *= 10) {
			Integer out[]   = new Integer[positives.size()];
			for(int j = 0; j < vet.length; j++) 
				out[j] = 0;
			
			Integer count_digit[] = new Integer[10];
			
			for(int j = 0; j < 10; j++) 
				count_digit[j] = 0;
			
			for(int j = 0; j < positives.size(); j++) 
				count_digit[ positives.get(j) % i]++;
			
			for(int j = 1; j < 10 ; j++) {
				count_digit[j] += count_digit[j -1];
			}
			
			for (int j = positives.size() - 1; j >= 0; j--){
				 
	            out[count_digit[ (positives.get(j)/i)%10 ] - 1] = positives.get(j);
	            count_digit[ (positives.get(j)/i)%10 ]--;
	        }
			 
	        for (int j = 0; j < positives.size(); j++)
	            vet[j + (negatives.size() -1)] = out[j];
			
			
		}
		
		return vet;
	}
}
