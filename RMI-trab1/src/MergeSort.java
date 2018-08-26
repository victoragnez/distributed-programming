import java.util.Comparator;

public class MergeSort extends Sort {
	public MergeSort(){
		super();
	}
	
	public MergeSort(Integer[] Vet){
		super(Vet);
	}
	
	public MergeSort(Integer[] Vet, Comparator<Integer> Comp){
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
		return sort(vet);
	}
	
	private Integer[] sort(Integer vet[]){
		if(vet == null || vet.length < 2)
			return vet;
		int leftLen = vet.length/2, rightLen = vet.length - leftLen;
		Integer[] left = new Integer[leftLen];
		Integer[] right = new Integer[rightLen];
		for(int i = 0; i < leftLen; i++)
			left[i] = vet[i];
		for(int i = 0; i < rightLen; i++)
			right[i] = vet[i+leftLen];
		left = sort(left);
		right = sort(right);
		int i = 0, j = 0;
		while(i < leftLen || j < rightLen){
			if(i == leftLen || (j < rightLen && comp.compare(right[j],left[i]) < 0)){
				vet[i+j] = right[j];
				j++;
			}
			else{
				vet[i+j] = left[i];
				i++;
			}
		}
		return vet;
	}
}
