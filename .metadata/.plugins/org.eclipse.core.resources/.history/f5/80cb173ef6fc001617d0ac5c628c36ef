package p3calculodepiHilosCallable;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main {
		
	ExecutorService executor;
	CompletionService<Double> completionService;
	final static double REFERENCIAPI = 3.14159265358979323846;
	
	long numIntervalos;
	double valorPi;
	double anchuraIntervalo;
	
	public Main(long numIntervalos){
		valorPi = 0;
		this.numIntervalos = numIntervalos;
		anchuraIntervalo = 1.0/numIntervalos;
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		completionService = new ExecutorCompletionService<Double>(executor);
	}

	
	
	
	public static void main(String[] args) {
		long inicio,fin, kont;
		Main main = new Main(10000000000L);
		long taskAmounts = main.numIntervalos/Runtime.getRuntime().availableProcessors();
		kont=0;
		inicio = 0;
		fin = taskAmounts;
		
		long tiempoInicio = System.currentTimeMillis();
		
		while(fin <= main.numIntervalos){
			main.completionService.submit(new PiCanculator(main.anchuraIntervalo, inicio, fin));
			inicio = inicio+taskAmounts;
			fin = fin+taskAmounts;
			kont++;
		}
		
		for (int i=0; i<kont; i++){
			Double valor;
			Future<Double> future;
			try {
				future = main.completionService.take();
				if ((valor = future.get())!=null){
					main.valorPi += valor;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}						
		}
		main.valorPi *= main.anchuraIntervalo;
		main.executor.shutdown();
		
		long tiempoFin = System.currentTimeMillis();
		System.out.println("valor de pi: "+ main.valorPi);
		System.out.println("Error referencia = "+(Main.REFERENCIAPI - main.valorPi));
		System.out.println("Tiempo empleado : "+(tiempoFin-tiempoInicio)+" ms");
	}
}
