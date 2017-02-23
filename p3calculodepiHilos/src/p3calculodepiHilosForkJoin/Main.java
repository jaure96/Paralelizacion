package p3calculodepiHilosForkJoin;

import java.util.concurrent.ForkJoinPool;


public class Main {
		
	final static double REFERENCIAPI = 3.14159265358979323846;
	final static long NUMINTERVALOS = 10000000000L;
	final static long TASKAMOUNT = NUMINTERVALOS/Runtime.getRuntime().availableProcessors();
	final static double ANCHURAINTERVALO = 1.0/NUMINTERVALOS;
		
	public static void main(String[] args) {
		long tiempoInicio = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		
		double valorPi = pool.invoke(new PiCanculator(ANCHURAINTERVALO, 0, TASKAMOUNT, NUMINTERVALOS));
		pool.shutdown();
		
		long tiempoFin = System.currentTimeMillis();
		System.out.println("Tiempo empleado : "+(tiempoFin-tiempoInicio)+" ms");
	}
}