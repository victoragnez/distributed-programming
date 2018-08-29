@SuppressWarnings("serial")
public class Primo implements Task<Integer>{
	private int n;
	private static final int max = 1300000; //acima do maior primo possível
	private static boolean[] naoPrimo = new boolean[max];
	public Primo(){}
	public Primo(int N) {
		if(N < 0 || N >= 100000)
			throw new RuntimeException("Valor inválido para cálculo do crivo (deve ser entre 0 e 99999): " + N);
		n = N;
	}
	public Integer execute(){
		naoPrimo[0] = naoPrimo[1] = true;
		int i;
		for(i = 2; i*i < max; i++) {
			if(!naoPrimo[i]) {
				if(n == 0)
					return i;
				n--;
				for(int j = i*i; j < max; j += i)
					naoPrimo[j] = true;
			}
		}
		for(; i < max; i++) {
			if(!naoPrimo[i]) {
				if(n == 0)
					return i;
				n--;
			}
		}
		return 0;
	}
}
