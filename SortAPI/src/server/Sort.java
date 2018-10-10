package server;

public abstract class Sort{
	protected final Integer[] vet;
	protected final Boolean naturalOrder;
	public abstract Integer[] sort();
	
	public Sort(Integer[] Vet, Boolean NaturalOrder){
		vet = Vet;
		naturalOrder = NaturalOrder;
	}
	
}

