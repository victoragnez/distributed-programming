package server;

import java.util.ArrayList;
import java.util.List;

public class RadixSort extends Sort {
	public RadixSort(){
		super();
	}
	
	public RadixSort(Integer[] Vet){
		super(Vet);
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
	private ArrayList<Integer> countSort(List<Integer> positives,Integer max) {
		for(int  i = 1; max/i > 0; i *= 10) {
			
			Integer out[]   = new Integer[positives.size()];
			Integer count_digit[] = new Integer[10];
			
			for(int j = 0; j < 10; j++) 
				count_digit[j] = 0;
			
			for(int j = 0; j < positives.size(); j++) 
				count_digit[ (positives.get(j)/ i) % 10]++;
			
			for(int j = 1; j < 10 ; j++)
				count_digit[j] += count_digit[j -1];
				
			
			for (int j = positives.size() - 1; j >= 0; j--){
				 
	            out[count_digit[ (positives.get(j)/i)%10 ] - 1] = positives.get(j);
	            count_digit[ (positives.get(j)/i)%10 ]--;
	       
			}
			 
	        for (int j = 0; j < positives.size(); j++)
	        	positives.set(j, out[j]);
		}
		
		return (ArrayList<Integer>) positives;
		
	}
	private Integer[] sort(Integer vet[],Integer max,Integer min){
		if(vet == null || vet.length < 2)
			return vet;
		
		List<Integer> negatives = new ArrayList<Integer>();
		List<Integer> positives = new ArrayList<Integer>();
		
		for(int  i = 0; i < vet.length; i++) {
			if(vet[i] < 0)
				negatives.add(Math.abs(vet[i]));
			else
				positives.add(vet[i]);
		}
		
		negatives = countSort(negatives, Math.abs(min));
		
		for(int i = 0; i < negatives.size(); i++) {
			vet[i] = -1 * negatives.get(negatives.size() - i -1);
		}
		
		positives = countSort(positives, max);
		
		for(int i = 0; i < positives.size(); i++) 
			vet[i + negatives.size()] = positives.get(i);
		
		return vet;
	}
}
