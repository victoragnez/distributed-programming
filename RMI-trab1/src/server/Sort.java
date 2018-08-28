package server;

public abstract class Sort{
	protected Integer[] vet;
	protected abstract Integer[] sort();
	
	public Sort(){}
	
	public Sort(Integer[] Vet){
		vet = Vet;
	}
	
}

