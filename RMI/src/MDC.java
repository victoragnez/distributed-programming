@SuppressWarnings("serial")
public class MDC implements Task<Integer>{
	private int a, b;
	public MDC(){}
	public MDC(int A, int B) {
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
