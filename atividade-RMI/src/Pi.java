@SuppressWarnings("serial")
public class Pi implements Task<Double>{
	private double eps;
	public Pi(){}
	public Pi(double EPS){ eps = EPS; }
	public Double execute(){
		double ret = 0, diff, nxt;
		int n = 0;
		do{
			double cur = 1/(double)(2*n+1);
			if(n%2 == 1) cur *= -1;
			nxt = ret + 4*cur;
			diff = nxt - ret;
			if(diff < 0) diff *= -1;
			n++;
			ret = nxt;
		}while(diff > eps);
		return ret;
	}
}
