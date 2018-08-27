import java.util.Comparator;

public abstract class Sort{
	protected Integer[] vet;
	protected Comparator<Integer> comp;
	protected abstract Integer[] sort();
	
	public Sort(){}
	
	public Sort(Integer[] Vet){
		this(Vet, Comparator.<Integer>naturalOrder());
	}
	
	public Sort(Integer[] Vet, Comparator<Integer> Comp){
		vet = Vet;
		comp = Comp;
	}
}

