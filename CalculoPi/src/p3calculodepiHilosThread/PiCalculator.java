package p3calculodepiHilosThread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class PiCalculator extends Thread {
	
	long inicio;
	long fin;
	double anchuraIntervalo;
	Lock lock;
	ArrayList<Double> resultList;
	
	public PiCalculator(long inicio, long fin, double anchuraIntervalo, Lock lock, ArrayList<Double> resultList){
		this.inicio = inicio;
		this.fin = fin;
		this.anchuraIntervalo = anchuraIntervalo;
		this.lock = lock;
		this.resultList = resultList;
	}
	
	public void run(){
		double x = 0;
		double valorPi = 0;
		for (long i = inicio; i<fin; i++){
			x= (i+0.5)*anchuraIntervalo;
			valorPi += 4/(1+(x*x));
		}
		lock.lock();
		resultList.add(valorPi);
		lock.unlock();
	}

}
