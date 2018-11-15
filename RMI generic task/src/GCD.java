@SuppressWarnings("serial")
public class GCD implements Task<Integer>{
	private int a, b;
	public GCD(){}
	public GCD(int A, int B) {
		a = A;
		b = B;
		if(a < 0)
			a = -a;
		if(b < 0)
			b = -b;
	}
	public Integer execute(){
		while(b > 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
}
