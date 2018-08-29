package server;

public abstract class Sort{
	protected Integer[] vet;
	protected Boolean naturalOrder;
	public abstract Integer[] sort();
	
	public Sort(){}
	
	public Sort(Integer[] Vet, Boolean NaturalOrder){
		vet = Vet;
		naturalOrder = NaturalOrder;
	}
	
	public Sort(Integer[] Vet){
		this(Vet, true);
	}
	
}

