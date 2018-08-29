@SuppressWarnings("serial")
public class Fatorial implements Task<Long>{
	private int n;
	public Fatorial(){}
	public Fatorial(int N) {
		if(N < 0 || N > 20)
			throw new RuntimeException("Valor inválido para cálculo de fatorial (deve ser entre 0 e 20): " + N);
		n = N;
	}
	public Long execute(){
		Long ret = 1L;
		for(int i = 2; i <= n; i++)
			ret *= i;
		return ret;
	}
}
