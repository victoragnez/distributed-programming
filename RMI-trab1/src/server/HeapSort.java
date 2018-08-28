package server;

import java.util.PriorityQueue;

public class HeapSort extends Sort{
	public HeapSort(){
		super();
	}
	
	public HeapSort(Integer[] Vet){
		super(Vet);
	}
	
	@Override
	protected Integer[] sort() {
		if(vet == null || vet.length < 2)
			return vet;
		
		PriorityQueue<Integer>  heap = new PriorityQueue<Integer>(vet.length);
		for (int i = 0; i < vet.length; i++){
			heap.add(vet[i]);
		}
		for (int i = 0; i <vet.length; i++){
			vet[i] = heap.poll();
		}
		return vet;		
	}

}
