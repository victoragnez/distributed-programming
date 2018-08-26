import java.util.Comparator;

public class InsertionSort extends Sort {
	public InsertionSort(){
		super();
	}
	
	public InsertionSort(Integer[] Vet){
		super(Vet);
	}
	
	public InsertionSort(Integer[] Vet, Comparator<Integer> Comp){
		super(Vet, Comp);
	}
	
	public void test() {
		super.smallTests();
		super.bigElementsTests();
		super.bigArrayTests();
	}
	
	@Override
	protected Integer[] sort() {
		return sort(vet);
	}
	
	private Integer[] sort(Integer vet[]){
		if(vet == null || vet.length < 2)
			return vet;
		for(int i = 0; i < vet.length; i++){
			try{
				for(int j = i; j > 0 && comp.compare(vet[j],vet[j-1]) < 0; j--){
					Integer tmp = vet[j-1];
					vet[j-1] = vet[j];
					vet[j] = tmp;
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return vet;
	}
}
