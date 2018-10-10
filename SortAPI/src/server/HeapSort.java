package server;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapSort extends Sort{
	
	private final Comparator<Integer> comp;
	
	public HeapSort(Integer[] Vet, Boolean order){
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
		
		PriorityQueue<Integer>  heap = new PriorityQueue<Integer>(vet.length, comp);
		for (int i = 0; i < vet.length; i++){
			heap.add(vet[i]);
		}
		for (int i = 0; i <vet.length; i++){
			vet[i] = heap.poll();
		}
		return vet;		
	}

}
