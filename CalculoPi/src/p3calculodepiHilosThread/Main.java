package p3calculodepiHilosThread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {
	final static double REFERENCIAPI = 3.14159265358979323846;
	Lock lock;
	ArrayList<Double> listaResultados;	
	long numIntervalos;
	double anchuraIntervalo;
	long taskAmount;
	
	
	public Main(long numIntervalos){
		this.lock = new ReentrantLock();
		this.listaResultados = new ArrayList<Double>();
		this.numIntervalos = numIntervalos;
		this.anchuraIntervalo = 1.0/numIntervalos;
		this.taskAmount = numIntervalos/2;
	}

	public static void main(String[] args) {
		Main main = new Main(100000000);
		double valorPi = 0;
		long inicio=0;
		long fin=main.taskAmount;
		Thread []myThreadList = new Thread[2];	
		
		long tiempoInicio = System.currentTimeMillis();
		
		for(int kont=0; kont<2; kont++){
			myThreadList[kont]= new PiCalculator(inicio, fin, main.anchuraIntervalo, main.lock, main.listaResultados);
			myThreadList[kont].start();
			inicio += main.taskAmount;
			fin +=main.taskAmount;
		}
		
		for(int kont=0; kont<2; kont++){
			try {
				myThreadList[kont].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(Double results: main.listaResultados){
			valorPi += results;
		}
		
		long tiempoFin = System.currentTimeMillis();
		System.out.println("Tiempo empleado : "+(tiempoFin-tiempoInicio)+" ms");
		System.out.println("Valor pi:" +valorPi);
		
	}

}
