package server;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapSort extends Sort{
	
	private Comparator<Integer> comp;
	
	public HeapSort(){
		super();
	}
	
	public HeapSort(Integer[] Vet){
		super(Vet);
	}
	
	public HeapSort(Integer[] Vet, Boolean order){
		super(Vet, order);
	}
	
	@Override
	public Integer[] sort() {
		if(naturalOrder)
			comp = Comparator.<Integer>naturalOrder();
		else 
			comp = Comparator.<Integer>reverseOrder();
		
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
