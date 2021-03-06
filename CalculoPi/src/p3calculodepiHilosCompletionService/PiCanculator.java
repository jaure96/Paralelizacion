package p3calculodepiHilosCompletionService;

import java.util.concurrent.Callable;

public class PiCanculator implements Callable<Double>{
	
	double anchuraIntervalo;
	long inicio;
	long fin;
	
	public PiCanculator(double anchuraIntervalo, long inicio, long fin){
		this.anchuraIntervalo = anchuraIntervalo;
		this.inicio = inicio;
		this.fin = fin;		
	}	
	
	@Override
	public Double call() throws Exception {
		double valorPi = 0;
		double x;
		for (long i = inicio; i<fin; i++){
			x= (i+0.5)*anchuraIntervalo;
			valorPi += 4/(1+(x*x));
		}
		return valorPi;
	}

}
